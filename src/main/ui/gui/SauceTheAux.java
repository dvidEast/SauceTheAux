package ui.gui;

import model.*;
import persistence.*;
import ui.gui.tabs.GenreTab;
import ui.gui.tabs.HomeTab;
import ui.gui.tabs.PlaylistTab;
import ui.gui.tabs.SongTab;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// GUI for Sauce the AUX playlist creator application
// Tabs referenced from SmartHomeUI
public class SauceTheAux extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private JTabbedPane sidebar;
    private SongTab songTab;
    private PlaylistTab playlistTab;

    private GenreCollection myGenres;
    private PlaylistCollection myPlaylists;
    private SongCollection mySongs;

    private static final String JSON_STORE_SONG = "./data/songs.json";
    private static final String JSON_STORE_PLAYLIST = "./data/playlists.json";
    private static final String JSON_STORE_GENRE = "./data/genres.json";
    private JsonSongsReader jsonSongsReader;
    private JsonPlaylistsReader jsonPlaylistsReader;
    private JsonGenresReader jsonGenresReader;
    private JsonSongsWriter jsonSongsWriter;
    private JsonPlaylistsWriter jsonPlaylistsWriter;
    private JsonGenresWriter jsonGenresWriter;

    // MODIFIES: this
    // EFFECTS: creates song, genre, and playlist collections, displays sidebar and tabs
    public SauceTheAux(Boolean load) throws FileNotFoundException {
        super("Sauce The AUX");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.BOTTOM);

        myGenres = new GenreCollection();
        myPlaylists = new PlaylistCollection();
        mySongs = new SongCollection();

        jsonSongsReader = new JsonSongsReader(JSON_STORE_SONG);
        jsonPlaylistsReader = new JsonPlaylistsReader(JSON_STORE_PLAYLIST);
        jsonGenresReader = new JsonGenresReader(JSON_STORE_GENRE);
        jsonSongsWriter = new JsonSongsWriter(JSON_STORE_SONG);
        jsonPlaylistsWriter = new JsonPlaylistsWriter(JSON_STORE_PLAYLIST);
        jsonGenresWriter = new JsonGenresWriter(JSON_STORE_GENRE);

        if (load) {
            loadSongs();
            loadPlaylists();
            loadGenres();
        }

        loadTabs();
        add(sidebar);
        setVisible(true);
        promptSaveOnClose();
    }

    public void promptSaveOnClose() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                        SauceTheAux.this,
                        "Do you want to save your data?",
                        "Save Data",
                        JOptionPane.YES_NO_CANCEL_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    System.out.println("Data saved!");
                    saveSongs();
                    saveGenres();
                    savePlaylists();

                    printLog();
                    System.exit(0);
                } else if (choice == JOptionPane.NO_OPTION) {

                    printLog();
                    System.exit(0);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds songs tab, genres tab, and playlist with their respective saved JSON to this UI
    public void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        songTab = new SongTab(this);
        JPanel genreTab = new GenreTab(this);
        playlistTab = new PlaylistTab(this);

        sidebar.add(homeTab, 0);
        sidebar.setTitleAt(0, "Save");
        sidebar.add(songTab, 1);
        sidebar.setTitleAt(1, "Songs");
        sidebar.add(genreTab, 2);
        sidebar.setTitleAt(2, "Genres");
        sidebar.add(playlistTab, 3);
        sidebar.setTitleAt(3, "Playlists");
    }

    // MODIFIES: this
    // EFFECTS: adds genre to myGenres
    public void addSong(Song song) {
        mySongs.addSong(song);
    }

    // MODIFIES: this
    // EFFECTS: adds genre to myGenres
    public void addGenre(Genre genre) {
        myGenres.addGenre(genre);
        songTab.updateGenreChoices();
        playlistTab.updateGenreChoices();
    }

    // MODIFIES: this
    // EFFECTS: adds genre to myGenres
    public void addPlaylist(Playlist playlist) {
        myPlaylists.addPlaylist(playlist);
    }

    // EFFECTS: returns list of songs
    public SongCollection getSongs() {
        return mySongs;
    }

    // EFFECTS: returns list of genres
    public GenreCollection getGenres() {
        return myGenres;
    }

    // EFFECTS: returns list of playlists
    public PlaylistCollection getPlaylists() {
        return myPlaylists;
    }

    // MODIFIES: this
    // EFFECTS: loads songs from file
    public void loadSongs() {
        try {
            mySongs = jsonSongsReader.readSongCollection();
            System.out.println("Loaded song collection from: " + JSON_STORE_SONG);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_SONG);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads playlists from file
    public void loadPlaylists() {
        try {
            myPlaylists = jsonPlaylistsReader.readPlaylistCollection();
            System.out.println("Loaded playlist collection from: " + JSON_STORE_PLAYLIST);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_PLAYLIST);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads genres from file
    public void loadGenres() {
        try {
            myGenres = jsonGenresReader.readGenreCollection();
            System.out.println("Loaded genre collection from: " + JSON_STORE_GENRE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_GENRE);
        }
    }

    //EFFECTS: saves mySongs to file
    public void saveSongs() {
        try {
            jsonSongsWriter.open();
            jsonSongsWriter.writeSongCollection(mySongs);
            jsonSongsWriter.close();
            System.out.println("Saved songs to: " + JSON_STORE_SONG);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_SONG);
        }
    }

    //EFFECTS: saves myGenres to file
    public void saveGenres() {
        try {
            jsonGenresWriter.open();
            jsonGenresWriter.writeGenreCollection(myGenres);
            jsonGenresWriter.close();
            System.out.println("Saved genres to: " + JSON_STORE_GENRE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_GENRE);
        }
    }

    //EFFECTS: saves myPlaylists to file
    public void savePlaylists() {
        try {
            jsonPlaylistsWriter.open();
            jsonPlaylistsWriter.writePlaylistCollection(myPlaylists);
            jsonPlaylistsWriter.close();
            System.out.println("Saved playlists to: " + JSON_STORE_PLAYLIST);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_PLAYLIST);
        }
    }

    //EFFECTS:
    public void printLog() {
        EventLog events = EventLog.getInstance();

        for (Event e : events) {
            System.out.println(e.getDate() + ": " + e.getDescription());
        }
    }
}
