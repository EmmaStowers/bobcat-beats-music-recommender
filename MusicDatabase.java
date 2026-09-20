/* Emma Stowers  and Jasmine 
  Dr. Phelps/Liu has allowed students to discuss the algorithm 
  and/or general concepts about the assignment with other students
   and to receive limited help on specific topics. However, I 
	assume full responsibility for the content and integrity of 
	this assignment. I have developed my own solution to this 
	assigned project. I have not used or copied (by any means) 
	anotherís work (or portions of it) in order to represent it 
	as my own including material from the internet. If I used a 
	common computer, I have remembered to delete the files and 
	empty the recycle bin. I have destroyed all extra printouts 
	of my code. I have not shared my code with anyone. I am sole 
	author of the assignment; however,

I received outside help from the following people:

 ......

These are the websites that I used as reference:
https://docs.oracle.com/javase
*/

import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.layout.Pane;

/* 
where most of the information is processed from the GUI
most of the GUI uses methods from this file.
it sorts, adds, finds based on user inputs from the GUI file and uses the objects from the
the Song class to store song information 
*/
public class MusicDatabase{  //stores and manages all the music data

   private List<Song> songs = new ArrayList<>();
   
   //loads a few preset songs into the list
   public MusicDatabase() {
        loadPresetSongs();    
   }
   
   //static method that displays a defult text in the GUI that is later replaced 
   public static String printWelcomeMessage() {
      return ("Welcome to Bobcat Beats! (Press Enter to get random suggestions)");
   }
    
    //preset songs 
    public void loadPresetSongs() {
        addSong("Vienna", "Billy Joel", "Soft Rock", "Calm", "Relax");
        addSong("Billie Jean", "Michael Jackson", "Pop", "Up Beat", "Workout");
        addSong("Shape of You", "Ed Sheeran", "Pop", "Happy", "Workout");
        addSong("Blinding Lights", "The Weeknd", "Pop", "Energetic", "Workout");
        addSong("MC24", "Charles Leclerc", "Classical", "Calm", "Study"); 
        addSong("Nica's Theme", "Andrea Farri", "Classical", "Calm", "Study"); 

    }

    //used to check weather a song already exists in our list of songs
    public Song searchSong(String title, String artist) {  
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title) && song.getArtist().equalsIgnoreCase(artist)) {
                return song; // want to display in metadata label
            }
        }
        return null; //display song does not exist yet/not found in metadata label
    }
    
   /* method to add new songs to the list. it first checks if it is a repeat, and if it is not 
   the song is added to the songs list.
   */
   public boolean addSong(String title, String artist, String genre, String mood, String activity) { 
        if(searchSong(title, artist) != null){
            return false; 
        } else {
            Song newSong = new Song(title, artist, genre, mood, activity);
            songs.add(newSong);
            return true;
        }
    }
    
    /*used with the rate existing. It searches to find if the song exists in the list and if it does
    it allows the rating and liked status to be set for the song
    */
    public void rateSong(String title, String artist, int rating, Song.SongPreference liked) { 
        Song song = searchSong(title, artist);
        if (song != null){
                song.setRating(rating);
                song.setLiked(liked);
                System.out.println("Song rated successfully!"); //display 
                return;
        }
        System.out.println("Song not found!");
    }
    
    /* used to explore new songs based on what the users search criteria is. it will check to see what they imput
    as wanting to search from and will go through the list creating the matchingSongs list 
    with the songs that match that paramater and return a random matching suggestion
    */
    public String exploreSongs(String genre, String artist, String mood, String activity) { //explore new 
        System.out.println("Exploring songs with the following criteria:");
        System.out.println("Genre: " + genre + ", Artist: " + artist + ", Mood: " + mood + ", Activity: " + activity);

        List<Song> matchingSongs = new ArrayList<>(); //holds matched songs

        for (Song song : songs) {
            boolean matches = true;
            if (genre != null && !song.getGenre().equalsIgnoreCase(genre)) {
                matches = false;
            }
            if (artist != null && !song.getArtist().equalsIgnoreCase(artist)) {
                matches = false;
            }
            if (mood != null && !song.getMood().equalsIgnoreCase(mood)) {
                matches = false;
            }
            if (activity != null && !song.getActivity().equalsIgnoreCase(activity)) {
                matches = false;
            }

            if (matches) {
                 matchingSongs.add(song);
            }
        }
         if (matchingSongs.isEmpty()) {
             return "Could not find a song that matched";
         } else { //randomly picks a matched song from the list 
            Random rand = new Random();
            Song selected = matchingSongs.get(rand.nextInt(matchingSongs.size()));
            return selected.getTitle() + " by " + selected.getArtist();
         }    
    }

    // Display all songs currently in the songs list
    public void displayAllSongs() {
        System.out.println("All songs:");
        for (Song song : songs) {
            System.out.println("Title: " + song.getTitle() + ", Artist: " + song.getArtist() +
                               ", Genre: " + song.getGenre() + ", Mood: " + song.getMood() +
                               ", Activity: " + song.getActivity() + ", Rating: " + song.getRating() +
                               ", Liked: " + song.getLiked());
        }
    }
    
    
    //returns all of the artists in an arrayList format for the dropdown in the GUI
    public ArrayList<String> getAllArtists(){  
      ArrayList<String> str = new ArrayList<>();
      for (Song song : songs) {
            str.add(song.getArtist());
      }
      return str;
   }
    
   /*
   will create a list of the liked songs and update the most seen genre and artist out 
   of those liked songs and then return it as a string to be displayed in the GUI
   */ 
   public String userHabits(){ //to track what the user likes (genre and artist)
      
      String favoriteGenre = "";
      String favoriteArtist = "";
      int maxCountGenre = 0;
      int maxCountArtist = 0;
      ArrayList<Song> likedSongs = new ArrayList<>();


      for (Song s : songs) {  //adds songs to the liked list
         if (s.getLiked() == Song.SongPreference.LIKED) {
         likedSongs.add(s);
         }
      }
      
     for(Song song : likedSongs){ //sees the most common artist and genre for the liked list
        int countGenre = 0;
        int countArtist = 0;
        for (Song compare : likedSongs) {
            if (song.getGenre().equals(compare.getGenre())) {
                countGenre++;
            }
            if(song.getArtist().equals(compare.getArtist())){
               countArtist++;
            }
        }
        if (countGenre > maxCountGenre) {
            maxCountGenre = countGenre;
            favoriteGenre = song.getGenre();
        }
        if (countArtist > maxCountArtist){
            maxCountArtist = countArtist;
            favoriteArtist = song.getArtist();
        }
    }

    return "Most liked genre: " + favoriteGenre + "        Most liked artist: " + favoriteArtist;
   
   
   
   }
}


   
   