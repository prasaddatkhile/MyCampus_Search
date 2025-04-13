package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AdminLoginPage {

    private Scene adminLoginScene;
    public Scene createScene(Stage primaryStage) {
        // GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(50, 50, 50, 50));
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setMaxWidth(1950);
        grid.setMaxHeight(1000);

        Label titleLabel = new Label("!!Admin Login!!");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.web("#2c3e50"));
        GridPane.setConstraints(titleLabel, 1, 0, 2, 1);
        grid.getChildren().add(titleLabel);

        Label nameLabel = new Label("Admin Name:");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        GridPane.setConstraints(nameLabel, 0, 1);
        grid.getChildren().add(nameLabel);

        TextField nameInput = createStyledTextField();
        GridPane.setConstraints(nameInput, 1, 1, 2, 1);
        grid.getChildren().add(nameInput);

        Label idLabel = new Label("Admin ID:");
        idLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        GridPane.setConstraints(idLabel, 0, 2);
        grid.getChildren().add(idLabel);

        TextField idInput = createStyledTextField();
        GridPane.setConstraints(idInput, 1, 2, 2, 1);
        grid.getChildren().add(idInput);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        GridPane.setConstraints(passwordLabel, 0, 3);
        grid.getChildren().add(passwordLabel);

        PasswordField passwordInput = createStyledPasswordField();
        GridPane.setConstraints(passwordInput, 1, 3, 2, 1);
        grid.getChildren().add(passwordInput);

        // Back button
        Button backButton = createStyledButton("Back", "#3498db");
        GridPane.setConstraints(backButton, 0, 4);
        grid.getChildren().add(backButton);
        backButton.setOnAction(e -> {
            primaryStage.setScene(SignUpLoginApp.loginScene);
        });

        // Login button
        Button loginButton = createStyledButton("Login", "#2ecc71");
        GridPane.setConstraints(loginButton, 1, 4);
        grid.getChildren().add(loginButton);

        loginButton.setOnAction(e -> {
            String name = nameInput.getText();
            String id = idInput.getText();
            String password = passwordInput.getText();

            // Hardcoded authentication
            if (name.equals("Umesh") && id.equals("Umesh1971") && password.equals("2004")) {
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + name + "!");
                navigateToAdminHomepage(primaryStage);
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid credentials, please try again.");
            }
        });

        // Clear button
        Button clearButton = createStyledButton("Clear", "#e74c3c");
        GridPane.setConstraints(clearButton, 2, 4);
        grid.getChildren().add(clearButton);

        clearButton.setOnAction(e -> {
            nameInput.clear();
            idInput.clear();
            passwordInput.clear();
        });
        double sceneWidth = 1950;
        double sceneHeight = 1000;

        Image backgroundImage = new Image("file:D://MVN//C2W_Main//demo//src//main//Assets//360_F_105726195_r0MpL0Noxp2NeMh3RsRwCskbeL7ensjV.jpg");
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.ROUND,
                BackgroundRepeat.ROUND,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(sceneWidth, sceneHeight, false, false, true, false));
        grid.setBackground(new Background(bgImage));

        // Scene and add grid to it
        Scene scene = new Scene(grid, sceneWidth, sceneHeight);
        this.adminLoginScene=scene;
        // scene.setFill(Color.AQUA);
        return scene;
    }

    private void navigateToAdminHomepage(Stage primaryStage) {
        AdminHomepage adminHomepage = new AdminHomepage(primaryStage,this);
        primaryStage.setScene(adminHomepage.getScene());
        primaryStage.show();
        // try {
        //     adminHomepage.start(primaryStage); // Start the AdminHomepage scene
        // } catch (Exception ex) {
        //     ex.printStackTrace();
        // }
    }

    private TextField createStyledTextField() {
        TextField textField = new TextField();
        textField.setPrefWidth(300);
        textField.setStyle("-fx-padding: 10; -fx-font-size: 14px;");
        textField.setEffect(new DropShadow(5, Color.BLACK));
        return textField;
    }

    private PasswordField createStyledPasswordField() {
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(300);
        passwordField.setStyle("-fx-padding: 10; -fx-font-size: 14px;");
        passwordField.setEffect(new DropShadow(5, Color.BLACK));
        return passwordField;
    }

    private Button createStyledButton(String text, String backgroundColor) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;");
        button.setEffect(new DropShadow(5, Color.BLACK));
        return button;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Scene getScene(){
        return adminLoginScene;
    }

}