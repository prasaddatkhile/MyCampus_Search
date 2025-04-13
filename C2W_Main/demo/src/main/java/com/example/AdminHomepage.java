package com.example;

import com.example.DataServices.FirestoreService;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.common.util.concurrent.MoreExecutors;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class AdminHomepage {

    private Firestore db;
    private Stage primaryStage;
    private Scene adminHomeScene;
    private AdminLoginPage adminLoginPage;
    private CombinedChartApp chartApp;

    public AdminHomepage(Stage primaryStage, AdminLoginPage adminLoginPage) {
        this.adminLoginPage = adminLoginPage;
        this.primaryStage = primaryStage;
        this.chartApp = new CombinedChartApp(this); // Instantiate the chart application
        this.adminHomeScene = createAdminHomePage(primaryStage);
    }

    public Scene createAdminHomePage(Stage primaryStage) {
        db = FirestoreService.db;

        // Main Layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);

        // Labels and Controls
        Label titleLabel = createLabel("Admin Homepage", 30, Color.BLUE);
        Label welcomeLabel = createLabel("\"Welcome Admin...!!\"", 45, Color.MAROON);
      //  Label selectLabel = createLabel("Select User Category", 18, Color.BLACK);

        // Buttons
        Button findButton = createButton("Find", "GREEN");
        Button backButton = createButton("Back", "RED");
        Button clearButton = createButton("Clear", "BLUE");
        Button showChartsButton = createButton("Show Charts", "ORANGE");

        // Button Actions
        backButton.setOnAction(event -> primaryStage.setScene(adminLoginPage.getScene()));
        showChartsButton.setOnAction(event -> {
            CombinedChartApp chartApp = new CombinedChartApp(this); // Pass current AdminHomepage instance
            primaryStage.setScene(chartApp.createScene(primaryStage, this));
        });
        // Layouts
        VBox labelVBox = new VBox(20, titleLabel, welcomeLabel);
        labelVBox.setAlignment(Pos.TOP_CENTER);
        labelVBox.setPadding(new Insets(50, 50, 50, 50));

        HBox buttonHBox = new HBox(20, findButton, backButton, clearButton, showChartsButton);
        buttonHBox.setAlignment(Pos.CENTER);

        // ScrollPanes with labels above them
        VBox leftScrollPaneBox = new VBox(30);
        leftScrollPaneBox.setAlignment(Pos.TOP_CENTER);
        leftScrollPaneBox.setPrefWidth(600);
        Label studentListLabel = createLabel("Users List", 18, Color.BLACK);
        studentListLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
        leftScrollPaneBox.getChildren().addAll(studentListLabel, createScrollPane("users"));

        VBox rightScrollPaneBox = new VBox(30);
        rightScrollPaneBox.setAlignment(Pos.TOP_CENTER);
        rightScrollPaneBox.setPrefWidth(600);
        Label ownerListLabel = createLabel("Owner List", 18, Color.BLACK);
        ownerListLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
        rightScrollPaneBox.getChildren().addAll(ownerListLabel, createScrollPane("owner"));

        HBox contentHBox = new HBox(60);
        contentHBox.setAlignment(Pos.CENTER);
        contentHBox.getChildren().addAll(leftScrollPaneBox, rightScrollPaneBox);

        // Adding spacer VBox for pushing buttonHBox to the bottom
        VBox spacer = new VBox(20);
        VBox.setVgrow(spacer, Priority.ALWAYS);

        mainLayout.getChildren().addAll(labelVBox, contentHBox, spacer, buttonHBox);
        mainLayout.setStyle("-fx-background-image: url('file:D:/MVN/C2W_Main/demo/src/main/Assets/profilePageback.jpg');");

        Scene scene = new Scene(mainLayout, 1950, 1000);
        primaryStage.setTitle("Admin Dashboard");

        return scene;
    }

    private ScrollPane createScrollPane(String userType) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(600);
        scrollPane.setPrefWidth(500);

        VBox cardContainer = new VBox(30);
        cardContainer.setPadding(new Insets(10));

        Task<Void> fetchDataTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    CollectionReference collectionRef;
                    if ("users".equals(userType)) {
                        collectionRef = db.collection("users");
                    } else if ("owner".equals(userType)) {
                        collectionRef = db.collection("owner");
                    } else {
                        throw new IllegalArgumentException("Invalid user type: " + userType);
                    }

                    ApiFuture<QuerySnapshot> query = collectionRef.get();
                    QuerySnapshot querySnapshot = query.get();

                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        HBox card;
                        if ("users".equals(userType)) {
                            String name = document.getString("username");
                            String address = document.getString("email");
                            String phone = document.getString("mobileno");
                            card = createCard(name, address, phone, userType);
                        } else {
                            String ownerName = document.getString("username");
                            String business = document.getString("businessName");
                            String location = document.getString("category");
                            card = createOwnerCard(ownerName, business, location);
                        }

                        javafx.application.Platform.runLater(() -> cardContainer.getChildren().add(card));
                    }
                } catch (Exception e) {
                    System.err.println("Error fetching data for user type: " + userType);
                    e.printStackTrace();
                }
                return null;
            }
        };

        fetchDataTask.setOnFailed(event -> {
            Throwable throwable = fetchDataTask.getException();
            System.err.println("Error fetching data task failed for user type: " + userType);
            throwable.printStackTrace();
        });

        new Thread(fetchDataTask).start();

        scrollPane.setContent(cardContainer);
        return scrollPane;
    }

    private HBox createCard(String name, String address, String phone, String userType) {
        Label nameLabel = createStyledLabel("Name: " + name);
        Label addressLabel = createStyledLabel("Email: " + address);
        Label phoneLabel = createStyledLabel("Phone: " + phone);

        VBox labelsVBox = new VBox(10, nameLabel, addressLabel, phoneLabel);
        labelsVBox.setPadding(new Insets(20));

        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px;");
        deleteButton.setOnAction(e -> {
            ((VBox) deleteButton.getParent().getParent()).getChildren().remove(deleteButton.getParent());
            deleteCardData(name, userType);
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox card = new HBox(30, labelsVBox, spacer, deleteButton);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: #ecf0f1; -fx-background-radius: 5px; -fx-padding: 30px; -fx-spacing: 20px; -fx-border-color: #bdc3c7; -fx-border-width: 1px;");
        card.setEffect(new DropShadow(5, Color.GRAY));

        return card;
    }

    private HBox createOwnerCard(String ownerName, String business, String location) {
        Label nameLabel = createStyledLabel("Owner Name: " + ownerName);
        Label businessLabel = createStyledLabel("Business Name: " + business);
        Label locationLabel = createStyledLabel("Category: " + location);

        VBox labelsVBox = new VBox(10, nameLabel, businessLabel, locationLabel);
        labelsVBox.setPadding(new Insets(20));

        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px;");
        deleteButton.setOnAction(e -> {
            ((VBox) deleteButton.getParent().getParent()).getChildren().remove(deleteButton.getParent());
            deleteCardData(ownerName, location);
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox card = new HBox(30, labelsVBox, spacer, deleteButton);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: #ecf0f1; -fx-background-radius: 5px; -fx-padding: 30px; -fx-spacing: 20px; -fx-border-color: #bdc3c7; -fx-border-width: 1px;");
        card.setEffect(new DropShadow(5, Color.GRAY));

        return card;
    }

   private void deleteCardData(String name, String userType) {
    Task<Void> deleteTask = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            try {
                CollectionReference collectionRef;
                if ("users".equals(userType)) {
                    collectionRef = db.collection("users");
                } else if ("owner".equals(userType)) {
                    collectionRef = db.collection("owner");
                } else {
                    throw new IllegalArgumentException("Invalid user type: " + userType);
                }

                ApiFuture<QuerySnapshot> future = collectionRef.whereEqualTo("username", name).get();
                ApiFutures.addCallback(future, new ApiFutureCallback<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        if (!querySnapshot.isEmpty()) {
                            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                                collectionRef.document(document.getId()).delete();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        System.err.println("Error deleting data for user type: " + userType);
                        t.printStackTrace();
                    }
                }, MoreExecutors.directExecutor());

            } catch (Exception e) {
                System.err.println("Error deleting data for user type: " + userType);
                e.printStackTrace();
            }
            return null;
        }
    };
        deleteTask.setOnFailed(event -> {
            Throwable throwable = deleteTask.getException();
            System.err.println("Error deleting data task failed for user type: " + userType);
            throwable.printStackTrace();
        });

        new Thread(deleteTask).start();
    }

    private Label createLabel(String text, int fontSize, Color color) {
        Label label = new Label(text);
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, fontSize));
        label.setTextFill(color);
        return label;
    }

    private Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", 16));
        label.setTextFill(Color.BLACK);
        return label;
    }

    private Button createButton(String text, String color) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
        button.setOnMouseEntered(event -> button.setScaleX(1.2));
        button.setOnMouseExited(event -> button.setScaleX(1));
        return button;
    }

    public Scene getAdminHomeScene() {
        return adminHomeScene;
    }

    public Scene getScene(){
        return adminHomeScene;
    }
}
