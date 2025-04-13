package com.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HomePage extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setHeight(700); 
        primaryStage.setWidth(1000); 
        primaryStage.setResizable(true); 

        //  background image
        Image image = new Image("file:images/w1.png");

        // Labels
        Label titleLabel = new Label("Admin Homepage");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        titleLabel.setTextFill(Color.BLUE);

        Label welcomeLabel = new Label("\"Welcome Admin...!!\"");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        welcomeLabel.setTextFill(Color.MAROON);

        Label selectLabel = new Label("Select User Category");
        selectLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        selectLabel.setTextFill(Color.BLACK);

        // Checkboxes
        CheckBox studentCheckBox = new CheckBox("Student");
        studentCheckBox.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        studentCheckBox.setMinSize(150, 30);

        CheckBox ownerCheckBox = new CheckBox("Owner");
        ownerCheckBox.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        ownerCheckBox.setMinSize(150, 30);

        // ChoiceBox
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Select Name", "Mess", "Hostels", "Electrician", "Plumber", "Classes", "Hostels");
        choiceBox.setValue("Select Name");
        choiceBox.setMinSize(200, 30);
        choiceBox.setStyle("-fx-font-size: 14; -fx-background-color: YELLOW; -fx-text-fill: BLACK;");
        choiceBox.setDisable(true);

        // Event handling for checkboxes
        studentCheckBox.setOnAction(e -> {
            if (studentCheckBox.isSelected()) {
                ownerCheckBox.setSelected(false);
                choiceBox.setDisable(true);
            }
        });

        ownerCheckBox.setOnAction(e -> {
            if (ownerCheckBox.isSelected()) {
                studentCheckBox.setSelected(false);
                choiceBox.setDisable(false);
            } else {
                choiceBox.setDisable(true);
            }
        });

        // Buttons
        Button findButton = new Button(" Find ");
        findButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // Handle find button action
            }
        });
        findButton.setMinSize(100, 30);
        findButton.setStyle("-fx-font-size: 14");
        findButton.setStyle("-fx-background-color:GREEN; -fx-text-fill:WHITE");
        findButton.getStyleClass().add("action-button"); 

        Button backButton = new Button(" Back ");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // Handle back button action
            }
            
        });
        backButton.setMinSize(100, 30);
        backButton.setStyle("-fx-font-size: 14;");
        backButton.setStyle("-fx-background-color:RED; -fx-text-fill:WHITE");
        backButton.getStyleClass().add("action-button");

        Button clearButton = new Button(" Clear ");
        clearButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // Handle clear button action
            }
        });
        clearButton.setMinSize(100, 30);
        clearButton.setStyle("-fx-font-size: 14px;");
        clearButton.setStyle("-fx-background-color:BLUE; -fx-text-fill:WHITE");
        clearButton.getStyleClass().add("action-button"); 

        // Layouts
        VBox vbox = new VBox(20, titleLabel, welcomeLabel, selectLabel, studentCheckBox, ownerCheckBox, choiceBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(50, 50, 50, 50));
    
        HBox buttonhbox = new HBox(20, findButton, backButton, clearButton);
        buttonhbox.setAlignment(Pos.CENTER);

        VBox vbox2 = new VBox(40, vbox, buttonhbox);
        vbox2.setAlignment(Pos.CENTER);
        

        Scene scene = new Scene(vbox2);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Dashboard"); 
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}