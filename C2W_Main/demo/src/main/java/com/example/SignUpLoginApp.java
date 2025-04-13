package com.example;
import com.example.ownerHome;
import com.example.DataServices.FirestoreService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;

public class SignUpLoginApp extends Application {

    private Stage primaryStage; // Reference to the primary stage
    public static Scene loginScene; // Reference to the login scene

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // Store the primary stage reference

        // Create the GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(30);
        grid.setMaxHeight(1000);
        grid.setMaxWidth(1950);
        grid.setPadding(new Insets(25,300, 25, 300));
        primaryStage.setResizable(false);

        // Title label
        Label titleLabel = new Label("           Sign Up / Login");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: black;");
        grid.add(titleLabel, 0, 0, 3, 1);

        // Campus selection
        Label campusLabel = new Label("Select Campus :");
        campusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
      //  campusLabel.setStyle("-fx-text-fill: white;");
        grid.add(campusLabel, 0, 1);
        ChoiceBox<String> campusChoiceBox = new ChoiceBox<>();
        campusChoiceBox.setPrefWidth(300);
        grid.add(campusChoiceBox, 1, 1, 2, 1);
        campusChoiceBox.getItems().addAll("Sinhagad College Vadgaon, Pune", "Jspm Narhe, Pune", "Bharati Vidyapeeth, Pune",
                "Cummins College Karve Nagar, Pune", "Modern College Shivajinagar, Pune");

        // Create the UI components for username and password
        Label userNameLabel = new Label("Username:");
      //  userNameLabel.setStyle("-fx-text-fill: white;");
        
        userNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        grid.add(userNameLabel, 0, 2);
        TextField userNameField = new TextField();
        userNameField.setPrefWidth(300);
        grid.add(userNameField, 1, 2, 2, 1);

        Label pwLabel = new Label("Password:");
     //   pwLabel.
     //   setStyle("-fx-text-fill: white;");
        pwLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        grid.add(pwLabel, 0, 3);
        PasswordField pwField = new PasswordField();
        pwField.setPrefWidth(300);
        grid.add(pwField, 1, 3, 2, 1);

        // Checkboxes for user type
        CheckBox studentCheckBox = new CheckBox("Users");
        studentCheckBox.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        CheckBox otherAdminsCheckBox = new CheckBox("Owner");
        otherAdminsCheckBox.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        HBox checkBoxContainer = new HBox(20, studentCheckBox, otherAdminsCheckBox);
        checkBoxContainer.setAlignment(Pos.CENTER_LEFT);
        grid.add(checkBoxContainer, 1, 4, 2, 1);

        // Ensure only one checkbox can be selected
        studentCheckBox.setOnAction(e -> {
            if (studentCheckBox.isSelected()) {
                otherAdminsCheckBox.setSelected(false);
            }
        });
        otherAdminsCheckBox.setOnAction(e -> {
            if (otherAdminsCheckBox.isSelected()) {
                studentCheckBox.setSelected(false);
            }
        });

        // Buttons for Sign Up, Login, and Clear
        Button signUpButton = createStyledButton("Sign Up", "#2ecc71");
        Button loginButton = createStyledButton("Login", "#f39c12");
        Button clearButton = createStyledButton("Clear", "#e74c3c");
        Button adminLogin = createStyledButton("AdminLogin", "#f39c12");

        HBox buttonContainer = new HBox(20, signUpButton, loginButton, clearButton, adminLogin);
        buttonContainer.setAlignment(Pos.CENTER);
        grid.add(buttonContainer, 0, 5, 3, 1);

        // Event handler for the Clear button
        clearButton.setOnAction(e -> {
            userNameField.clear();
            pwField.clear();
            otherAdminsCheckBox.setSelected(false);
            studentCheckBox.setSelected(false);
            campusChoiceBox.setValue(null);
        });

        Image backgroundImage = new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/loginback.jpeg");
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.ROUND,
                BackgroundRepeat.ROUND,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        grid.setBackground(new Background(bgImage));

        // Set the login scene
        loginScene = new Scene(grid, 1950, 1000);
        loginScene.setFill(Color.AQUAMARINE);

        // Event handler for the Sign Up button
        signUpButton.setOnAction(e -> {
            if (otherAdminsCheckBox.isSelected()) {
                AdminRegistration adminRegistration = new AdminRegistration(this);
                Scene adminScene = adminRegistration.createRegistrationScene(primaryStage);
                primaryStage.setScene(adminScene);
                userNameField.clear();
                pwField.clear();
                otherAdminsCheckBox.setSelected(false);
                studentCheckBox.setSelected(false);
                campusChoiceBox.setValue(null);
            } else if (studentCheckBox.isSelected()) {
                StudentRegistrationApp registrationApp = new StudentRegistrationApp();
                Scene registrationScene = registrationApp.createScene(primaryStage, loginScene);
                primaryStage.setScene(registrationScene);
                userNameField.clear();
                pwField.clear();
                otherAdminsCheckBox.setSelected(false);
                studentCheckBox.setSelected(false);
                campusChoiceBox.setValue(null);
            }
        });

        // Event handler for the Login button
        loginButton.setOnAction(e -> {
            String username = userNameField.getText();
            String password = pwField.getText();
            String campus=campusChoiceBox.getValue();
            if (username.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter your username and password");
                return;
            }

            boolean isAuthenticated;
            if (studentCheckBox.isSelected()) {
                isAuthenticated = authenticateUser("users", username, password);
            } else if (otherAdminsCheckBox.isSelected()) {
                isAuthenticated = authenticateUser("owner", username, password);
            } else {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please select a user type");
                return;
            }

            if (isAuthenticated) {
                if (studentCheckBox.isSelected()) {
                    showHomePage(username,password,campus);
                    showownerHome(username) ;// Call method to switch to home page
                    userNameField.clear();
                    pwField.clear();
                    otherAdminsCheckBox.setSelected(false);
                    studentCheckBox.setSelected(false);
                    campusChoiceBox.setValue(null);
                } else if (otherAdminsCheckBox.isSelected()) {
                    showAlert(Alert.AlertType.INFORMATION, "Login Success", "Admin login successful");
                    userNameField.clear();
                    pwField.clear();
                    otherAdminsCheckBox.setSelected(false);
                    studentCheckBox.setSelected(false);
                    campusChoiceBox.setValue(null);
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password");
            }
        });

        // Event handler for the AdminLogin button
        adminLogin.setOnAction(e -> {
            AdminLoginPage adminLoginPage = new AdminLoginPage();
            Scene adminLoginScene = adminLoginPage.createScene( primaryStage);
            primaryStage.setScene(adminLoginScene);
            userNameField.clear();
            pwField.clear();
            otherAdminsCheckBox.setSelected(false);
            studentCheckBox.setSelected(false);
            campusChoiceBox.setValue(null);
        });

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Sign Up / Login App");
        primaryStage.show();
    }

    // Method to create styled buttons
    private Button createStyledButton(String text, String backgroundColor) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;");
        return button;
    }

    // Method to authenticate user
    private boolean authenticateUser(String collection, String username, String password) {
        Firestore db = FirestoreService.db;
        CollectionReference collectionRef = db.collection(collection);
        Query query = collectionRef.whereEqualTo("username", username);
        try {
            ApiFuture<QuerySnapshot> future = query.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            if (!documents.isEmpty()) {
                DocumentSnapshot document = documents.get(0); // Assuming usernames are unique and only one document will be returned
                String storedPassword = document.getString("password");
                return storedPassword != null && storedPassword.equals(password);
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to show alert messages
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to return the login scene
    public Scene getLoginScene(Stage stage) {
        primaryStage = stage; // Update the primary stage reference
        return loginScene;
    }

    // Method to show the home page
    private void showHomePage(String username,String password,String campus) {
        home homePage = new home(loginScene, username,password,campus); // Pass loginScene to home constructor
        try {
            homePage.start(primaryStage); // Start the home page scene with the primary stage
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void showownerHome(String username) {
        ownerHome ownerHomePage = new ownerHome(username); // Pass the username to OwnerHomePage
        try {
            ownerHomePage.start(primaryStage); // Start the home page scene with the primary stage
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
  //  public void ProfilePage(String username,);


}