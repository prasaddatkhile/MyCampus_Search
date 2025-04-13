package com.example;

import com.example.DataServices.FirestoreService;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import javafx.util.Duration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AdminRegistration extends Application {

    private ImageView imageView;
    private SignUpLoginApp signUpLoginApp;
    private File selectedImageFile;

    public AdminRegistration() {
        // No-argument constructor
    }

    public AdminRegistration(SignUpLoginApp signUpLoginApp) {
        this.signUpLoginApp = signUpLoginApp;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = createRegistrationScene(primaryStage);

        // Apply fade-in animation to the scene
        fadeInTransition(scene.getRoot());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Owner Registration");
        primaryStage.show();
    }

    public Scene createRegistrationScene(Stage primaryStage) {
        // Create the main layout container
        GridPane mainContainer = new GridPane();
        mainContainer.setPadding(new Insets(20));
        mainContainer.setVgap(20);
        mainContainer.setHgap(20);
        mainContainer.setAlignment(Pos.CENTER);

        // Create UI components
        Label title = new Label("!!Owner Registration!!");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #34495e;");
        GridPane.setColumnSpan(title, 2);
        GridPane.setHalignment(title, HPos.CENTER);

        Label nameLabel = createLabel("Admin Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter admin name");
        nameField.setPrefWidth(300);

        Label emailLabel = createLabel("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter email");
        emailField.setPrefWidth(300);

        Label passwordLabel = createLabel("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        passwordField.setPrefWidth(300);

        Label categoryLabel = createLabel("Choose Business Category:");
        ChoiceBox<String> categoryChoiceBox = new ChoiceBox<>();
        categoryChoiceBox.getItems().addAll("select", "Mess","Rooms", "Hostel", "Tuition/classes", "Maid", "Hospital", "Electrician", "Plumber", "Stationary");
        categoryChoiceBox.getSelectionModel().selectFirst();
        categoryChoiceBox.setPrefWidth(300);

        Label businessnameLabel = createLabel("Business Name:");
        TextField businessnameField = new TextField();
        businessnameField.setPromptText("Enter business name");
        businessnameField.setPrefWidth(300);

        Label photoLabel = createLabel("Photo:");
        Button photoButton = createStyledButton("Choose Photo", "#3498db");
        imageView = new ImageView();
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);

        Label descriptionLabel = createLabel("Details:");
        TextArea descriptionArea = new TextArea();
        descriptionArea.setWrapText(true);
        descriptionArea.setPromptText("Enter details/fees");
        descriptionArea.setPrefWidth(300);
        descriptionArea.setPrefHeight(100);

        Label contactNumberLabel = createLabel("Contact Number:");
        TextField contactNumberField = new TextField();
        contactNumberField.setPromptText("Enter contact number");
        contactNumberField.setPrefWidth(300);

        Label addressLabel = createLabel("Address:");
        TextField addressField = new TextField();
        addressField.setPromptText("Enter address");
        addressField.setPrefWidth(300);

        Label googleMapLink = createLabel("GoogleMaplink");
        TextField link = new TextField();
        link.setPromptText("Enter MapLink");
        link.setPrefWidth(300);

        Label ratingLabel = createLabel("Rating:");
        TextField ratingField = new TextField();
        ratingField.setPrefWidth(300);

        Label reviewLabel = createLabel("Review:");
        TextField reviewField = new TextField();
        reviewField.setPrefWidth(300);

        Label feesLabel = createLabel("Fees/Rent:");
        TextField feesField = new TextField();
        feesField.setPromptText("Enter fees/rent");
        feesField.setPrefWidth(300);

        HBox buttonContainer = new HBox(20);
        buttonContainer.setAlignment(Pos.CENTER);
        Button signUpButton = createStyledButton("Sign Up", "#2ecc71");
        Button updateButton = createStyledButton("Update", "#f39c12");
        Button clearButton = createStyledButton("Clear", "#e74c3c");
        Button backButton = createStyledButton("Back", "#3498db");
        buttonContainer.getChildren().addAll(backButton, signUpButton, updateButton, clearButton);
        GridPane.setColumnSpan(buttonContainer, 2);
        GridPane.setHalignment(buttonContainer, HPos.CENTER);

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: #34495e; -fx-font-size: 16px;");
        GridPane.setColumnSpan(messageLabel, 2);
        GridPane.setHalignment(messageLabel, HPos.CENTER);

        // Add UI components to the GridPane
        mainContainer.add(title, 0, 0, 2, 1);
        mainContainer.add(nameLabel, 0, 1);
        mainContainer.add(nameField, 1, 1);
        mainContainer.add(emailLabel, 0, 2);
        mainContainer.add(emailField, 1, 2);
        mainContainer.add(passwordLabel, 0, 3);
        mainContainer.add(passwordField, 1, 3);
        mainContainer.add(categoryLabel, 0, 4);
        mainContainer.add(categoryChoiceBox, 1, 4);
        mainContainer.add(businessnameLabel, 0, 5);
        mainContainer.add(businessnameField, 1, 5);
        mainContainer.add(photoLabel, 0, 6);
        mainContainer.add(photoButton, 1, 6);
        mainContainer.add(imageView, 1, 7);
        mainContainer.add(descriptionLabel, 0, 8);
        mainContainer.add(descriptionArea, 1, 8);
        mainContainer.add(contactNumberLabel, 0, 9);
        mainContainer.add(contactNumberField, 1, 9);
        mainContainer.add(addressLabel, 0, 10);
        mainContainer.add(addressField, 1, 10);
        mainContainer.add(googleMapLink, 0, 11);
        mainContainer.add(link, 1, 11);
      //  mainContainer.add(feesLabel, 0, 12);
      //  mainContainer.add(feesField, 1, 12);
        mainContainer.add(buttonContainer, 0, 14, 2, 1);
        mainContainer.add(messageLabel, 0, 15, 2, 1);

     /*   // Wrap the main container in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(mainContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefWidth(800);
        scrollPane.setStyle("-fx-background-color: transparent;");
        */

        // Create the root StackPane
      //  StackPane root = new StackPane();
        //root.getChildren().add(scrollPane);

        VBox vb1=new VBox(mainContainer);

        // Set the background image
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/admin1.jpeg"),
                BackgroundRepeat.ROUND,
                BackgroundRepeat.ROUND,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        vb1.setBackground(new Background(backgroundImage));

        // Add the ScrollPane to the StackPane
     //   root.getChildren().add(scrollPane);

        // Create the scene with the StackPane
        Scene scene = new Scene(vb1, 1950, 1000);

        // Event handler for the Photo button
        photoButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
            selectedImageFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedImageFile != null) {
                Image image = new Image(selectedImageFile.toURI().toString());
                imageView.setImage(image);
            }
        });

        // Event handler for the Sign Up button
        signUpButton.setOnAction(e -> {
            // Retrieve data from the input fields
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String category = categoryChoiceBox.getValue();
            String businessName = businessnameField.getText();
            String details = descriptionArea.getText();
            String contactNumber = contactNumberField.getText();
            String address = addressField.getText();
            String fees = feesField.getText();
            String imageUrl = null;
            String mapLink = link.getText();
            String rating = ratingField.getText();
            String review = reviewField.getText();

            // Upload image if one was selected
            if (selectedImageFile != null) {
                String fileName = "admin_images/" + selectedImageFile.getName();
                imageUrl = ImageUploder.uploadImage(selectedImageFile.getAbsolutePath(), fileName);
            }

            // Store the registration data in Firestore
            storeAdminData(name, email, password, category, businessName, details, contactNumber, address, fees, imageUrl, mapLink, rating, review);

            // Update the message label to show success
            messageLabel.setText("Sign up successful!");
            messageLabel.setTextFill(Color.GREEN);
        });

        // Event handler for the Update button
        updateButton.setOnAction(e -> {
            // Add your update logic here
            messageLabel.setText("Update successful!");
            messageLabel.setTextFill(Color.BLUE);
        });

        // Event handler for the Clear button
        clearButton.setOnAction(e -> {
            nameField.clear();
            businessnameField.clear();
            emailField.clear();
            passwordField.clear();
            imageView.setImage(null);
            descriptionArea.clear();
            contactNumberField.clear();
            addressField.clear();
            feesField.clear();
            categoryChoiceBox.getSelectionModel().selectFirst();
            messageLabel.setText("");
        });

        // Event handler for the Back button
        backButton.setOnAction(e -> {
            // Return to the login scene
            primaryStage.setScene(signUpLoginApp.getLoginScene(primaryStage));
        });

        return scene;
    }

    private void storeAdminData(String name, String email, String password, String category, String businessName,
                                String details, String contactNumber, String address, String fees, String imageUrl, String mapLink, String rating, String review) {
        Firestore db = FirestoreService.getFirestore();

        CollectionReference ownerCollection = db.collection("owner");

        Map<String, Object> adminData = new HashMap<>();
        adminData.put("username", name);
        adminData.put("email", email);
        adminData.put("password", password);
        adminData.put("category", category);
        adminData.put("businessName", businessName);
        adminData.put("details", details);
        adminData.put("contactNumber", contactNumber);
        adminData.put("address", address);
        adminData.put("fees", fees);
        adminData.put("googlemaplink", mapLink);
        adminData.put("rating", rating);
        adminData.put("review", review);
        if (imageUrl != null) {
            adminData.put("imageUrl", imageUrl);
        }

        ownerCollection.add(adminData).addListener(() -> {
            System.out.println("Owner data stored successfully");
        }, Runnable::run);
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 18px; -fx-text-fill: #34495e; -fx-font-weight: bold;");
        return label;
    }

    private Button createStyledButton(String text, String backgroundColor) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-background-radius: 5;");
        return button;
    }

    private void fadeInTransition(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(1500), node);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    public static void main(String[] args) {
        Application.launch(AdminRegistration.class, args);
    }
}
