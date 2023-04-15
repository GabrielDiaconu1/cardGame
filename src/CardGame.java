/**
 * This code defines a simple card game application using JavaFX. The game generates two random cards, one for the player and one for the computer, and displays them along with the result of the game (win, lose, or tie) and the current score.
 * The application window is created using the JavaFX Scene class and contains a label to display the result of each game, a button to generate a new game, and a score label to display the current win-loss record. The images of the cards are loaded from a set of image files stored in the "/images" directory.
 * When the player clicks the "Draw Card" button, the application generates two random card values and determines the winner based on their values. The application then updates the labels with the result of the game and the current score.
 * The getCardString method is a helper function that converts the integer values of the cards to their string representations (e.g., "Ace" for a value of 1).
 * Finally, the main method launches the application.
 *
 * @author Gabriel Diaconu 2023
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class CardGame extends Application {
    /**
     * Label to display the output
     */
    Label output;
    /**
     * Label to display the score
     */
    Label scoreLabel;
    /**
     * Number of wins
     */
    int wins = 0;

    /**
     * Number of losses
     */
    int losses = 0;

    /**
     * This method creates the game window, sets its properties and creates its components.
     *
     * @param stage JavaFX Stage object
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 700, 600);
        stage.setTitle("Card Game");
        stage.setScene(scene);
        //The code creates a new Label object and sets its text, font, background color, text color, preferred width, and position on the GUI. The label will display the text "Press the button to generate a card" with a font size of 20, a light blue background color, dark blue text color, a preferred width of 600, and positioned at coordinates (50,10).
        output = new Label();
        output.setText("Press the button to generate a card");
        output.setFont(new Font("System", 20));
        output.setStyle("-fx-background-color: lightblue;-fx-text-fill: darkblue;");
        output.setPrefWidth(600);
        output.relocate(50, 10);
        //list of images
        String[] imageNames = {"2_of_diamonds.png", "3_of_clubs.png", "4_of_hearts.png", "5_of_spades.png", "6_of_clubs.png", "7_of_clubs.png", "8_of_clubs.png", "9_of_clubs.png", "10_of_clubs.png", "jack_of_clubs.png", "queen_of_clubs.png", "king_of_clubs.png", "ace_of_clubs.png", "2_of_spades.png", "3_of_diamonds.png", "4_of_clubs.png", "5_of_hearts.png", "6_of_spades.png", "7_of_diamonds.png", "8_of_hearts.png", "9_of_spades.png", "10_of_diamonds.png", "jack_of_hearts.png", "queen_of_spades.png", "king_of_diamonds.png", "ace_of_hearts.png", "2_of_clubs.png", "3_of_hearts.png", "4_of_spades.png", "5_of_diamonds.png", "6_of_clubs.png", "7_of_hearts.png", "8_of_spades.png", "9_of_diamonds.png", "10_of_hearts.png", "jack_of_spades.png", "queen_of_diamonds.png", "king_of_hearts.png", "ace_of_spades.png"};
        //This code creates a new button object labeled "Draw Card" and sets its position on the screen to x=50 and y=475.
        Button button = new Button("Draw Card");

        button.relocate(50, 475);
        //This code creates a new label named scoreLabel, sets its text to display the current number of wins and losses, changes the font and styling of the label, sets the preferred width of the label to 600 pixels, and positions the label at coordinates (50, 500) on the screen.
        scoreLabel = new Label();
        scoreLabel.setText("Wins: " + wins + "  Losses: " + losses);
        scoreLabel.setFont(new Font("System", 16));
        scoreLabel.setStyle("-fx-background-color: lightgreen;-fx-text-fill: darkgreen;");
        scoreLabel.setPrefWidth(600);
        scoreLabel.relocate(50, 500);
        //This line of code adds the Label "output", the Button "button", and the Label "scoreLabel" to the root container.
        root.getChildren().addAll(output, button, scoreLabel);

        button.setOnAction(event -> {
            //This code generates two random numbers between 1 and 13, representing the user's and computer's cards. It then converts the numbers to strings representing the card's name and creates a file path to the image of the user's card. An image view is created using the image file and added to the GUI. Finally, the GUI displays a message indicating the user's and computer's card.
            Random rand = new Random();
            int userCard = rand.nextInt(13) + 1;
            int compCard = rand.nextInt(13) + 1;
            String userCardString = getCardString(userCard);
            String compCardString = getCardString(compCard);
            //The code snippet loads an image of a card based on the randomly generated card value, creates an ImageView object from the loaded image, sets the position of the image on the screen, and adds the ImageView object to the root node.
            String userImagePath = "/images/" + userCardString + "_of_clubs.png";
            Image userCardImage = new Image(getClass().getResource(userImagePath).toExternalForm());
            ImageView userCardImageView = new ImageView(userCardImage);
            userCardImageView.relocate(300, 90);
            root.getChildren().add(userCardImageView);

            output.setText("Your card is " + userCardString + ", the computer's card is " + compCardString);

            // Generate the computer's card image
            String compImagePath = "/images/" + compCardString + "_of_clubs.png";
            Image compCardImage = new Image(getClass().getResource(compImagePath).toExternalForm());
            ImageView compCardImageView = new ImageView(compCardImage);
            compCardImageView.relocate(300, 275);
            root.getChildren().add(compCardImageView);

            // Determine the winner and update score
            if (userCard > compCard) {
                output.setText(output.getText() + "\nYou win!");
                wins++;
            } else if (userCard < compCard) {
                output.setText(output.getText() + "\nYou lose.");
                losses++;
            } else {
                output.setText(output.getText() + "\nIt's a tie.");
            }
            scoreLabel.setText("Wins: " + wins + "  Losses: " + losses);
        });

        stage.show();
    }

    /**
     * Returns the string representation of a card value.
     *
     * @param card the card value as an integer
     * @return the string representation of the card value
     */
    private String getCardString(int card) {
        if (card == 1) {
            return "Ace";
        } else if (card == 11) {
            return "Jack";
        } else if (card == 12) {
            return "Queen";
        } else if (card == 13) {
            return "King";
        } else {
            return String.valueOf(card);
        }
    }

    /**
     * The main method launches the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}