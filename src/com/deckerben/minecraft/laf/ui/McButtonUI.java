package com.deckerben.minecraft.laf.ui;

import com.deckerben.minecraft.laf.DrawSettings;
import com.deckerben.minecraft.laf.ExpandableTexture;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.util.ArrayList;

public class McButtonUI extends BasicButtonUI {

    //Felder
    private boolean isAlreadyRollover = false;
    private final ExpandableTexture texture = new ExpandableTexture(McComponentTextureEnum.BUTTON);

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
        ButtonModel model = ((AbstractButton)c).getModel();
        DrawSettings holeSetting = DrawSettings.NOT_SET;
        if (!c.isOpaque()) holeSetting = DrawSettings.HOLES_TRANSPARENT;
        if (model.isEnabled()) {
            if (model.isRollover()||model.isPressed()) {
                if (model.isPressed()) {
                    //pressed
                    texture.setTexType(McComponentTextureEnum.BUTTON_PRESSED);
                }   else {
                    //hovered
                    texture.setTexType(McComponentTextureEnum.BUTTON_HOVERED);
                }
            }   else {
                //normal
                texture.setTexType(McComponentTextureEnum.BUTTON);
            }
        }   else {
            //disabled
            texture.setTexType(McComponentTextureEnum.BUTTON_DISABLED);
        }
        texture.paintTexture(g,new Rectangle(c.getSize()),Color.BLACK, holeSetting);
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
