package persistence;

import model.GenreCollection;
import model.SongCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonGenresReaderTest {
    @Test
    void testSongsReaderNonExistentFile() {
        JsonSongsReader reader = new JsonSongsReader("./data/noSuchFile.json");
        try {
            SongCollection sc = reader.readSongCollection();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testGenresReaderEmpty() {
        JsonGenresReader reader = new JsonGenresReader("./data/testGenresReaderEmpty.json");
        try {
            GenreCollection gc = reader.readGenreCollection();
            assertEquals(0, gc.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testGenresReaderOneGenre() {
        JsonGenresReader reader = new JsonGenresReader("./data/testGenresReaderOne.json");
        try {
            GenreCollection gc = reader.readGenreCollection();
            assertEquals(1, gc.getSize());
            assertEquals("g1", gc.getGenres().get(0).getGenreName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testGenresReaderMultipleGenre() {
        JsonGenresReader reader = new JsonGenresReader("./data/testGenresReaderMultiple.json");
        try {
            GenreCollection gc = reader.readGenreCollection();
            assertEquals(3, gc.getSize());
            assertEquals("g1", gc.getGenres().get(0).getGenreName());
            assertEquals("g2", gc.getGenres().get(1).getGenreName());
            assertEquals("g3", gc.getGenres().get(2).getGenreName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
