package com.deckerben.minecraft.laf.ui;

import com.deckerben.minecraft.components.McInternalFrameTitlePane;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.awt.*;

public class McDesktopIconUI extends BasicDesktopIconUI {

    //Felder

    //Listener

    //Konstruktoren
    public McDesktopIconUI() {

    }

    //Methoden
    public static ComponentUI createUI(JComponent b)    {
        return new McDesktopIconUI();
    }

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////BasicDesktopIconUI
    @Override
    protected void installComponents() {
        iconPane = new McInternalFrameTitlePane(frame);
        desktopIcon.setLayout(new BorderLayout());
        desktopIcon.add(iconPane, BorderLayout.CENTER);
    }

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
