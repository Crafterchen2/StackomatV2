package com.deckerben.stackomat.components;

import com.deckerben.stackomat.UnitEnumInterface;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TogglableItemIOField<U extends UnitEnumInterface> extends ItemIOField<U>{

    //Felder

    //Listener
    private final MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            toggleItemDisplayEnabled();
        }
    };

    //Konstruktoren
    public TogglableItemIOField(U unit) {
        super(unit);
        setup();
    }

    public TogglableItemIOField(ChangeListener inputListener, U unit) {
        super(inputListener, unit, false);
        setup();
    }

    //Methoden
    private void setup(){
        iconDisplay.addMouseListener(mouseAdapter);
        iconDisplay.setToolTipText("Ob dieser Container bei der Verteilung berücksichtigt werden soll.");
    }

    //Getter
    public boolean isContainerEnabled(){
        return iconDisplay.isEnabled();
    }

    //Setter

    //Maker

    //Overrides aus


    //Interne Klassen
    ////Klasse "<Klassenname>"
}
