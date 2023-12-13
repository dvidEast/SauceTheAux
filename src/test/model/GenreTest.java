package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreTest {
    private Genre testGenre1;
    private Genre testGenre2;

    @BeforeEach
    void runBefore() {
        testGenre1 = new Genre("Jazz");
        testGenre2 = new Genre("Pop");
    }

    @Test
    void testConstructor() {
        assertEquals("Jazz", testGenre1.getGenreName());
    }

    @Test
    void testIsSameGenreTrue() {
        assertTrue(testGenre1.isSameGenre(testGenre1));
    }

    @Test
    void testIsSameGenreFalse() {
        assertFalse(testGenre1.isSameGenre(testGenre2));
    }
}