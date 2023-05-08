package com.deckerben.stackomat.components;

import com.deckerben.stackomat.UnitEnumInterface;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TogglableItemIOField<U extends UnitEnumInterface> extends ItemIOField<U>{

    //Felder
    private final JToggleButton activationButton = new JToggleButton();

    //Listener

    //Konstruktoren
    public TogglableItemIOField(U unit) {
        super(unit);
        placeButton();
    }

    public TogglableItemIOField(ChangeListener inputListener, U unit) {
        super(inputListener, unit);
        placeButton();
    }

    //Methoden
    private void placeButton(){
        Dimension wh = iconDisplay.getSize();
        activationButton.setBorderPainted(false);
        activationButton.setContentAreaFilled(false);
        activationButton.setBackground(new Color(110, 110, 110, 92));
        activationButton.setBounds(0,0,wh.width/3,wh.height/3);
        activationButton.addActionListener(e -> {
            if (activationButton.isSelected()){
                activationButton.setText("-");
                activationButton.setToolTipText("Deaktiviere diesen Container.");
            }   else {
                activationButton.setText("+");
                activationButton.setToolTipText("Aktiviere diesen Container.");
            }
            setEnabled(activationButton.isSelected());
        });
    }

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////<Oberklasse>

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
