package com.deckerben.minecraft.components;

import javax.swing.*;
import java.awt.*;

public class McFrame extends JFrame {

    //Felder

    //Listener

    //Konstruktoren
    public McFrame() throws HeadlessException {
        this("");
    }

    public McFrame(GraphicsConfiguration gc) {
        super("",gc);
    }

    public McFrame(String title) throws HeadlessException {
        super(title);
        setup(title);
    }

    public McFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
        setup(title);
    }

    //Methoden
    private void setup(String title){

    }

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////<Oberklasse>

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
