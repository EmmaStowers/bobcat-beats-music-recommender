/* Emma Stowers and Jasmine 
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

/*
This is the Song class that stores information about each song object.
It utilizes both acessors and mutators to work with the UserDatabase program.
**/
public class Song {
    private String title;
    private String artist;
    private String genre;
    private String mood;
    private String activity;
    private int rating;
    private SongPreference liked;
    
    /*stores the like/dislike status of each song and
    provides a neutral option for songs that have not yet been rated
    */
    public enum SongPreference {
      LIKED,
      DISLIKED,
      NEUTRAL 
   }

    public Song(String title, String artist, String genre, String mood, String activity) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.mood = mood;
        this.activity = activity;
        this.rating = 0;  
        this.liked = SongPreference.NEUTRAL;
    }

    public Song(String title, String artist, String genre, String mood, String activity, int rating, SongPreference liked) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.mood = mood;
        this.activity = activity;
        this.rating = rating;
        this.liked = liked;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title){
      this.title = title;
    }

    public String getArtist() {
        return artist;
    }
    
    public void setArtist(String artist){
      this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre){
      this.genre = genre;
    }

    public String getMood() {
        return mood;
    }
    
    public void setMood(String mood){
      this.mood = mood;
    }

    public String getActivity() {
        return activity;
    }
    
    public void setActivity(String activity){
      this.activity = activity;
    }

    public int getRating() {
        return rating;
    }

    public SongPreference getLiked() {
        return liked;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setLiked(SongPreference liked) {
        this.liked = liked;
    }   
    public String toString() {
         String str = title + " by " + artist + " (" + genre + ") \nMood: " + mood + "\nActivity: " + activity + "\nRating: " + rating +"\nLike Status: " + liked;   
         return str;
    }

}