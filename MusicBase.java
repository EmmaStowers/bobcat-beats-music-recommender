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

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.text.Font;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Alert; 
import javafx.scene.control.Alert.AlertType; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;

/* 
where all of the GUI components are created 
*/
public class MusicBase extends Application {

    MusicDatabase songManager = new MusicDatabase(); //song manager is an instance of MusicDatabase
    TextField songNameField, artistField, rateSongField, rateArtistField, searchField;
    Label addNewSongsLabel, rateExistingLabel, exploreNewSongsLabel, ratingLabel;
    Slider ratingSlider;
    ComboBox<String> genreComboBox;
    ComboBox<String> moodComboBox;
    ComboBox<String> activityComboBox;
    ComboBox<String> exploreGenreComboBox;
    ComboBox<String> exploreArtistComboBox;
    ComboBox<String> exploreMoodComboBox;
    ComboBox<String> exploreActivityComboBox;
    Label exploreMoodPromptLabel;
    Label exploreActivityPromptLabel;
    boolean likeDislike;

    VBox infoDisplayVBox; // VBox for displaying persistent text information
    Text messageText, userHabits; // Text for displaying messages in the persistent box
    VBox audioControlVBox; // VBox for audio controls

    public static void main(String[] args) {
        launch(args);
    }

    // Method to display alerts
    private void showAlert(AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) {
        // Main title label
        Label bobcatBeatsLabel = new Label("Bobcat Beats");
        bobcatBeatsLabel.setId("bobcat-label");

        // Labels for the VBoxes
        addNewSongsLabel = new Label("Add New Songs:");
        addNewSongsLabel.setId("addNewSongsLabel-label");
        rateExistingLabel = new Label("Rate Existing:");
        rateExistingLabel.setId("rateExistingLabel-label");
        exploreNewSongsLabel = new Label("Explore New Songs:");
        exploreNewSongsLabel.setId("exploreNewSongsLabel-label");

        // Add New Songs Section
        Label songNameLabel = new Label("Song Name: ");
        Label artistLabel = new Label("Artist: ");
        Label genreLabel = new Label("Genre: ");
        Label moodLabel = new Label("Mood: ");
        Label activityLabel = new Label("Activities: ");
        songNameField = new TextField();
        artistField = new TextField();
        
        //user habbits holds static method words until user rates songs
        userHabits = new Text(songManager.printWelcomeMessage());

        // Create the ComboBox for Genre
        genreComboBox = new ComboBox<>(FXCollections.observableArrayList(
                "Pop", "Rock", "Hip-Hop", "Electronic", "Classical", "Jazz", "Country", "Other"
        ));
        genreComboBox.setPromptText("Select Genre");

        // Create the ComboBox for Mood
        moodComboBox = new ComboBox<>(FXCollections.observableArrayList(
                "Happy", "Sad", "Hype", "Energetic", "Relaxed", "Romantic", "Angry", "Calm", "Other"
        ));
        moodComboBox.setPromptText("Select Mood");

        // Create the ComboBox for Activity
        activityComboBox = new ComboBox<>(FXCollections.observableArrayList(
                "Gym", "Dance", "Club", "Beach", "Road Trip", "Study", "Other"
        ));
        activityComboBox.setPromptText("Select Activity");

        Button addButton = new Button("Add");

        VBox addNewSongsVBox = new VBox(10, addNewSongsLabel, songNameLabel, songNameField, artistLabel, artistField, genreLabel, genreComboBox, moodLabel, moodComboBox, activityLabel, activityComboBox, addButton);
        addNewSongsVBox.getStyleClass().add("addNewSongsVBox-container");

        // Rate Existing Section
        Label rateSongLabel = new Label("Song Name:");
        Label rateArtistLabel = new Label("Artist:");
        rateSongField = new TextField();
        rateArtistField = new TextField();
        ratingLabel = new Label("Rate Song: ");

        Button findButton = new Button("Find");

        // Create the Slider
        Slider ratingSlider = new Slider(1, 10, 1);
        ratingSlider.setShowTickLabels(true);
        ratingSlider.setShowTickMarks(true);
        ratingSlider.setMajorTickUnit(1);
        ratingSlider.setMinorTickCount(0);
        ratingSlider.setSnapToTicks(true);

        // Create labels for rating words (Bad, Okay, Good, Great)
        Label badLabel = new Label("Bad");
        Label okLabel = new Label("OK");
        Label goodLabel = new Label("Good");
        Label greatLabel = new Label("Great");

        // Position rating words above the slider using an HBox
        HBox ratingWordsHBox = new HBox(badLabel, okLabel, goodLabel, greatLabel);
        ratingWordsHBox.setAlignment(Pos.CENTER);
        ratingWordsHBox.setSpacing(20); // Adjust spacing if needed

        // HBox for "Rating: " label, slider, and numerical rating
        HBox ratingHBox = new HBox(5, ratingLabel, ratingSlider); 
        ratingHBox.setAlignment(Pos.CENTER);

        Label numericalRatingLabel = new Label("1"); // Initial value

        // Create like and dislike buttons
        Button likeButton = new Button("\uD83D\uDC4D"); // Thumbs-up Unicode
        likeButton.setStyle("-fx-background-color: green; -fx-text-fill: white;"); // Green background, white text

        Button dislikeButton = new Button("\uD83D\uDC4E"); // Thumbs-down Unicode
        dislikeButton.setStyle("-fx-background-color: red; -fx-text-fill: white;"); // Red background, white text

        // HBox to hold the like and dislike buttons
        HBox buttonsHBox = new HBox(10, likeButton, dislikeButton); // Spacing between buttons
        buttonsHBox.setAlignment(Pos.CENTER);

        // Add ChangeListener to the Slider (to update ratingLabel and numerical rating)
        ratingSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int rating = newValue.intValue();
                String ratingText;

                if (rating >= 1 && rating <= 3) {
                    ratingText = "Bad";
                } else if (rating >= 4 && rating <= 6) {
                    ratingText = "Okay";
                } else if (rating >= 7 && rating <= 8) {
                    ratingText = "Good";
                } else {
                    ratingText = "Great";
                }

                ratingLabel.setText("Rate Song: " + ratingText + " (" + newValue.intValue() + ")");
                numericalRatingLabel.setText(String.valueOf(newValue.intValue()));
            }
        });

        // Create and populate rateExistingVBox
        VBox rateExistingVBox = new VBox(10, rateExistingLabel, rateSongLabel, rateSongField,
                rateArtistLabel, rateArtistField, findButton, ratingWordsHBox, ratingHBox, buttonsHBox);
        rateExistingVBox.getStyleClass().add("rateExistingVBox-container");


        // Explore New Songs Section
        Label exploreGenreLabel = new Label("Explore Genre: ");
        Label exploreArtistLabel = new Label("Explore Artists: ");
        Label exploreMoodPromptLabel = new Label("Explore Mood: ");
        Label exploreActivityPromptLabel = new Label("Explore Activity: ");

        exploreGenreComboBox = new ComboBox<>(FXCollections.observableArrayList(
                "Pop", "Rock", "Hip-Hop", "Electronic", "Classical", "Jazz", "Country", "Other"
        ));
        exploreGenreComboBox.setPromptText("Select Genre");

        //where the getAllArtists is called to set the dropdown with the current artists in list
        exploreArtistComboBox = new ComboBox<>(FXCollections.observableArrayList(
                songManager.getAllArtists()
        ));
        exploreArtistComboBox.setPromptText("Select Artist");

        exploreMoodComboBox = new ComboBox<>(FXCollections.observableArrayList(
                "Happy", "Sad", "Hype", "Calm", "Angry", "Other"
        ));
        exploreMoodComboBox.setPromptText("Select Mood");

        exploreActivityComboBox = new ComboBox<>(FXCollections.observableArrayList(
                "Gym", "Dance", "Club", "Beach", "Road Trip", "Study", "Other"
        ));
        exploreActivityComboBox.setPromptText("Select Activity");

        Button generateButton = new Button("Generate");

        VBox exploreNewSongsVBox = new VBox(10, exploreNewSongsLabel, exploreGenreLabel, exploreGenreComboBox, exploreArtistLabel, exploreArtistComboBox, exploreMoodPromptLabel, exploreMoodComboBox, exploreActivityPromptLabel, exploreActivityComboBox, generateButton);
        exploreNewSongsVBox.getStyleClass().add("exploreNewSongsVBox-container");

        // Create the info display box (for persistent messages)
        infoDisplayVBox = new VBox();
        infoDisplayVBox.getStyleClass().add("infoDisplayVBox-container");
        infoDisplayVBox.setMinWidth(200); // Set a minimum width
        infoDisplayVBox.setMinHeight(100); // Set a minimum height

        messageText = new Text();
        infoDisplayVBox.getChildren().add(messageText);

        // Main layout container for the two display boxes
        HBox displayBoxesHBox = new HBox(10, infoDisplayVBox);
        displayBoxesHBox.getStyleClass().add("displayBoxesHBox-container");

        // Main layout container
        HBox mainContentHBox = new HBox(10, addNewSongsVBox, rateExistingVBox, exploreNewSongsVBox);
        mainContentHBox.getStyleClass().add("mainContentHBox-container");

        // Main VBox to hold all elements
        VBox mainVBox = new VBox(10, bobcatBeatsLabel, mainContentHBox, displayBoxesHBox, userHabits);
        mainVBox.setAlignment(Pos.TOP_CENTER);

        // Set action for the Add button
        addButton.setOnAction(event -> {
            //gather information from the text feilds
            String songName = songNameField.getText();
            String artist = artistField.getText();
            String genre = genreComboBox.getValue(); // Get selected genre
            String mood = moodComboBox.getValue(); // Get selected mood
            String activity = activityComboBox.getValue(); // Get selected activity
            
            //images displayed in the alert labels 
            Image imageSucess = new Image("file:music.png");
            ImageView imageViewSuccess = new ImageView(imageSucess);
            imageViewSuccess.setFitHeight(50);
            imageViewSuccess.setFitWidth(50);
            Image imageDuplicate = new Image("file:duplicate Background Removed.png");
            ImageView imageViewDuplicate = new ImageView(imageDuplicate);
            imageViewDuplicate.setFitHeight(50);
            imageViewDuplicate.setFitWidth(50);

            //if the song is added this will run through and give the proper output
            if (songManager.addSong(songName, artist, genre, mood, activity)) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("You've added a song! Congrats!");
                alert.setGraphic(imageViewSuccess);  
                alert.showAndWait();                
                messageText.setText("Song Added!\nSong Name: " + songName + "\nArtist: " + artist + "\nGenre: " + genre + "\nMood: " + mood + "\nActivity: " + activity);
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Duplicate");
                alert.setHeaderText(null);
                alert.setContentText("This song is already in your library.");
                alert.setGraphic(imageViewDuplicate);  
                alert.showAndWait();    
                messageText.setText("Attempted to add duplicate: " + songName + " by " + artist); // Update persistent box
            }
            songNameField.clear();
            artistField.clear();
            genreComboBox.setValue(null); // Reset the ComboBox
            moodComboBox.setValue(null); // Reset the ComboBox
            activityComboBox.setValue(null); // Reset the ComboBox
        });
        
        //sound element that is displayed when like and dislike button alerts are active 
         File soundFile = new File("guitar.wav");
         Media media = new Media(soundFile.toURI().toString());
         MediaPlayer player = new MediaPlayer(media);
         player.setVolume(.1);

         //like button set action. will give take the song being rated and give it to the rating function in musicdatabase
        likeButton.setOnAction(event -> {
            String songName = rateSongField.getText();
            String artist = rateArtistField.getText();

            likeDislike = true;
            int rating = (int) ratingSlider.getValue();

            songManager.rateSong(songName, artist, rating, Song.SongPreference.LIKED); //sets the song as being liked and sets rating
            Song song = songManager.searchSong(songName, artist);
            
            //sets the image for the alerts
            Image imageRated = new Image("file:musicRated.png");
            ImageView imageViewRated = new ImageView(imageRated);
            imageViewRated.setFitHeight(50);
            imageViewRated.setFitWidth(50);
            Image imageNotFound = new Image("file:musicNotFound.png");
            ImageView imageViewNotFound = new ImageView(imageNotFound);
            imageViewNotFound.setFitHeight(50);
            imageViewNotFound.setFitWidth(50);
            //if song is found then alert is set and song is displayed
            if (song != null) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Rated");
                alert.setHeaderText(null);
                alert.setContentText("You've rated a song!");
                alert.setGraphic(imageViewRated); 
                player.play();
                alert.showAndWait();     
                player.stop();
                messageText.setText("Rated '" + songName + "' by '" + artist + "' (Liked)." + "\nRaiting: " + rating); // Update box
            } else { //if song is not found 
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Not Found");
                alert.setHeaderText(null);
                alert.setContentText("Couldn't find that song to rate.");
                alert.setGraphic(imageViewNotFound);  
                alert.showAndWait(); 
                messageText.setText("Error rating: Song not found."); // Update box
            }
            
            userHabits.setText(songManager.userHabits());
            rateSongField.clear();
            rateArtistField.clear();
            ratingSlider.setValue(1);
        });

         //does the same as the like button but instead when the user dislikes a song 
        dislikeButton.setOnAction(event -> {
            String songName = rateSongField.getText();
            String artist = rateArtistField.getText();

            likeDislike = false;
            int rating = (int) ratingSlider.getValue();

            songManager.rateSong(songName, artist, rating, Song.SongPreference.DISLIKED); //sets the song as disliked and gives rating 
            Song song = songManager.searchSong(songName, artist);
            
            //sets the images for the alerts
            Image imageRated = new Image("file:musicRated.png");
            ImageView imageViewRated = new ImageView(imageRated);
            imageViewRated.setFitHeight(50);
            imageViewRated.setFitWidth(50);
            Image imageNotFound = new Image("file:musicNotFound.png");
            ImageView imageViewNotFound = new ImageView(imageNotFound);
            imageViewNotFound.setFitHeight(50);
            imageViewNotFound.setFitWidth(50);

            //if the song was found alert graphic is shown and song is displayed
            if (song != null) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Rated");
                alert.setHeaderText(null);
                alert.setContentText("You've rated a song!");
                alert.setGraphic(imageViewRated);  
                player.play();
                alert.showAndWait(); 
                player.stop();    
                messageText.setText("Rated '" + songName + "' by '" + artist + "' (Disliked)." + "\nRaiting: " + rating); // Update persistent box
            } else { //if song is not found 
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Not Found");
                alert.setHeaderText(null);
                alert.setContentText("Couldn't find that song to rate.");
                alert.setGraphic(imageViewNotFound);  
                alert.showAndWait(); 
                messageText.setText("Error rating: Song not found."); // Update persistent box
            }
            userHabits.setText(songManager.userHabits());

            rateSongField.clear();
            rateArtistField.clear();
            ratingSlider.setValue(1);
        });

        // Set action for the Find button
        findButton.setOnAction(event -> {
            String songName = rateSongField.getText();
            String artist = rateArtistField.getText();

            //searches for the song
            Song song = songManager.searchSong(songName, artist);

            //if the song is found it is displayed otherwise it shows it was not in the list
            if (song != null) {
                String findSong = "Found song: " + song.getTitle() + " by " + song.getArtist();
                showAlert(AlertType.INFORMATION, "Found It!", null, "We found the song you were looking for! \n" + findSong);
                messageText.setText(song.toString()); // Update persistent box
            } else {
                showAlert(AlertType.ERROR, "Not Found", null, "Oops! That song isn't in your library yet.");
                messageText.setText("Search failed: Song not found."); // Update persistent box
            }
           
        });

        // Set action for the Generate button - finds songs that match the users search paramaters 
       generateButton.setOnAction(event -> {

            String genre = exploreGenreComboBox.getValue();
            String artist = exploreArtistComboBox.getValue();
            String mood = exploreMoodComboBox.getValue();
            String activity = exploreActivityComboBox.getValue();
            
            String song = songManager.exploreSongs(genre, artist, mood, activity);

            //if there is a song that matches it will build the display for the output
            if (!song.equals("Could not find a song that matched")) {
               //using string builder it will diplay the found song and what the user searched for in it
                StringBuilder displayText = new StringBuilder("Generated suggestion: " + song);

                if (genre != null) displayText.append("\nGenre: ").append(genre);
                if (artist != null) displayText.append("\nArtist: ").append(artist);
                if (mood != null) displayText.append("\nMood: ").append(mood);
                if (activity != null) displayText.append("\nActivity: ").append(activity);

                showAlert(AlertType.INFORMATION, "Suggestions!", null, "Here's a song we think you'll like! \n" + displayText.toString());
                messageText.setText(displayText.toString());            
            } else {
                showAlert(AlertType.INFORMATION, "No Match Found", null, "Sorry, we couldn't find a song matching your criteria.");
                messageText.setText("No song suggestions found."); // Update persistent box
            }
            exploreGenreComboBox.setValue(null);
            exploreArtistComboBox.setValue(null);
            exploreMoodComboBox.setValue(null);
            exploreActivityComboBox.setValue(null);
        });
        
         


        // Create the scene
        Scene scene = new Scene(mainVBox, 700, 600); // Increased height for the display box
        
        //creates the random search with the ENTER key - will display a random song from the users library 
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
               case ENTER:
                  String song = songManager.exploreSongs(null,null,null,null);
                  messageText.setText(song);
                  break;
               default:
                   break;
            }
        });
        
        //sets stage
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setTitle("Bobcat Beats");
        primaryStage.show();
    }
}


