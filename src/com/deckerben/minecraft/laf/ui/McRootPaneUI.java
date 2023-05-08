package com.deckerben.minecraft.laf.ui;

import com.deckerben.minecraft.components.McTitlePane;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRootPaneUI;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.Objects;

public class McRootPaneUI extends BasicRootPaneUI {

    //Felder
    private Frame frame;

    //Listener

    //Konstruktoren
    public McRootPaneUI() {

    }

    //Methoden
    public static ComponentUI createUI(JComponent c) {
        return new McRootPaneUI();
    }

    //Getter
    public Window getFrame() {
        return frame;
    }

    //Setter

    //Maker

    //Overrides aus
    ////BasicRootPaneUI
    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
    }

    @Override
    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
    }

    @Override
    protected void installComponents(JRootPane root) {
        //JButton button = new JButton("Ein Knopf");
        //final Container contentPane = root.getContentPane();
        //root.setLayout(new BorderLayout());
        //root.add(button, BorderLayout.NORTH);
        //root.add(contentPane, BorderLayout.CENTER);
        frame = (Frame) root.getParent();
        super.installComponents(root);
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        super.propertyChange(e);
        JRootPane root = (JRootPane)e.getSource();
        if (Objects.equals(e.getPropertyName(), "ancestor") && (root.getWindowDecorationStyle() != JRootPane.NONE )) {
            System.out.println("update");
            frame = (Frame) root.getParent();
            final Container contentPane = root.getContentPane();
            root.setLayout(new BorderLayout());
            if (frame == null) {
                JComponent titlePane = new JButton("Ein Knopf");
                root.add(titlePane, BorderLayout.NORTH);
            }   else {
                McTitlePane titlePane = new McTitlePane(frame);
                root.add(titlePane, BorderLayout.NORTH);
            }
            root.add(contentPane, BorderLayout.CENTER);
        }
    }

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
