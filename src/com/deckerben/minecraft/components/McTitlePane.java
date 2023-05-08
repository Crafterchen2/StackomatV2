package com.deckerben.minecraft.components;

import com.deckerben.minecraft.laf.ExpandableTexture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class McTitlePane extends JComponent implements MCTitlePaneInterface {

    //Felder
    private Frame frame;
    private final ArrayList<JButton> additionalButtons = new ArrayList<>();

    private JButton closeButton, maxButton, iconButton;

    //Listener

    //Konstruktoren
    public McTitlePane(Frame frame) {
        this.frame = frame;
        closeButton = new JButton("");
        closeButton.addActionListener(e -> {
            try {
                JFrame jFrame = (JFrame) frame;
                switch (jFrame.getDefaultCloseOperation()) {
                    case JFrame.DISPOSE_ON_CLOSE -> jFrame.dispose();
                    case JFrame.HIDE_ON_CLOSE -> jFrame.setVisible(false);
                    case JFrame.EXIT_ON_CLOSE -> System.exit(0);
                }
            }   catch (Exception exception) {
                frame.dispose();
            }
        });
        add(closeButton);
        maxButton = new JButton("");
        maxButton.addActionListener(e -> {
            if (frame.getExtendedState() != Frame.MAXIMIZED_BOTH) frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            else frame.setExtendedState(Frame.NORMAL);
        });
        add(maxButton);
        iconButton = new JButton("");
        iconButton.addActionListener(e -> frame.setExtendedState(Frame.ICONIFIED));
        add(iconButton);
        super.setLayout(createLayoutManager());
    }

    //Methoden

    //Getter
    public Frame getFrame() {
        return frame;
    }

    //Setter

    //Maker

    //Overrides aus
    ////<Oberklasse>
    @Override
    public Dimension getPreferredSize() {
        return getMinimumSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(getTargetWidth(),getTargetHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        paintTitlePane(g);
    }

    ////Container
    @Override
    public void setLayout(LayoutManager layout){

    }

    //Implements aus
    ////MCTitlePaneInterface
    @Override
    public ArrayList<JButton> getAdditionalButtons() {
        return additionalButtons;
    }

    @Override
    public boolean isClosable() {
        return true;
    }

    @Override
    public boolean isMaximizable() {
        return frame.isResizable();
    }

    @Override
    public boolean isIconifiable() {
        return true;
    }

    @Override
    public JButton getCloseButton() {
        return closeButton;
    }

    @Override
    public JButton getMaxButton() {
        return maxButton;
    }

    @Override
    public JButton getIconButton() {
        return iconButton;
    }

    @Override
    public Icon getFrameIcon() {
        return (frame.getIconImage() != null)? new ImageIcon(frame.getIconImage()) : new ImageIcon();
    }

    @Override
    public JMenuBar getMenuBar() {
        try {
            return ((JFrame)frame).getJMenuBar();
        }   catch (Exception ex) {
            return null;
        }
    }

    @Override
    public JLabel getTitleLabel() {
        return new JLabel(frame.getTitle());
    }

    @Override
    public int getTargetWidth() {
        return frame.getWidth();
    }

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
