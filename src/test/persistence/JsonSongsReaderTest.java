package persistence;

import model.Song;
import model.SongCollection;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonSongsReaderTest {
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
    void testSongsReaderEmpty() {
        JsonSongsReader reader = new JsonSongsReader("./data/testSongsReaderEmpty.json");
        try {
            SongCollection sc = reader.readSongCollection();
            assertEquals(0, sc.getSongCollectionSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testSongsReaderOneSong() {
        JsonSongsReader reader = new JsonSongsReader("./data/testSongsReaderOneSong.json");
        try {
            SongCollection sc = reader.readSongCollection();
            assertEquals(1, sc.getSongCollectionSize());
            List<Song> mySongs = sc.getSongs();
            assertEquals("Song1", mySongs.get(0).getName());
            assertEquals("Genre1", mySongs.get(0).getGenre().getGenreName());
            assertEquals("SongArtist1", mySongs.get(0).getArtist());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testSongsReaderMultipleSongs() {
        JsonSongsReader reader = new JsonSongsReader("./data/testSongsReaderMultipleSong.json");
        try {
            SongCollection sc = reader.readSongCollection();
            assertEquals(3, sc.getSongCollectionSize());
            List<Song> mySongs = sc.getSongs();
            assertEquals("Song1", mySongs.get(0).getName());
            assertEquals("Genre1", mySongs.get(0).getGenre().getGenreName());
            assertEquals("SongArtist1", mySongs.get(0).getArtist());
            assertEquals("Song2", mySongs.get(1).getName());
            assertEquals("Genre2", mySongs.get(1).getGenre().getGenreName());
            assertEquals("SongArtist2", mySongs.get(1).getArtist());
            assertEquals("Song3", mySongs.get(2).getName());
            assertEquals("Genre3", mySongs.get(2).getGenre().getGenreName());
            assertEquals("SongArtist3", mySongs.get(2).getArtist());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
