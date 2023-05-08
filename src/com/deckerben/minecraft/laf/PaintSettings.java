package com.deckerben.minecraft.laf;

import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;

import java.awt.*;
import java.util.HashSet;
import java.util.List;

//Externe Klassen
////Klasse "PaintSettings"
public class PaintSettings {

    //Felder
    private Rectangle size;
    private McComponentTextureEnum texType;
    private int scale;
    private int fillWidth;
    private Color background;
    private HashSet<DrawSettings> modifier;

    //Konstruktoren
    public PaintSettings() {
        this.size = new Rectangle();
        this.texType = McComponentTextureEnum.PANEL_THIN;
        this.scale = ExpandableTexture.getGlobalScale();
        this.fillWidth = 0;
        this.background = Color.BLACK;
        this.modifier = new HashSet<>();
        this.modifier.add(DrawSettings.NOT_SET);
    }

    public PaintSettings(Rectangle size, McComponentTextureEnum texType, int scale, int fillWidth, Color background, HashSet<DrawSettings> modifier) {
        this.size = size;
        this.texType = texType;
        this.scale = scale;
        this.fillWidth = fillWidth;
        this.background = background;
        this.modifier = modifier;
        this.modifier.add(DrawSettings.NOT_SET);
    }

    //Methoden

    /**
     * Gibt "true" zurück, falls Änderungen vorgenommen wurden.
     */
    public boolean addDrawSettings(DrawSettings... modifierToAdd) {
        modifier.add(DrawSettings.NOT_SET);
        return modifier.addAll(java.util.List.of(modifierToAdd));
    }

    /**
     * Gibt "true" zurück, falls Änderungen vorgenommen wurden.
     */
    public boolean removeDrawSettings(DrawSettings... modifierToRemove) {
        HashSet<DrawSettings> mods = new HashSet<>(java.util.List.of(modifierToRemove));
        mods.remove(DrawSettings.NOT_SET);
        return modifier.removeAll(mods);
    }

    /**
     * Gibt "true" zurück, falls Änderungen vorgenommen wurden.
     */
    public boolean removeAllDrawSettings() {
        return modifier.retainAll(List.of(DrawSettings.NOT_SET));
    }

    //Getter
    public Rectangle getSize() {
        return size;
    }

    public McComponentTextureEnum getTexType() {
        return texType;
    }

    public int getScale() {
        return scale;
    }

    public int getFillWidth() {
        return fillWidth;
    }

    public Color getBackground() {
        return background;
    }

    public HashSet<DrawSettings> getModifier() {
        this.modifier.add(DrawSettings.NOT_SET);
        return modifier;
    }

    //Setter
    public PaintSettings setSize(Rectangle size) {
        this.size = size;
        return this;
    }

    public PaintSettings setTexType(McComponentTextureEnum texType) {
        this.texType = texType;
        return this;
    }

    public PaintSettings setScale(int scale) {
        this.scale = scale;
        return this;
    }

    public PaintSettings setFillWidth(int fillWidth) {
        this.fillWidth = fillWidth;
        return this;
    }

    public PaintSettings setBackground(Color background) {
        this.background = background;
        return this;
    }

    public PaintSettings setModifier(HashSet<DrawSettings> modifier) {
        this.modifier = modifier;
        this.modifier.add(DrawSettings.NOT_SET);
        return this;
    }

    /**
     * Gibt "true" zurück, falls Änderungen vorgenommen wurden.
     */
    public boolean setDrawSettings(DrawSettings... modifierToSet) {
        removeAllDrawSettings();
        return addDrawSettings(modifierToSet);
    }
}
