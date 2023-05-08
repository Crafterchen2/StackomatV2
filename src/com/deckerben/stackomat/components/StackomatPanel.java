package com.deckerben.stackomat.components;

import com.deckerben.minecraft.laf.ExpandableTexture;
import com.deckerben.minecraft.laf.textures.McComponentTextureEnum;

import javax.swing.*;
import java.awt.*;

public class StackomatPanel extends JPanel {

    private ExpandableTexture border;

    public StackomatPanel(){

        ly();
        //init();
    }

    private void sds(){
        setLayout(new BorderLayout());
        add(new UnitChooser(),BorderLayout.NORTH);
        JPanel divPanel = new JPanel(new GridLayout(1,2));
        JPanel inPanel = new JPanel(new BorderLayout());
        inPanel.add(new InputPanel(), BorderLayout.NORTH);
        divPanel.add(inPanel);
        JPanel outPanel = new JPanel(new BorderLayout());
        outPanel.add(new OutputPanel(),BorderLayout.NORTH);
        outPanel.add(new DistributionPanel(),BorderLayout.CENTER);
        divPanel.add(outPanel);
        add(divPanel,BorderLayout.CENTER);
    }

    private void ly(){
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);

        //c.insets = new Insets(-2*ExpandableTexture.getGlobalScale(),-2*ExpandableTexture.getGlobalScale(),-2*ExpandableTexture.getGlobalScale(),-2*ExpandableTexture.getGlobalScale());
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        addComponent(new UnitChooser(), gridbag, c); //another row

        c.ipady = 0;
        c.gridwidth = 1;                //reset to the default
        c.gridheight = 2;
        c.weighty = 0.0;
        addComponent(new InputPanel(), gridbag, c);

        c.gridwidth = GridBagConstraints.REMAINDER;//end row
        c.gridheight = 1;//reset to the default
        addComponent(new OutputPanel(), gridbag, c);
        addComponent(new DistributionPanel(), gridbag, c);
    }

    public void init() {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        makebutton("Button5", gridbag, c); //another row

        c.gridwidth = 1;                //reset to the default
        c.gridheight = 2;
        c.weighty = 0.0;
        makebutton("Button8", gridbag, c);

        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        c.gridheight = 1;               //reset to the default
        makebutton("Button9", gridbag, c);
        makebutton("Button10", gridbag, c);
    }

    private void addComponent(Component com, GridBagLayout gridbag, GridBagConstraints c){
        gridbag.setConstraints(com, c);
        add(com);
    }

    protected void makebutton(String name, GridBagLayout gridbag, GridBagConstraints c) {
        Button button = new Button(name);
        gridbag.setConstraints(button, c);
        add(button);
    }

    //Overrides aus
    ////JComponent
    /*
    protected void paintBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(getBackground());
        Rectangle interiorRect = border.getInteriorRectangle(this,0,0,getWidth(),getHeight());
        g.fillRect(interiorRect.x-2*ExpandableTexture.getGlobalScale(),interiorRect.y-2*ExpandableTexture.getGlobalScale(),interiorRect.width+4*ExpandableTexture.getGlobalScale(),interiorRect.height+4*ExpandableTexture.getGlobalScale());
        super.paintBorder(g);
    }
     */
}
