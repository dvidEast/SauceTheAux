package ui.gui.tabs;

import ui.gui.SauceTheAux;
import ui.gui.ButtonNames;

import javax.swing.*;
import java.awt.*;

// Creates the home tab to greet users (reference from SmartHome)
public class HomeTab extends Tab {
    private static final String INIT_GREETING = "Click button below to save your work!";
    private JLabel greeting;

    // EFFECTS: constructs home tab with grid layout
    public HomeTab(SauceTheAux controller) {
        super(controller);

        setLayout(new GridLayout(3, 1));

        placeGreeting();
        placeHomeButtons();
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    //EFFECTS: creates load and save buttons
    private void placeHomeButtons() {
        JButton saveButton = new JButton(ButtonNames.SAVE.getValue());

        JPanel buttonRow = formatButtonRow(saveButton);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        saveButton.addActionListener(e -> {
            greeting.setText("Data saved!");
            getController().saveSongs();
            getController().saveGenres();
            getController().savePlaylists();
        });

        this.add(buttonRow);
    }
}
