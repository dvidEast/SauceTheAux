package ui.gui.tabs;

import ui.gui.SauceTheAux;

import javax.swing.*;
import java.awt.*;

public abstract class Tab extends JPanel {

    private final SauceTheAux controller;

    //REQUIRES: SauceTheAux controller that holds this tab
    public Tab(SauceTheAux controller) {
        this.controller = controller;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    //EFFECTS: returns the SauceTheAux controller for this tab
    public SauceTheAux getController()  {
        return controller;
    }
}
