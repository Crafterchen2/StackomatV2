package com.deckerben.stackomat.components;

import com.deckerben.minecraft.laf.ExpandableTexture;
import com.deckerben.stackomat.UnitEnumInterface;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ItemIOField<U extends UnitEnumInterface> extends JComponent {

    //Felder
    private final boolean IS_INPUT;
    private final U UNIT;
    private final SpinnerNumberModel numberModel = new SpinnerNumberModel(0,0,Integer.MAX_VALUE,1);
    protected ItemDisplay<U> iconDisplay;
    private final ChangeListener inputListener;

    private int num = 0;
    private JTextField numDisplay;
    //Listener

    //Konstruktoren
    public ItemIOField(final U unit) {
        IS_INPUT = false;
        UNIT = unit;
        inputListener = null;
        initializeComponent();
    }

    public ItemIOField(final ChangeListener inputListener, final U unit){
        IS_INPUT = true;
        UNIT = unit;
        this.inputListener = inputListener;
        initializeComponent();
    }

    //Methoden
    public void initializeComponent(){
        setOpaque(false);
        setLayout(new BorderLayout());
        JPanel list = new JPanel(new GridLayout(2,1));
        list.setOpaque(false);
        JLabel nameDisplay = new JLabel(UNIT.getName());
        list.add(nameDisplay);
        if (IS_INPUT) {
            JSpinner numSpin = new JSpinner(numberModel);
            numSpin.addChangeListener(inputListener);
            numSpin.addChangeListener(e -> updateNum());
            list.add(numSpin);
        }   else {
            numDisplay = new JTextField(""+num);
            numDisplay.setEditable(false);
            list.add(numDisplay);
        }
        add(list,BorderLayout.CENTER);
        iconDisplay = new ItemDisplay<>(UNIT,true);
        iconDisplay.setPreferredSize(new Dimension(24* ExpandableTexture.getGlobalScale(),24* ExpandableTexture.getGlobalScale()));
        add(iconDisplay,BorderLayout.WEST);
    }

    public void resetNum(){
        num = 0;
        updateDisplay();
    }

    private void updateNum(){
        num = numberModel.getNumber().intValue();
    }

    private void updateDisplay(){numDisplay.setText(""+num);}

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
        updateDisplay();
    }

    /**
     * Die Benutzung der Methode "setNum(int num)" sollte bevorzugt werden.
     * */
    public void forceDisplayMessage(String text){
        numDisplay.setText(text);
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
