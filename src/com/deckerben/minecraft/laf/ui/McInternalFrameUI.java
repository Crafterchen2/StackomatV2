package com.deckerben.minecraft.laf.ui;

import com.deckerben.minecraft.components.McInternalFrameTitlePane;
import com.deckerben.minecraft.laf.DrawSettings;
import com.deckerben.minecraft.laf.ExpandableTexture;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.image.BufferedImage;

import static com.deckerben.minecraft.laf.ExpandableTexture.getGlobalScale;

public class McInternalFrameUI extends BasicInternalFrameUI {

    //Felder
    protected JButton[] additionalButtons = new JButton[0];

    //Listener

    //Konstruktoren
    public McInternalFrameUI(JInternalFrame b) {
        super(b);
    }

    public McInternalFrameUI(JInternalFrame b, JButton... additionalButtons) {
        super(b);
        this.additionalButtons = additionalButtons;
    }

    //Methoden
    public static ComponentUI createUI(JComponent b)    {
        return new McInternalFrameUI((JInternalFrame)b);
    }

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////BasicInternalFrameUI
    @Override
    protected JComponent createNorthPane(JInternalFrame w) {
        titlePane = new McInternalFrameTitlePane(w);
        for (JButton value: additionalButtons) {
            titlePane.add("",value);
        }
        return titlePane;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        BufferedImage border = new BufferedImage(c.getWidth(),c.getHeight() + (4 * getGlobalScale()),BufferedImage.TYPE_INT_RGB);
        ExpandableTexture.paintTexture(g,new Rectangle(c.getWidth(),c.getHeight() + (4 * getGlobalScale())), McComponentTextureEnum.PANEL_THICK, DrawSettings.JUST_OUTER);
        g.drawImage(border.getSubimage(0, 4 * getGlobalScale(), c.getWidth(), c.getHeight()),0,0,null);
        //super.paint(g, c);
    }

    //Interne Klassen
    ////Klasse "<Klassenname>"

}
