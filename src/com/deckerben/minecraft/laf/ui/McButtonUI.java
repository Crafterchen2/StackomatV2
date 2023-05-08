package com.deckerben.minecraft.laf.ui;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class McButtonUI extends BasicButtonUI {

    //Felder
    private boolean isAlreadyRollover = false;

    //Listener

    //Konstruktoren
    public McButtonUI(){

    }

    //Methoden
    public static ComponentUI createUI(JComponent c) {
        return new McButtonUI();
    }

    //Overrides aus
    ////ComponentUI
    @Override
    public void update(Graphics g, JComponent c) {
        if (c.isOpaque()) {
            if (c.isEnabled()) g.setColor(UIManager.getColor("Button.background"));
                else g.setColor(UIManager.getColor("Button.disabledBackground"));
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
        isAlreadyRollover = ((JButton)c).isRolloverEnabled();
        ((JButton)c).setRolloverEnabled(true);
    }

    @Override
    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        ((JButton)c).setRolloverEnabled(isAlreadyRollover);
    }

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
