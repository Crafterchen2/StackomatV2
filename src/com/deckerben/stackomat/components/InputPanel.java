package com.deckerben.stackomat.components;

import com.deckerben.minecraft.laf.ExpandableBorder;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;
import com.deckerben.stackomat.ContainerUnitEnum;
import com.deckerben.stackomat.ItemUnitEnum;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.HashMap;

public class InputPanel extends JPanel {
    private final ItemIOField<ItemUnitEnum> rawItemInput;
    private final ItemIOField<ContainerUnitEnum> slotInput;
    private final ItemIOField<ContainerUnitEnum> shulkerInput;
    private final ItemIOField<ContainerUnitEnum> chestInput;
    private final ItemIOField<ContainerUnitEnum> shulkerChestInput;

    //Felder

    //Listener

    //Konstruktoren
    public InputPanel(ChangeListener inputListener) {
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
        JLabel label = new JLabel("Eingabe");
        add(label,BorderLayout.NORTH);
        JPanel list = new JPanel(new GridLayout(5,1));
        list.setOpaque(false);
        rawItemInput = new ItemIOField<>(inputListener, ItemUnitEnum.FULL_STACK);
        slotInput = new ItemIOField<>(inputListener, ContainerUnitEnum.SLOTS);
        shulkerInput = new ItemIOField<>(inputListener, ContainerUnitEnum.SHULKER);
        chestInput = new ItemIOField<>(inputListener, ContainerUnitEnum.DOUBLE_CHEST);
        shulkerChestInput = new ItemIOField<>(inputListener, ContainerUnitEnum.SHULKER_IN_DOUBLE_CHEST);
        list.add(rawItemInput);
        list.add(slotInput);
        list.add(shulkerInput);
        list.add(chestInput);
        list.add(shulkerChestInput);
        add(list,BorderLayout.CENTER);
    }

    //Methoden

    //Getter
    public HashMap<ContainerUnitEnum, Integer> getInputValues(){
        HashMap<ContainerUnitEnum, Integer> rv = new HashMap<>(4);
        rv.put(slotInput.getUnit(),slotInput.getNum());
        rv.put(shulkerInput.getUnit(),shulkerInput.getNum());
        rv.put(chestInput.getUnit(),chestInput.getNum());
        rv.put(shulkerChestInput.getUnit(),shulkerChestInput.getNum());
        return rv;
    }

    public int getRawItemAmount(){
        return rawItemInput.getNum();
    }

    //Setter
    public void setRawType(ItemUnitEnum unit){
        rawItemInput.setUnit(unit);
    }

    //Maker

    //Overrides aus
    ////JComponent
    /*
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.GREEN);
        Dimension size = getSize();
        g.fillRect(0,0, size.width, size.height);
    }
    */


    //Interne Klassen
    ////Klasse "<Klassenname>"
}
