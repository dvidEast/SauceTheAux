package persistence;

import model.Genre;
import model.GenreCollection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

// Represents a reader that reads GenreCollection from JSON data stored in file
// Referenced from JsonSerializationDemo from the 210 repository
public class JsonGenresReader extends JsonReader {
    public JsonGenresReader(String source) {
        super(source);
    }

    // EFFECTS: reads genreCollection from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GenreCollection readGenreCollection() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        return parseGenreCollection(jsonObject);
    }

    // EFFECTS: parses genres from JSON object and returns it
    private GenreCollection parseGenreCollection(JSONObject jsonObject) {
        GenreCollection gc = new GenreCollection();
        addGenres(gc, jsonObject);

        return gc;
    }

    // MODIFIES: sc
    // EFFECTS: parses songCollection from JSON object and adds them to songCollection
    private void addGenres(GenreCollection gc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray(JsonKeyNames.GENRES.getValue());
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addGenre(gc, nextSong);
        }
    }

    // MODIFIES: sc
    // EFFECTS: parses song from JSON object and adds it to songCollection
    private void addGenre(GenreCollection gc, JSONObject jsonObject) {
        String genreName = jsonObject.getString(JsonKeyNames.GENRE_NAME.getValue());
        Genre genre = new Genre(genreName);
        gc.addGenre(genre);
    }
}

