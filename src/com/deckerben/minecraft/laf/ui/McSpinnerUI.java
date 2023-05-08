package com.deckerben.minecraft.laf.ui;

import com.deckerben.minecraft.laf.DrawSettings;
import com.deckerben.minecraft.laf.ExpandableTexture;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSpinnerUI;
import java.awt.*;

public class McSpinnerUI extends BasicSpinnerUI {

    //Felder

    //Listener

    //Konstruktoren
    public McSpinnerUI() {

    }

    //Methoden
    public static ComponentUI createUI(JComponent c) {
        return new McSpinnerUI();
    }

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////<Oberklasse>

    @Override
    public void update(Graphics g, JComponent c) {
        Rectangle editorSize = new Rectangle(((JSpinner)c).getEditor().getSize());
        Rectangle arrowSize = new Rectangle(c.getSize().width - ((JSpinner)c).getEditor().getSize().width,c.getSize().height - ((JSpinner)c).getEditor().getSize().height);
        ExpandableTexture.paintTexture(g,editorSize, McComponentTextureEnum.TEXTFIELD, DrawSettings.FORCE_PAINTING);
        ExpandableTexture.paintTexture(g,arrowSize,McComponentTextureEnum.BUTTON, DrawSettings.FORCE_PAINTING);
        super.update(g, c);
    }


    //Interne Klassen
    ////Klasse "<Klassenname>"
}
