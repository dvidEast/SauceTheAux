package persistence;

import model.Playlist;
import model.PlaylistCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonPlaylistsReaderTest {
    @Test
    void testPlaylistsReaderNonExistentFile() {
        JsonPlaylistsReader reader = new JsonPlaylistsReader("./data/noSuchFile.json");
        try {
            PlaylistCollection pc = reader.readPlaylistCollection();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testPlaylistsReaderEmpty() {
        JsonPlaylistsReader reader = new JsonPlaylistsReader("./data/testPlaylistsReaderEmpty.json");
        try {
            PlaylistCollection pc = reader.readPlaylistCollection();
            assertEquals(0, pc.getPlaylistCollectionSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testPlaylistReaderOnePlaylistOneSong() {
        JsonPlaylistsReader reader = new JsonPlaylistsReader("./data/testPlaylistsReaderOnePlaylist.json");
        try {
            PlaylistCollection pc = reader.readPlaylistCollection();
            Playlist p1 = pc.getPlaylistCollection().get(0);
            assertEquals(1, p1.getPlaylistSize());
            assertEquals("Poppy", p1.getName());
            assertEquals("Pop", p1.getGenre().getGenreName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testPlaylistReaderOnePlaylistMultipleSongs() {
        JsonPlaylistsReader reader = new JsonPlaylistsReader("./data/testPlaylistsReaderMultiplePlaylist.json");
        try {
            PlaylistCollection pc = reader.readPlaylistCollection();
            assertEquals("p1", pc.getPlaylistCollection().get(0).getName());
            assertEquals("g1", pc.getPlaylistCollection().get(0).getGenre().getGenreName());
            assertEquals(2, pc.getPlaylistCollection().get(0).getPlaylistSize());
            assertEquals("p2", pc.getPlaylistCollection().get(1).getName());
            assertEquals("g3", pc.getPlaylistCollection().get(1).getGenre().getGenreName());
            assertEquals(1, pc.getPlaylistCollection().get(1).getPlaylistSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
