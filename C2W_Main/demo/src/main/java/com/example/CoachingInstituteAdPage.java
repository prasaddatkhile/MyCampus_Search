package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import com.example.DataServices.FirestoreService;

public class CoachingInstituteAdPage extends Application {

    private static final int STAR_SIZE = 20; // Size of each star
    private static final int STAR_COUNT = 5; // Number of stars in the rating system

    private String businessName;
    private String contact;
    private String address;
    private String fees;
    private String imageUrl;
    private String details;
    private String username; // Username to identify the user

    private FirestoreService firestoreService;

    public CoachingInstituteAdPage(String businessName, String contact, String address, String fees, String imageUrl, String details, String name) {
        this.businessName = businessName;
        this.contact = contact;
        this.address = address;
        this.fees = fees;
        this.imageUrl = imageUrl;
        this.details = details;
        this.username = name; // Ensure the username is properly set
        FirestoreService.initializeFb();
        this.firestoreService = new FirestoreService();
        System.out.println(username);
    }
    
   
  
    


    






    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Coaching Institute Advertisement");

        // Institute details
        Label nameLabel = new Label(businessName);
        nameLabel.setFont(new Font("Arial Bold", 24));
        nameLabel.setTextFill(Color.DARKBLUE);

        Label contactLabel = new Label("Contact: " + contact);
        contactLabel.setFont(new Font("Arial", 16));

        Label addressLabel = new Label("Address: " + address);
        addressLabel.setFont(new Font("Arial", 14));

        Label educationalDetailsLabel = new Label("");
        educationalDetailsLabel.setFont(new Font("Arial", 14));

        // Fee structure
        Label feesLabel = new Label("Fees Structure:");
        feesLabel.setFont(new Font("Arial Bold", 18));
        ListView<String> feesListView = new ListView<>();
        feesListView.getItems().addAll(details.split(","));
        feesListView.setMaxHeight(100);

        // Institute photo
        ImageView instituteImageView = new ImageView();
        try {
            Image instituteImage = new Image(imageUrl);
            instituteImageView.setImage(instituteImage);
        } catch (Exception e) {
            System.out.println("Image not found. Please check the path.");
        }
        instituteImageView.setFitWidth(300);
        instituteImageView.setFitHeight(200);
        instituteImageView.setPreserveRatio(true);
        instituteImageView.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

        // Rating section
        Label ratingLabel = new Label("Rate Us:");
        ratingLabel.setFont(new Font("Arial Bold", 18));
        HBox ratingBox = new HBox(5);
        ratingBox.setAlignment(Pos.CENTER);
        Polygon[] stars = new Polygon[STAR_COUNT];

        for (int i = 0; i < STAR_COUNT; i++) {
            Polygon star = createStar(STAR_SIZE);
            star.setFill(Color.GRAY);
            stars[i] = star;

            final int index = i;
            star.setOnMouseClicked(event -> highlightStars(stars, index));

            ratingBox.getChildren().add(star);
        }

        // Review section
        Label reviewLabel = new Label("Leave a Review:");
        reviewLabel.setFont(new Font("Arial Bold", 18));
        TextArea reviewTextArea = new TextArea();
        reviewTextArea.setPromptText("Write your review here...");
        reviewTextArea.setWrapText(true);
        reviewTextArea.setMaxHeight(80);
        reviewTextArea.setMaxWidth(300);

        Button submitReviewButton = new Button("Submit Review");
        submitReviewButton.setFont(new Font("Arial Bold", 14));
        submitReviewButton.setStyle("-fx-background-color: darkblue; -fx-text-fill: white;");

        submitReviewButton.setOnAction(e -> {
            int rating = getRating(stars);
            String review = reviewTextArea.getText();
            if (rating > 0 && !review.isEmpty()) {
                // Handle the submission of the rating and review
                System.out.println("Rating: " + rating + " stars");
                System.out.println("Review: " + review);
        
                // Store review in Firestore
                firestoreService.addReview(username, rating, review);
        
                // Show confirmation alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Review Submitted");
                alert.setHeaderText(null);
                alert.setContentText("Your rating and review have been submitted successfully!");
                alert.showAndWait();
        
                reviewTextArea.clear();
                clearStars(stars);
        
                // Close the current stage
                primaryStage.close();
            } else {
                // Handle the case where the user did not fill all fields
                System.out.println("Please provide a rating and a review.");
        
                // Show warning alert
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incomplete Submission");
                alert.setHeaderText(null);
                alert.setContentText("Please provide both a rating and a review.");
                alert.showAndWait();
            }
        });
        

        VBox instituteDetails = new VBox(10, nameLabel, instituteImageView, educationalDetailsLabel, feesLabel,
                feesListView, contactLabel, addressLabel, ratingLabel, ratingBox, reviewLabel, reviewTextArea,
                submitReviewButton);
        instituteDetails.setPadding(new Insets(20));
        instituteDetails.setAlignment(Pos.TOP_CENTER);

        // Set background color
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        instituteDetails.setBackground(background);

        Scene scene = new Scene(instituteDetails, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Polygon createStar(double size) {
        Polygon star = new Polygon();
        star.getPoints().addAll(
                size * 0.5, 0.0,
                size * 0.6, size * 0.35,
                size, size * 0.35,
                size * 0.7, size * 0.55,
                size * 0.8, size,
                size * 0.5, size * 0.7,
                size * 0.2, size,
                size * 0.3, size * 0.55,
                0.0, size * 0.35,
                size * 0.4, size * 0.35);
        return star;
    }

    private void highlightStars(Polygon[] stars, int index) {
        for (int i = 0; i < stars.length; i++) {
            if (i <= index) {
                stars[i].setFill(Color.GOLD);
            } else {
                stars[i].setFill(Color.GRAY);
            }
        }
    }

    private int getRating(Polygon[] stars) {
        int rating = 0;
        for (Polygon star : stars) {
            if (star.getFill() == Color.GOLD) {
                rating++;
            }
        }
        return rating;
    }

    private void clearStars(Polygon[] stars) {
        for (Polygon star : stars) {
            star.setFill(Color.GRAY);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
