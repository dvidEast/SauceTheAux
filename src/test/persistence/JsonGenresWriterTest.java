package persistence;

import model.Genre;
import model.GenreCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonGenresWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonGenresWriter writer = new JsonGenresWriter("./data/my\0illegalasdf:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterOneWithNoGenreToEmpty() {
        try {
            GenreCollection gCollection = new GenreCollection();
            JsonGenresWriter writer = new JsonGenresWriter("./data/testGenresWriterEmpty.json");
            writer.open();
            writer.writeGenreCollection(gCollection);
            writer.close();

            JsonGenresReader reader = new JsonGenresReader("./data/testGenresWriterEmpty.json");
            gCollection = reader.readGenreCollection();
            assertEquals(0, gCollection.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterOneWithGenreToEmpty() {
        try {
            Genre gen1 = new Genre("gen1");
            Genre gen2 = new Genre("gen2");
            Genre gen3 = new Genre("gen3");
            GenreCollection gCollection = new GenreCollection();
            gCollection.addGenre(gen1);
            gCollection.addGenre(gen2);
            gCollection.addGenre(gen3);
            JsonGenresWriter writer = new JsonGenresWriter("./data/testGenresWriterEmpty.json");
            writer.open();
            writer.writeGenreCollection(gCollection);
            writer.close();

            JsonGenresReader reader = new JsonGenresReader("./data/testGenresWriterEmpty.json");
            gCollection = reader.readGenreCollection();
            assertEquals(3, gCollection.getSize());
            assertEquals("gen1", gCollection.getGenres().get(0).getGenreName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterMultipleGenreToEmpty() {
        try {
            Genre gen1 = new Genre("gen1");
            Genre gen2 = new Genre("gen2");
            Genre gen3 = new Genre("gen3");
            GenreCollection gCollection = new GenreCollection();
            gCollection.addGenre(gen1);
            gCollection.addGenre(gen2);
            gCollection.addGenre(gen3);
            JsonGenresWriter writer = new JsonGenresWriter("./data/testGenresWriterEmpty.json");
            writer.open();
            writer.writeGenreCollection(gCollection);
            writer.close();

            JsonGenresReader reader = new JsonGenresReader("./data/testGenresWriterEmpty.json");
            gCollection = reader.readGenreCollection();
            assertEquals(3, gCollection.getSize());
            assertEquals("gen1", gCollection.getGenres().get(0).getGenreName());
            assertEquals("gen2", gCollection.getGenres().get(1).getGenreName());
            assertEquals("gen3", gCollection.getGenres().get(2).getGenreName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
