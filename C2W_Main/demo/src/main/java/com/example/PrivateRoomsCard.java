package com.example;

import com.example.DataServices.FirestoreService;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import javafx.animation.ScaleTransition;
import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class PrivateRoomsCard {

    private Firestore db;
    private VBox adminCardContainer;
    private home homePage; // Reference to the home page
    private HostServices hostServices; // HostServices instance for opening URLs

   

    public PrivateRoomsCard(home homePage, HostServices hostServices) {
    
        this.homePage = homePage;
        this.hostServices = hostServices;
    }

    public Scene createCardScene() {
        db = FirestoreService.getFirestore(); // Initialize Firestore

        // Create the main layout container
        VBox mainContainer = new VBox(20);
        mainContainer.setPrefHeight(600);
        mainContainer.setPadding(new Insets(20));
        mainContainer.setAlignment(Pos.CENTER);

        Button back = new Button("Back");
        back.setLayoutX(300);
        back.setLayoutY(200);
        back.setStyle("-fx-background-color: #FF5722; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");
        back.setOnAction(event -> {
            // Navigate back to the home page
            homePage.goToHomePage();
        });
        addHoverEffect(back); // Add hover effect to the back button

        Image backgroundImage = new Image("file:///D://MVN//C2W_Main//demo//src//main//Assets//privateroomsbutton.jpeg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.ROUND,
                BackgroundRepeat.ROUND,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
                );
                mainContainer.setBackground(new Background(background));
        // Create UI components
        Label title = new Label("Available Private Rooms");
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Create VBox for admin cards
        adminCardContainer = new VBox(20);
        adminCardContainer.setPadding(new Insets(20));
        adminCardContainer.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-border-color: #bdc3c7; -fx-border-width: 1px;");
        adminCardContainer.setAlignment(Pos.CENTER);

        // Add existing admin cards
        fetchExistingAdminData();

        // Wrap VBox in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(adminCardContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(500); // Adjust height as needed

        // HBox to hold the ScrollPane in the center
        HBox centerHBox = new HBox(scrollPane);
        centerHBox.setAlignment(Pos.CENTER);
        centerHBox.setPrefHeight(600);
        centerHBox.setPrefWidth(1300);

        // Add components to the main container
        mainContainer.getChildren().addAll(title, centerHBox, back);

        // Create the scene with the main container
        Scene scene = new Scene(mainContainer, 1950, 1000); // Adjusted size
        
        return scene;
    }

    private void fetchExistingAdminData() {
        CollectionReference adminCollection = db.collection("owner");
        Query query = adminCollection.whereEqualTo("category", "Rooms");

        try {
            QuerySnapshot querySnapshot = query.get().get();

            for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                Map<String, Object> adminData = document.getData();
                addAdminCard(adminData);
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error fetching documents: " + e.getMessage());
        }
    }

    private void addAdminCard(Map<String, Object> adminData) {
        // Extract other fields
        String name = (String) adminData.get("username");
        String email = (String) adminData.get("email");
        String category = (String) adminData.get("category");
        String businessName = (String) adminData.get("businessName");
        String details = (String) adminData.get("details");
        String contactNumber = (String) adminData.get("contactNumber");
        String address = (String) adminData.get("address");
        String fees = (String) adminData.get("fees");
        String imagePath = (String) adminData.get("imageUrl");
        String googleMapLink = (String) adminData.get("googlemaplink");
       // int rating = adminData.containsKey("rating") ? ((Long) adminData.get("rating")).intValue() : 0; // Fetch rating
       System.out.println(name);

    int rating = 0;
    if (adminData.containsKey("rating")) {
        Object ratingObj = adminData.get("rating");
        if (ratingObj instanceof Long) {
            rating = ((Long) ratingObj).intValue();
        } else if (ratingObj instanceof String) {
            try {
                rating = Integer.parseInt((String) ratingObj);
            } catch (NumberFormatException e) {
                // Handle the case where the rating string is not a valid integer
                System.err.println("Invalid rating format: " + ratingObj);
            }
        }
    }


        HBox adminCard = createAdminCard(name, email, category, businessName, details, contactNumber, address, fees, imagePath, googleMapLink, rating);

        // Add new admin card to the UI
        adminCardContainer.getChildren().add(adminCard);
    }

    private HBox createAdminCard(String name, String email, String category, String businessName,
                                 String details, String contactNumber, String address, String fees, String imagePath, String googleMapLink, int rating) {
        // Create and style admin image
        Image adminImage;
        if (imagePath != null && !imagePath.isEmpty()) {
            adminImage = new Image(imagePath);
        } else {
            adminImage = new Image("placeholder.png"); // Placeholder image URL
        }
        ImageView adminImageView = new ImageView(adminImage);
        adminImageView.setFitWidth(300); // Reduced width
        adminImageView.setFitHeight(200); // Reduced height
        adminImageView.setPreserveRatio(true);
        adminImageView.setStyle("-fx-border-color: #34495e; -fx-border-width: 1px; -fx-border-radius: 5px;");

        // Create admin information labels
        Label nameLabel = createStyledLabel("Name :   " + name);
        Label emailLabel = createStyledLabel("Email :  " + email);
        Label businessNameLabel = createStyledLabel("Business Name :  " + businessName);
        Label feesLabel = createStyledLabel("Fees : " + fees);

        // Create Google Maps hyperlink
        Hyperlink googleMapsHyperlink = new Hyperlink("View on Google Maps");
        googleMapsHyperlink.setOnAction(event -> {
            if (googleMapLink != null && !googleMapLink.isEmpty()) {
                hostServices.showDocument(googleMapLink);
            } else {
                // Handle case where googleMapLink is not available
                System.out.println("Google Maps link not available.");
            }
        });

        // Create visit button
        Button visitButton = new Button("Visit");
        visitButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 18px; -fx-padding: 10 25; -fx-background-radius: 5px;");
        visitButton.setOnAction(event -> openAdPage(businessName, contactNumber, address, fees, imagePath, details,name));
        addHoverEffect(visitButton); // Add hover effect to the visit button

        // Spacer to push the button to the right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // VBox to hold admin information
        VBox adminInfo = new VBox(5, nameLabel, emailLabel, businessNameLabel, feesLabel, googleMapsHyperlink);
        adminInfo.setPadding(new Insets(10));
        adminInfo.setAlignment(Pos.CENTER_LEFT);

        // Create rating label
        Label ratingLabel = new Label("Rating: " + rating);
        ratingLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #27ae60;"); // Adjust styling

        // Add rating label to the admin information VBox
        adminInfo.getChildren().add(ratingLabel);

        // HBox to hold entire admin card with spacer and button
        HBox adminCard = new HBox(20, adminImageView, adminInfo, spacer, visitButton);
        adminCard.setPrefWidth(1000);
        adminCard.setStyle("-fx-background-color: #e8f0fe; -fx-background-radius: 10px; -fx-border-color: #dfe6e9; -fx-border-width: 1px; -fx-padding: 20px;");
        adminCard.setAlignment(Pos.CENTER_LEFT);
        adminCard.setEffect(new DropShadow(5, Color.GRAY)); // Add shadow effect

        return adminCard;
    }

    private Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;"); // Set font size, weight, and color
        return label;
    }

    private void openAdPage(String businessName, String contact, String address, String fees, String imageUrl, String details,String name) {
        Stage adStage = new Stage();
        CoachingInstituteAdPage adPage = new CoachingInstituteAdPage(businessName, contact, address, fees, imageUrl, details,name);
        System.out.println(fees);
        try {
            adPage.start(adStage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        adStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows until this one is closed
        adStage.showAndWait();
    }

    private void addHoverEffect(Button button) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), button);
        scaleUp.setToX(1.1);
        scaleUp.setToY(1.1);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), button);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        button.setOnMouseEntered(event -> scaleUp.playFromStart());
        button.setOnMouseExited(event -> scaleDown.playFromStart());
    }
}
