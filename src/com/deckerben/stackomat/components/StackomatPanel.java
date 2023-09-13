package com.deckerben.stackomat.components;

import com.deckerben.stackomat.Stackomat;

import javax.swing.*;
import java.awt.*;

public class StackomatPanel extends JPanel {

    //Felder
    private final InputPanel inputPanel = new InputPanel(e -> updateInputs());
    private final OutputPanel outputPanel = new OutputPanel();
    private final DistributionPanel distributionPanel = new DistributionPanel(e -> updateInputs());

    private final Stackomat stackomat = new Stackomat(true,false);
    private final UnitChooser unitChooser = new UnitChooser(e -> updateUnit());

    //Konstruktoren
    public StackomatPanel(){
        stackomat.setEnabledContainer(distributionPanel.getEnabledContainer());
        updateUnit();
        applyLayout();
    }

    //Methoden
    private void updateInputs() {
        stackomat.setEnabledContainer(distributionPanel.getEnabledContainer());
        stackomat.setInputItemNum(inputPanel.getRawItemAmount());
        stackomat.setInputContainer(inputPanel.getInputValues());
        updateOutputs();
    }

    private void updateUnit() {
        stackomat.setSelectedItemUnit(unitChooser.getSelectedUnit());
        inputPanel.setRawType(unitChooser.getSelectedUnit());
        outputPanel.setStackTypeDisplay(unitChooser.getSelectedUnit());
        updateOutputs();
    }

    private void updateOutputs() {
        outputPanel.setTotalItems(stackomat.getTotalItemAmount());
        outputPanel.setStacks(stackomat.getFullSlotNum(),stackomat.getOverflowNum());
        distributionPanel.setEfficiency(stackomat.getFillPercent());
        distributionPanel.setValues(stackomat.getOutputContainer());
    }

    private void applyLayout(){
        setLayout(new BorderLayout());
        add(unitChooser,BorderLayout.NORTH);
            JPanel calcPanel = new JPanel();
            calcPanel.setLayout(new GridLayout(1,2));
                JPanel inPanel = new JPanel(new BorderLayout());
                inPanel.add(inputPanel,BorderLayout.NORTH);
            calcPanel.add(inPanel);
                JPanel outPanel = new JPanel();
                outPanel.setLayout(new BoxLayout(outPanel,BoxLayout.Y_AXIS));
                outPanel.add(outputPanel);
                outPanel.add(distributionPanel);
            calcPanel.add(outPanel);
        add(calcPanel,BorderLayout.SOUTH);
    }

}