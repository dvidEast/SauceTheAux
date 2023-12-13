package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistTest {
    private Playlist testPlaylist;
    private SongCollection sc0;
    private SongCollection sc1;
    private SongCollection sc2;
    private SongCollection sc3;
    private Song s1;
    private Song s2;
    private Song s3;
    private Song s4;
    private Genre g1;
    private Genre g2;
    private Genre g3;

    @BeforeEach
    void runBefore() {
        g1 = new Genre("Pop");
        g2 = new Genre("Rock");
        g3 = new Genre("K-pop");
        s1 = new Song("Counting Stars", "OneRepublic", g1);
        s2 = new Song("Paradise City", "Guns N' Roses", g2);
        s3 = new Song("Dynamite", "BTS", g3);
        s4 = new Song("Whistle", "Black Pink", g3);

        sc0 = new SongCollection();

        sc1 = new SongCollection();
        sc1.addSong(s1);

        sc2 = new SongCollection();
        sc2.addSong(s1);
        sc2.addSong(s2);

        sc3 = new SongCollection();
        sc3.addSong(s1);
        sc3.addSong(s2);
        sc3.addSong(s3);
        sc3.addSong(s4);
    }

    @Test
    void testConstructorEmptySongCollection() {
        testPlaylist = new Playlist("Pop Playlist", sc0, g1);
        assertEquals(0, testPlaylist.getPlaylistSize());
        assertEquals("Pop Playlist", testPlaylist.getName());
        assertEquals(g1, testPlaylist.getGenre());
    }

    @Test
    void testConstructorOneSongWorks() {
        testPlaylist = new Playlist("Pop Playlist", sc1, g1);
        assertEquals(1, testPlaylist.getPlaylistSize());
        assertEquals("Pop Playlist", testPlaylist.getName());
        assertEquals(g1, testPlaylist.getGenre());
        assertEquals(s1, testPlaylist.getSongs().getSongs().get(0));
    }

    @Test
    void testConstructorNoSongsWork() {
        testPlaylist = new Playlist("Rock Playlist", sc1, g2);
        assertEquals(0, testPlaylist.getPlaylistSize());
        assertEquals("Rock Playlist", testPlaylist.getName());
        assertEquals(g2, testPlaylist.getGenre());
    }

    @Test
    void testConstructorMultipleSongsOneWorks() {
        testPlaylist = new Playlist("Rock Playlist", sc2, g2);
        assertEquals(1, testPlaylist.getPlaylistSize());
        assertEquals("Rock Playlist", testPlaylist.getName());
        assertEquals(g2, testPlaylist.getGenre());
        assertEquals(s2, testPlaylist.getSongs().getSongs().get(0));
    }

    @Test
    void testConstructorMultipleSongsWork() {
        testPlaylist = new Playlist("K-pop Playlist", sc3, g3);
        assertEquals(2, testPlaylist.getPlaylistSize());
        assertEquals("K-pop Playlist", testPlaylist.getName());
        assertEquals(g3, testPlaylist.getGenre());
        assertEquals(s3, testPlaylist.getSongs().getSongs().get(0));
        assertEquals(s4, testPlaylist.getSongs().getSongs().get(1));
    }
}