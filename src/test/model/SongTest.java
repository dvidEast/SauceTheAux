package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SongTest {
    private Genre g1;
    private Genre g2;
    private Song s1;
    private Song s2;
    private Song s3;
    private Song s4;
    private Song s5;
    private Song s6;
    private Song s7;

    @BeforeEach
    void runBefore() {
        g1 = new Genre("g1");
        g2 = new Genre("g2");
        s1 = new Song("s1", "a1", g1);
        s2 = new Song("s1", "a1", g2);
        s3 = new Song("s1", "a2", g2);
        s4 = new Song("s2", "a1", g1);
        s5 = new Song("s2", "a1", g2);
        s6 = new Song("s2", "a2", g1);
        s7 = new Song("s2", "a2", g2);
    }

    @Test
    void testConstructor() {
        assertEquals("s1", s1.getName());
        assertEquals("a1", s1.getArtist());
        assertEquals(g1, s1.getGenre());
    }

    // TEST
//    - name diff, artist diff, genre diff, first two diff, last two, first and last, all diff
    @Test
    void testIsSameSongTrue() {
        assertTrue(s1.isSameSong(s1));
    }

    @Test
    void testIsSameSongDiffGenre() {
        assertFalse(s1.isSameSong(s2));
    }

    @Test
    void testIsSameSongDiffArtist() {
        assertFalse(s2.isSameSong(s3));
    }

    @Test
    void testIsSameSongDiffArtistAndGenre() {
        assertFalse(s1.isSameSong(s3));
    }

    @Test
    void testIsSameSongDiffName() {
        assertFalse(s1.isSameSong(s4));
    }

    @Test
    void testIsSameSongDiffNameAndGenre() {
        assertFalse(s1.isSameSong(s5));
    }

    @Test
    void testIsSameSongDiffNameAndArtist() {
        assertFalse(s1.isSameSong(s6));
    }

    @Test
    void testIsSameSongAllDiff() {
        assertFalse(s1.isSameSong(s7));
    }
}
