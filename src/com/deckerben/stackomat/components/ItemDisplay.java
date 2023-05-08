package com.deckerben.stackomat.components;

import com.deckerben.minecraft.laf.ExpandableTexture;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;
import com.deckerben.stackomat.ItemUnitEnum;
import com.deckerben.stackomat.UnitEnumInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ItemDisplay<U extends UnitEnumInterface> extends JComponent {

    //Felder
    private U unit;
    private BufferedImage icon;

    private boolean isSquare = true;

    private final ExpandableTexture border = new ExpandableTexture(true) {
        @Override
        protected boolean globalScaleUpdated() {
            updateUI();
            return true;
        }
    };

    //Listener

    //Konstruktoren
    public ItemDisplay(U unit) {
        setUnit(unit);
    }

    public ItemDisplay(U unit, boolean isSquare) {
        setUnit(unit);
        setSquare(isSquare);
        border.setBorderTexType(McComponentTextureEnum.ICON);
        setBorder(border);
    }

    //Methoden

    //Getter
    public U getUnit() {
        return unit;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public boolean isSquare() {
        return isSquare;
    }

    //Setter
    public void setUnit(U unit) {
        this.unit = unit;
        icon = unit.getImage();
    }

    public void setSquare(boolean square) {
        isSquare = square;
    }

    //Maker

    //Overrides aus
    ////JComponent
    @Override
    protected void paintComponent(Graphics g) {
        Rectangle bounds = getBounds();
        Rectangle innerRect = new Rectangle(border.getInteriorRectangle(this,bounds.x, bounds.y, bounds.width, bounds.height));
        g.setColor(ExpandableTexture.getCenterColor(McComponentTextureEnum.ICON));
        g.fillRect(innerRect.x, innerRect.y, innerRect.width, innerRect.height);
        g.drawImage(icon,innerRect.x, innerRect.y, innerRect.width, innerRect.height,null);
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (enabled) {
            border.setBorderTexType(McComponentTextureEnum.ICON);
        } else {
            border.setBorderTexType(McComponentTextureEnum.ICON_DISABLED);
        }
        updateUI();
        super.setEnabled(enabled);
    }
    //Interne Klassen
    ////Klasse
}