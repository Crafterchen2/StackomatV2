package com.deckerben.stackomat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public interface UnitEnumInterface {

    String imagePath = "/com/deckerben/stackomat/assets/UnitIcons.png";

    default BufferedImage createImageIcon(Rectangle iconLoc){
        try {
            return ImageIO.read(Objects.requireNonNull(UnitEnumInterface.class.getResourceAsStream(imagePath))).getSubimage(iconLoc.x,iconLoc.y,iconLoc.width,iconLoc.height);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    int getAmount();
    String getName();
    BufferedImage getImage();

}
