package persistence;

// Names for JSON keys, GENRE_NAME same for all uses
public enum JsonKeyNames {
    SONGS("songs"),
    GENRES("genres"),
    PLAYLISTS("playlists"),
    SONG_NAME("songName"),
    SONG_ARTIST("songArtist"),
    SONG_GENRE("songGenre"),
    GENRE_NAME("genreName"),
    PLAYLIST_NAME("playlistName"),
    PLAYLIST_GENRE("playlistGenre");

    private final String name;

    // EFFECTS: sets enumeration key's name to this name
    JsonKeyNames(String name) {
        this.name = name;
    }

    // EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
