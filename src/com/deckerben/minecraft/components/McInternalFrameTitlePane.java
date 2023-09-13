package com.deckerben.minecraft.components;

import com.deckerben.minecraft.laf.McUtils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.util.ArrayList;

public class McInternalFrameTitlePane extends BasicInternalFrameTitlePane implements MCTitlePaneInterface{

    //Felder
    private final ArrayList<JButton> additionalButtons = new ArrayList<>();
    private JLabel titleLabel = new JLabel("Text");

    //Konstruktoren
    public McInternalFrameTitlePane(JInternalFrame f) {
        super(f);
        //titleLabel.setText(frame.getTitle());
    }

    //Overrides aus
    ////BasicInternalFrameTitlePane
    @Override
    public void paintComponent(Graphics g) {
        this.paintTitlePane(g);
    }

    @Override
    protected LayoutManager createLayout() {
        return this.createLayoutManager();
    }

    @Override
    protected void addSubComponents() {
        titleLabel = new JLabel("Text");
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setFont(McUtils.getMcFont().deriveFont(20f));
        titleLabel.setEnabled(true);
        super.addSubComponents();
        add(titleLabel);
    }

    //Implements aus
    ////MCTitlePaneInterface
    @Override
    public ArrayList<JButton> getAdditionalButtons() {
        return additionalButtons;
    }

    @Override
    public boolean isClosable() {
        return frame.isClosable();
    }

    @Override
    public boolean isMaximizable() {
        return frame.isMaximizable();
    }

    @Override
    public boolean isIconifiable() {
        return frame.isIconifiable();
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
        return frame.getFrameIcon();
    }

    @Override
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    @Override
    public JLabel getTitleLabel() {
        return titleLabel;
    }

    @Override
    public int getTargetWidth() {
        return frame.getWidth();
    }
}