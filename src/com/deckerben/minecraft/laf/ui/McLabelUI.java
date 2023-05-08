package com.deckerben.minecraft.laf.ui;

import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;

public class McLabelUI extends BasicLabelUI {

    //Felder
    private final McComponentTextureEnum iconTexType = McComponentTextureEnum.valueOf(UIManager.getString("Label.iconBackgroundType"));

    //Listener

    //Konstruktoren
    public McLabelUI() {

    }

    //Methoden
    public static ComponentUI createUI(JComponent c) {
        return new McLabelUI();
    }

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////ComponentUI
    @Override
    public void update(Graphics g, JComponent c) {
        super.update(g, c);
    }

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
