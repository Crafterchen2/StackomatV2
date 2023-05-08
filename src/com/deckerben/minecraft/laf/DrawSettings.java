package com.deckerben.minecraft.laf;

public enum DrawSettings {

    NOT_SET(-1),
    HOLES_TRANSPARENT(0),
    HOLES_FILLING(0),
    HOLES_CENTER(0),
    JUST_OUTER(1),
    JUST_INNER(1),
    JUST_CENTER(1),
    JUST_FILLING(1),
    NO_OUTER(2),
    NO_INNER(2),
    NO_CENTER(2),
    NO_FILLING(2),
    FORCE_PAINTING(3);

    public static final int NOT_SET_ID = -1;
    public static final int HOLES_ID = 0;
    public static final int JUST_ID = 1;
    public static final int NO_ID = 2;
    public static final int OTHER_ID = 3;

    private final int id;

    DrawSettings(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
