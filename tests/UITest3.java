import com.deckerben.minecraft.laf.McUtils;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class UITest3 {

    //Felder

    //Listener

    //Konstruktoren

    //Methoden
    public static void main(String[] args) {
        McUtils.installMinecraftUI();

        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500,500);
        Container con = frame.getContentPane();
        con.setLayout(null);

        JButton b1 = new JButton("TestText");
        b1.setBounds(10,10,400,150);
        con.add(b1);

        frame.setVisible(true);
    }

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////<Oberklasse>

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
