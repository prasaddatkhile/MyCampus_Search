package com.example;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class food extends Application {

    @Override
    public void start(Stage foodStage) throws Exception {

        foodStage.setTitle("Food Stage");
        foodStage.setHeight(900);
        foodStage.setWidth(900);

       
        

        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);  
        scrollPane.setPrefWidth(880); 

       
        Group gp = new Group(scrollPane);
        Scene sc = new Scene(gp, 900, 900); 
        scrollPane.prefViewportHeightProperty().bind(sc.heightProperty()); 

        foodStage.setScene(sc);
        foodStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
