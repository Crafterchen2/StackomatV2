package com.deckerben.stackomat;

import com.deckerben.minecraft.laf.McUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public enum ItemUnitEnum implements UnitEnumInterface{

    SINGLE_ITEM(1,"Unstackbar", new Rectangle(0,32,16,16)),
    SMALL_STACK(16 * SINGLE_ITEM.getAmount(),"Kleine Stacks", new Rectangle(0,16,16,16)),
    FULL_STACK(4 * SMALL_STACK.getAmount(),"Große Stacks", new Rectangle(0,0,16,16));

    private final int amount;
    private final String name;
    private final BufferedImage icon;

    ItemUnitEnum(int amount, String name, Rectangle iconLoc){
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
