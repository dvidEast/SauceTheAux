package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonPlaylistsWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonPlaylistsWriter writer = new JsonPlaylistsWriter("./data/my\0illegalasdf:fileName.json");
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
            sCollection.addSong(song1);
            sCollection.addSong(song2);
            sCollection.addSong(song3);
            PlaylistCollection pCollection = new PlaylistCollection();

            JsonPlaylistsWriter writer = new JsonPlaylistsWriter("./data/testPlaylistsWriterEmpty.json");
            writer.open();
            writer.writePlaylistCollection(pCollection);
            writer.close();

            JsonPlaylistsReader reader = new JsonPlaylistsReader("./data/testPlaylistsWriterEmpty.json");
            pCollection = reader.readPlaylistCollection();
            assertEquals(0, pCollection.getPlaylistCollectionSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterOnePlaylist() {
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
            PlaylistCollection pCollection = new PlaylistCollection();

            Playlist p1 = new Playlist("p1", sCollection, gen1);
            pCollection.addPlaylist(p1);
            JsonPlaylistsWriter writer = new JsonPlaylistsWriter("./data/testPlaylistsWriterEmpty.json");
            writer.open();
            writer.writePlaylistCollection(pCollection);
            writer.close();

            JsonPlaylistsReader reader = new JsonPlaylistsReader("./data/testPlaylistsWriterEmpty.json");
            PlaylistCollection pc = reader.readPlaylistCollection();
            Playlist testP1 = pc.getPlaylistCollection().get(0);
            assertEquals(2, testP1.getPlaylistSize());
            assertEquals("p1", testP1.getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterMultiplePlaylist() {
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
            PlaylistCollection pCollection = new PlaylistCollection();

            Playlist p1 = new Playlist("p1", sCollection, gen1);
            Playlist p2 = new Playlist("p2", sCollection, gen2);
            pCollection.addPlaylist(p1);
            pCollection.addPlaylist(p2);
            JsonPlaylistsWriter writer = new JsonPlaylistsWriter("./data/testPlaylistsWriterEmpty.json");
            writer.open();
            writer.writePlaylistCollection(pCollection);
            writer.close();

            JsonPlaylistsReader reader = new JsonPlaylistsReader("./data/testPlaylistsWriterEmpty.json");
            pCollection = reader.readPlaylistCollection();
            assertEquals(2, pCollection.getPlaylistCollectionSize());
            assertEquals("p1", pCollection.getPlaylistCollection().get(0).getName());
            assertEquals("p2", pCollection.getPlaylistCollection().get(1).getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
