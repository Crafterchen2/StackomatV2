package com.deckerben.minecraft.laf;

import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;

import javax.imageio.ImageIO;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

public abstract class ExpandableTexture extends AbstractBorder {

    //Felder
    private static int globalScale = 3;
    private static final HashSet<ExpandableTexture> updateWatchList = new HashSet<>();

    private McComponentTextureEnum borderTexType = McComponentTextureEnum.PANEL_THIN;
    private Color borderBackgroundColor = null;

    private static final String TEXTURE_PATH = "/com/deckerben/minecraft/laf/textures/assets/components.png";
    private static final BufferedImage TEXTURE = loadTextureFile();

    //Listener

    //Konstruktoren
    public ExpandableTexture(boolean addToWatchList){
        if (addToWatchList) updateWatchList.add(this);
    }

    //Methoden
    public static void paintTexture(Graphics g, Rectangle size, McComponentTextureEnum texType){
        paintTexture(g,size,texType,DrawSettings.NOT_SET);
    }

    public static void paintTexture(Graphics g, Rectangle size, McComponentTextureEnum texType, int scale){
        paintTexture(g,size,texType,scale,DrawSettings.NOT_SET);
    }

    public static void paintTexture(Graphics g, Rectangle size, McComponentTextureEnum texType, int scale, int fillingWidth){
        paintTexture(g,size,texType,scale,fillingWidth,DrawSettings.NOT_SET);
    }

    public static void paintTexture(Graphics g, Rectangle size, McComponentTextureEnum texType, Color background){
        paintTexture(g,size,texType,background,DrawSettings.NOT_SET);
    }

    public static void paintTexture(Graphics g, Rectangle size, McComponentTextureEnum texType, DrawSettings... modifier){
        paintTexture(g,size,texType,globalScale,modifier);
    }

    public static void paintTexture(Graphics g, Rectangle size, McComponentTextureEnum texType, int scale, DrawSettings... modifier){
        paintTexture(g,size,texType,scale,1,modifier);
    }

    public static void paintTexture(Graphics g, Rectangle size, McComponentTextureEnum texType, int scale, int fillingWidth, DrawSettings... modifier){
        paintTexture(g,size,texType,scale,fillingWidth,new Color(0,0,0,0),modifier);
    }

    public static void paintTexture(Graphics g, Rectangle size, McComponentTextureEnum texType, Color background, DrawSettings... modifier){
        paintTexture(g,size,texType,globalScale,background,modifier);
    }

    public static void paintTexture(Graphics g, Rectangle size, McComponentTextureEnum texType, int scale, Color background, DrawSettings... modifier){
        paintTexture(g,size,texType,scale,1,background,modifier);
    }

    public static void paintTexture(Graphics g, Rectangle size, McComponentTextureEnum texType, int scale, int fillWidth, Color background, DrawSettings... modifier){
        int fillingWidth = (texType.hasFilling())? fillWidth : 0;
        if (scale <= 0) throw new IllegalArgumentException("\"scale\" '"+scale+"' must be bigger or equal to 1.");
        if (fillingWidth < 0) throw new IllegalArgumentException("\"fillingWidth\" '"+fillingWidth+"' must be bigger or equal to 0.");
        DrawSettings justSetting = DrawSettings.NOT_SET;
        DrawSettings holeSetting = DrawSettings.NOT_SET;
        HashSet<DrawSettings> otherSettings = new HashSet<>();
        for (DrawSettings setting: modifier) {
            switch (setting.getId()){
                case DrawSettings.JUST_ID -> {
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
            case JUST_OUTER -> {otherSettings.add(DrawSettings.NO_INNER);otherSettings.add(DrawSettings.NO_CENTER);otherSettings.add(DrawSettings.NO_FILLING);}
            case JUST_INNER -> {otherSettings.add(DrawSettings.NO_OUTER);otherSettings.add(DrawSettings.NO_CENTER);otherSettings.add(DrawSettings.NO_FILLING);}
            case JUST_CENTER -> {otherSettings.add(DrawSettings.NO_INNER);otherSettings.add(DrawSettings.NO_OUTER);otherSettings.add(DrawSettings.NO_FILLING);}
            case JUST_FILLING -> {otherSettings.add(DrawSettings.NO_INNER);otherSettings.add(DrawSettings.NO_OUTER);otherSettings.add(DrawSettings.NO_CENTER);}
        }
        if (calcCurrentScaleFactor(scale,size.width-(((fillingWidth-1)*2)*scale),size.height-(((fillingWidth-1)*2)*scale)) > texType.getMaxScaleFactor() && !otherSettings.contains(DrawSettings.FORCE_PAINTING)){
            throw new IllegalArgumentException("New scaleFactor '"+calcCurrentScaleFactor(scale,size.width-(((fillingWidth-1)*2)*scale),size.height-(((fillingWidth-1)*2)*scale))+"' is bigger than '"+texType.getMaxScaleFactor()+"', causing visual oddities. Add \"DrawSettings.FORCE_PAINTING\" to modification list to suppress this error.");
        }
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage unscaledTexture = readTexture(texType);
        Rectangle texCoords = texType.getTexCoords();
        BufferedImage texture = scaleTexture(unscaledTexture, scale);
        Color backgroundColor = switch (holeSetting) {
            case HOLES_CENTER -> getCenterColor(unscaledTexture);
            case HOLES_TRANSPARENT -> new Color(0,0,0,0);
            default -> background;
        };
        if (texType.isComplex()){
            Rectangle innerCoords = texType.getInnerCoords();
            Color fillColor = (texType.hasFilling()) ? new Color(unscaledTexture.getRGB(innerCoords.x -1,innerCoords.y -1)) : backgroundColor;
            if (holeSetting == DrawSettings.HOLES_FILLING) backgroundColor = fillColor;
            BufferedImage outerTexture = new BufferedImage(texCoords.width-innerCoords.width-1,texCoords.height-innerCoords.height-1,BufferedImage.TYPE_INT_ARGB);
            Graphics outerG = outerTexture.getGraphics();
            outerG.drawImage(unscaledTexture.getSubimage(0,0,(texCoords.width-innerCoords.width-2) /2,(texCoords.height-innerCoords.height-2) /2),0,0,(texCoords.width-innerCoords.width-2) /2,(texCoords.height-innerCoords.height-2) /2,null);
            outerG.drawImage(unscaledTexture.getSubimage(texCoords.width - ((texCoords.width-innerCoords.width-2) /2),0,(texCoords.width-innerCoords.width-2) /2,(texCoords.height-innerCoords.height-2) /2),outerTexture.getWidth() - (outerTexture.getWidth() /2),0,(texCoords.width-innerCoords.width-2) /2,(texCoords.height-innerCoords.height-2) /2,null);
            outerG.drawImage(unscaledTexture.getSubimage(texCoords.width - ((texCoords.width-innerCoords.width-2) /2),texCoords.height - ((texCoords.height-innerCoords.height-2) /2),(texCoords.width-innerCoords.width-2) /2,(texCoords.height-innerCoords.height-2) /2),outerTexture.getWidth() - (outerTexture.getWidth() /2),outerTexture.getHeight() - (outerTexture.getHeight() /2),(texCoords.width-innerCoords.width-2) /2,(texCoords.height-innerCoords.height-2) /2,null);
            outerG.drawImage(unscaledTexture.getSubimage(0,texCoords.height - ((texCoords.height-innerCoords.height-2) /2),(texCoords.width-innerCoords.width-2) /2,(texCoords.height-innerCoords.height-2) /2),0,outerTexture.getHeight() - (outerTexture.getHeight() /2),(texCoords.width-innerCoords.width-2) /2,(texCoords.height-innerCoords.height-2) /2,null);
            outerG.drawImage(unscaledTexture.getSubimage(texCoords.width/2,0,1,(texCoords.height-innerCoords.height-2) /2),outerTexture.getWidth()/2,0,1,(texCoords.height-innerCoords.height-2) /2,null);
            outerG.drawImage(unscaledTexture.getSubimage(texCoords.width - ((texCoords.width-innerCoords.width-2) /2),texCoords.height/2,(texCoords.width-innerCoords.width-2) /2,1),outerTexture.getWidth() - ((texCoords.width-innerCoords.width-2) /2),outerTexture.getHeight()/2,(texCoords.width-innerCoords.width-2) /2,1,null);
            outerG.drawImage(unscaledTexture.getSubimage(texCoords.width - ((texCoords.width-innerCoords.width-2) /2) -1,texCoords.height - ((texCoords.height-innerCoords.height-2) /2),1,(texCoords.height-innerCoords.height-2) /2),outerTexture.getWidth() - (outerTexture.getWidth() /2) -1,outerTexture.getHeight() - (outerTexture.getHeight() /2),1,(texCoords.height-innerCoords.height-2) /2,null);
            outerG.drawImage(unscaledTexture.getSubimage(0,texCoords.height/2,(texCoords.width-innerCoords.width-2) /2,1),0,outerTexture.getHeight()/2,(texCoords.width-innerCoords.width-2) /2,1,null);
            outerG.setColor(fillColor);
            outerG.fillRect(outerTexture.getWidth() /2,outerTexture.getHeight() /2,1,1);
            outerG.dispose();
            drawTexture(g2d, scaleTexture(outerTexture, scale), backgroundColor, size, !otherSettings.contains(DrawSettings.NO_OUTER), !otherSettings.contains(DrawSettings.NO_FILLING));
            BufferedImage innerTexture = scaleTexture(unscaledTexture.getSubimage(innerCoords.x,innerCoords.y,innerCoords.width,innerCoords.height),scale);
            drawTexture(g2d, innerTexture, backgroundColor, new Rectangle(size.x + ((innerCoords.x + fillingWidth-1) * scale), size.y + ((innerCoords.y + fillingWidth-1) * scale), size.width - (((innerCoords.x + fillingWidth-1) * scale)*2), size.height - (((innerCoords.y + fillingWidth-1) * scale)*2)), !otherSettings.contains(DrawSettings.NO_INNER), !otherSettings.contains(DrawSettings.NO_CENTER));
        }   else {
            drawTexture(g2d, texture, backgroundColor, size,!otherSettings.contains(DrawSettings.NO_OUTER),!otherSettings.contains(DrawSettings.NO_CENTER));
        }
    }

    public static BufferedImage scaleTexture(BufferedImage unscaledTexture, int scale){
        BufferedImage texture = new BufferedImage(1+((unscaledTexture.getWidth()/2)*2)*scale,1+((unscaledTexture.getHeight()/2)*2)*scale,BufferedImage.TYPE_INT_ARGB);
        Graphics gTex = texture.getGraphics();
        gTex.drawImage(unscaledTexture.getSubimage(0,0,unscaledTexture.getWidth()/2,unscaledTexture.getHeight()/2),0,0,texture.getWidth()/2,texture.getHeight()/2,null);
        gTex.drawImage(unscaledTexture.getSubimage(unscaledTexture.getWidth()-unscaledTexture.getWidth()/2,0,unscaledTexture.getWidth()/2,unscaledTexture.getHeight()/2),texture.getWidth()-texture.getWidth()/2,0,texture.getWidth()/2,texture.getHeight()/2,null);
        gTex.drawImage(unscaledTexture.getSubimage(unscaledTexture.getWidth()-unscaledTexture.getWidth()/2,unscaledTexture.getHeight()-unscaledTexture.getHeight()/2,unscaledTexture.getWidth()/2,unscaledTexture.getHeight()/2),texture.getWidth()-texture.getWidth()/2,texture.getHeight()-texture.getHeight()/2,texture.getWidth()/2,texture.getHeight()/2,null);
        gTex.drawImage(unscaledTexture.getSubimage(0,unscaledTexture.getHeight()-unscaledTexture.getHeight()/2,unscaledTexture.getWidth()/2,unscaledTexture.getHeight()/2),0,texture.getHeight()-texture.getHeight()/2,texture.getWidth()/2,texture.getHeight()/2,null);
        gTex.drawImage(unscaledTexture.getSubimage(unscaledTexture.getWidth()/2,0,1,unscaledTexture.getHeight()/2),texture.getWidth()/2,0,1,texture.getHeight()/2,null);
        gTex.drawImage(unscaledTexture.getSubimage(0,unscaledTexture.getHeight()/2,unscaledTexture.getWidth()/2,1),0,texture.getHeight()/2,texture.getWidth()/2,1,null);
        gTex.drawImage(unscaledTexture.getSubimage(1+unscaledTexture.getWidth()/2,unscaledTexture.getHeight()/2,unscaledTexture.getWidth()/2,1),1+texture.getWidth()/2,texture.getHeight()/2,texture.getWidth()/2,1,null);
        gTex.drawImage(unscaledTexture.getSubimage(unscaledTexture.getWidth()/2,1+unscaledTexture.getHeight()/2,1,unscaledTexture.getHeight()/2),texture.getWidth()/2,1+texture.getHeight()/2,1,texture.getHeight()/2,null);
        gTex.drawImage(unscaledTexture.getSubimage(unscaledTexture.getWidth()/2,unscaledTexture.getHeight()/2,1,1),texture.getWidth()/2,texture.getHeight()/2,1,1,null);
        gTex.dispose();
        return texture;
    }

    private static void drawTexture(Graphics2D g2d, BufferedImage texture, Color fillColor, Rectangle size, boolean drawBorder, boolean drawCenter){
        g2d.setClip(size);
        g2d.setColor(fillColor);
        g2d.fillRect(size.x,size.y,size.width,size.height);
        if (drawCenter) {
            Color center = new Color(texture.getRGB(texture.getWidth() / 2, texture.getHeight() / 2));
            g2d.setColor(center);
            g2d.fillRect(size.x +texture.getWidth()/2,size.y + texture.getHeight()/2, size.width - texture.getWidth() + (texture.getWidth()-((texture.getWidth()/2)*2)), size.height - texture.getHeight() + (texture.getHeight()-((texture.getHeight()/2)*2)));
        }
        if (drawBorder) {
            BufferedImage[] corners = new BufferedImage[4];
            TexturePaint[] sides = new TexturePaint[4];
            corners[0] = texture.getSubimage(0,0,texture.getWidth()/2,texture.getHeight()/2);
            corners[1] = texture.getSubimage(texture.getWidth() - texture.getWidth()/2,0,texture.getWidth()/2,texture.getHeight()/2);
            corners[2] = texture.getSubimage(0,texture.getHeight() - texture.getHeight()/2,texture.getWidth()/2,texture.getHeight()/2);
            corners[3] = texture.getSubimage(texture.getWidth() - texture.getWidth()/2,texture.getHeight() - texture.getHeight()/2,texture.getWidth()/2,texture.getHeight()/2);
            sides[0] = new TexturePaint(texture.getSubimage(texture.getWidth()/2,0,1,texture.getHeight()/2),new Rectangle(0,size.y,1,texture.getHeight()/2));
            sides[1] = new TexturePaint(texture.getSubimage(texture.getWidth() - texture.getWidth()/2,texture.getHeight()/2,texture.getWidth()/2,1),new Rectangle(size.x + size.width % (texture.getWidth()/2),0,texture.getWidth()/2,1));
            sides[2] = new TexturePaint(texture.getSubimage(texture.getWidth()/2,texture.getHeight() - texture.getHeight()/2,1,texture.getHeight()/2),new Rectangle(0,size.y + size.height % (texture.getHeight()/2),1,texture.getHeight()/2));
            sides[3] = new TexturePaint(texture.getSubimage(0,texture.getHeight()/2,texture.getWidth()/2,1),new Rectangle(size.x,0,texture.getWidth()/2,1));
            g2d.drawImage(corners[0],size.x,size.y,new Color(0,0,0,0),null);
            g2d.drawImage(corners[1],size.x + size.width - texture.getWidth()/2,size.y,new Color(0,0,0,0),null);
            g2d.drawImage(corners[3],size.x + size.width - texture.getWidth()/2,size.y + size.height - texture.getHeight()/2,new Color(0,0,0,0),null);
            g2d.drawImage(corners[2],size.x,size.y + size.height - texture.getHeight()/2,new Color(0,0,0,0),null);
            g2d.setPaint(sides[0]);
            g2d.fillRect(size.x + texture.getWidth()/2,size.y,size.width - texture.getWidth() + (texture.getWidth()-((texture.getWidth()/2)*2)),texture.getHeight()/2);
            g2d.setPaint(sides[1]);
            g2d.fillRect(size.x + size.width - texture.getWidth()/2,size.y + texture.getHeight()/2,texture.getWidth()/2,size.height - texture.getHeight() + (texture.getHeight()-((texture.getHeight()/2)*2)));
            g2d.setPaint(sides[2]);
            g2d.fillRect(size.x + texture.getWidth()/2, size.y + size.height - texture.getHeight()/2, size.width - texture.getWidth() + (texture.getWidth()-((texture.getWidth()/2)*2)),texture.getHeight()/2);
            g2d.setPaint(sides[3]);
            g2d.fillRect(size.x,size.y + texture.getHeight()/2,texture.getWidth()/2,size.height - texture.getHeight() + (texture.getHeight()-((texture.getHeight()/2)*2)));
        }
    }

    public static double calcCurrentScaleFactor(int scale, int width, int height){
        return (double)scale/(double)Math.min(width,height);
    }

    private static BufferedImage loadTextureFile(){
        BufferedImage unscaledTexture;
        try {
            unscaledTexture = ImageIO.read(Objects.requireNonNull(McUtils.class.getResourceAsStream(TEXTURE_PATH)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return unscaledTexture;
    }

    public void addWatchList(){
        updateWatchList.add(this);
    }

    public void removeFromWatchList(){
        updateWatchList.remove(this);
    }

    public boolean isOnWatchList(){
        return updateWatchList.contains(this);
    }

    /**
     * Diese Methode gibt zurück, wie viele Fehler durch das Ändern der setGlobalScale()
     * erzeugt wurden.
     * */
    public static int callUpdatesInWatchList(){
        int updateErrors = 0;
        for (ExpandableTexture expandableTexture : updateWatchList) {
            if(!expandableTexture.globalScaleUpdated()) updateErrors++;
        }
        return updateErrors;
    }

    public static BufferedImage readTexture(McComponentTextureEnum texType){
        Rectangle texCoords = texType.getTexCoords();
        return TEXTURE.getSubimage(texCoords.x,texCoords.y,texCoords.width,texCoords.height);
    }

    //Abstrakte Methoden
    /**
     * Diese Methode sollte bei den entsprechenden ExpandableTexture Instanzen implementiert
     * werden und dafür sorgen, dass sich das Dargestellte an den neuen getGlobalScale()
     * anpasst. Es wird "true" zurückgegeben, wenn der neue getGlobalScale() keine Fehler
     * erzeugt hat und das Darzustellende korrekt gemalt werden konnte. Andernfalls soll
     * "false" zurückgegeben werden.
    **/
    protected abstract boolean globalScaleUpdated();

    //Getter
    public static int getGlobalScale() {
        return globalScale;
    }

    public static HashSet<ExpandableTexture> getUpdateWatchList() {
        return updateWatchList;
    }

    public static Color getCenterColor(BufferedImage texture){
        return new Color(texture.getRGB(texture.getWidth() / 2, texture.getHeight() / 2));
    }

    public static Color getFillingColor(McComponentTextureEnum texType){
        BufferedImage unscaledTexture = readTexture(texType);
        Rectangle innerCoords = texType.getInnerCoords();
        return new Color(unscaledTexture.getRGB(innerCoords.x -1,innerCoords.y -1));
    }

    public static Color getCenterColor(McComponentTextureEnum texType){
        return getCenterColor(readTexture(texType));
    }

    public McComponentTextureEnum getBorderTexType() {
        return borderTexType;
    }

    public Color getBorderBackgroundColor() {
        return borderBackgroundColor;
    }

    public static BufferedImage getTexture(){
        return TEXTURE;
    }

    //Setter
    public static void setGlobalScale(int newGlobalScale) {
        if (newGlobalScale <= 0) throw new IllegalArgumentException("globalScale '"+newGlobalScale+"' must be bigger or equal to 1.");
        globalScale = newGlobalScale;
        callUpdatesInWatchList();
    }

    public void setBorderTexType(McComponentTextureEnum borderTexType) {
        this.borderTexType = borderTexType;
    }

    public void setBorderBackgroundColor(Color borderBackgroundColor) {
        this.borderBackgroundColor = borderBackgroundColor;
    }

    //Maker

    //Overrides aus
    ////AbstractBorder
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        if (getBorderBackgroundColor() == null) {
            paintTexture(g,new Rectangle(x,y,width,height),getBorderTexType(),DrawSettings.FORCE_PAINTING,DrawSettings.JUST_OUTER,DrawSettings.HOLES_TRANSPARENT);
        } else {
            paintTexture(g,new Rectangle(x,y,width,height),getBorderTexType(),getBorderBackgroundColor(),DrawSettings.FORCE_PAINTING,DrawSettings.JUST_OUTER);
        }
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return getBorderInsets(c,null);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return new Insets((borderTexType.getMinSize().height/2)*getGlobalScale(),(borderTexType.getMinSize().width/2)*getGlobalScale(),(borderTexType.getMinSize().height/2)*getGlobalScale(),(borderTexType.getMinSize().width/2)*getGlobalScale());
    }

    @Override
    public boolean isBorderOpaque() {
        return !McComponentTextureEnum.PANEL_THIN.hasTransparency();
    }

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
