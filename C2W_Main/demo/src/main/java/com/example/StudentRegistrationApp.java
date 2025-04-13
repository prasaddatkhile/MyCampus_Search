
package com.example;
import com.example.DataServices.FirestoreService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class StudentRegistrationApp {

    public Scene createScene(Stage primaryStage, Scene loginScene) {
        // Create the inner VBox layout
        VBox innerVBox = new VBox(20);
        innerVBox.setPadding(new Insets(20, 20, 20, 20));
        innerVBox.setAlignment(Pos.CENTER);
        innerVBox.setPrefHeight(700);
        innerVBox.setPrefWidth(700);

        Label title = new Label("!!!USER REGISTRATION!!!");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Create a GridPane for the form elements
        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(30);
        formGrid.setVgap(30);
        formGrid.setPadding(new Insets(20, 20, 20, 20));

        // Create the UI components
        Label nameLabel = new Label("Enter User Name:");
        nameLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        TextField nameField = new TextField();
        nameField.setPrefWidth(300);

        Label emailLabel = new Label("Enter Email:");
        emailLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        TextField emailField = new TextField();
        emailField.setPrefWidth(300);

        Label mobileLabel = new Label("Enter Mobile Number:");
        mobileLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        TextField mobileField = new TextField();
        mobileField.setPrefWidth(300);

        Label passwordLabel = new Label("Enter Password:");
        passwordLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(300);

        CheckBox termsCheckBox = new CheckBox("I accept the terms and conditions");

        // Add components to the GridPane
        formGrid.add(nameLabel, 0, 0);
        formGrid.add(nameField, 1, 0);
        formGrid.add(emailLabel, 0, 1);
        formGrid.add(emailField, 1, 1);
        formGrid.add(mobileLabel, 0, 2);
        formGrid.add(mobileField, 1, 2);
        formGrid.add(passwordLabel, 0, 3);
        formGrid.add(passwordField, 1, 3);
        formGrid.add(termsCheckBox, 1, 4);

        // Create buttons
        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        Button clearButton = new Button("Clear");
        clearButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");

        Label messageLabel = new Label();

        // Create an HBox for the buttons
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(signUpButton, clearButton, backButton);

        // Add the title, form, buttons, and message label to the inner VBox
        innerVBox.getChildren().addAll(title, formGrid, hbox, messageLabel);

        // Create the outer VBox layout
        VBox outerVBox = new VBox();
        outerVBox.setAlignment(Pos.CENTER);
        outerVBox.getChildren().add(innerVBox);
        outerVBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        // Event handler for the Sign Up button
        signUpButton.setOnAction(e -> {
            if (termsCheckBox.isSelected()) {
                String name = nameField.getText();
                String email = emailField.getText();
                String mobile = mobileField.getText();
                String password = passwordField.getText();

                // Store the registration data in Firestore
                storeRegistrationData(name, email, mobile, password);

                messageLabel.setText("Registration successful!");
                messageLabel.setTextFill(Color.GREEN);
            } else {
                messageLabel.setText("You must accept the terms and conditions!");
                messageLabel.setTextFill(Color.RED);
            }
        });

        // Event handler for the Clear button
        clearButton.setOnAction(e -> {
            nameField.clear();
            emailField.clear();
            mobileField.clear();
            passwordField.clear();
            termsCheckBox.setSelected(false);
            messageLabel.setText("");
        });

        // Event handler for the Back button
        backButton.setOnAction(e -> primaryStage.setScene(loginScene));

        Image backgroundImage = new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/UserREg.jpg");
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

        outerVBox.setBackground(new Background(bgImage));

        // Create and return the scene
        Scene scene = new Scene(outerVBox, 1950, 1000);
        primaryStage.fullScreenProperty().addListener((obs, wasFullScreen, isFullScreen) -> {
            if (!isFullScreen) {
                primaryStage.setWidth(800);
                primaryStage.setHeight(600);
            }
        });
        return scene;
    }

    private void storeRegistrationData(String name, String email, String mobile, String password) {
        Firestore db = FirestoreService.getFirestore();
        
        // Assume 'students' is the collection name
        CollectionReference studentsCollection = db.collection("users");
       // DocumentReference docRef =db.collection("users").document("info");

        
        Map<String, Object> studentData = new HashMap<>();
        studentData.put("username", name);
        studentData.put("email", email);
        studentData.put("mobile", mobile);
        studentData.put("password", password);

        // Add a new document with a generated ID
        studentsCollection.add(studentData);
        
    }
}
