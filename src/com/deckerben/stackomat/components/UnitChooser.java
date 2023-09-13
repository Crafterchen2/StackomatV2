package com.deckerben.stackomat.components;

import com.deckerben.stackomat.ItemUnitEnum;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnitChooser extends JComponent {

    //Felder
    private final JLabel display = new JLabel();
    private final JComboBox<String> selector;

    //Listener

    //Konstruktoren
    public UnitChooser(ActionListener actionListener) {
        setLayout(new GridLayout(1,1));
        selector = new JComboBox<>();
        for (int i = ItemUnitEnum.values().length-1; i >= 0; i--) {
            selector.addItem(ItemUnitEnum.values()[i].getName());
        }
        selector.addActionListener(actionListener);
        add(selector);
    }

    //Methoden
    private void showPopup(){

    }

    //Getter
    public ItemUnitEnum getSelectedUnit(){
        return ItemUnitEnum.values()[(ItemUnitEnum.values().length-1)-selector.getSelectedIndex()];
    }

    //Setter

    //Maker

    //Overrides aus
    ////JComponent

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
