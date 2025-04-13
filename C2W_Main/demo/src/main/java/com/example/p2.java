package com.example;

// package com.project;

// import javafx.application.Application;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.stage.FileChooser;
// import javafx.stage.Stage;

// import java.io.File;

// public class p2 extends Application {

//     @Override
//     public void start(Stage prStage) throws Exception {
//         prStage.setHeight(900);
//         prStage.setWidth(1400);
//         prStage.setResizable(true);

//         // Labels and Profile Details
//         Label label1 = new Label("Profile Details");
//         label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
//         label1.setTextFill(Color.DARKSLATEBLUE);

//         Label nameLabel = new Label("Name:");
//         nameLabel.setFont(Font.font(null, FontWeight.BOLD, 15));
//         nameLabel.setTextFill(Color.DARKSLATEGRAY);
//         TextField usernametf = new TextField();
//         usernametf.setFont(Font.font(null, FontWeight.MEDIUM, 14));
//         setRoundedCorners(usernametf);
//         usernametf.setPrefWidth(350);
//         usernametf.setPrefHeight(30);
//         HBox nameHBox = new HBox(10, nameLabel, usernametf);
//         nameHBox.setAlignment(Pos.CENTER);

//         Label emailLabel = new Label("Email:");
//         emailLabel.setFont(Font.font(null, FontWeight.BOLD, 15));
//         emailLabel.setTextFill(Color.DARKSLATEGRAY);
//         TextField emailtf = new TextField();
//         emailtf.setFont(Font.font(null, FontWeight.MEDIUM, 14));
//         setRoundedCorners(emailtf);
//         emailtf.setPrefWidth(350);
//         emailtf.setPrefHeight(30);
//         HBox emailHBox = new HBox(10, emailLabel, emailtf);
//         emailHBox.setAlignment(Pos.CENTER);

//         Label aboutLabel = new Label("About:");
//         aboutLabel.setFont(Font.font(null, FontWeight.BOLD, 15));
//         aboutLabel.setTextFill(Color.DARKSLATEGRAY);
//         TextField abouttf = new TextField();
//         abouttf.setFont(Font.font(null, FontWeight.MEDIUM, 14));
//         setRoundedCorners(abouttf);
//         abouttf.setPrefWidth(350);
//         abouttf.setPrefHeight(30);
//         HBox aboutHBox = new HBox(10, aboutLabel, abouttf);
//         aboutHBox.setAlignment(Pos.CENTER);

//         Label addressLabel = new Label("Address:");
//         addressLabel.setFont(Font.font(null, FontWeight.BOLD, 15));
//         addressLabel.setTextFill(Color.DARKSLATEGRAY);
//         TextField addresstf = new TextField();
//         addresstf.setFont(Font.font(null, FontWeight.MEDIUM, 14));
//         setRoundedCorners(addresstf);
//         addresstf.setPrefWidth(350);
//         addresstf.setPrefHeight(30);
//         HBox addressHBox = new HBox(10, addressLabel, addresstf);
//         addressHBox.setAlignment(Pos.CENTER);

//         // Profile Image
//        // Image image = new Image("");
//        // ImageView imageView = new ImageView(image);
//         //imageView.setFitWidth(150);
//         imageView.setFitHeight(150);
//         Circle clip = new Circle(75, 75, 75);
//         imageView.setClip(clip);

//         imageView.setOnMouseClicked(event -> {
//             FileChooser fileChooser = new FileChooser();
//             fileChooser.getExtensionFilters().addAll(
//                     new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg", "*.gif")
//             );
//             File selectedFile = fileChooser.showOpenDialog(prStage);
//             if (selectedFile != null) {
//                 Image newImage = new Image(selectedFile.toURI().toString());
//                 imageView.setImage(newImage);
//                 imageView.setClip(clip);
//             }
//         });

//         StackPane imagePane = new StackPane(imageView);
//         imagePane.setAlignment(Pos.CENTER);

//         // Save Button
//         Button savebutton = new Button("Save");
//         savebutton.setStyle(
//                 "-fx-background-color: linear-gradient(#61a2b1, #2A5058);" +
//                 "-fx-background-radius: 30;" +
//                 "-fx-text-fill: white;" +
//                 "-fx-font-size: 15px;"
//         );
//         savebutton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
//         savebutton.setPrefWidth(130);
//         savebutton.setPrefHeight(50);
//         savebutton.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent event) {
//                 // Add functionality for save button action here
//             }
//         });

//         // Back Button
//         Button backbutton = new Button("Back");
//         backbutton.setStyle(
//                 "-fx-background-color: linear-gradient(#bc2b2b, #e33e41);" +
//                 "-fx-background-radius: 30;" +
//                 "-fx-text-fill: white;" +
//                 "-fx-font-size: 15px;"
//         );
//         backbutton.setFont(Font.font(null, FontWeight.BOLD, 25));
//         backbutton.setPrefWidth(130);
//         backbutton.setPrefHeight(50);
//         backbutton.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent event) {
//                 // Add functionality for back button action here
//             }
//         });

//         VBox bbutton = new VBox(backbutton);
//         bbutton.setAlignment(Pos.TOP_LEFT);

//         VBox sbutton = new VBox(savebutton);
//         sbutton.setAlignment(Pos.BOTTOM_RIGHT);

//         // Layouts
//         VBox labelsvbox = new VBox(22, nameHBox, emailHBox, aboutHBox, addressHBox);
//         labelsvbox.setAlignment(Pos.CENTER);

//         VBox profilevbox = new VBox(20, label1, imagePane);
//         profilevbox.setAlignment(Pos.CENTER);

//         // Include backbutton in VBox2
//         VBox VBox2 = new VBox(50, bbutton, profilevbox, labelsvbox, sbutton);
//         VBox2.setAlignment(Pos.CENTER);
//         VBox2.setPrefSize(500, 700);
//         VBox2.setMinSize(500, 700);
//         VBox2.setMaxSize(500, 700);
//         VBox2.setStyle(
//                 "-fx-background-color: #f0f4f7;" +
//                 "-fx-border-color: #2a5058;" +
//                 "-fx-border-width: 3;" +
//                 "-fx-border-radius: 45 0 45 0;" +
//                 "-fx-padding: 20;" +
//                 "-fx-background-radius: 45 0 45 0;"  // Rounded background
//         );

//         StackPane borderedVBox = new StackPane(VBox2);
//         borderedVBox.setPrefSize(1400, 900);

//         VBox mainVBox = new VBox(20, borderedVBox);
//         mainVBox.setAlignment(Pos.CENTER);
//         mainVBox.setStyle("-fx-background-color: #dde7ec;");

//         // Scene setup
//         Scene scene = new Scene(mainVBox, 1400, 900);
//         prStage.setScene(scene);
//         prStage.show();
//     }

//     private void setRoundedCorners(TextField textField) {
//         textField.setStyle(
//                 "-fx-background-radius: 14; " +
//                 "-fx-border-radius: 14; " +
//                 "-fx-padding: 10 20; " +
//                 "-fx-border-color: #2a5058; " +  // Updated border color
//                 "-fx-border-width: 1.3;"
//         );
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class p2 extends Application {

    @Override
    public void start(Stage prStage) throws Exception {
        prStage.setHeight(900);
        prStage.setWidth(1400);
        prStage.setResizable(true);

        // Labels and Profile Details
        Label label1 = new Label("Profile Details");
        label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        label1.setTextFill(Color.DARKSLATEBLUE);

        // Name input
        Label nameLabel = new Label("Name:");
        nameLabel.setFont(Font.font(null, FontWeight.BOLD, 15));
        nameLabel.setTextFill(Color.DARKSLATEGRAY);
        TextField usernametf = new TextField();
        setRoundedCorners(usernametf);
        usernametf.setPrefWidth(350);
        usernametf.setPrefHeight(30);
        HBox nameHBox = new HBox(10, nameLabel, usernametf);
        nameHBox.setAlignment(Pos.CENTER);

        // Email input
        Label emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font(null, FontWeight.BOLD, 15));
        emailLabel.setTextFill(Color.DARKSLATEGRAY);
        TextField emailtf = new TextField();
        setRoundedCorners(emailtf);
        emailtf.setPrefWidth(350);
        emailtf.setPrefHeight(30);
        HBox emailHBox = new HBox(10, emailLabel, emailtf);
        emailHBox.setAlignment(Pos.CENTER);

        // About input
        Label aboutLabel = new Label("About:");
        aboutLabel.setFont(Font.font(null, FontWeight.BOLD, 15));
        aboutLabel.setTextFill(Color.DARKSLATEGRAY);
        TextField abouttf = new TextField();
        setRoundedCorners(abouttf);
        abouttf.setPrefWidth(350);
        abouttf.setPrefHeight(30);
        HBox aboutHBox = new HBox(10, aboutLabel, abouttf);
        aboutHBox.setAlignment(Pos.CENTER);

        // Address input
        Label addressLabel = new Label("Address:");
        addressLabel.setFont(Font.font(null, FontWeight.BOLD, 15));
        addressLabel.setTextFill(Color.DARKSLATEGRAY);
        TextField addresstf = new TextField();
        setRoundedCorners(addresstf);
        addresstf.setPrefWidth(350);
        addresstf.setPrefHeight(30);
        HBox addressHBox = new HBox(10, addressLabel, addresstf);
        addressHBox.setAlignment(Pos.CENTER);

        // Profile Image (placeholder)
        Image image = new Image("https://via.placeholder.com/150"); // Or leave blank
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        Circle clip = new Circle(75, 75, 75);
        imageView.setClip(clip);

        // Image picker on click
        imageView.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );
            File selectedFile = fileChooser.showOpenDialog(prStage);
            if (selectedFile != null) {
                Image newImage = new Image(selectedFile.toURI().toString());
                imageView.setImage(newImage);
                imageView.setClip(clip);
            }
        });

        StackPane imagePane = new StackPane(imageView);
        imagePane.setAlignment(Pos.CENTER);

        // Save Button
        Button savebutton = new Button("Save");
        savebutton.setStyle(
                "-fx-background-color: linear-gradient(#61a2b1, #2A5058);" +
                        "-fx-background-radius: 30;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;"
        );
        savebutton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        savebutton.setPrefWidth(130);
        savebutton.setPrefHeight(50);
        savebutton.setOnAction(event -> {
            // Add save logic
        });

        // Back Button
        Button backbutton = new Button("Back");
        backbutton.setStyle(
                "-fx-background-color: linear-gradient(#bc2b2b, #e33e41);" +
                        "-fx-background-radius: 30;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;"
        );
        backbutton.setFont(Font.font(null, FontWeight.BOLD, 25));
        backbutton.setPrefWidth(130);
        backbutton.setPrefHeight(50);
        backbutton.setOnAction(event -> {
            // Add back logic
        });

        VBox bbutton = new VBox(backbutton);
        bbutton.setAlignment(Pos.TOP_LEFT);

        VBox sbutton = new VBox(savebutton);
        sbutton.setAlignment(Pos.BOTTOM_RIGHT);

        // Layouts
        VBox labelsvbox = new VBox(22, nameHBox, emailHBox, aboutHBox, addressHBox);
        labelsvbox.setAlignment(Pos.CENTER);

        VBox profilevbox = new VBox(20, label1, imagePane);
        profilevbox.setAlignment(Pos.CENTER);

        VBox VBox2 = new VBox(50, bbutton, profilevbox, labelsvbox, sbutton);
        VBox2.setAlignment(Pos.CENTER);
        VBox2.setPrefSize(500, 700);
        VBox2.setStyle(
                "-fx-background-color: #f0f4f7;" +
                        "-fx-border-color: #2a5058;" +
                        "-fx-border-width: 3;" +
                        "-fx-border-radius: 45 0 45 0;" +
                        "-fx-padding: 20;" +
                        "-fx-background-radius: 45 0 45 0;"
        );

        StackPane borderedVBox = new StackPane(VBox2);
        borderedVBox.setPrefSize(1400, 900);

        VBox mainVBox = new VBox(20, borderedVBox);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setStyle("-fx-background-color: #dde7ec;");

        Scene scene = new Scene(mainVBox, 1400, 900);
        prStage.setScene(scene);
        prStage.show();
    }

    private void setRoundedCorners(TextField textField) {
        textField.setStyle(
                "-fx-background-radius: 14; " +
                        "-fx-border-radius: 14; " +
                        "-fx-padding: 10 20; " +
                        "-fx-border-color: #2a5058; " +
                        "-fx-border-width: 1.3;"
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
