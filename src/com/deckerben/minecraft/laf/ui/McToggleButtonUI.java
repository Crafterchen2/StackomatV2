package com.deckerben.minecraft.laf.ui;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToggleButtonUI;
import java.awt.*;

public class McToggleButtonUI extends BasicToggleButtonUI {

    //Felder
    private boolean isAlreadyRollover = false;

    //Listener

    //Konstruktoren
    public McToggleButtonUI(){

    }

    //Methoden
    public static ComponentUI createUI(JComponent c) {
        return new McToggleButtonUI();
    }

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////ComponentUI
    @Override
    public void update(Graphics g, JComponent c) {
        if (c.isOpaque()) {
            if (c.isEnabled()) {
                if (((JToggleButton)c).isSelected()) {
                    g.setColor(UIManager.getColor("ToggleButton.selectedBackground"));
                }   else {
                    g.setColor(UIManager.getColor("ToggleButton.background"));
                }
            } else {
                g.setColor(UIManager.getColor("ToggleButton.disabledBackground"));
            }
            g.fillRect(0, 0, c.getWidth(),c.getHeight());
        }
        paint(g, c);
    }

    ////BasicButtonUI
    @Override
    protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
        if (b.isEnabled()) g.setColor(UIManager.getColor("controlText")); else g.setColor(UIManager.getColor("controlTextDisabled"));
        g.drawString(text,textRect.x, (int) (textRect.y+textRect.height-b.getFont().getLineMetrics(text,g.getFontMetrics().getFontRenderContext()).getDescent()));
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        isAlreadyRollover = ((JToggleButton)c).isRolloverEnabled();
        ((JToggleButton)c).setRolloverEnabled(true);
    }

    @Override
    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        ((JToggleButton)c).setRolloverEnabled(isAlreadyRollover);
    }

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
