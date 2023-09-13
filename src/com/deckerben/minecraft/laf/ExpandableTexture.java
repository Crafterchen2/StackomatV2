package com.deckerben.minecraft.laf;

import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Consumer;

public class ExpandableTexture {

    //Felder
    private static final String TEXTURE_PATH = "/com/deckerben/minecraft/laf/textures/assets/components.png";
    private static final BufferedImage TEXTURE_FILE = loadTextureFile();

    private BufferedImage unscaledTexture;

    private McComponentTextureEnum texType;

    private final HashMap<SectorEnum, BufferedImage> unscaledSectors = SectorEnum.makeEmptyMap();
    private final HashMap<SectorEnum, BufferedImage> scaledSectors = SectorEnum.makeEmptyMap();

    private static int globalScale = 3;
    private int usedScale = -1;

    private static final ArrayList<ExpandableTexture> watchList = new ArrayList<>();
    private final Consumer<Integer> alertAction;

    //Listener

    //Konstruktoren
    public ExpandableTexture(McComponentTextureEnum texType){
        this(texType,null);
    }

    public ExpandableTexture(McComponentTextureEnum texType, int customScale){
        this(texType,null, customScale);
    }

    public ExpandableTexture(McComponentTextureEnum texType, Consumer<Integer> alertAction){
        setTexType(texType);
        this.alertAction = alertAction;
        if (this.alertAction != null) watchList.add(this);
    }

    public ExpandableTexture(McComponentTextureEnum texType, Consumer<Integer> alertAction, int customScale){
        checkScale(customScale);
        usedScale = customScale;
        setTexType(texType);
        this.alertAction = alertAction;
        if (this.alertAction != null) watchList.add(this);
    }

    //Methoden
    public void paintTexture(Graphics g, Rectangle cords, Color background, DrawSettings... modifier){
        DrawSettings justSetting = DrawSettings.NOT_SET;
        DrawSettings holeSetting = DrawSettings.NOT_SET;
        HashSet<DrawSettings> otherSettings = new HashSet<>();
        for (DrawSettings setting: modifier) {
            switch (setting.ID) {
                case DrawSettings.JUST_SPECIFIC_CORNER_ID, DrawSettings.JUST_SPECIFIC_EDGE_ID, DrawSettings.JUST_ID -> {
                    if (justSetting == DrawSettings.NOT_SET) justSetting = setting;
                }
                case DrawSettings.HOLES_ID -> {
                    if (holeSetting == DrawSettings.NOT_SET) holeSetting = setting;
                }
                default -> otherSettings.add(setting);
            }
        }
        //JUST-modifier in NO-modifier übersetzen
        switch (justSetting) {
            case JUST_OUTER -> otherSettings.add(DrawSettings.NO_CENTER);
            case JUST_CENTER -> otherSettings.add(DrawSettings.NO_OUTER);
            case JUST_CORNERS -> {otherSettings.add(DrawSettings.NO_EDGES);otherSettings.add(DrawSettings.NO_CENTER);}
            case JUST_EDGES -> {otherSettings.add(DrawSettings.NO_CORNERS);otherSettings.add(DrawSettings.NO_CENTER);}
            case JUST_NW -> {otherSettings.add(DrawSettings.NO_EDGES); otherSettings.add(DrawSettings.NO_SW); otherSettings.add(DrawSettings.NO_ES); otherSettings.add(DrawSettings.NO_NE);otherSettings.add(DrawSettings.NO_CENTER);}
            case JUST_NE -> {otherSettings.add(DrawSettings.NO_EDGES); otherSettings.add(DrawSettings.NO_NW); otherSettings.add(DrawSettings.NO_SW); otherSettings.add(DrawSettings.NO_ES);otherSettings.add(DrawSettings.NO_CENTER);}
            case JUST_ES -> {otherSettings.add(DrawSettings.NO_EDGES); otherSettings.add(DrawSettings.NO_NE); otherSettings.add(DrawSettings.NO_NW); otherSettings.add(DrawSettings.NO_SW);otherSettings.add(DrawSettings.NO_CENTER);}
            case JUST_SW -> {otherSettings.add(DrawSettings.NO_EDGES); otherSettings.add(DrawSettings.NO_ES); otherSettings.add(DrawSettings.NO_NE); otherSettings.add(DrawSettings.NO_NW);otherSettings.add(DrawSettings.NO_CENTER);}
            case JUST_N ->  {otherSettings.add(DrawSettings.NO_CORNERS); otherSettings.add(DrawSettings.NO_E); otherSettings.add(DrawSettings.NO_S); otherSettings.add(DrawSettings.NO_W);otherSettings.add(DrawSettings.NO_CENTER);}
            case JUST_E ->  {otherSettings.add(DrawSettings.NO_CORNERS); otherSettings.add(DrawSettings.NO_S); otherSettings.add(DrawSettings.NO_W); otherSettings.add(DrawSettings.NO_N);otherSettings.add(DrawSettings.NO_CENTER);}
            case JUST_S ->  {otherSettings.add(DrawSettings.NO_CORNERS); otherSettings.add(DrawSettings.NO_W); otherSettings.add(DrawSettings.NO_N); otherSettings.add(DrawSettings.NO_E);otherSettings.add(DrawSettings.NO_CENTER);}
            case JUST_W ->  {otherSettings.add(DrawSettings.NO_CORNERS); otherSettings.add(DrawSettings.NO_N); otherSettings.add(DrawSettings.NO_E); otherSettings.add(DrawSettings.NO_S);otherSettings.add(DrawSettings.NO_CENTER);}
        }
        //modifier in spezifische Modifier übersetzen
        if (otherSettings.contains(DrawSettings.NO_OUTER)){otherSettings.add(DrawSettings.NO_CORNERS); otherSettings.add(DrawSettings.NO_EDGES);}
        if (otherSettings.contains(DrawSettings.NO_CORNERS)){otherSettings.add(DrawSettings.NO_NW); otherSettings.add(DrawSettings.NO_NE); otherSettings.add(DrawSettings.NO_ES); otherSettings.add(DrawSettings.NO_SW);}
        if (otherSettings.contains(DrawSettings.NO_EDGES)){otherSettings.add(DrawSettings.NO_N); otherSettings.add(DrawSettings.NO_E); otherSettings.add(DrawSettings.NO_S); otherSettings.add(DrawSettings.NO_W);}
        //Größe des Bilds berechnen
        Dimension imgSize = new Dimension(cords.getSize());
        if ((imgSize.width <= 0) && (imgSize.height <= 0)) return;
        //Malen vorbereiten
        g.setClip(cords);
        //Hintergrund malen
        if (holeSetting != DrawSettings.HOLES_TRANSPARENT) {
            if (holeSetting == DrawSettings.HOLES_CENTER) {
                g.setColor(new Color(scaledSectors.get(SectorEnum.C).getRGB(0,0)));
            } else {
                g.setColor(background);
            }
            g.fillRect(0,0, imgSize.width, imgSize.height);
        }
        //Umrandung malen
        for (DrawSettings setting : DrawSettings.getNoValues()) {
            if (!otherSettings.contains(setting)) drawSector(g,setting.getMatchingSector(),imgSize);
        }
        if (!otherSettings.contains(DrawSettings.NO_CENTER)) drawSector(g,SectorEnum.C,imgSize);
    }

    private void drawSector(Graphics g, SectorEnum sector, Dimension maxSize){
        if (sector == null) return;
        switch (sector) {
            case NW -> drawSector(g,sector,0,0);
            case NE -> drawSector(g,sector,maxSize.width-scaledSectors.get(sector).getWidth(),0);
            case ES -> drawSector(g,sector,maxSize.width-scaledSectors.get(sector).getWidth(),maxSize.height-scaledSectors.get(sector).getHeight());
            case SW -> drawSector(g,sector,0,maxSize.height-scaledSectors.get(sector).getHeight());
            case N ->  drawSector(g,sector,scaledSectors.get(SectorEnum.NW).getWidth(),0, maxSize.width-scaledSectors.get(SectorEnum.NW).getWidth()-scaledSectors.get(SectorEnum.NE).getWidth(), scaledSectors.get(SectorEnum.NW).getHeight());
            case E ->  drawSector(g,sector,maxSize.width-scaledSectors.get(SectorEnum.NE).getWidth(),scaledSectors.get(SectorEnum.NE).getHeight(), scaledSectors.get(SectorEnum.NE).getWidth(), maxSize.height-scaledSectors.get(SectorEnum.NE).getHeight()-scaledSectors.get(SectorEnum.ES).getHeight());
            case S ->  drawSector(g,sector,scaledSectors.get(SectorEnum.SW).getWidth(),maxSize.height-scaledSectors.get(SectorEnum.SW).getHeight(), maxSize.width-scaledSectors.get(SectorEnum.SW).getWidth()-scaledSectors.get(SectorEnum.NE).getWidth(), scaledSectors.get(SectorEnum.SW).getHeight());
            case W ->  drawSector(g,sector,0,scaledSectors.get(SectorEnum.NW).getHeight(), scaledSectors.get(SectorEnum.NW).getWidth(), maxSize.height-scaledSectors.get(SectorEnum.NE).getHeight()-scaledSectors.get(SectorEnum.ES).getHeight());
            case C ->  drawSector(g,sector,scaledSectors.get(SectorEnum.NW).getWidth(),scaledSectors.get(SectorEnum.NW).getHeight(),maxSize.width-scaledSectors.get(SectorEnum.NW).getWidth()-scaledSectors.get(SectorEnum.NE).getWidth(),maxSize.height-scaledSectors.get(SectorEnum.NE).getHeight()-scaledSectors.get(SectorEnum.ES).getHeight());
        }
    }

    private void drawSector(Graphics g, SectorEnum sector, int x, int y){
        drawSector(g,sector,x,y,scaledSectors.get(sector).getWidth(),scaledSectors.get(sector).getHeight());
    }

    private void drawSector(Graphics g, SectorEnum sector, int x, int y, int width, int height){
        g.drawImage(scaledSectors.get(sector),x,y,width,height,null);
    }

    private void scaleSectors(int scale){
        scaledSectors.forEach((sectorEnum, bufferedImage) -> {
            switch (sectorEnum.TYPE){
                case SectorEnum.CENTER -> scaledSectors.replace(sectorEnum, unscaledSectors.get(sectorEnum));
                case SectorEnum.CORNER -> scaledSectors.replace(sectorEnum, scaleTexture(unscaledSectors.get(sectorEnum), scale, scale));
                case SectorEnum.VERTICAL_EDGE -> scaledSectors.replace(sectorEnum, scaleTexture(unscaledSectors.get(sectorEnum), 1, scale));
                case SectorEnum.HORIZONTAL_EDGE -> scaledSectors.replace(sectorEnum, scaleTexture(unscaledSectors.get(sectorEnum), scale, 1));
            }
        });
    }

    private BufferedImage scaleTexture(BufferedImage tex, int widthScale, int heightScale){
        BufferedImage rv = new BufferedImage(tex.getWidth()*widthScale,tex.getHeight()*heightScale,BufferedImage.TYPE_INT_ARGB);
        Graphics g = rv.getGraphics();
        g.drawImage(tex,0,0,rv.getWidth(), rv.getHeight(), null);
        g.dispose();
        return rv;
    }

    private void splitTexture(){
        //corners
        unscaledSectors.replace(SectorEnum.NW, unscaledTexture.getSubimage(0,0, unscaledTexture.getWidth()/2, unscaledTexture.getHeight()/2));
        unscaledSectors.replace(SectorEnum.NE, unscaledTexture.getSubimage(unscaledTexture.getWidth()-(unscaledTexture.getWidth()/2),0, unscaledTexture.getWidth()/2, unscaledTexture.getHeight()/2));
        unscaledSectors.replace(SectorEnum.ES, unscaledTexture.getSubimage(unscaledTexture.getWidth()-(unscaledTexture.getWidth()/2), unscaledTexture.getHeight()-(unscaledTexture.getHeight()/2), unscaledTexture.getWidth()/2, unscaledTexture.getHeight()/2));
        unscaledSectors.replace(SectorEnum.SW, unscaledTexture.getSubimage(0, unscaledTexture.getHeight()-(unscaledTexture.getHeight()/2), unscaledTexture.getWidth()/2, unscaledTexture.getHeight()/2));
        //edges
        unscaledSectors.replace(SectorEnum.N, unscaledTexture.getSubimage(unscaledTexture.getWidth()/2,0,1, unscaledTexture.getHeight()/2));
        unscaledSectors.replace(SectorEnum.E, unscaledTexture.getSubimage(unscaledTexture.getWidth()-(unscaledTexture.getWidth()/2), unscaledTexture.getHeight()/2, unscaledTexture.getWidth()/2,1));
        unscaledSectors.replace(SectorEnum.S, unscaledTexture.getSubimage(unscaledTexture.getWidth()/2, unscaledTexture.getHeight()-(unscaledTexture.getHeight()/2),1, unscaledTexture.getHeight()/2));
        unscaledSectors.replace(SectorEnum.W, unscaledTexture.getSubimage(0, unscaledTexture.getHeight()/2, unscaledTexture.getWidth()/2,1));
        //center
        unscaledSectors.replace(SectorEnum.C, unscaledTexture.getSubimage(unscaledTexture.getWidth()/2, unscaledTexture.getHeight()/2,1,1));
    }

    //public BufferedImage assembleTexture(boolean scaledVersion){
    //    return (scaledVersion)?assembleScaledTexture():unscaledTexture;
    //}
    //
    //private BufferedImage assembleScaledTexture(){
    //
    //}

    public static BufferedImage readTexture(McComponentTextureEnum texType){
        Rectangle texCoords = texType.getTexCoords();
        return TEXTURE_FILE.getSubimage(texCoords.x,texCoords.y,texCoords.width,texCoords.height);
    }

    private static BufferedImage loadTextureFile(){
        BufferedImage unscaledTexture;
        try {
            unscaledTexture = ImageIO.read(Objects.requireNonNull(ExpandableTexture.class.getResourceAsStream(TEXTURE_PATH)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return unscaledTexture;
    }

    public void swapOutSectors(SectorEnum sector, BufferedImage newSector, boolean isAlreadyScaled){
        if (isAlreadyScaled) {
            checkSectorSize(scaledSectors.get(sector),newSector);
            scaledSectors.replace(sector,newSector);
        } else {
            checkSectorSize(unscaledSectors.get(sector),newSector);
            unscaledSectors.replace(sector,newSector);
            scaleSectors(getUsedScale());
        }
    }

    //Checker
    private static void checkScale(int scaleToCheck) throws IllegalArgumentException{
        if (scaleToCheck < 1) throw new IllegalArgumentException("Scale "+scaleToCheck+" too small: Scale must be >= 1.");
    }

    private void checkSectorSize(BufferedImage ogImage, BufferedImage imageToCheck) throws IllegalArgumentException{
        if ((ogImage.getHeight() == imageToCheck.getHeight())&&(ogImage.getWidth() == imageToCheck.getWidth())) {
            throw new IllegalArgumentException("New image does not fit size "+ogImage.getWidth()+"X"+ogImage.getHeight()+".");
        }
    }

    //Getter
    public BufferedImage getUnscaledTexture() {
        return unscaledTexture;
    }

    public McComponentTextureEnum getTexType() {
        return texType;
    }

    public HashMap<SectorEnum, BufferedImage> getUnscaledSectors() {
        return unscaledSectors;
    }

    public HashMap<SectorEnum, BufferedImage> getScaledSectors() {
        return scaledSectors;
    }

    public static int getGlobalScale() {
        return globalScale;
    }

    public int getUsedScale() {
        return (usedScale < 1)? globalScale : usedScale;
    }

    public Rectangle getInnerRectangle(Dimension outerSize){
        return new Rectangle(scaledSectors.get(SectorEnum.NW).getWidth(),scaledSectors.get(SectorEnum.NW).getHeight(),outerSize.width-scaledSectors.get(SectorEnum.NW).getWidth()-scaledSectors.get(SectorEnum.NE).getWidth(),outerSize.height-scaledSectors.get(SectorEnum.NE).getHeight()-scaledSectors.get(SectorEnum.ES).getHeight());
    }

    //Setter
    public static void setGlobalScale(int globalScale) {
        checkScale(globalScale);
        ExpandableTexture.globalScale = globalScale;
        watchList.forEach(expandableTexture -> expandableTexture.alertAction.accept(globalScale));
    }

    public void setCustomScale(int customScale){
        checkScale(customScale);
        usedScale = customScale;
    }

    public void setTexType(McComponentTextureEnum texType) {
        if (this.texType != texType){
            this.texType = texType;
            unscaledTexture = readTexture(texType);
            splitTexture();
            scaleSectors(getUsedScale());
        }
    }

    //Maker

    //Overrides aus
    ////<Oberklasse>

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
