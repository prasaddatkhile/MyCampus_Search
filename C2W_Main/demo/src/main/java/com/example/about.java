package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class about {

    public Scene createAboutScene(Stage primaryStage, Runnable backToLogin) {
        BorderPane root = new BorderPane();

        // Set background gradient
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, purple, pink, skyblue);");

        // Mentor's image at top center with curved corners
        ImageView mentorImageView = createImageViewWithRoundedCorners("images/sir.jpeg", 500, 400, 30);
        mentorImageView.setPreserveRatio(true);

        // Create a back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");

        // Action for the back button (navigate back to login)
        backButton.setOnAction(e -> backToLogin.run());

        // HBox to hold the back button and mentor image
        HBox topBox = new HBox();
        topBox.setAlignment(Pos.TOP_RIGHT);
        topBox.setPadding(new Insets(10));
        topBox.getChildren().addAll(mentorImageView, backButton);
        topBox.setSpacing(700); // Adjust spacing between image and button

        BorderPane.setAlignment(topBox, Pos.TOP_CENTER);
        BorderPane.setMargin(topBox, new Insets(50, 0, 0, 0)); // Adjust top margin here
        root.setTop(topBox);

        // VBox for thank you messages
        VBox thankYouBox = new VBox();
        thankYouBox.setAlignment(Pos.CENTER);
        thankYouBox.setSpacing(10);
        thankYouBox.setPadding(new Insets(20));

        // Adding thank you messages
        Label thankYou1Label = new Label("THANK YOU CORE2WEB FOR GIVING US THIS VALUABLE OPPORTUNITY.");
        thankYou1Label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label thankYou2Label = new Label("SPECIAL THANKS TO SHASHI SIR, PRAMOD SIR, SACHIN SIR, RAHUL SIR FOR GUIDING US THROUGH THE WHOLE PROCESS.");
        thankYou2Label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label thankYou3Label = new Label("WE HAVE GAINED DEEP KNOWLEDGE WITH YOUR HELP.");
        thankYou3Label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label thankYou4Label = new Label("THIS PROJECT WOULD NOT HAVE BEEN POSSIBLE WITHOUT YOUR MENTORSHIP.");
        thankYou4Label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        thankYouBox.getChildren().addAll(thankYou1Label, thankYou2Label, thankYou3Label, thankYou4Label);
        root.setCenter(thankYouBox);

        // GridPane for group members
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        // Adding member cards
        ImageView member1ImageView = createImageViewWithRoundedCorners("images/vaishnavi.jpeg", 200, 200, 20);
        Label member1Label = new Label("VAISHNAVI");
        member1Label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        VBox member1Box = new VBox();
        member1Box.setAlignment(Pos.CENTER);
        member1Box.getChildren().addAll(member1ImageView, member1Label);
        gridPane.add(member1Box, 0, 0);

        ImageView member2ImageView = createImageViewWithRoundedCorners("images/sai.jpeg", 200, 200, 20);
        Label member2Label = new Label("SAIDEEP");
        member2Label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        VBox member2Box = new VBox();
        member2Box.setAlignment(Pos.CENTER);
        member2Box.getChildren().addAll(member2ImageView, member2Label);
        gridPane.add(member2Box, 1, 0);

        ImageView member3ImageView = createImageViewWithRoundedCorners("images/aenna.jpeg", 200, 200, 20);
        Label member3Label = new Label("ANAMIKA");
        member3Label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        VBox member3Box = new VBox();
        member3Box.setAlignment(Pos.CENTER);
        member3Box.getChildren().addAll(member3ImageView, member3Label);
        gridPane.add(member3Box, 2, 0);

        ImageView member4ImageView = createImageViewWithRoundedCorners("images/bharat.jpeg", 200, 200, 20);
        Label member4Label = new Label("BHARAT");
        member4Label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        VBox member4Box = new VBox();
        member4Box.setAlignment(Pos.CENTER);
        member4Box.getChildren().addAll(member4ImageView, member4Label);
        gridPane.add(member4Box, 3, 0);

        root.setBottom(gridPane);

        Scene scene = new Scene(root, 1980, 1000);
        return scene;
    }

    private ImageView createImageViewWithRoundedCorners(String imageUrl, double fitWidth, double fitHeight, double cornerRadius) {
        ImageView imageView = new ImageView(new Image(imageUrl));
        imageView.setFitWidth(fitWidth);
        imageView.setFitHeight(fitHeight);

        // Create a rectangle with rounded corners for clipping
        Rectangle clip = new Rectangle();
        clip.setWidth(fitWidth);
        clip.setHeight(fitHeight);
        clip.setArcWidth(cornerRadius);
        clip.setArcHeight(cornerRadius);
        imageView.setClip(clip);

        return imageView;
    }
}