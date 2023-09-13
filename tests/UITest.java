import com.deckerben.minecraft.laf.McUtils;
import com.deckerben.minecraft.laf.ui.McInternalFrameUI;
import com.deckerben.stackomat.components.StackomatPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UITest {

    //Felder

    //Listener

    //Konstruktoren

    //Methoden
    public static void  main(String[] args){

        McUtils.installMinecraftUI();
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame();
        //frame.setLayout(new BorderLayout());
        StackomatPanel stackomatPanel = new StackomatPanel();
        JInternalFrame internalFrame = new JInternalFrame("Stackomat",true,true,true,true);
        internalFrame.setLayout(new GridLayout(1,1));
        internalFrame.add(stackomatPanel);
        internalFrame.setVisible(true);
        JButton button = new JButton();
        button.addActionListener(e -> System.out.println("pressed"));
        McInternalFrameUI mcInternalFrameUI = new McInternalFrameUI(internalFrame,button);
        internalFrame.setUI(mcInternalFrameUI);
        JPanel panel = new JPanel(new FlowLayout());
        JButton panelButton = new JButton("srcContentButton");
        panelButton.addActionListener(e -> {
            panel.add(new JButton("genButton3"));
            frame.getRootPane().updateUI();
        });
        panel.add(panelButton);
        frame.setContentPane(new JButton("ContentButton"));
        //frame.add(new JButton("Hallo"));
        frame.setSize(800,800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        //frame.setVisible(true);
        //frame.setAlwaysOnTop(true);
        //SwingUtilities.updateComponentTreeUI(frame);
        //ExpandableBorder.setGlobalScale(6);

        JFrame inFrame = new JFrame();
        inFrame.setContentPane(new JDesktopPane());
        JInternalFrame internal = new JInternalFrame("Ein Titel");
        internal.setBounds(50,50,700,700);
        inFrame.setSize(800,800);
        internal.setClosable(true);
        internal.setIconifiable(true);
        internal.setMaximizable(true);
        internal.setResizable(true);
        inFrame.setLocationRelativeTo(null);
        inFrame.add(internal);
        inFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inFrame.setResizable(true);
        inFrame.setVisible(true);
        internal.setVisible(true);

        System.out.println("allDone");
    }

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////<Oberklasse>

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
