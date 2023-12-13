package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongCollectionTest {

    private SongCollection testCollection;
    private Song s1;
    private Song s2;
    private Song s3;
    private Genre g1;
    private Genre g2;

    @BeforeEach
    void runBefore() {
        testCollection = new SongCollection();
        g1 = new Genre("Genre 1");
        g2 = new Genre("Genre 2");
        s1 = new Song("Song 1", "Artist 1", g1);
        s2 = new Song("Song 2", "Artist 2", g1);
        s3 = new Song("Song 3", "Artist 3", g2);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testCollection.getSongCollectionSize());
    }

    @Test
    void testAddSongOne() {
        testCollection.addSong(s1);
        assertEquals(1, testCollection.getSongCollectionSize());
        assertEquals(s1, testCollection.getSongs().get(0));
    }

    @Test
    void testAddSongMultiple() {
        testCollection.addSong(s1);
        testCollection.addSong(s2);
        testCollection.addSong(s3);
        assertEquals(s1, testCollection.getSongs().get(0));
        assertEquals(s2, testCollection.getSongs().get(1));
        assertEquals(s3, testCollection.getSongs().get(2));
        assertEquals(3, testCollection.getSongCollectionSize());
    }

//    @Test
//    void testGetSongsWithGenreSingle() {
//        testCollection.addSong(s1);
//        testCollection.filterSongs(g1);
//        List<Song> collection = testCollection.getSongs();
//        assertEquals(s1, collection.get(0));
//    }
//
//    @Test
//    void testGetSongsWithGenreMultiple() {
//        testCollection.addSong(s1);
//        testCollection.addSong(s2);
//        testCollection.filterSongs(g1);
//        List<Song> collection = testCollection.getSongs();
//        assertEquals(s1, collection.get(0));
//        assertEquals(s2, collection.get(1));
//    }
//
//    @Test
//    void testGetSongsWithGenreNone() {
//        testCollection.addSong(s1);
//        testCollection.filterSongs(g2);
//        List<Song> collection = testCollection.getSongs();
//        assertEquals(0, collection.size());
//    }
}
