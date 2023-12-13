# Sauce the AUX

## Music Playlist Creator

An application that can be used by anyone who listens to music, **Sauce the AUX** 
is a playlist creator that groups and manages all your favourite songs based on their genre. 
Who picks the genre's? You!

As someone with 50+ playlists swimming around, it's increasingly difficult to manage
all of my favourite songs into a new playlist. 

With this app, you'll have the opportunity to add songs to the database with multiple different genre's attached
that you create. Then you can request to create a playlist based on a genre that you pick 
(e.g. Pop, Rap, Jazz)!. Sauce the AUX saves time, effort, and allows you to get creative with your playlist
combinations!

## User Stories

As a user, I want to be able to add a song to my song collection and specify the:

- name of the song
- name of the artist
- genre of the song (selected from my genre collection)

As a user, if a genre I want to select for a new song doesn't exist, I want to be able to 
add and save a new genre to my genre collection.

As a user, I want to be able to create and save a new playlist by specifying a genre for it.

As a user, I want to be able to view all the songs in my collection.

As a user, I want to be able to save all my song, genre, and playlist collections to file 
(if I so choose).

As a user, I want to ba able to load all my saved collections including songs, genres, 
and playlists from file (if I so choose).

## Instructions for Grader

- After running the main class, you can reload the state of my application by clicking "Yes" when prompted

- You can generate the first required action related to the user story "adding multiple Genre's to a Genre 
Collection" by clicking the tab labelled "Genres", inputting a new genre name in the field with the placeholder 
"Enter New Genre Name", and clicking the "Add Genre" button

- You can generate the second required action related to the user story "adding multiple song's to a song collection" 
by clicking the tab labelled "Songs", inputting a new song name in the field with the placeholder
"Enter New Song Name", inputting a new song artist in the field with the placeholder "Enter New Song Artist",
selecting a genre in the drop-down menu, and clicking the "Add Song" button

- You can locate my visual component by going to the Playlist Tab and finding the "create" button

- You can save the state of my application whenever you wish by clicking "Save Data" in the Save Tab or by clicking
  "Yes" when prompted as you close the application

## Phase 4: Task 2

Thu Nov 30 11:47:27 PST 2023: Added Genre 'Genre ' to GenreCollection

Thu Nov 30 11:47:37 PST 2023: Added Song: 'Song name' to SongCollection

Thu Nov 30 11:47:39 PST 2023: Created new Playlist 'Playlist name' with Genre 'Genre  ' and the following songs:

Thu Nov 30 11:47:39 PST 2023: Added Song: 'Song name' to SongCollection

Thu Nov 30 11:47:39 PST 2023: Added Playlist 'Playlist name' to PlaylistCollection

## Phase 4: Task 3
One thing I could do is to refactor the `Song` and `Genre` classes to utilize an id system that would 
help with the accuracy of persistence and duplication. To do this I could:
- refactor the two classes to have and id field
- use two singleton patterns for each of the classes which would ensure I have a new id
every time I create a new instance of either class

Another thing I could do is to improve the playlist creation to use a `GenreCollection` 
instead of just `Genre` to add more flexibility. To do this I could:
- refactor the genres field in `Playlist` to be a `GenreCollection`
- refactor the `Playlist` method `filterSongs()` to filter over all genres in given `GenreCollection`
- refactor saving, loading, and ui as necessary