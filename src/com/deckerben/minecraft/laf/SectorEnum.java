package com.deckerben.minecraft.laf;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public enum SectorEnum {

    NW(1),
    N(2),
    NE(1),
    E(3),
    ES(1),
    S(2),
    SW(1),
    W(3),
    C(0);

    public static final int CENTER = 0;
    public static final int CORNER = 1;
    /**
     * Falls der Sektor eine BREITE von einem Pixel hat
     */
    public static final int VERTICAL_EDGE = 2;
    /**
     * Falls der Sektor eine HÖHE von einem Pixel hat
     */
    public static final int HORIZONTAL_EDGE = 3;

    public final int TYPE;

    SectorEnum(int type) {
        TYPE = type;
    }

    public static HashMap<SectorEnum, BufferedImage> makeEmptyMap(){
        HashMap<SectorEnum, BufferedImage> rv = new HashMap<>(getLength());
        for (SectorEnum value : values()) rv.put(value, null);
        return rv;
    }

    public static int getLength(){
        return values().length;
    }

}
