package com.deckerben.minecraft.laf;

public enum DrawSettings {

    NOT_SET(-1),
    HOLES_TRANSPARENT(0),
    HOLES_CENTER(0),
    JUST_OUTER(1),
    JUST_EDGES(1),
    JUST_CORNERS(1),
    JUST_CENTER(1),
    NO_OUTER(2),
    NO_EDGES(2),
    NO_CORNERS(2),
    NO_CENTER(2),
    NO_NW(4),
    NO_NE(4),
    NO_ES(4),
    NO_SW(4),
    NO_N(5),
    NO_E(5),
    NO_S(5),
    NO_W(5),
    JUST_NW(6),
    JUST_NE(6),
    JUST_ES(6),
    JUST_SW(6),
    JUST_N(7),
    JUST_E(7),
    JUST_S(7),
    JUST_W(7),
    FORCE_PAINTING(3);

    public static final int NOT_SET_ID = -1;
    public static final int HOLES_ID = 0;
    public static final int JUST_ID = 1;
    public static final int NO_ID = 2;
    public static final int OTHER_ID = 3;
    /**
     * Jede ID, die > SPECIFIC_THRESHOLD ist, bezieht sich auf spezifische Teile einer Textur.
     */
    public static final int SPECIFIC_THRESHOLD = 3;
    public static final int NO_SPECIFIC_CORNER_ID = SPECIFIC_THRESHOLD+1;
    public static final int NO_SPECIFIC_EDGE_ID = SPECIFIC_THRESHOLD+2;
    public static final int JUST_SPECIFIC_CORNER_ID = SPECIFIC_THRESHOLD+3;
    public static final int JUST_SPECIFIC_EDGE_ID = SPECIFIC_THRESHOLD+4;

    public final int ID;

    DrawSettings(int id) {
        ID = id;
    }

    public SectorEnum getMatchingSector(){
        if (ID > SPECIFIC_THRESHOLD) {
            return SectorEnum.valueOf(name().split("_",2)[1]);
        }   else return null;
    }

    public static DrawSettings[] getNoValues(){
        return new DrawSettings[]{
                DrawSettings.NO_NW,
                DrawSettings.NO_N,
                DrawSettings.NO_NE,
                DrawSettings.NO_E,
                DrawSettings.NO_ES,
                DrawSettings.NO_S,
                DrawSettings.NO_SW,
                DrawSettings.NO_W};
    }

}
