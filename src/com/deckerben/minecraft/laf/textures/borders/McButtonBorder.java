package com.deckerben.minecraft.laf.textures.borders;

import com.deckerben.minecraft.laf.DrawSettings;
import com.deckerben.minecraft.laf.ExpandableTexture;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;

import javax.swing.*;
import java.awt.*;

public abstract class McButtonBorder extends ExpandableTexture {

    //Felder

    //Listener

    //Konstruktoren

    public McButtonBorder(boolean addToWatchList) {
        super(addToWatchList);
    }

    //Methoden

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////ExpandableTexture

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        AbstractButton button = (AbstractButton)c;
        ButtonModel model = button.getModel();
        boolean isTogglePressed = button instanceof JToggleButton && button.isSelected();
        Rectangle size = new Rectangle(x,y,width,height);
        if (model.isEnabled()) {
            if (model.isRollover()||model.isPressed() || isTogglePressed) {
                if (model.isPressed() || isTogglePressed) {
                    if (isTogglePressed) {
                        if (model.isRollover()) {
                            //selected & hovered
                            ExpandableTexture.paintTexture(g, size, McComponentTextureEnum.TOGGLEBUTTON_SELECTED_HOVERED, DrawSettings.FORCE_PAINTING, DrawSettings.JUST_OUTER);
                        }   else {
                            //selected
                            ExpandableTexture.paintTexture(g, size, McComponentTextureEnum.TOGGLEBUTTON_SELECTED, DrawSettings.FORCE_PAINTING, DrawSettings.JUST_OUTER);
                        }
                    }   else {
                        //pressed
                        ExpandableTexture.paintTexture(g, size, McComponentTextureEnum.BUTTON_PRESSED, DrawSettings.FORCE_PAINTING, DrawSettings.JUST_OUTER);
                    }
                }   else {
                    //hovered
                    ExpandableTexture.paintTexture(g, size, McComponentTextureEnum.BUTTON_HOVERED, DrawSettings.FORCE_PAINTING, DrawSettings.JUST_OUTER);

                }
            }   else {
                //normal
                ExpandableTexture.paintTexture(g, size, McComponentTextureEnum.BUTTON, DrawSettings.FORCE_PAINTING, DrawSettings.JUST_OUTER);
            }
        }   else {
            //disabled
            ExpandableTexture.paintTexture(g, size, McComponentTextureEnum.BUTTON_DISABLED, DrawSettings.FORCE_PAINTING, DrawSettings.JUST_OUTER);
        }
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
