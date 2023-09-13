package com.deckerben.stackomat.components;

import com.deckerben.minecraft.laf.ExpandableBorder;
import com.deckerben.stackomat.UnitEnumInterface;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ItemIOField<U extends UnitEnumInterface> extends JComponent {

    //Felder
    protected final boolean IS_INPUT;
    private U unit;
    private final SpinnerNumberModel numberModel = new SpinnerNumberModel(0,0,Integer.MAX_VALUE,1);
    protected ItemDisplay<U> iconDisplay;
    private final ChangeListener inputListener;

    private int num = 0;
    private JTextField numDisplay;
    //Listener

    //Konstruktoren
    public ItemIOField(final U unit) {
        this(null,unit,false);
    }

    public ItemIOField(final ChangeListener inputListener, final U unit){
        this(inputListener,unit,true);
    }

    protected ItemIOField(final ChangeListener inputListener, final U unit, boolean input){
        IS_INPUT = input;
        this.unit = unit;
        this.inputListener = inputListener;
        initializeComponent();
    }

    //Methoden
    private void initializeComponent(){
        setOpaque(false);
        setLayout(new BorderLayout());
        JPanel list = new JPanel(new GridLayout(2,1));
        list.setOpaque(false);
        JLabel nameDisplay = new JLabel(unit.getName());
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
        iconDisplay = new ItemDisplay<>(unit,true);
        iconDisplay.setPreferredSize(new Dimension(24* ExpandableBorder.getGlobalScale(),24* ExpandableBorder.getGlobalScale()));
        add(iconDisplay,BorderLayout.WEST);
    }

    public void resetNum(){
        num = 0;
        updateDisplay();
    }

    private void updateNum(){
        num = numberModel.getNumber().intValue();
    }

    private void updateDisplay(){
        numDisplay.setText(""+num);
    }

    //Getter
    public boolean isInput() {
        return IS_INPUT;
    }

    public U getUnit() {
        return unit;
    }

    public int getNum() {
        return num;
    }

    //Setter
    public void setNum(int num) {
        this.num = num;
        updateDisplay();
    }

    public void setUnit(U unit){
        this.unit = unit;
        iconDisplay.setUnit(unit);
        iconDisplay.updateUI();
    }

    /**
     * Die Benutzung der Methode "setNum(int num)" sollte bevorzugt werden.
     * */
    public void forceDisplayMessage(String text){
        numDisplay.setText(text);
    }

    protected void setItemDisplayEnabled(boolean enabled){
        iconDisplay.setEnabled(enabled);
        inputListener.stateChanged(new ChangeEvent(this));
    }

    protected void toggleItemDisplayEnabled(){
        iconDisplay.toggleEnabled();
        inputListener.stateChanged(new ChangeEvent(this));
    }

    //Maker

    //Overrides aus
    ////<Oberklasse>


    //Interne Klassen
    ////Klasse
}
