package ui.gui;

// Names for all buttons (referenced from SmartHome)
public enum ButtonNames {
    SAVE("Save Data"),
    ADDSONG("Add Song"),
    ADDGENRE("Add Genre"),
    ADDPLAYLIST("Add Playlist");

    private final String name;

    // EFFECTS: sets enumeration key's name to this name
    ButtonNames(String name) {
        this.name = name;
    }

    // EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
