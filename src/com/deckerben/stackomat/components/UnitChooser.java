package com.deckerben.stackomat.components;

import com.deckerben.stackomat.ItemUnitEnum;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnitChooser extends JComponent {

    //Felder
    private final JLabel display = new JLabel();

    //Listener

    //Konstruktoren
    public UnitChooser() {
        setLayout(new GridLayout(1,1));
        add(new JComboBox<>(ItemUnitEnum.values()));
    }

    //Methoden
    private void showPopup(){

    }

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////JComponent

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
