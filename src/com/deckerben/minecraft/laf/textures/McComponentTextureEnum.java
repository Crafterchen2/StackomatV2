package com.deckerben.minecraft.laf.textures;

import java.awt.*;

public enum McComponentTextureEnum {

    ICON(0,13,9,9,true),
    ICON_DISABLED(9,13,9,9,true),
    TEXTFIELD(19,10,3,3,false),
    TEXTFIELD_FOCUSSED(22,10,3,3,false),
    SCROLLBAR_SCROLLER(25,10,3,3,false),
    SCROLLBAR_BACKGROUND(13,10,3,3,false),
    TOOLTIP(28,0,5,5,true),
    BUTTON(13,0,5,5,false),
    BUTTON_PRESSED(18,5,5,5,false),
    BUTTON_HOVERED(13,5,5,5,false),
    BUTTON_DISABLED(18,0,5,5,false),
    TOGGLEBUTTON_SELECTED(28,5,5,5,false),
    TOGGLEBUTTON_SELECTED_HOVERED(28,10,5,5,false),
    FANCY_TEXTFIELD(23,0,5,5,false),
    FANCY_TEXTFIELD_DISABLED(23,5,5,5,false),
    PANEL_THIN(16,10,3,3,false),
    PANEL_DARK(5,5,3,3,false),
    PANEL_THICK(0,0,13,13,true,5,5,3,3,true);

    private final Rectangle texCoords;
    private final Rectangle innerCoords;    //Sind relativ zu texCoords zu setzen
    private final boolean hasFilling;
    private final boolean hasTransparency;

    /**
     * Aufbau der einfachen Textur:
     * |1 2 3|  1,3,7,9:    Ecken. Müssen alle die gleiche größe haben
     * |4 5 6|  5:          Zentrum. Muss Zentral in der Textur liegen und darf ist nur ein Pixel
     * |7 8 9|  2,4,6,8:    Seiten. Müssen die direkt anliegenden Ecken verbinden
    * */
    McComponentTextureEnum(Rectangle texCoords, boolean hasTransparency) {
        this.texCoords = texCoords;
        this.hasTransparency = hasTransparency;
        innerCoords = null;
        hasFilling = false;
    }

    McComponentTextureEnum(int x, int y, int width, int height, boolean hasTransparency){
        this(new Rectangle(x,y,width,height), hasTransparency);
    }

    /**
     * Aufbau der komplexen Textur:
     * |1 - - 2 - - 3|  1,3,7,9:    Äußere Ecken. Müssen alle die gleiche größe haben
     * |- # # # # # -|  2,8:        Äußere horizontale Seiten. dürfen nur einem Pixel breit sein und genau zwischen den anliegenden Ecken mit Eckenhöhe liegen
     * |- # A B C # -|  4,6:        Äußere vertikale Seiten. dürfen nur einen Pixel hoch sein und genau zwischen den anliegenden Ecken mit Eckenbreite liegen
     * |4 # D E F # 6|  #:          Füllung zwischen der äußeren und inneren Umrandung. Darf nur einen Pixel breit / hoch sein
     * |- # G H I # -|  A,C,G,I:    Innere Ecken. Müssen alle die gleiche größe haben
     * |- # # # # # -|  E:          Inneres Zentrum. Muss Zentral in der Textur liegen und darf ist nur ein Pixel
     * |7 - - 8 - - 9|  B,D,F,H:    Innere Seiten. Müssen die direkt anliegenden Ecken verbinden
     * Die Füllung ist optional. Ohne Füllung sieht das Schema folgendermaßen aus:
     * |1 - 2 - 3|
     * |- A B C -|
     * |4 D E F 6|
     * |- G H I -|
     * |7 - 8 - 9|
     * */
    McComponentTextureEnum(Rectangle texCoords, boolean hasTransparency, Rectangle innerCoords, boolean hasFilling) {
        this.texCoords = texCoords;
        this.innerCoords = innerCoords;
        this.hasFilling = hasFilling;
        this.hasTransparency = hasTransparency;
    }

    McComponentTextureEnum(int x, int y, int width, int height, boolean hasTransparency, int innerX, int innerY, int innerWidth, int innerHeight, boolean hasFilling){
        this(new Rectangle(x,y,width,height),hasTransparency,new Rectangle(innerX,innerY,innerWidth,innerHeight),hasFilling);
    }

    public boolean isComplex(){
        return innerCoords != null;
    }

    public boolean hasFilling() {
        return hasFilling;
    }

    public boolean hasTransparency(){
        return hasTransparency;
    }

    public Rectangle getTexCoords() {
        return texCoords;
    }

    public Rectangle getInnerCoords() {
        return innerCoords;
    }

    public double getMaxScaleFactor(){
        return 1.0 / ((double)Math.min(texCoords.height,texCoords.width));
    }

    public Dimension getMinSize(){
        return getTexCoords().getSize();
    }
}

