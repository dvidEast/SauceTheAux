package ui.gui.tabs;

import model.Genre;
import model.GenreCollection;
import model.Song;
import model.SongCollection;
import ui.gui.ButtonNames;
import ui.gui.SauceTheAux;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SongTab extends Tab {

    private static final String INIT_GREETING = "Create a new song below!";
    private JLabel greeting;

    private DefaultTableModel tableModel;
    private JTable table;
    private JComboBox<String> songGenreInput;

    private SongCollection mySongs;
    private GenreCollection myGenres;

    // EFFECTS: constructs song tab with grid layout
    public SongTab(SauceTheAux controller) {
        super(controller);
        mySongs = controller.getSongs();
        myGenres = controller.getGenres();

        tableModel = new DefaultTableModel(new Object[]{"Song Name", "Artist", "Genre"}, 0);
        table = new JTable(tableModel);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        placeGreeting();
        displaySongPane();
        placeSongInputs();
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    // EFFECTS: creates model to display genres of genre collection
    private void displaySongPane() {
        for (Song s : mySongs.getSongs()) {
            tableModel.addRow(new Object[]{s.getName(), s.getArtist(), s.getGenre().getGenreName()});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        add(scrollPane);
    }

    // EFFECTS: creates load and save buttons
    private void placeSongInputs() {
        songGenreInput = new JComboBox<String>(getChoices().toArray(new String[0]));

        JTextField songNameInput = new JTextField("Enter New Song Name");
        JTextField songArtistInput = new JTextField("Enter New Song Artist");
        songNameInput.setPreferredSize(new Dimension(200, 50));
        songArtistInput.setPreferredSize(new Dimension(200, 50));

        JButton createSongButton = new JButton(ButtonNames.ADDSONG.getValue());
        JPanel buttonRow = formatButtonRow(createSongButton);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        createButtonListener(createSongButton, songNameInput, songArtistInput, songGenreInput);

        this.add(songNameInput);
        this.add(songArtistInput);
        this.add(songGenreInput);
        this.add(buttonRow);
    }

    // EFFECTS: performs actions for create song button
    private void createButtonListener(JButton button, JTextField name, JTextField artist, JComboBox<String> genre) {
        button.addActionListener(e -> {
            greeting.setText("Song Created");
            String songName = name.getText();
            String songArtist = artist.getText();
            Genre songGenre = getChosenGenre((String) genre.getSelectedItem());

            Song newSong = new Song(songName, songArtist, songGenre);
            getController().addSong(newSong);

            tableModel.addRow(new Object[]{
                    newSong.getName(),
                    newSong.getArtist(),
                    newSong.getGenre().getGenreName()});
        });
    }


    // EFFECTS: creates drop-down menu for genre selection
    private Genre getChosenGenre(String choseGenre) {
        if (choseGenre != null) {
            return myGenres.findGenre(choseGenre);
        } else {
            return new Genre("NO GENRE SELECTED");
        }
    }

    // EFFECTS: return myGenres collection as strings for drop-down menu choices
    private List<String> getChoices() {
        List<String> choices = new ArrayList<>();
        for (Genre g : myGenres.getGenres()) {
            choices.add(g.getGenreName());
        }
        return choices;
    }

    // EFFECTS: update genre choices when myGenres change
    public void updateGenreChoices() {
        songGenreInput.setModel(new DefaultComboBoxModel<>(getChoices().toArray(new String[0])));
    }
}