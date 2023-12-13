package persistence;

import model.Genre;
import model.Song;
import model.SongCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonSongsWriterTest {
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
    void testWriterEmptyCollectionToEmpty() {
        try {
            Genre gen1 = new Genre("gen1");
            Genre gen2 = new Genre("gen2");
            Song song1 = new Song("song1", "art1", gen1);
            Song song2 = new Song("song2", "art2", gen1);
            Song song3 = new Song("song3", "art3", gen2);
            SongCollection sCollection = new SongCollection();

            JsonSongsWriter writer = new JsonSongsWriter("./data/testSongsWriterEmpty.json");
            writer.open();
            writer.writeSongCollection(sCollection);
            writer.close();

            JsonSongsReader reader = new JsonSongsReader("./data/testSongsWriterEmpty.json");
            sCollection = reader.readSongCollection();
            assertEquals(0, sCollection.getSongCollectionSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterCollectionMultiple() {
        try {
            Genre gen1 = new Genre("gen1");
            Genre gen2 = new Genre("gen2");
            Song song1 = new Song("song1", "art1", gen1);
            Song song2 = new Song("song2", "art2", gen1);
            Song song3 = new Song("song3", "art3", gen2);
            SongCollection sCollection = new SongCollection();

            sCollection.addSong(song1);
            sCollection.addSong(song2);
            sCollection.addSong(song3);
            JsonSongsWriter writer = new JsonSongsWriter("./data/testSongsWriterEmpty.json");
            writer.open();
            writer.writeSongCollection(sCollection);
            writer.close();

            JsonSongsReader reader = new JsonSongsReader("./data/testSongsWriterEmpty.json");
            sCollection = reader.readSongCollection();
            assertEquals(3, sCollection.getSongCollectionSize());
            assertEquals("song1", sCollection.getSongs().get(0).getName());
            assertEquals("song2", sCollection.getSongs().get(1).getName());
            assertEquals("song3", sCollection.getSongs().get(2).getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

