package com.deckerben.stackomat;

import com.deckerben.stackomat.components.StackomatPanel;

import javax.swing.*;

public class StackomatExec extends JFrame {

    public StackomatExec(){
        super("Stackomat");
        setContentPane(new StackomatPanel());
        setLocationRelativeTo(null);
        setSize(460,460);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    public static void  main(String[] args){
        new StackomatExec();
    }

}