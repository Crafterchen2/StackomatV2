package com.deckerben.stackomat.components;

import com.deckerben.minecraft.laf.ExpandableBorder;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;
import com.deckerben.stackomat.ContainerUnitEnum;
import com.deckerben.stackomat.ItemUnitEnum;

import javax.swing.*;
import java.awt.*;

public class OutputPanel extends JPanel {
    private final ItemIOField<ItemUnitEnum> totalItemsOutput;
    private final ItemIOField<ContainerUnitEnum> stackOutput;

    //Felder

    //Listener

    //Konstruktoren
    public OutputPanel() {
        ExpandableBorder border = new ExpandableBorder(true) {
            @Override
            protected boolean globalScaleUpdated() {
                updateUI();
                return true;
            }
        };
        border.setBorderTexType(McComponentTextureEnum.PANEL_DARK);
        //setBorder(border);
        //setBackground(ExpandableBorder.getCenterColor(border.getBorderTexType()));
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Ausgabe");
        add(label,BorderLayout.NORTH);
        JPanel list = new JPanel(new GridLayout(2,1));
        list.setOpaque(false);
        totalItemsOutput = new ItemIOField<>(ItemUnitEnum.FULL_STACK);
        stackOutput = new ItemIOField<>(ContainerUnitEnum.SLOTS);
        list.add(totalItemsOutput);
        list.add(stackOutput);
        add(list,BorderLayout.CENTER);
    }

    //Methoden

    //Getter

    //Setter
    public void setTotalItems(int totalItems){
        totalItemsOutput.setNum(totalItems);
    }

    public void setStacks(int stackCount, int overflow){
        stackOutput.forceDisplayMessage(stackCount+" + "+overflow);
    }

    public void setStackTypeDisplay(ItemUnitEnum unit){
        totalItemsOutput.setUnit(unit);
    }

    //Maker

    //Overrides aus
    ////<Klassenname>

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
