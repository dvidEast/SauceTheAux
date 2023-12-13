package ui.gui.tabs;

import model.*;
import ui.gui.ButtonNames;
import ui.gui.SauceTheAux;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Playlist tab for creating and viewing playlists
public class PlaylistTab extends Tab {
    private static final String INIT_GREETING = "Create a new genre below!";
    private JLabel greeting;

    private DefaultTableModel tableModel;
    private JTable table;
    private JComboBox<String> playlistGenreInput;

    private PlaylistCollection myPlaylists;
    private GenreCollection myGenres;
    private SongCollection mySongs;

    // EFFECTS: constructs playlist tab with grid layout
    public PlaylistTab(SauceTheAux controller) {
        super(controller);
        myPlaylists = controller.getPlaylists();
        mySongs = controller.getSongs();
        myGenres = controller.getGenres();

        tableModel = new DefaultTableModel(new Object[]{"Playlist Name", "Genre", "Songs"}, 0);
        table = new JTable(tableModel);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        placeGreeting();
        displayGenrePane();
        placePlaylistInputs();
    }

    // EFFECTS: creates greeting at the top of the console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    // EFFECTS: creates model to display genres of genre collection
    private void displayGenrePane() {
        for (Playlist p : myPlaylists.getPlaylistCollection()) {
            tableModel.addRow(new Object[]{
                    p.getName(),
                    p.getGenre().getGenreName(),
                    songsToString(p.getSongs().getSongs())
            });
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        this.add(scrollPane);
    }

    // EFFECTS: creates load and save buttons
    private void placePlaylistInputs() {
        playlistGenreInput = new JComboBox<>(getChoices().toArray(new String[0]));

        JTextField playlistNameInput = new JTextField("Enter New Playlist Name");
        playlistNameInput.setPreferredSize(new Dimension(300, 100));

        JButton createPlaylistButton = new JButton();

        ImageIcon icon = new ImageIcon("./src/main/ui/gui/create_icon.png");
        createPlaylistButton.setIcon(icon);

        JPanel buttonRow = formatButtonRow(createPlaylistButton);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        createButtonListener(createPlaylistButton, playlistNameInput, playlistGenreInput);

        this.add(playlistNameInput);
        this.add(playlistGenreInput);
        this.add(buttonRow);
    }

    // EFFECTS: returns songs as a single string to display to user
    private String songsToString(List<Song> songs) {
        String songsAsString = "";
        for (Song s : songs) {
            songsAsString = songsAsString + s.getName() + ", ";
        }
        System.out.println(songsAsString);
        return songsAsString;
    }

    // EFFECTS: performs actions for create song button
    private void createButtonListener(JButton button, JTextField name, JComboBox<String> genre) {
        button.addActionListener(e -> {
            greeting.setText("Playlist Created!");
            String playlistName = name.getText();
            Genre playlistGenre = getChosenGenre((String) genre.getSelectedItem());

            Playlist newPlaylist = new Playlist(playlistName, mySongs, playlistGenre);
            getController().addPlaylist(newPlaylist);

            tableModel.addRow(new Object[]{
                    newPlaylist.getName(),
                    newPlaylist.getGenre().getGenreName(),
                    songsToString(newPlaylist.getSongs().getSongs())});
        });
    }

    // REQUIRES: must select a genre
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
        playlistGenreInput.setModel(new DefaultComboBoxModel<>(getChoices().toArray(new String[0])));
    }
}
