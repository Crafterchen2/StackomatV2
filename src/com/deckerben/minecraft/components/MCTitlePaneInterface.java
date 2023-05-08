package com.deckerben.minecraft.components;

import com.deckerben.minecraft.laf.DrawSettings;
import com.deckerben.minecraft.laf.ExpandableTexture;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static com.deckerben.minecraft.laf.ExpandableTexture.getGlobalScale;

public interface MCTitlePaneInterface {

    //Felder

    //Listener

    //Konstruktoren

    //Methoden
    /**
     * Paints the title Pane.
     * @param g
     * Graphics that get modified.
     */
    default void paintTitlePane(Graphics g){
        final BufferedImage border = new BufferedImage(getTargetWidth(), getTargetHeight(),BufferedImage.TYPE_INT_RGB);
        ExpandableTexture.paintTexture(border.getGraphics(),new Rectangle(getTargetWidth(), getTargetHeight() + 4 * getGlobalScale()), McComponentTextureEnum.PANEL_THICK, DrawSettings.JUST_OUTER);
        g.setPaintMode();
        g.drawImage(border,0,0,null);
        g.setColor(ExpandableTexture.getFillingColor(McComponentTextureEnum.PANEL_THICK));
        g.fillRect(4 * getGlobalScale(), 4 * getGlobalScale(), getTargetWidth() - (8 * getGlobalScale()), getTargetHeight() - (4 * getGlobalScale()));
        BufferedImage image = ExpandableTexture.readTexture(McComponentTextureEnum.PANEL_THICK);
        image = ExpandableTexture.scaleTexture(image,getGlobalScale());
        BufferedImage outerCorner = image.getSubimage(1+(9*getGlobalScale()), 0, 3 * getGlobalScale(), 3 * getGlobalScale());
        BufferedImage innerCorner = ExpandableTexture.getTexture().getSubimage(19,13,3,3);
        g.setColor(Color.BLACK);
        g.fillRect(getTargetWidth() - (3 * getGlobalScale()), 9 * getGlobalScale(),3 * getGlobalScale(),3 * getGlobalScale());
        g.drawImage(outerCorner, getTargetWidth() - (3 * getGlobalScale()), 9 * getGlobalScale(), null);
        g.fillRect(getTargetWidth() - (getNumButtons() * getButtonSquareSize()) - (3 * getGlobalScale()), 0,3 * getGlobalScale(),3 * getGlobalScale());
        g.drawImage(outerCorner, getTargetWidth() - (getNumButtons() * getButtonSquareSize()) - (3 * getGlobalScale()), 0,null);
        g.drawImage(innerCorner, getTargetWidth() - (getNumButtons() * getButtonSquareSize()) - (3 * getGlobalScale()),9 * getGlobalScale(),3 * getGlobalScale(),3 * getGlobalScale(),null);
        g.drawImage(image.getSubimage(1+(9*getGlobalScale()),3* getGlobalScale(),3* getGlobalScale(),getGlobalScale()), getTargetWidth() - (getNumButtons() * getButtonSquareSize()) - (3 * getGlobalScale()),3 * getGlobalScale(), 3 * getGlobalScale(), 6 * getGlobalScale(),null);
        g.drawImage(image.getSubimage(1+(8*getGlobalScale()),0,getGlobalScale(),3* getGlobalScale()), getTargetWidth() - (getNumButtons() * getButtonSquareSize()),9 * getGlobalScale(),(getNumButtons() * getButtonSquareSize()) - 3 * getGlobalScale(),3 * getGlobalScale(),null);
        g.setColor(ExpandableTexture.getCenterColor(McComponentTextureEnum.PANEL_THIN));
        g.fillRect(4 * getGlobalScale(), 13 * getGlobalScale(), getTargetWidth() - (8 * getGlobalScale()), getTargetHeight() - (13 * getGlobalScale()));
    }
    
    default LayoutManager createLayoutManager(){
        return new LayoutManager() {
            @Override
            public void addLayoutComponent(String name, Component comp) {
                getAdditionalButtons().add((JButton) comp);
            }

            @Override
            public void removeLayoutComponent(Component comp) {
                getAdditionalButtons().remove((JButton) comp);
            }

            @Override
            public Dimension preferredLayoutSize(Container c) {
                return minimumLayoutSize(c);
            }

            @Override
            public Dimension minimumLayoutSize(Container c) {
                // Calculate width.
                int width = getNumButtons() * getButtonSquareSize();
                width += getTitleLabel().getMinimumSize().width;
                int height = 15 * getGlobalScale();

                return new Dimension(width, height);
            }

            @Override
            public void layoutContainer(Container c) {
                int w = getTargetWidth();
                final int[] x = new int[1];
                int y = 4 * getGlobalScale();

                int buttonSize = getButtonSquareSize();

                Icon icon = getFrameIcon();
                int iconHeight = 0;
                if (icon != null) {
                    iconHeight = icon.getIconHeight();
                }

                x[0] = 4 * getGlobalScale();
                if (getMenuBar() != null) getMenuBar().setBounds(x[0], y, buttonSize, buttonSize);

                //TODO: Text anzeigen
                x[0] += buttonSize;
                if (getTitleLabel() != null) getTitleLabel().setBounds(x[0],y, getTitleLabel().getMinimumSize().width,buttonSize);

                x[0] = w - buttonSize;

                if (isClosable()) {
                    getCloseButton().setBounds(x[0], 0, buttonSize, buttonSize);
                    x[0] -= buttonSize;
                }

                if (isMaximizable()) {
                    getMaxButton().setBounds(x[0], 0, buttonSize, buttonSize);
                    x[0] -= buttonSize;
                }

                if (isIconifiable()) {
                    getIconButton().setBounds(x[0], 0, buttonSize, buttonSize);
                    x[0] -= buttonSize;
                }

                getAdditionalButtons().forEach(jButton -> {
                    jButton.setBounds(x[0], 0, buttonSize, buttonSize);
                    x[0] -= buttonSize;
                });
            }
        };
    }

    //Getter
    default int getButtonSquareSize(){
        return 9 * getGlobalScale();
    }

    default int getNumButtons(){
        int rv = 0;
        if (isClosable()) rv++;
        if (isMaximizable()) rv++;
        if (isIconifiable()) rv++;
        rv += getAdditionalButtons().size();
        return rv;
    }
    
    ////Abstrakt
    ArrayList<JButton> getAdditionalButtons();
    boolean isClosable();
    boolean isMaximizable();
    boolean isIconifiable();
    int getTargetWidth();
    default int getTargetHeight(){
        return 15 * ExpandableTexture.getGlobalScale();
    }
    JButton getCloseButton();
    JButton getMaxButton();
    JButton getIconButton();
    Icon getFrameIcon();
    JMenuBar getMenuBar();
    JLabel getTitleLabel();

    //Setter

    //Maker

    //Overrides aus
    ////<Oberklasse>

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
