package com.deckerben.stackomat.components;

import com.deckerben.minecraft.laf.ExpandableBorder;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;
import com.deckerben.stackomat.ContainerUnitEnum;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DistributionPanel extends JPanel {
    private final TogglableItemIOField<ContainerUnitEnum> shulkerOutput;
    private final TogglableItemIOField<ContainerUnitEnum> chestOutput;
    private final TogglableItemIOField<ContainerUnitEnum> shulkerChestOutput;
    private final JLabel efficiencyLabel = new JLabel("Optimale Verteilung");

    //Felder

    //Listener

    //Konstruktoren
    public DistributionPanel(ChangeListener distributionListener) {
        setLayout(new BorderLayout());
        add(efficiencyLabel,BorderLayout.NORTH);
        JPanel list = new JPanel(new GridLayout(3,1));
        list.setOpaque(false);
        shulkerOutput = new TogglableItemIOField<>(distributionListener,ContainerUnitEnum.SHULKER);
        chestOutput = new TogglableItemIOField<>(distributionListener,ContainerUnitEnum.DOUBLE_CHEST);
        shulkerChestOutput = new TogglableItemIOField<>(distributionListener,ContainerUnitEnum.SHULKER_IN_DOUBLE_CHEST);
        list.add(shulkerOutput);
        list.add(chestOutput);
        list.add(shulkerChestOutput);
        add(list,BorderLayout.CENTER);
    }

    //Methoden

    //Getter
    public ContainerUnitEnum[] getEnabledContainer(){
        ArrayList<ContainerUnitEnum> rv = new ArrayList<>(3);
        if (shulkerOutput.isContainerEnabled()) rv.add(shulkerOutput.getUnit());
        if (chestOutput.isContainerEnabled()) rv.add(chestOutput.getUnit());
        if (shulkerChestOutput.isContainerEnabled()) rv.add(shulkerChestOutput.getUnit());
        return rv.toArray(new ContainerUnitEnum[0]);
    }

    private int getValue(HashMap<ContainerUnitEnum, Integer> outputContainer, ContainerUnitEnum selector){
        return (outputContainer.get(selector) == null)? 0 : outputContainer.get(selector);
    }

    //Setter
    public void setValues(HashMap<ContainerUnitEnum, Integer> outputContainer){
        shulkerOutput.setNum(getValue(outputContainer,shulkerOutput.getUnit()));
        chestOutput.setNum(getValue(outputContainer,chestOutput.getUnit()));
        shulkerChestOutput.setNum(getValue(outputContainer,shulkerChestOutput.getUnit()));
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
