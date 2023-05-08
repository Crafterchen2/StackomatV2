package com.deckerben.stackomat;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum ContainerUnitEnum implements UnitEnumInterface{

    //Müssen in aufsteigender Containergröße sortiert sein
    SLOTS(1,"Stacks",new Rectangle(16,32,16,16)),
    SHULKER(27* SLOTS.getAmount(),"Shulker",new Rectangle(32,0,16,16)),
    DOUBLE_CHEST(2 * SHULKER.getAmount(),"Doppelkisten",new Rectangle(32,16,16,16)),
    SHULKER_IN_DOUBLE_CHEST(DOUBLE_CHEST.getAmount() * SHULKER.getAmount(),"Shulkerkisten",new Rectangle(32,32,16,16));

    private final int amount;
    private final String name;
    private final BufferedImage icon;

    ContainerUnitEnum(int amount, String name, Rectangle iconLoc){
        this.amount = amount;
        this.name = name;
        icon = createImageIcon(iconLoc);
    }

    //Getter
    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getImage() {
        return icon;
    }
}
