# bobcat-beats-music-recommender
Java desktop app that recommends songs based on genre, mood, and activity. Features a searchable song database, rating/like tracking, and a habit-analysis tool that identifies a user's most-listened genre and artist. Built with JavaFX for the GUI and custom-styled CSS.

# Bobcat Beats

Bobcat Beats is a Java desktop application that helps users discover and manage music based on their preferences. Built with JavaFX for the GUI and structured around clean object-oriented design, the app lets users:

Add new songs to a personal database, with automatic duplicate detection
Rate and mark songs as liked/disliked, building a preference history over time
Explore new songs by filtering on genre, artist, mood, or activity, returning a randomized matching suggestion
View listening habits, with the app automatically calculating the user's most-liked genre and artist based on rating history

The project separates data modeling (Song.java) from application logic (MusicDatabase.java), uses enums to manage song preference states, and applies custom-styled CSS for the JavaFX interface.

Tech stack: Java, JavaFX, CSS
Built by: Emma Stowers (with a project partner)
