package com.example;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class CombinedChartApp extends Application {

    private static Firestore db;
    private AdminHomepage adminHomepage; // Changed to instance variable

    public CombinedChartApp(AdminHomepage adminHomepage) { // Constructor with AdminHomepage parameter
        this.adminHomepage = adminHomepage;
    }

    public static void initializeFb() {
        if (FirebaseApp.getApps().isEmpty()) { // Check if FirebaseApp is already initialized
            try {
                FileInputStream serviceAccount = new FileInputStream("D:\\MVN\\C2W_Main\\demo\\src\\main\\resources\\c2w-main-firebase-adminsdk-d4pte-58d9e7b0e7.json");

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        db = FirestoreClient.getFirestore(); // Initialize Firestore
    }

    private Map<String, Integer> getCategoryCounts() {
        CollectionReference ownersRef = db.collection("owner");
        ApiFuture<QuerySnapshot> future = ownersRef.get();
        Map<String, Integer> categoryCounts = new HashMap<>();

        try {
            QuerySnapshot querySnapshot = future.get();
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                String category = document.getString("category");

                if (category != null) {
                    categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + 1);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return categoryCounts;
    }

    public static int getCount(String collectionName) {
        CollectionReference collectionRef = db.collection(collectionName);
        ApiFuture<QuerySnapshot> future = collectionRef.get();
        try {
            QuerySnapshot querySnapshot = future.get();
            return querySnapshot.size();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void start(Stage stage) {
        // Initialize Firestore
        initializeFb();

        // Create the AdminHomepage instance
        AdminHomepage adminHomepage = new AdminHomepage(stage, new AdminLoginPage()); // Pass in the appropriate AdminLoginPage instance

        // Set the scene to the combined chart scene
        stage.setScene(createScene(stage, adminHomepage));
        stage.setTitle("Users and Owners Distribution");
        stage.show();
    }

    public Scene createScene(Stage primaryStage, AdminHomepage adminHomepage) {
        // Initialize Firestore
        initializeFb();

        // Retrieve counts
        int userCount = getCount("users");
        int ownerCount = getCount("owner");
        Map<String, Integer> categoryCounts = getCategoryCounts();

        // Create PieCharts
        PieChart userOwnerPieChart = createPieChart(userCount, ownerCount);
        PieChart categoryPieChart = createCategoryPieChart(categoryCounts);

        // Labels
        Label userOwnerLabel = new Label("Distribution of Users and Owners");
        userOwnerLabel.setFont(javafx.scene.text.Font.font("Times New Roman", javafx.scene.text.FontWeight.BOLD, 30));
        userOwnerLabel.setStyle("-fx-text-fill: #333;");

        Label categoryLabel = new Label("Distribution of Owners by Category");
        categoryLabel.setFont(javafx.scene.text.Font.font("Times New Roman", javafx.scene.text.FontWeight.BOLD, 30));
        categoryLabel.setStyle("-fx-text-fill: #333;");

        // Back Button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;");
        backButton.setEffect(new DropShadow(5, Color.BLACK));
        backButton.setOnAction(event -> primaryStage.setScene(adminHomepage.getScene())); // Use adminHomepage instance

        // Layouts
        VBox userOwnerBox = new VBox(20, userOwnerLabel, userOwnerPieChart);
        userOwnerBox.setAlignment(Pos.CENTER);
        userOwnerBox.setPrefHeight(800);
        userOwnerBox.setPrefWidth(600);
        userOwnerBox.setStyle("-fx-background-color: #f9f9f9; -fx-border-radius: 20; -fx-background-radius: 20;");

        VBox categoryBox = new VBox(20, categoryLabel, categoryPieChart);
        categoryBox.setAlignment(Pos.CENTER);
        categoryBox.setPrefHeight(800);
        categoryBox.setPrefWidth(600);
        categoryBox.setPadding(new Insets(20));
        categoryBox.setStyle("-fx-background-color: #f9f9f9; -fx-border-radius: 20; -fx-background-radius: 20;");

        HBox hbox = new HBox(20, userOwnerBox, categoryBox);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20));
        hbox.setStyle("-fx-background-color: #ececec; -fx-border-color: #ccc; -fx-border-width: 1; -fx-border-radius: 10;");

        // Adding Back Button
        VBox root = new VBox(20, backButton, hbox);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #ffffff;");
          Image backgroundImage = new Image("file:///D:/MVN/C2W_Main/demo/src/main/Assets/chart2.jpeg");
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.ROUND,
                BackgroundRepeat.ROUND,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        root.setBackground(new Background(background));


        // Scene setup
        Scene scene = new Scene(root, 1950, 1000);
        return scene;
    }

    private PieChart createPieChart(int userCount, int ownerCount) {
        PieChart.Data userData = new PieChart.Data("Users", userCount);
        PieChart.Data ownerData = new PieChart.Data("Owner", ownerCount);

        PieChart pieChart = new PieChart();
        pieChart.getData().addAll(userData, ownerData);
        pieChart.setTitle("Distribution of Users and Owners");

        addAnimations(pieChart);

        return pieChart;
    }

    private PieChart createCategoryPieChart(Map<String, Integer> categoryCounts) {
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Owners Distribution by Category");

        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            PieChart.Data data = new PieChart.Data(entry.getKey(), entry.getValue());
            pieChart.getData().add(data);
        }

        addAnimations(pieChart);

        return pieChart;
    }

    private void addAnimations(PieChart pieChart) {
        for (PieChart.Data data : pieChart.getData()) {
            // Fade Transition
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), data.getNode());
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.3);
            fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
            fadeTransition.setAutoReverse(true);
            fadeTransition.play();

            // Scale Transition
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(2000), data.getNode());
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
            scaleTransition.setAutoReverse(true);
            scaleTransition.play();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
