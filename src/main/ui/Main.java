package ui;

import ui.gui.SauceTheAux;

import javax.swing.*;
import java.io.FileNotFoundException;

// Main class to run application and dictate persistence
public class Main {
    public static void main(String[] args) {
        int choice = JOptionPane.showConfirmDialog(
                null,
                "Do you want to load data?",
                "Startup Screen",
                JOptionPane.YES_NO_OPTION);

        // Check the user's choice
        if (choice == JOptionPane.YES_OPTION) {
            loadApp(true);
            System.out.println("Data loaded.");
        } else {
            loadApp(false);
            System.out.println("Data not loaded.");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates new app with gui; if input is true, loads collections, else loads new collections
    private static void loadApp(Boolean loadData) {
        System.out.println("Loading application...");

        try {
            new SauceTheAux(loadData);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: application cannot be run");
        }
    }
}