package com.example;
import com.example.PrivateRoomsCard;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class home extends Application {

    private Stage homeStage;
    private Scene loginScene; // Reference to the login/signup scene
    private Scene homeScene;
    private String username;
    private String password;
    private String campus;

    public home() {
        // Default constructor
    }

    public home(Scene loginScene, String username,String password,String campus) {
        this.loginScene = loginScene; // Set the login scene reference
        this.username = username;
        this.password=password;
        this.campus=campus;
    }

    @Override
    public void start(Stage primaryStage) {
        this.homeStage = primaryStage;
        homeStage.setTitle("Home Page");
        homeStage.setWidth(1950);
        homeStage.setHeight(1050);
        homeStage.setResizable(false);

   /*      // Create buttons for various functionalities with labels
        Button button1 = createButtonWithLabel("","Mess");
        Button button2 = createButtonWithLabel("","Hostels");
        Text t1=new Text("Hostels");
        Image hostelImage=new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/hostelbutton.jpeg");
        ImageView img2=new ImageView(hostelImage);
        img2.setFitWidth(230);  // Set desired width
        img2.setFitHeight(130);  // Set desired height
        img2.setPreserveRatio(false);  // Preserve aspect ratio

        button2.setGraphic(img2);
        button2.setAlignment(Pos.CENTER);

     //   button2.setStyle("-fx-background-image: url('file: demo/src/main/Assets/background.jpeg'); ");
        Button button3 = createButtonWithLabel("","Classes");
        Image classImage=new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/classbutton.jpeg");
        ImageView img3=new ImageView(classImage);
        img3.setFitWidth(230);  // Set desired width
        img3.setFitHeight(130);  // Set desired height
        img3.setPreserveRatio(false);  // Preserve aspect ratio

        button3.setGraphic(img3);
        button3.setAlignment(Pos.CENTER);
        Button button4 = createButtonWithLabel("","Hospitals");
        Image hospitalImage=new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/Hospitalbutton.jpeg");
        ImageView img4=new ImageView(hospitalImage);
        img4.setFitWidth(230);  // Set desired width
        img4.setFitHeight(130);  // Set desired height
        img4.setPreserveRatio(false);  // Preserve aspect ratio

        button4.setGraphic(img4);
        button4.setAlignment(Pos.CENTER);
        Button button5 = createButtonWithLabel("","Rooms");
        Image roomsImage=new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/privateroomsbutton.jpeg");
        ImageView img5=new ImageView(roomsImage);
        img5.setFitWidth(230);  // Set desired width
        img5.setFitHeight(130);  // Set desired height
        img5.setPreserveRatio(false);  // Preserve aspect ratio

        button5.setGraphic(img5);
        button5.setAlignment(Pos.CENTER);
        Button button6 = createButtonWithLabel("","Plumbers");
        Image plumberImage=new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/plumberbutton.jpeg");
        ImageView img6=new ImageView(plumberImage);
        img6.setFitWidth(230);  // Set desired width
        img6.setFitHeight(130);  // Set desired height
        img6.setPreserveRatio(false);  // Preserve aspect ratio

        button6.setGraphic(img6);
        button6.setAlignment(Pos.CENTER);
        
        Button button7 = createButtonWithLabel("","Electricians");
        Image electricianImage=new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/electricianbutton.jpeg");
        ImageView img7=new ImageView(electricianImage);
        img7.setFitWidth(230);  // Set desired width
        img7.setFitHeight(130);  // Set desired height
        img7.setPreserveRatio(false);  // Preserve aspect ratio

        button7.setGraphic(img7);
        button7.setAlignment(Pos.CENTER);
        Button button8 = createButtonWithLabel("","Collages");
        Image collageImage=new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/collagebutton.jpeg");
        ImageView img8=new ImageView(collageImage);
        img8.setFitWidth(230);  // Set desired width
        img8.setFitHeight(130);  // Set desired height
        img8.setPreserveRatio(false);  // Preserve aspect ratio

        button8.setGraphic(img8);
        button8.setAlignment(Pos.CENTER);
        Button button9 = createButtonWithLabel("","YourExpenses");
        Image expensesImage=new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/expenses.jpeg");
        ImageView img9=new ImageView(expensesImage);
        img9.setFitWidth(230);  // Set desired width
        img9.setFitHeight(130);  // Set desired height
        img9.setPreserveRatio(false);  // Preserve aspect ratio

//        button9.setGraphic(img9);
  //      button9.setAlignment(Pos.CENTER);
        

        Image messImage=new Image("file:D:/MVN/C2W_Main/demo/src/main/Assets/download.jpeg");
        ImageView img=new ImageView(messImage);
        img.setFitWidth(230);  // Set desired width
        img.setFitHeight(130);  // Set desired height
        img.setPreserveRatio(false);  // Preserve aspect ratio

        button1.setGraphic(img);
        button1.setAlignment(Pos.CENTER);

*/
Button button1 = createButtonWithImageAndText("D:/MVN/C2W_Main/demo/src/main/Assets/download.jpeg", "Mess");
Button button2 = createButtonWithImageAndText("D:/MVN/C2W_Main/demo/src/main/Assets/hostelbutton.jpeg", "Hostels");
Button button3 = createButtonWithImageAndText("D:/MVN/C2W_Main/demo/src/main/Assets/classbutton.jpeg", "Classes");
Button button4 = createButtonWithImageAndText("D:/MVN/C2W_Main/demo/src/main/Assets/Hospitalbutton.jpeg", "Hospital");
Button button5 = createButtonWithImageAndText("D:/MVN/C2W_Main/demo/src/main/Assets/privateroomsbutton.jpeg", "Private Rooms");
Button button6 = createButtonWithImageAndText("D:/MVN/C2W_Main/demo/src/main/Assets/plumberbutton.jpeg", "Plumber");
Button button7 = createButtonWithImageAndText("D:/MVN/C2W_Main/demo/src/main/Assets/electricianbutton.jpeg", "Electrician");
Button button8 = createButtonWithImageAndText("D:/MVN/C2W_Main/demo/src/main/Assets/collagebutton.jpeg", "Collage");
Button button9 = createButtonWithImageAndText("D:/MVN/C2W_Main/demo/src/main/Assets/expenses.jpeg", "Expenses");

        // Arrange buttons in HBox and VBox
        HBox hb1 = new HBox(20, button1, button2, button3);
        HBox hb2 = new HBox(20, button4, button5, button6);
        HBox hb3 = new HBox(20, button7, button8, button9);

        VBox vb1 = new VBox(30, hb1, hb2, hb3);
        vb1.setAlignment(Pos.CENTER);
        vb1.setPadding(new Insets(50));

        // Add animations to buttons
        addButtonAnimations(button1, button2, button3, button4, button5, button6, button7, button8, button9);

        // Search functionality
        TextField searchField = new TextField();
        searchField.setPrefWidth(400);
        searchField.setPromptText("Search...");

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        searchButton.setOnAction(event -> {
            String searchText = searchField.getText();
            System.out.println("Search query: " + searchText);
        });

        HBox searchBox = new HBox(10, searchField, searchButton);
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setPadding(new Insets(20));

        // Text elements
        Text helloText = new Text("Hello, " + username);
        helloText.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        helloText.setFill(Color.BLACK);

        Text titleText = new Text("Welcome to MyCampus");
        titleText.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        titleText.setFill(Color.BLACK);

        Text subtitleText = new Text("Explore your "+ campus +" campus resources");
        subtitleText.setFont(Font.font("Arial", FontPosture.ITALIC, 24));
        subtitleText.setFill(Color.BLACK);

        // Add animations to text elements
        addTextAnimations(helloText, titleText, subtitleText);

        // Layout elements
        VBox textBox = new VBox(10, titleText, subtitleText);
        textBox.setAlignment(Pos.CENTER);
        textBox.setPadding(new Insets(20));

        // Combine all content into a single VBox
        VBox mainLayout = new VBox(20, helloText, textBox, searchBox, vb1);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(50));
       

        // Profile button
        Button profileButton = new Button("Profile");
        profileButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");
        profileButton.setOnAction(e -> {
            ProfilePage profilePage = new ProfilePage(this,username,password,campus);
            primaryStage.setScene(profilePage.getScene());
        });

        // Back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #FF5722; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20;");
        backButton.setOnAction(event -> {
            System.out.println("Back button clicked");
            homeStage.setScene(loginScene); // Switch scene to login/signup page
        });

        HBox buttonBox = new HBox(700, backButton, profileButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20));

        // Group and Scene setup for home page
       

        
        Group root = new Group(mainLayout, buttonBox);
        VBox outer=new VBox(root);
        outer.setAlignment(Pos.CENTER);
        Image backgroundImage = new Image("file:///D:/MVN/C2W_Main/demo/src/main/Assets/collage2back.jpg");
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.ROUND,
                BackgroundRepeat.ROUND,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
              
        );
       outer.setBackground(new Background(background));
       // outer.setLayoutX(500);
       // outer.setLayoutY(80);
        homeScene = new Scene(outer, 1950, 1000);
     //   homeScene.setFill(Color.DARKSLATEBLUE);

        // Set background image for home page
       
        // Set the initial scene
        homeStage.setScene(homeScene);
        homeStage.show();

        // Set action for Mess button to navigate to card page
        button1.setOnAction(event -> {
            System.out.println("Navigating to card page...");
            navigateToCardPage();
        });

        // Set action for Hostel button to navigate to HostelCard page
        button2.setOnAction(event -> {
            System.out.println("Navigating to HostelCard page...");
            navigateToHostelCardPage();
        });

        // Set action for Classes button to navigate to ClassesCard page
        button3.setOnAction(event -> {
            System.out.println("Navigating to ClassesCard page...");
            navigateToClassesCard();
        });

        // Set action for Hospital button to navigate to HospitalCard page
        button4.setOnAction(event -> {
            System.out.println("Navigating to HospitalCard page...");
            navigateToHospitalCardPage();
        });
        button5.setOnAction(event -> {
            System.out.println("Navigating to card page...");
            navigateToPrivateRoomsCardPage();
        });
    }

    private void navigateToCardPage() {
        card cardPage = new card(this, getHostServices()); // Pass reference to home page
        Scene cardScene = cardPage.createCardScene();
        homeStage.setScene(cardScene); // Set the scene to the card page
    }

    private void navigateToHostelCardPage() {
        HostelCard hostelCardPage = new HostelCard(this, getHostServices());
        Scene hostelCardScene = hostelCardPage.createHostelCardScene();
        homeStage.setScene(hostelCardScene); // Set the scene to the HostelCard page
    }

    private void navigateToClassesCard() {
        ClassesCard classesCardPage = new ClassesCard(this, getHostServices());
        Scene classesCardScene = classesCardPage.createClassCardScene();
        homeStage.setScene(classesCardScene); // Set the scene to the ClassesCard page
    }

    private void navigateToHospitalCardPage() {
        HospitalCards hospitalCardPage = new HospitalCards(this, getHostServices());
        Scene hospitalCardScene = hospitalCardPage.createHospitalCardScene();
        homeStage.setScene(hospitalCardScene); // Set the scene to the HospitalCard page
    }
    private void navigateToPrivateRoomsCardPage() {
        PrivateRoomsCard PrivateCardPage = new PrivateRoomsCard(this,getHostServices());
        Scene privateCardScene = PrivateCardPage.createCardScene();
        homeStage.setScene(privateCardScene); // Set the scene to the HospitalCard page
    }

/* 
    // Method to create styled buttons with labels
    private Button createButtonWithImageAndText(String img,String text) {
        Button button = new Button();
        button.setPrefSize(230, 130);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setTextFill(Color.BLACK);
    Rectangle roundedRectangle = new Rectangle(230, 130);
    roundedRectangle.setArcWidth(20); // Adjust the arc width for rounding
    roundedRectangle.setArcHeight(20); // Adjust the arc height for rounding
    roundedRectangle.setFill(Color.LIGHTGRAY); // Background color
    roundedRectangle.setStroke(Color.DARKGRAY); // Border color
    roundedRectangle.setStrokeWidth(2); // Border width
    StackPane stackPane = new StackPane();
    stackPane.setPrefSize(230, 130);

    // Add the label text
    Label textLabel = new Label(text);
    textLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-background-color: rgba(0, 0, 0, 0.5);");
    textLabel.setPadding(new Insets(5));

    // Add the StackPane to the button
    stackPane.getChildren().addAll(textLabel);
    button.setGraphic(stackPane);
    
    
    // Set button shape
    button.setShape(roundedRectangle);
    button.setStyle(text);
    // Add shadow effect
    button.setEffect(new DropShadow(5, Color.GRAY));
      //  button.setStyle("-fx-background-color: #3b5998; -fx-border-color: black; -fx-border-width: 2px;");

        return button;
    }

    */
    private Button createButtonWithImageAndText(String imgPath, String text) {
        Button button = new Button();
        button.setPrefSize(230, 130);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setTextFill(Color.BLACK);
    
        // Create a rounded rectangle for the button's shape
        Rectangle roundedRectangle = new Rectangle(230, 130);
        roundedRectangle.setArcWidth(20);
        roundedRectangle.setArcHeight(20);
        roundedRectangle.setFill(Color.LIGHTGRAY);
        roundedRectangle.setStroke(Color.DARKGRAY);
        roundedRectangle.setStrokeWidth(2);
        
        // Create an ImageView for the image
        Image image = new Image("file:" + imgPath); // Ensure the path is correct
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(230);  // Match button width
        imageView.setFitHeight(130); // Match button height
        imageView.setPreserveRatio(false);
        // Create a StackPane to hold the image and text
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(230, 130);
        
        // Add the ImageView and Label to the StackPane
        Label textLabel = new Label(text);
        textLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-background-color:gray;-fx-font-weight: bold;");
        textLabel.setPadding(new Insets(5));
      //  textLabel.setStyle(Pos.BOTTOM_CENTER);
        
        stackPane.getChildren().addAll(imageView, textLabel); // Add image and text
        stackPane.setAlignment(Pos.BOTTOM_CENTER);
        // Set button shape and style
        button.setShape(roundedRectangle);
        button.setStyle(text); // Optional: Add any button style here
        button.setEffect(new DropShadow(5, Color.GRAY));
    
        button.setGraphic(stackPane); // Set the StackPane as the button's graphic
    
        return button;
    }
    public Stage getStage() {
        return homeStage;
    }

    public void goToHomePage() {
        homeStage.setScene(homeScene);
    }
    // Method to add animations to buttons
    private void addButtonAnimations(Button... buttons) {
        for (Button button : buttons) {
            // Fade-in animation
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), button);
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(1.0);

            // Rotation animation
            RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), button);
            rotateTransition.setByAngle(360);
            rotateTransition.setCycleCount(1);
            rotateTransition.setInterpolator(Interpolator.EASE_BOTH);

            // Scale animation
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000), button);
            scaleTransition.setFromX(0.5);
            scaleTransition.setFromY(0.5);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);

            // Translate animation
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), button);
            translateTransition.setFromY(-50);
            translateTransition.setToY(0);

            // Bounce effect
            ScaleTransition bounceTransition = new ScaleTransition(Duration.millis(500), button);
            bounceTransition.setFromX(1.0);
            bounceTransition.setFromY(1.0);
            bounceTransition.setToX(1.1);
            bounceTransition.setToY(1.1);
            bounceTransition.setCycleCount(2);
            bounceTransition.setAutoReverse(true);

            // Play animations sequentially
            SequentialTransition sequentialTransition = new SequentialTransition(
                    fadeTransition,
                    new ParallelTransition(rotateTransition, scaleTransition, translateTransition),
                    bounceTransition
            );
            sequentialTransition.play();
        }
    }

    // Method to add translate and fade-in animation to text elements
    private void addTextAnimations(Text... texts) {
        for (Text text : texts) {
            // Translate animation
            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), text);
            translateTransition.setFromY(-50);
            translateTransition.setToY(0);

            // Fade-in animation
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), text);
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(1.0);

            // Bounce effect
            ScaleTransition bounceTransition = new ScaleTransition(Duration.millis(500), text);
            bounceTransition.setFromX(1.0);
            bounceTransition.setFromY(1.0);
            bounceTransition.setToX(1.1);
            bounceTransition.setToY(1.1);
            bounceTransition.setCycleCount(2);
            bounceTransition.setAutoReverse(true);

            // Play animations together
            ParallelTransition parallelTransition = new ParallelTransition(
                    translateTransition,
                    fadeTransition,
                    bounceTransition
            );
            parallelTransition.play();
        }
    }

    
}