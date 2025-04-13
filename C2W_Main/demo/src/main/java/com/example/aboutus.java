package com.example;

import javafx.application.Application;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class aboutus extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Profile Image
        ImageView imageView = new ImageView(new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/profilePageback.jpg"));
        imageView.setFitWidth(300);  // Set width
        imageView.setFitHeight(300);  // Set height
        imageView.setClip(new javafx.scene.shape.Circle(75, 75, 75));  // Circular clip

        // About Section
        Label titleLabel = new Label("   Shashi Sir");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label descriptionLabel = new Label("");
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(300);

       

        VBox aboutVBox = new VBox(10, titleLabel, descriptionLabel);
        aboutVBox.setAlignment(Pos.CENTER_LEFT);

        VBox aboutHBox = new VBox(10, imageView, aboutVBox);
        aboutHBox.setAlignment(Pos.CENTER);
        aboutHBox.setMaxWidth(300);

        // Sections 1, 2, 3
        Label section1Title = new Label("Mentor");
        section1Title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Label section1Description = new Label("Rahul Sir");
        section1Description.setWrapText(true);
        section1Description.setMaxWidth(180);

        Label section2Title = new Label("Our  Team :");
        section2Title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Label section2Description2 = new Label("1 : Umesh Sake ");
        Label section2Description1 = new Label("2 : Prasad Datkhile ");
        section2Description2.setWrapText(true);
        section2Description2.setMaxWidth(180);

        Label section3Title = new Label("");
        section3Title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Label section3Description = new Label("Our Goal :");
        section3Description.setWrapText(true);
        section3Description.setMaxWidth(300);

        VBox section1VBox = new VBox(5, section1Title, section1Description);
        section1VBox.setStyle("-fx-background-color: #E0E6ED; -fx-padding: 10;");
        section1VBox.setMaxWidth(300);
        section1VBox.setAlignment(Pos.TOP_CENTER);

        VBox section2VBox = new VBox(5, section2Title,section2Description2, section2Description1);
        section2VBox.setStyle("-fx-background-color: #A0B6ED; -fx-padding: 10;");
        section2VBox.setMaxWidth(300);
        section2VBox.setAlignment(Pos.TOP_CENTER);

        VBox section3VBox = new VBox(5, section3Title, section3Description);
        section3VBox.setStyle("-fx-background-color: #C0D6ED; -fx-padding: 10;");
        section3VBox.setMaxWidth(200);
        section3VBox.setAlignment(Pos.TOP_CENTER);

        HBox sectionsHBox = new HBox(20, section1VBox, section2VBox, section3VBox);
        sectionsHBox.setAlignment(Pos.CENTER);

        VBox mainVBox = new VBox(30, aboutHBox, sectionsHBox);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setPadding(new javafx.geometry.Insets(20));

        Image backgroundImage = new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/background.jpg");
        BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.ROUND,
            BackgroundRepeat.ROUND,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        mainVBox.setBackground(new Background(background));

        // Scene setup
        Scene scene = new Scene(mainVBox, 1950, 1000);

        primaryStage.setTitle("About Us");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
