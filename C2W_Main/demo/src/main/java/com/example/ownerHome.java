// package com.example;

// import com.example.DataServices.FirestoreService;
// import com.google.api.core.ApiFuture;
// import com.google.cloud.firestore.*;
// import com.google.common.util.concurrent.MoreExecutors;

// import javafx.application.Application;
// import javafx.application.Platform;
// import javafx.geometry.HPos;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.image.Image;
// import javafx.scene.layout.*;
// import javafx.stage.Screen;
// import javafx.stage.Stage;

// import java.util.List;

// public class ownerHome extends Application {

//     private String ownerUsername; // Store the owner's username

//     public ownerHome(String ownerUsername) {
//         this.ownerUsername = ownerUsername;
//     }

//     @Override
//     public void start(Stage primaryStage) {
//         // Get screen dimensions
//         double screenWidth = Screen.getPrimary().getBounds().getWidth();
//         double screenHeight = Screen.getPrimary().getBounds().getHeight();

//         // Create GridPane
//         GridPane mainLayout = new GridPane();
//         mainLayout.setAlignment(Pos.CENTER);
//         mainLayout.setPadding(new Insets(20, 200, 20, 200)); // Padding to provide space on the sides
//         mainLayout.setVgap(10);
//         mainLayout.setHgap(10);

//         // Create heading label
//         Label heading = new Label("Owner HomePage");
//         heading.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF; -fx-padding: 10px;");
//         GridPane.setConstraints(heading, 0, 0);
//         GridPane.setHalignment(heading, HPos.CENTER); // Center horizontally

//         // Create title label
//         Label title = new Label("Welcome TO MyCampus App");
//         title.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: #FFD700; -fx-padding: 20px;");
//         GridPane.setConstraints(title, 0, 1);
//         GridPane.setHalignment(title, HPos.CENTER); // Center horizontally

//         // Create welcome label
//         Label welcomeLabel = new Label("Welcome " + ownerUsername + "!!..");
//         welcomeLabel.setStyle("-fx-font-size: 30px; -fx-font-style: italic; -fx-text-fill: green ; -fx-padding: 20px;");
//         GridPane.setConstraints(welcomeLabel, 0, 2);
//         GridPane.setHalignment(welcomeLabel, HPos.CENTER); // Center horizontally

//         // Create ScrollPane
//         ScrollPane scrollPane = new ScrollPane();
//         scrollPane.setFitToWidth(true);
//         GridPane.setConstraints(scrollPane, 0, 3);

//         // Create VBox for cards
//         VBox cardContainer = new VBox();
//         cardContainer.setSpacing(10);

//         // Fetch data and add cards
//         fetchDataAndAddCards(cardContainer);

//         scrollPane.setContent(cardContainer);

//         // Add all elements to main layout
//         mainLayout.getChildren().addAll(heading, title, welcomeLabel, scrollPane);

//         // Create a StackPane to hold the GridPane and apply the background image
//         StackPane root = new StackPane();
//         root.getChildren().add(mainLayout);

//         // Background Image
//         Image backgroundImage = new Image(getClass().getResource("/Assets/background.jpeg").toExternalForm());
//         BackgroundSize backgroundSize = new BackgroundSize(screenWidth, screenHeight, false, false, true, true);
//         BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
//                 BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
//         root.setBackground(new Background(background));

//         // Create and set the scene
//         Scene scene = new Scene(root, screenWidth, screenHeight);
//         primaryStage.setScene(scene);
//         primaryStage.setTitle("Owner Homepage");
//         primaryStage.show();
//     }
//     public VBox createContent() {
//         VBox cardContainer = new VBox();
//         cardContainer.setSpacing(10);
//         cardContainer.setPadding(new Insets(10));

//         // Add title or header for the owner home page
//         Label welcomeLabel = new Label("Welcome, " + ownerUsername);
//         welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
//         cardContainer.getChildren().add(welcomeLabel);

//         // Fetch and add cards/data here
//         fetchDataAndAddCards(cardContainer); // Method to fetch data and populate cards

//         return cardContainer; // Return the container for the scene
//     }


//     private void fetchDataAndAddCards(VBox cardContainer) {
//     Firestore db = FirestoreService.db;
//     CollectionReference collectionRef = db.collection("owner");
//     Query query = collectionRef.whereEqualTo("ownerUsername", ownerUsername);

//     ApiFuture<QuerySnapshot> querySnapshot = query.get();
//     querySnapshot.addListener(() -> {
//         try {
//             List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
//             Platform.runLater(() -> {
//                 for (DocumentSnapshot document : documents) {
//                     String title = document.getString("title");
//                     String content = document.getString("content");
//                     cardContainer.getChildren().add(new Card(title, content).getCardLayout());
//                 }
//             });
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//      MoreExecutors.directExecutor());


//     // Inner Card class
//     class Card {
//         private String title;
//         private String content;

//         public Card(String title, String content) {
//             this.title = title;
//             this.content = content;
//         }

//         public VBox getCardLayout() {
//             VBox cardLayout = new VBox();
//             cardLayout.setPadding(new Insets(10));
//             cardLayout.setSpacing(10);
//             cardLayout.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: aqua;");

//             // Create title label
//             Label titleLabel = new Label(title);
//             titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

//             // Create content label
//             Label contentLabel = new Label(content);
//             contentLabel.setStyle("-fx-font-size: 14px;");

//             // Create button container
//             VBox buttonContainer = new VBox();
//             buttonContainer.setAlignment(Pos.CENTER_RIGHT); // Align buttons to the right
//             buttonContainer.setSpacing(10);

//             // Create update button
//             Button updateButton = new Button("Update");
//             updateButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
//             updateButton.setOnAction(event -> {
//                 System.out.println(title + " updated");
//             });

//             // Create delete button
//             Button deleteButton = new Button("Delete");
//             deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
//             deleteButton.setOnAction(event -> {
//                 System.out.println(title + " deleted");
//                 VBox parent = (VBox) cardLayout.getParent();
//                 parent.getChildren().remove(cardLayout);
//             });

//             // Add buttons to button container
//             buttonContainer.getChildren().addAll(updateButton, deleteButton);

//             // Add labels and button container to card layout
//             cardLayout.getChildren().addAll(titleLabel, contentLabel, buttonContainer);

//             return cardLayout;
//         }
//     }
// }
package com.example;

import com.example.DataServices.FirestoreService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.common.util.concurrent.MoreExecutors;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

public class ownerHome extends Application {

    private String ownerUsername;

    public ownerHome(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public ownerHome() {
        // Default constructor required for Application.launch()
        this.ownerUsername = "DemoOwner"; // fallback
    }

    @Override
    public void start(Stage primaryStage) {
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        GridPane mainLayout = new GridPane();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20, 200, 20, 200));
        mainLayout.setVgap(10);
        mainLayout.setHgap(10);

        Label heading = new Label("Owner HomePage");
        heading.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF; -fx-padding: 10px;");
        GridPane.setConstraints(heading, 0, 0);
        GridPane.setHalignment(heading, HPos.CENTER);

        Label title = new Label("Welcome TO MyCampus App");
        title.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: #FFD700; -fx-padding: 20px;");
        GridPane.setConstraints(title, 0, 1);
        GridPane.setHalignment(title, HPos.CENTER);

        Label welcomeLabel = new Label("Welcome " + ownerUsername + "!!..");
        welcomeLabel.setStyle("-fx-font-size: 30px; -fx-font-style: italic; -fx-text-fill: green ; -fx-padding: 20px;");
        GridPane.setConstraints(welcomeLabel, 0, 2);
        GridPane.setHalignment(welcomeLabel, HPos.CENTER);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        GridPane.setConstraints(scrollPane, 0, 3);

        VBox cardContainer = new VBox();
        cardContainer.setSpacing(10);

        fetchDataAndAddCards(cardContainer);
        scrollPane.setContent(cardContainer);

        mainLayout.getChildren().addAll(heading, title, welcomeLabel, scrollPane);

        StackPane root = new StackPane();
        root.getChildren().add(mainLayout);

        Image backgroundImage = new Image(getClass().getResource("/Assets/background.jpeg").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(screenWidth, screenHeight, false, false, true, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        root.setBackground(new Background(background));

        Scene scene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Owner Homepage");
        primaryStage.show();
    }

    private void fetchDataAndAddCards(VBox cardContainer) {
        Firestore db = FirestoreService.db;
        CollectionReference collectionRef = db.collection("owner");
        Query query = collectionRef.whereEqualTo("ownerUsername", ownerUsername);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        querySnapshot.addListener(() -> {
            try {
                List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
                Platform.runLater(() -> {
                    for (DocumentSnapshot document : documents) {
                        String title = document.getString("title");
                        String content = document.getString("content");
                        cardContainer.getChildren().add(new Card(title, content).getCardLayout());
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, MoreExecutors.directExecutor());
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Inner Card class
    class Card {
        private String title;
        private String content;

        public Card(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public VBox getCardLayout() {
            VBox cardLayout = new VBox();
            cardLayout.setPadding(new Insets(10));
            cardLayout.setSpacing(10);
            cardLayout.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: aqua;");

            Label titleLabel = new Label(title);
            titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

            Label contentLabel = new Label(content);
            contentLabel.setStyle("-fx-font-size: 14px;");

            VBox buttonContainer = new VBox();
            buttonContainer.setAlignment(Pos.CENTER_RIGHT);
            buttonContainer.setSpacing(10);

            Button updateButton = new Button("Update");
            updateButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
            updateButton.setOnAction(event -> {
                System.out.println(title + " updated");
            });

            Button deleteButton = new Button("Delete");
            deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            deleteButton.setOnAction(event -> {
                System.out.println(title + " deleted");
                VBox parent = (VBox) cardLayout.getParent();
                parent.getChildren().remove(cardLayout);
            });

            buttonContainer.getChildren().addAll(updateButton, deleteButton);
            cardLayout.getChildren().addAll(titleLabel, contentLabel, buttonContainer);
            return cardLayout;
        }
    }
}
