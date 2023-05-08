package com.deckerben.minecraft.laf.ui;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;

public class McPanelUI extends BasicPanelUI {

    //Felder

    //Listener

    //Konstruktoren
    public McPanelUI() {

    }

    //Methoden
    public static ComponentUI createUI(JComponent c) {
        return new McPanelUI();
    }

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////BasicPanelUI
    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        c.setBorder(UIManager.getBorder("Panel.border"));
    }

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
