package com.deckerben.stackomat.components;

import com.deckerben.minecraft.laf.ExpandableTexture;
import com.deckerben.stackomat.UnitEnumInterface;

import javax.swing.*;
import java.awt.*;

public class ItemIOField<U extends UnitEnumInterface> extends JComponent {

    //Felder
    private final boolean IS_INPUT;
    private final U UNIT;
    private final SpinnerNumberModel numberModel = new SpinnerNumberModel(0,0,Integer.MAX_VALUE,1);
    private final ItemDisplay<U> iconDisplay;

    private int num = 0;
    //Listener

    //Konstruktoren
    public ItemIOField(final boolean isInput, final U unit) {
        IS_INPUT = isInput;
        UNIT = unit;
        setOpaque(false);
        setLayout(new BorderLayout());
        JPanel list = new JPanel(new GridLayout(2,1));
        list.setOpaque(false);
        JLabel nameDisplay = new JLabel(UNIT.getName());
        list.add(nameDisplay);
        if (IS_INPUT) {
            JSpinner numSpin = new JSpinner(numberModel);
            list.add(numSpin);
        }   else {
            JTextField numDisplay = new JTextField(""+num);
            numDisplay.setEditable(false);
            list.add(numDisplay);

        }
        add(list,BorderLayout.CENTER);
        iconDisplay = new ItemDisplay<>(UNIT,true);
        iconDisplay.setPreferredSize(new Dimension(24* ExpandableTexture.getGlobalScale(),24* ExpandableTexture.getGlobalScale()));
        add(iconDisplay,BorderLayout.WEST);
    }

    //Methoden
    public void resetNum(){
        num = 0;
    }

    public void updateNum(){
        num = numberModel.getNumber().intValue();
    }

    //Getter
    public boolean isInput() {
        return IS_INPUT;
    }

    public U getUnit() {
        return UNIT;
    }

    public int getNum() {
        return num;
    }

    //Setter
    public void setNum(int num) {
        this.num = num;
    }

    public void setItemDisplayEnabled(boolean enabled){
        iconDisplay.setEnabled(enabled);
    }

    //Maker

    //Overrides aus
    ////<Oberklasse>


    //Interne Klassen
    ////Klasse
}
