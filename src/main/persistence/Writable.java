package persistence;

import org.json.JSONObject;

// Interface for writing data into Json
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
