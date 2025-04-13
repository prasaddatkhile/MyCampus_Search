// package com.example;

// import javafx.application.Application;
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

// import java.util.ArrayList;
// import java.util.List;

// public class profile extends Application {

//     @Override
//     public void start(Stage primaryStage) {
//         double screenWidth = Screen.getPrimary().getBounds().getWidth();
//         double screenHeight = Screen.getPrimary().getBounds().getHeight();

//         GridPane mainLayout = new GridPane();
//         mainLayout.setAlignment(Pos.CENTER);
//         mainLayout.setPadding(new Insets(20, 200, 20, 200));
//         mainLayout.setVgap(10);
//         mainLayout.setHgap(10);

//         Label heading = new Label("Owner HomePage");
//         heading.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF; -fx-padding: 10px;");
//         GridPane.setConstraints(heading, 0, 0);
//         GridPane.setHalignment(heading, HPos.CENTER);

//         Label title = new Label("Welcome TO MyCampus App");
//         title.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: #FFD700; -fx-padding: 20px;");
//         GridPane.setConstraints(title, 0, 1);
//         GridPane.setHalignment(title, HPos.CENTER);

//         Label welcomeLabel = new Label("Welcome Ownername!!..");
//         welcomeLabel.setStyle("-fx-font-size: 30px; -fx-font-style: italic; -fx-text-fill: green ; -fx-padding: 20px;");
//         GridPane.setConstraints(welcomeLabel, 0, 2);
//         GridPane.setHalignment(welcomeLabel, HPos.CENTER);

//         ScrollPane scrollPane = new ScrollPane();
//         scrollPane.setFitToWidth(true);
//         GridPane.setConstraints(scrollPane, 0, 3);

//         VBox cardContainer = new VBox();
//         cardContainer.setSpacing(10);

//         // Fetch data and create cards
//         List<CardData> cardDataList = fetchDataFromDatabase();
//         for (CardData data : cardDataList) {
//             cardContainer.getChildren().add(new Card(data.getTitle(), data.getContent()).getCardLayout());
//         }

//         scrollPane.setContent(cardContainer);
//         mainLayout.getChildren().addAll(heading, title, welcomeLabel, scrollPane);

//         StackPane root = new StackPane();
//         root.getChildren().add(mainLayout);

//         Image backgroundImage = new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/profilePageback.jpg");
//         BackgroundSize backgroundSize = new BackgroundSize(screnWidth, screenHeight, false, false, true, true);
//         BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
//                 BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
//         root.setBackground(new Background(background));

//         Scene scene = new Scene(root, screenWidth, screenHeight);
//         primaryStage.setScene(scene);
//         primaryStage.setTitle("Owner Homepage");
//         primaryStage.show();
//     }

//     private List<CardData> fetchDataFromDatabase() {
//         List<CardData> cardDataList = new ArrayList<>();
//         // Replace this with actual database fetching logic
//         cardDataList.add(new CardData("Card 1", "This is the content of card 1"));
//         cardDataList.add(new CardData("Card 2", "This is the content of card 2"));
//         return cardDataList;
//     }

//     public static void main(String[] args) {
//         Application.launch(profile.class, args);
//     }

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
//             Label titleLabel = new Label(title);
//             titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
//             Label contentLabel = new Label(content);
//             contentLabel.setStyle("-fx-font-size: 14px;");

//             VBox buttonContainer = new VBox();
//             buttonContainer.setAlignment(Pos.CENTER_RIGHT);
//             buttonContainer.setSpacing(10);

//             Button updateButton = new Button("Update");
//             updateButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");

//             Button deleteButton = new Button("Delete");
//             deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

//             updateButton.setOnAction(event -> {
//                 System.out.println(title + " updated");
//             });

//             deleteButton.setOnAction(event -> {
//                 System.out.println(title + " deleted");
//                 VBox parent = (VBox) cardLayout.getParent();
//                 parent.getChildren().remove(cardLayout);
//             });

//             buttonContainer.getChildren().addAll(updateButton, deleteButton);
//             cardLayout.getChildren().addAll(titleLabel, contentLabel, buttonContainer);

//             return cardLayout;
//         }
//     }
// }
package com.example;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class profile extends Application {

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

        Label welcomeLabel = new Label("Welcome Ownername!!..");
        welcomeLabel.setStyle("-fx-font-size: 30px; -fx-font-style: italic; -fx-text-fill: green ; -fx-padding: 20px;");
        GridPane.setConstraints(welcomeLabel, 0, 2);
        GridPane.setHalignment(welcomeLabel, HPos.CENTER);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        GridPane.setConstraints(scrollPane, 0, 3);

        VBox cardContainer = new VBox();
        cardContainer.setSpacing(10);

        // Fetch data and create cards
        List<CardData> cardDataList = fetchDataFromDatabase();
        for (CardData data : cardDataList) {
            cardContainer.getChildren().add(new Card(data.getTitle(), data.getContent()).getCardLayout());
        }

        scrollPane.setContent(cardContainer);
        mainLayout.getChildren().addAll(heading, title, welcomeLabel, scrollPane);

        StackPane root = new StackPane();
        root.getChildren().add(mainLayout);

        // Background image
        Image backgroundImage = new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/profilePageback.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(screenWidth, screenHeight, false, false, true, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        root.setBackground(new Background(background));

        Scene scene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Owner Homepage");
        primaryStage.show();
    }

    private List<CardData> fetchDataFromDatabase() {
        List<CardData> cardDataList = new ArrayList<>();
        // Replace this with actual database fetching logic
        cardDataList.add(new CardData("Card 1", "This is the content of card 1"));
        cardDataList.add(new CardData("Card 2", "This is the content of card 2"));
        return cardDataList;
    }

    public static void main(String[] args) {
        Application.launch(profile.class, args);
    }

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

    // ✅ Added missing CardData class
    class CardData {
        private final String title;
        private final String content;

        public CardData(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }
    }
}
