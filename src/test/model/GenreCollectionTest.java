package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenreCollectionTest {
    private GenreCollection testCollection;
    private Genre g1;
    private Genre g2;
    private Genre g3;
    private Genre g4;

    @BeforeEach
    void runBefore() {
        testCollection = new GenreCollection();
        g1 = new Genre("Pop");
        g2 = new Genre("Rock");
        g3 = new Genre("Jazz");
        g4 = new Genre("K-pop");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testCollection.getSize());
    }

    @Test
    void testAddGenre() {
        testCollection.addGenre(g1);
        testCollection.addGenre(g2);
        testCollection.addGenre(g3);
        testCollection.addGenre(g4);

        assertEquals(4, testCollection.getSize());
        assertEquals(g1, testCollection.getGenres().get(0));
        assertEquals(g4, testCollection.getGenres().get(3));
    }

    @Test
    void testContainsGenreFalse() {
        assertFalse(testCollection.containsGenre(g1));
    }

    @Test
    void testContainsGenreTrueOne() {
        testCollection.addGenre(g3);
        assertTrue(testCollection.containsGenre(g3));
    }

    @Test
    void testContainsGenreFalseMultiple() {
        testCollection.addGenre(g1);
        testCollection.addGenre(g3);
        testCollection.addGenre(g4);
        assertFalse(testCollection.containsGenre(g2));
    }

    @Test
    void testContainsGenreTrueMultiple() {
        testCollection.addGenre(g1);
        testCollection.addGenre(g2);
        testCollection.addGenre(g3);
        testCollection.addGenre(g4);
        assertTrue(testCollection.containsGenre(g4));
    }

    @Test
    void testFindGenreEmpty() {
        assertEquals(0, testCollection.getSize());
        assertNull(testCollection.findGenre("No Genre"));
    }

    @Test
    void testFindGenreSingle() {
        testCollection.addGenre(g2);
        assertEquals(1, testCollection.getSize());
        assertEquals(g2, testCollection.findGenre("Rock"));
    }

    @Test
    void testFindGenreSingleCantFind() {
        testCollection.addGenre(g4);
        assertEquals(1, testCollection.getSize());
        assertNull(testCollection.findGenre("J-poop"));
    }

    @Test
    void testFindGenreOneMultipleGenres() {
        testCollection.addGenre(g1);
        testCollection.addGenre(g2);
        testCollection.addGenre(g3);
        testCollection.addGenre(g4);
        assertEquals(4, testCollection.getSize());
        assertEquals(g3, testCollection.findGenre("Jazz"));
    }

    @Test
    void testFindGenreOneMultipleGenresCantFind() {
        testCollection.addGenre(g1);
        testCollection.addGenre(g2);
        testCollection.addGenre(g3);
        testCollection.addGenre(g4);
        assertEquals(4, testCollection.getSize());
        assertNull(testCollection.findGenre("Random Genre"));
    }
}
