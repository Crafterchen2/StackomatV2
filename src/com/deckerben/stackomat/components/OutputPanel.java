package com.deckerben.stackomat.components;

import com.deckerben.minecraft.laf.ExpandableTexture;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;
import com.deckerben.stackomat.ContainerUnitEnum;
import com.deckerben.stackomat.ItemUnitEnum;

import javax.swing.*;
import java.awt.*;

public class OutputPanel extends JPanel {

    //Felder

    //Listener

    //Konstruktoren
    public OutputPanel() {
        ExpandableTexture border = new ExpandableTexture(true) {
            @Override
            protected boolean globalScaleUpdated() {
                updateUI();
                return true;
            }
        };
        border.setBorderTexType(McComponentTextureEnum.PANEL_DARK);
        setBorder(border);
        setBackground(ExpandableTexture.getCenterColor(border.getBorderTexType()));
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Ausgabe");
        add(label,BorderLayout.NORTH);
        JPanel list = new JPanel(new GridLayout(2,1));
        list.setOpaque(false);
        list.add(new ItemIOField<>(false, ItemUnitEnum.FULL_STACK));
        list.add(new ItemIOField<>(false, ContainerUnitEnum.SLOTS));
        add(list,BorderLayout.CENTER);
    }

    //Methoden

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////JComponent
    /*
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        Dimension size = getSize();
        g.fillRect(0,0, size.width, size.height);
    }
    */

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
