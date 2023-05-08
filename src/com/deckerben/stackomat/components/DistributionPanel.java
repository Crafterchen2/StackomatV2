package com.deckerben.stackomat.components;

import com.deckerben.minecraft.laf.ExpandableTexture;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;
import com.deckerben.stackomat.ContainerUnitEnum;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class DistributionPanel extends JPanel {
    private final TogglableItemIOField<ContainerUnitEnum> shulkerOutput = new TogglableItemIOField<>(ContainerUnitEnum.SHULKER);
    private final TogglableItemIOField<ContainerUnitEnum> chestOutput = new TogglableItemIOField<>(ContainerUnitEnum.DOUBLE_CHEST);
    private final TogglableItemIOField<ContainerUnitEnum> shulkerChestOutput = new TogglableItemIOField<>(ContainerUnitEnum.SHULKER_IN_DOUBLE_CHEST);
    private final JLabel efficiencyLabel = new JLabel("Optimale Verteilung");

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
        //setBorder(border);
        //setBackground(ExpandableTexture.getCenterColor(border.getBorderTexType()));
        setLayout(new BorderLayout());
        add(efficiencyLabel,BorderLayout.NORTH);
        JPanel list = new JPanel(new GridLayout(3,1));
        list.setOpaque(false);
        list.add(shulkerOutput);
        list.add(chestOutput);
        list.add(shulkerChestOutput);
        add(list,BorderLayout.CENTER);
    }

    //Methoden

    //Getter

    //Setter
    public void setValues(HashMap<ContainerUnitEnum, Integer> outputContainer){
        shulkerOutput.setNum(outputContainer.get(shulkerOutput.getUnit()));
        chestOutput.setNum(outputContainer.get(chestOutput.getUnit()));
        shulkerChestOutput.setNum(outputContainer.get(shulkerChestOutput.getUnit()));
    }

    public void setEfficiency(double efficiency){
        double ef = ((double)((int)(efficiency*10000)))/100;
        efficiencyLabel.setText("Optimale Verteilung - "+ef+"%");
    }

    //Maker

    //Overrides aus
    ////<Klassenname>

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
