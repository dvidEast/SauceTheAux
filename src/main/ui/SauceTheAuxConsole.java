//package ui;
//
//import model.Song;
//import model.SongCollection;
//import model.Playlist;
//import model.PlaylistCollection;
//import model.Genre;
//import model.GenreCollection;
//
//import persistence.*;
//
//import java.io.FileNotFoundException;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//// Sauce the Aux playlist creator application
//public class SauceTheAuxConsole {
//    private Scanner input;
//
//    private GenreCollection myGenres;
//    private PlaylistCollection myPlaylists;
//    private SongCollection mySongs;
//
//    private static final String JSON_STORE_SONG = "./data/songs.json";
//    private static final String JSON_STORE_PLAYLIST = "./data/playlists.json";
//    private static final String JSON_STORE_GENRE = "./data/genres.json";
//    private JsonSongsWriter jsonSongsWriter;
//    private JsonPlaylistsWriter jsonPlaylistsWriter;
//    private JsonGenresWriter jsonGenresWriter;
//    private JsonSongsReader jsonSongsReader;
//    private JsonPlaylistsReader jsonPlaylistsReader;
//    private JsonGenresReader jsonGenresReader;
//
//
//    // EFFECTS: runs the playlist creator application
//    public SauceTheAuxConsole() throws FileNotFoundException {
//        input = new Scanner(System.in);
//        jsonSongsWriter = new JsonSongsWriter(JSON_STORE_SONG);
//        jsonPlaylistsWriter = new JsonPlaylistsWriter(JSON_STORE_PLAYLIST);
//        jsonGenresWriter = new JsonGenresWriter(JSON_STORE_GENRE);
//        jsonSongsReader = new JsonSongsReader(JSON_STORE_SONG);
//        jsonPlaylistsReader = new JsonPlaylistsReader(JSON_STORE_PLAYLIST);
//        jsonGenresReader = new JsonGenresReader(JSON_STORE_GENRE);
//        runPlaylistApp();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user input
//    private void runPlaylistApp() {
//        boolean running = true;
//        String command = null;
//
//        myGenres = new GenreCollection();
//        myPlaylists = new PlaylistCollection();
//        mySongs = new SongCollection();
//
//        while (running) {
//            displayMenu();
//            command = input.next();
//
//            if (command.equals("q")) {
//                running = false;
//            } else {
//                processMenuCommand(command);
//            }
//        }
//
//        System.out.println("\nThanks for choosing Sauce the AUX, goodbye!");
//    }
//
//    // EFFECTS: displays menu of options to user
//    private void displayMenu() {
//        System.out.println("\nMenu:");
//        System.out.println("\ts -> Add song");
//        System.out.println("\tp -> Add playlist");
//        System.out.println("\tg -> Add genre");
//        System.out.println("\tvs -> View All Songs");
//        System.out.println("\tvg -> View All Genres");
//        System.out.println("\tvp -> View All Playlists");
//        System.out.println("\tsv -> save work room to file");
//        System.out.println("\tld -> load work room from file");
//        System.out.println("\tq -> Quit");
//    }
//
//    //MODIFIES: this
//    //EFFECTS: process the user input
//    private void processMenuCommand(String command) {
//        if (command.equals("s")) {
//            createSong();
//        } else if (command.equals("p")) {
//            createPlaylist();
//        } else if (command.equals("g")) {
//            createGenre();
//        } else if (command.equals("vs")) {
//            viewAllSong();
//        } else if (command.equals("vg")) {
//            viewAllGenre();
//        } else if (command.equals("vp")) {
//            viewAllPlaylist();
//        } else if (command.equals("sv")) {
//            saveSongs();
//            savePlaylists();
//            saveGenres();
//        } else if (command.equals("ld")) {
//            loadSongs();
//            loadPlaylists();
//            loadGenres();
//        } else {
//            System.out.println(command + " is not a valid input.");
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: creates a song and adds to mySongs
//    private void createSong() {
//        String songName;
//        String songArtist;
//        String songGenreString;
//
//        System.out.println("Type in your songs name: ");
//        songName = input.next();
//
//        System.out.println("Type in the artist of your song: ");
//        songArtist = input.next();
//
//        if (myGenres.getGenres().size() == 0) {
//            System.out.println("You don't have any genres!");
//        } else {
//            System.out.println("Type in a genre from the list below or create one in the previous menu");
//            viewAllGenre();
//            songGenreString = input.next();
//            completeSongCreation(songName, songArtist, songGenreString);
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: creates a genre and adds to myGenres
//    private void createGenre() {
//        String genreName;
//        Genre newGenre;
//        System.out.println("Type in the name of your new genre: ");
//        genreName = input.next();
//
//        newGenre = new Genre(genreName);
//        myGenres.addGenre(newGenre);
//    }
//
//    //MODIFIES: this
//    //EFFECTS: creates a playlist and adds to myPlaylists
//    private void createPlaylist() {
//        System.out.println("Type the name of your new playlist");
//        String playlistName = input.next();
//        System.out.println("Please select a genre to create from: ");
//        viewAllGenre();
//        String playlistGenreString = input.next();
//
//        Genre selectedGenre = myGenres.findGenre(playlistGenreString);
//
//        if (selectedGenre != null) {
//            Playlist createdPlaylist = new Playlist(playlistName, mySongs, selectedGenre);
//            myPlaylists.addPlaylist(createdPlaylist);
//            System.out.println("Your playlist has been created!");
//        } else {
//            System.out.println("That is not a valid genre...");
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: processes song creation with user inputs
//    private void completeSongCreation(String songName, String songArtist, String songGenreString) {
//        if (myGenres.findGenre(songGenreString) != null) {
//            Song createdSong = new Song(songName, songArtist, myGenres.findGenre(songGenreString));
//            mySongs.addSong(createdSong);
//            System.out.println("Your song has been added! Check the details below..,");
//            System.out.println("\tName: " + songName);
//            System.out.println("\tArtist: " + songArtist);
//            System.out.println("\tGenre: " + songGenreString);
//        } else {
//            System.out.println("That is not a valid genre...");
//        }
//    }
//
//    //EFFECTS: displays all genres in myGenres
//    private void viewAllGenre() {
//        System.out.println("\tGenres: ");
//        if (myGenres.getGenres().size() == 0) {
//            System.out.println("\t\tYou have no genres!");
//        } else {
//            for (Genre g : myGenres.getGenres()) {
//                System.out.println("\t\t" + g.getGenreName());
//            }
//        }
//    }
//
//    //EFFECTS: displays all songs in mySongs
//    private void viewAllSong() {
//        System.out.println("\n\tSongs: ");
//        if (mySongs.getSongCollectionSize() == 0) {
//            System.out.println("\t\tYou have no songs!");
//            System.out.println("\nEnter any key to return to Menu");
//            input.next();
//        } else {
//            for (Song s : mySongs.getSongs()) {
//                System.out.println("\t\t" +
//                s.getSongName() + " " +
//                s.getSongArtist() + " " +
//                s.getGenre().getGenreName());
//            }
//        }
//    }
//
//    //EFFECTS: displays all created playlists
//    private void viewAllPlaylist() {
//        System.out.println("\n\tPlease select a playlist to view:");
//        if (myPlaylists.getPlaylistCollectionSize() == 0) {
//            System.out.println("\tYou have no playlists!");
//            System.out.println("\nEnter any key to return to Menu");
//            input.next();
//        } else {
//            for (Playlist p : myPlaylists.getPlaylistCollection()) {
//                System.out.println("\t\t" + p.getName());
//            }
//
////            createPlaylistOptions();
//        }
//    }
//
////    //MODIFIES: this
////    //EFFECTS: creates options of playlists of myPlaylist for viewing selection
////    private void createPlaylistOptions() {
////
////    }
////
////    //EFFECTS: displays songs in selected playlist
////    private void viewPlaylist(Playlist playlist) {
////
////    }
//
//    //EFFECTS: saves mySongs to file
//    public void saveSongs() {
//        try {
//            jsonSongsWriter.open();
//            jsonSongsWriter.writeSongCollection(mySongs);
//            jsonSongsWriter.close();
//            System.out.println("Saved songs to: " + JSON_STORE_SONG);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE_SONG);
//        }
//    }
//
//    //EFFECTS: saves myPlaylists to file
//    public void savePlaylists() {
//        try {
//            jsonPlaylistsWriter.open();
//            jsonPlaylistsWriter.writePlaylistCollection(myPlaylists);
//            jsonPlaylistsWriter.close();
//            System.out.println("Saved playlists to: " + JSON_STORE_PLAYLIST);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE_PLAYLIST);
//        }
//    }
//
//    //EFFECTS: saves myGenres to file
//    public void saveGenres() {
//        try {
//            jsonGenresWriter.open();
//            jsonGenresWriter.writeGenreCollection(myGenres);
//            jsonGenresWriter.close();
//            System.out.println("Saved genres to: " + JSON_STORE_GENRE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE_GENRE);
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: loads songs from file
//    public void loadSongs() {
//        try {
//            mySongs = jsonSongsReader.readSongCollection();
//            System.out.println("Loaded song collection from: " + JSON_STORE_SONG);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE_SONG);
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: loads playlists from file
//    public void loadPlaylists() {
//        try {
//            myPlaylists = jsonPlaylistsReader.readPlaylistCollection();
//            System.out.println("Loaded playlist collection from: " + JSON_STORE_PLAYLIST);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE_PLAYLIST);
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: loads genres from file
//    public void loadGenres() {
//        try {
//            myGenres = jsonGenresReader.readGenreCollection();
//            System.out.println("Loaded genre collection from: " + JSON_STORE_GENRE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE_GENRE);
//        }
//    }
//}