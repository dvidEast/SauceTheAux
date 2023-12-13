package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistCollectionTest {
    private PlaylistCollection testPlaylistCollection;
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
    private Playlist p1;
    private Playlist p2;
    private Playlist p3;
    private Playlist p4;

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

        p1 = new Playlist("P1", sc0, g1);
        p2 = new Playlist("P2", sc1, g1);
        p3 = new Playlist("P3", sc2, g2);
        p4 = new Playlist("P4", sc3, g3);

        testPlaylistCollection = new PlaylistCollection();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testPlaylistCollection.getPlaylistCollectionSize());
    }

    @Test
    void testAddPlaylistOnePlaylist() {
        testPlaylistCollection.addPlaylist(p1);
        assertEquals(1, testPlaylistCollection.getPlaylistCollectionSize());
        assertEquals(p1, testPlaylistCollection.getPlaylistCollection().get(0));
    }

    @Test
    void testAddPlaylistMultiplePlaylist () {
        testPlaylistCollection.addPlaylist(p1);
        testPlaylistCollection.addPlaylist(p2);
        testPlaylistCollection.addPlaylist(p3);
        assertEquals(3, testPlaylistCollection.getPlaylistCollectionSize());
        assertEquals(p2, testPlaylistCollection.getPlaylistCollection().get(1));
    }
}
