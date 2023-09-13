package com.deckerben.minecraft.laf;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class McUtils {

    private static final Font MC_FONT = initializeMcFont();

    public static Font initializeMcFont(){
        Font rv = UIManager.getDefaults().getFont("Label.font");
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(McUtils.class.getResourceAsStream("/com/deckerben/minecraft/laf/textures/assets/MinecraftRegular.ttf")));
            rv = font.deriveFont(12f * ExpandableBorder.getGlobalScale());
            ge.registerFont(font);
        } catch (IOException|FontFormatException e) {
            e.printStackTrace();
        }
        return rv;
    }

    public static Font getMcFont(){
        return MC_FONT;
    }

    public static void installMinecraftUI(){
        try {
            UIManager.setLookAndFeel(new MinecraftUI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}