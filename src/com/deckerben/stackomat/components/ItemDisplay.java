package com.deckerben.stackomat.components;

import com.deckerben.minecraft.laf.DrawSettings;
import com.deckerben.minecraft.laf.ExpandableBorder;
import com.deckerben.minecraft.laf.ExpandableTexture;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;
import com.deckerben.stackomat.UnitEnumInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public class ItemDisplay<U extends UnitEnumInterface> extends JComponent {

    //Felder
    private U unit;
    private BufferedImage icon;

    private boolean isSquare = true;

    private final ExpandableTexture texture = new ExpandableTexture(McComponentTextureEnum.ICON, integer -> updateUI());

    //Listener

    //Konstruktoren
    public ItemDisplay(U unit) {
        setUnit(unit);
    }

    public ItemDisplay(U unit, boolean isSquare) {
        setUnit(unit);
        setSquare(isSquare);
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

    public void toggleEnabled(){
        setEnabled(!isEnabled());
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
        Rectangle innerRect = texture.getInnerRectangle(bounds.getSize());
        texture.paintTexture(g, new Rectangle(bounds.getSize()),Color.BLACK, DrawSettings.HOLES_TRANSPARENT);
        g.drawImage(icon,innerRect.x, innerRect.y, innerRect.width, innerRect.height,null);
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (enabled) {
            texture.setTexType(McComponentTextureEnum.ICON);
        } else {
            texture.setTexType(McComponentTextureEnum.ICON_DISABLED);
        }
        updateUI();
        super.setEnabled(enabled);
    }
    //Interne Klassen
    ////Klasse
}