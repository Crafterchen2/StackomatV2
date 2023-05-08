import com.deckerben.minecraft.laf.McUtils;

import javax.swing.*;

public class UITest2 {

    //Felder

    //Listener

    //Konstruktoren

    //Methoden
    public static void main(String[] args) {
        McUtils.installMinecraftUI();
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame();
        frame.setContentPane(new JButton("ContentButton"));
        frame.setSize(800,800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
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
