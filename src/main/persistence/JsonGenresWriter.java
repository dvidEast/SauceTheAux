package persistence;

import model.GenreCollection;
import org.json.JSONObject;

// Represents a writer that writes JSON representation of GenreCollection to file
// Referenced from JsonSerializationDemo from the 210 repository
public class JsonGenresWriter extends JsonWriter {
    public JsonGenresWriter(String destination) {
        super(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of genre collection to file
    public void writeGenreCollection(GenreCollection gc) {
        JSONObject json = gc.toJson();
        saveToFile(json.toString(TAB));
    }
}