import com.deckerben.minecraft.laf.DrawSettings;
import com.deckerben.minecraft.laf.ExpandableTexture;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;
import sun.awt.image.ToolkitImage;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ScaleTest {

    //Felder

    //Listener

    //Konstruktoren

    //Methoden
    public static void main(String[] args) {
        ExpandableTexture et = new ExpandableTexture(McComponentTextureEnum.PANEL_THICK);
        BufferedImage b = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
        et.paintTexture(b.getGraphics(),new Rectangle(0,0,b.getWidth(),b.getHeight()),Color.GREEN, DrawSettings.NOT_SET);
        b = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
        et.paintTexture(b.getGraphics(),new Rectangle(0,0,b.getWidth(),b.getHeight()),Color.BLACK, DrawSettings.NOT_SET);
        b = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
        et.paintTexture(b.getGraphics(),new Rectangle(0,0,b.getWidth(),b.getHeight()),Color.BLACK, DrawSettings.JUST_ES);
        b = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
        et.paintTexture(b.getGraphics(),new Rectangle(0,0,b.getWidth(),b.getHeight()),Color.BLACK, DrawSettings.NO_ES);
        b = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
        et.paintTexture(b.getGraphics(),new Rectangle(0,0,b.getWidth(),b.getHeight()),Color.BLACK, DrawSettings.JUST_ES);
        b = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
        et.paintTexture(b.getGraphics(),new Rectangle(0,0,b.getWidth(),b.getHeight()),Color.BLACK, DrawSettings.NO_ES);
    }

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////<Oberklasse>

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
