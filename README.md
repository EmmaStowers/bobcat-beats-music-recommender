# bobcat-beats-music-recommender
Java desktop app that recommends songs based on genre, mood, and activity. Features a searchable song database, rating/like tracking, and a habit-analysis tool that identifies a user's most-listened genre and artist. Built with JavaFX for the GUI and custom-styled CSS.

# Bobcat Beats

Bobcat Beats is a Java desktop application that helps users discover and manage music based on their preferences. Built with JavaFX for the GUI and structured around clean object-oriented design, the app lets users:

Add new songs to a personal database, with automatic duplicate detection
Rate and mark songs as liked/disliked, building a preference history over time
Explore new songs by filtering on genre, artist, mood, or activity, returning a randomized matching suggestion
View listening habits, with the app automatically calculating the user's most-liked genre and artist based on rating history

The project separates data modeling (Song.java) from application logic (MusicDatabase.java), uses enums to manage song preference states, and applies custom-styled CSS for the JavaFX interface.

Features
Add New Songs — Add songs to the database with title, artist, genre, mood, and activity tags. Duplicate entries are automatically detected and blocked.
Rate Existing Songs — Search for a song and assign it a rating and a liked/disliked/neutral status.
Explore New Songs — Filter the song database by genre, artist, mood, or activity to get a randomized matching recommendation.
User Habit Analysis — Automatically calculates and displays the user's most-liked genre and most-liked artist based on rating history.
Audio Playback — Preview songs directly within the app.
Custom Styled GUI — Interface styled with CSS for a clean, cohesive look.
Tech Stack
Language: Java
GUI Framework: JavaFX
Styling: CSS
Core Concepts: Object-oriented design, enums, collections (ArrayList), custom data modeling
Project Structure
bobcat-beats/
├── src/
│   ├── MusicBase.java       # Main application entry point & GUI
│   ├── MusicDatabase.java   # Core logic: add, search, rate, explore, analyze
│   └── Song.java            # Song data model
├── assets/
│   ├── guitar.wav           # Sample audio for playback
│   └── *.png                # Icons used in the GUI
├── styles.css                # JavaFX GUI styling
└── README.md


Tech stack: Java, JavaFX, CSS
Built by: Emma Stowers and Jasmine Smith
