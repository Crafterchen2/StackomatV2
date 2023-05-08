package com.deckerben.stackomat.components;

import com.deckerben.minecraft.laf.ExpandableTexture;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;
import com.deckerben.stackomat.ContainerUnitEnum;
import com.deckerben.stackomat.ItemUnitEnum;

import javax.swing.*;
import java.awt.*;

public class DistributionPanel extends JPanel {

    //Felder

    //Listener

    //Konstruktoren
    public DistributionPanel() {
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
        JLabel label = new JLabel("Optimale Verteilung");
        add(label,BorderLayout.NORTH);
        JPanel list = new JPanel(new GridLayout(3,1));
        list.setOpaque(false);
        list.add(new ItemIOField<>(false, ContainerUnitEnum.SHULKER));
        list.add(new ItemIOField<>(false, ContainerUnitEnum.DOUBLE_CHEST));
        list.add(new ItemIOField<>(false, ContainerUnitEnum.SHULKER_IN_DOUBLE_CHEST));
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
        g.setColor(Color.BLUE);
        Dimension size = getSize();
        g.fillRect(0,0, size.width, size.height);
    }
     */


    //Interne Klassen
    ////Klasse "<Klassenname>"
}
