package ui.gui.tabs;

import model.Genre;
import model.GenreCollection;
import ui.gui.ButtonNames;
import ui.gui.SauceTheAux;

import javax.swing.*;
import java.awt.*;

// Creates the genre tab with its display model and adding features
public class GenreTab extends Tab {
    private static final String INIT_GREETING = "Create a new genre below!";
    private JLabel greeting;
    private DefaultListModel<String> listModel;
    private GenreCollection myGenres;

    public GenreTab(SauceTheAux controller) {
        super(controller);
        listModel = new DefaultListModel<>();
        myGenres = controller.getGenres();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        placeGreeting();
        displayGenrePane();
        placeGenreInputs();
    }

    // EFFECTS: creates greeting at the top of the console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    // EFFECTS: creates model to display genres of genre collection
    private void displayGenrePane() {
        JList<String> list = new JList<>(listModel);
        for (Genre g : myGenres.getGenres()) {
            listModel.addElement(g.getGenreName());
        }

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(200, 500));

        this.add(scrollPane);
    }

    // EFFECTS: creates button and text field to add genre
    private void placeGenreInputs() {
        JTextField genreNameInput = new JTextField("Enter New Genre Name");
        genreNameInput.setPreferredSize(new Dimension(200, 50));

        JButton createGenreButton = new JButton(ButtonNames.ADDGENRE.getValue());
        JPanel buttonRow = formatButtonRow(createGenreButton);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        createGenreButton.addActionListener(e -> {
            greeting.setText("Genre created!");
            String nameInput = genreNameInput.getText();

            // add genre to genre collection
            Genre newGenre = new Genre(nameInput);
            System.out.println(newGenre);
            getController().addGenre(newGenre);

            // Update the listModel with the new genre
            listModel.addElement(newGenre.getGenreName());
        });

        this.add(genreNameInput);
        this.add(buttonRow);
    }
}
