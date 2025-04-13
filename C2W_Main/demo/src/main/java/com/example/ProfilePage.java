package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ProfilePage {

    private Stage primaryStage;
    private Scene profilepageScene;
    private home homePage;  // Reference to the home page
    private String username;
    private String campus;
    private String password;

    public ProfilePage(home homePage, String username, String password, String campus) {
        this.homePage = homePage;
        this.primaryStage = homePage.getStage();  // Get the stage from the home page
        this.username = username;
        this.password = password;
        this.campus = campus;
        this.profilepageScene = createProfilePage(username, password, campus);
    }
 
    public Scene createProfilePage(String username, String password, String campus) {
        // Labels and Profile Details
        Label label1 = new Label("                        Profile Details");
        label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        label1.setTextFill(Color.DARKBLUE);

        Label nameLabel = new Label("Name: " + username);
        nameLabel.setFont(Font.font(null, FontWeight.BOLD, 25));
        nameLabel.setTextFill(Color.BLACK);

        Label emailLabel = new Label("Password: " + password);
        emailLabel.setFont(Font.font(null, FontWeight.BOLD, 25));
        emailLabel.setTextFill(Color.BLACK);

        Label aboutLabel = new Label("Campus: " + campus);
        aboutLabel.setFont(Font.font(null, FontWeight.BOLD, 25));
        aboutLabel.setTextFill(Color.BLACK);

        Label addressLabel = new Label("Address:");
        addressLabel.setFont(Font.font(null, FontWeight.BOLD, 25));
        addressLabel.setTextFill(Color.BLACK);

        // Back Button
        Button backButton = new Button("Back");
        backButton.setStyle(
                "-fx-background-color: linear-gradient(#bc2b2b, #e33e41);" +
                "-fx-background-radius: 30;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;"
        );
        backButton.setFont(Font.font(null, FontWeight.BOLD, 25));
        backButton.setPrefWidth(130);
        backButton.setPrefHeight(40);
        backButton.setOnAction(event -> homePage.goToHomePage()); // Navigate back to home page

        // Save Button
        Button saveButton = new Button("Save");
        saveButton.setStyle(
                "-fx-background-color: linear-gradient(#61a2b1, #2A5058);" +
                "-fx-background-radius: 30;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;"
        );
        saveButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        saveButton.setPrefWidth(150);
        saveButton.setPrefHeight(40);
        saveButton.setOnAction(event -> {
            // Add functionality for save button action here
        });

        // Profile Image
        Image placeholderImage = new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/profileicon.jpeg"); // Placeholder image path
        ImageView imageView = new ImageView(placeholderImage);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        Circle clip = new Circle(75, 75, 75); // Ensure the circle matches the imageView dimensions
        imageView.setClip(clip);

        Button uploadButton = new Button("Upload Image");
        uploadButton.setStyle(
                "-fx-background-color: linear-gradient(#61a2b1, #2A5058);" +
                "-fx-background-radius: 30;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;"
        );
        uploadButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        uploadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
            }
        });

        VBox imagePane = new VBox(10, imageView, uploadButton);
        imagePane.setAlignment(Pos.CENTER);

        // Layouts
        VBox labelsVBox = new VBox(10, 
                nameLabel, 
                emailLabel, 
                aboutLabel, 
                addressLabel
        );
        labelsVBox.setAlignment(Pos.TOP_LEFT);
        labelsVBox.setPadding(new Insets(20, 20, 20, 20));

        VBox profileVBox = new VBox(20, label1, imagePane);
        profileVBox.setAlignment(Pos.TOP_LEFT);
        profileVBox.setPadding(new Insets(20, 20, 20, 20));

        // Create VBox containers for buttons
        VBox backButtonVBox = new VBox(backButton);
        backButtonVBox.setAlignment(Pos.TOP_LEFT);

        VBox saveButtonVBox = new VBox(saveButton);
        saveButtonVBox.setAlignment(Pos.BOTTOM_CENTER);

        // Inner VBox with background color and drop shadow
        VBox innerVBox = new VBox(30, 
                backButtonVBox,
                profileVBox, 
                labelsVBox, 
                saveButtonVBox
        );
        innerVBox.setAlignment(Pos.TOP_LEFT);
        innerVBox.setPadding(new Insets(20));
        innerVBox.setPrefSize(700, 900);
        innerVBox.setMinSize(700, 900);
        innerVBox.setMaxSize(700, 900);

        // Set background color and drop shadow
        innerVBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 20;"
        );

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.GRAY);
        dropShadow.setRadius(10);
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        innerVBox.setEffect(dropShadow);

        StackPane borderedVBox = new StackPane(innerVBox);
        borderedVBox.setPrefSize(1500, 1000);

        VBox mainVBox = new VBox(20, borderedVBox);
        mainVBox.setAlignment(Pos.CENTER);
        Image backgroundImage = new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/profilePageback.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        mainVBox.setBackground(new Background(background));

        // Scene setup
        return new Scene(mainVBox, 1950, 1000);
    }

    public Scene getScene() {
        return profilepageScene;
    }
}
