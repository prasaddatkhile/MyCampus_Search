// package com.example;

// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.layout.*;
// import javafx.stage.Stage;

// import java.util.List;

// public class ExpenseCalendarApp extends Application {

//     private ExpenseCalendar expenseCalendar;
//     private ExpenseManager expenseManager;
//     private Categories categories;

//     @Override
//     public void start(Stage primaryStage) {
//         expenseCalendar = new ExpenseCalendar(2023, 7); // Example year and month
//         expenseManager = new ExpenseManager();
//         categories = new Categories();

//         VBox vbox = new VBox(10);
//         vbox.setPadding(new Insets(20));
//         vbox.setAlignment(Pos.CENTER);

//         Label titleLabel = new Label("Expense Calendar");
//         titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

//         GridPane calendarGrid = new GridPane();
//         calendarGrid.setPadding(new Insets(10));
//         calendarGrid.setHgap(10);
//         calendarGrid.setVgap(10);

//         List<List<Object>> calendar = expenseCalendar.getMonthCalendar();
//         for (int i = 0; i < calendar.size(); i++) {
//             List<Object> week = calendar.get(i);
//             for (int j = 0; j < week.size(); j++) {
//                 Object day = week.get(j);
//                 Label dayLabel = new Label(day == null ? "" : day.toString());
//                 dayLabel.setStyle("-fx-border-color: black; -fx-padding: 10;");
//                 dayLabel.setPrefSize(100, 100);
//                 dayLabel.setAlignment(Pos.CENTER);
//                 calendarGrid.add(dayLabel, j, i);
//             }
//         }

//         ChoiceBox<String> categoryChoiceBox = new ChoiceBox<>();
//         categoryChoiceBox.getItems().addAll(categories.getCategories());
//         categoryChoiceBox.getSelectionModel().selectFirst();

//         TextField descriptionField = new TextField();
//         descriptionField.setPromptText("Enter description");

//         TextField amountField = new TextField();
//         amountField.setPromptText("Enter amount");

//         TextField dayField = new TextField();
//         dayField.setPromptText("Enter day");

//         Button addButton = new Button("Add Expense");

//         addButton.setOnAction(e -> {
//             String category = categoryChoiceBox.getValue();
//             String description = descriptionField.getText();
//             double amount = Double.parseDouble(amountField.getText());
//             int day = Integer.parseInt(dayField.getText());

//             Expense expense = new Expense(category, description, amount);
//             if (expenseCalendar.addExpenseToCalendar(day, expense)) {
//                 // Update the UI to reflect the added expense
//                 for (int i = 0; i < calendar.size(); i++) {
//                     List<Object> week = calendar.get(i);
//                     if (week.contains(expense)) {
//                         Label dayLabel = new Label(expense.toString());
//                         dayLabel.setStyle("-fx-border-color: black; -fx-padding: 10;");
//                         dayLabel.setPrefSize(100, 100);
//                         dayLabel.setAlignment(Pos.CENTER);
//                         calendarGrid.add(dayLabel, week.indexOf(expense), i);
//                         break;
//                     }
//                 }
//             }

//             descriptionField.clear();
//             amountField.clear();
//             dayField.clear();
//         });

//         vbox.getChildren().addAll(titleLabel, calendarGrid, categoryChoiceBox, descriptionField, amountField, dayField, addButton);

//         Scene scene = new Scene(vbox, 800, 600);
//         primaryStage.setTitle("Expense Calendar");
//         primaryStage.setScene(scene);
//         primaryStage.show();
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }

package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpenseCalendarApp extends Application {

    private ExpenseCalendar expenseCalendar;
    private ExpenseManager expenseManager;
    private Categories categories;

    @Override
    public void start(Stage primaryStage) {
        expenseCalendar = new ExpenseCalendar(2023, 7); // Example year and month
        expenseManager = new ExpenseManager();
        categories = new Categories();

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Expense Calendar");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        GridPane calendarGrid = new GridPane();
        calendarGrid.setPadding(new Insets(10));
        calendarGrid.setHgap(10);
        calendarGrid.setVgap(10);

        List<List<Object>> calendar = expenseCalendar.getMonthCalendar();
        for (int i = 0; i < calendar.size(); i++) {
            List<Object> week = calendar.get(i);
            for (int j = 0; j < week.size(); j++) {
                Object day = week.get(j);
                Label dayLabel = new Label(day == null ? "" : day.toString());
                dayLabel.setStyle("-fx-border-color: black; -fx-padding: 10;");
                dayLabel.setPrefSize(100, 100);
                dayLabel.setAlignment(Pos.CENTER);
                calendarGrid.add(dayLabel, j, i);
            }
        }

        ChoiceBox<String> categoryChoiceBox = new ChoiceBox<>();
        categoryChoiceBox.getItems().addAll(categories.getCategories());
        categoryChoiceBox.getSelectionModel().selectFirst();

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Enter description");

        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");

        TextField dayField = new TextField();
        dayField.setPromptText("Enter day");

        Button addButton = new Button("Add Expense");

        addButton.setOnAction(e -> {
            try {
                String category = categoryChoiceBox.getValue();
                String description = descriptionField.getText();
                double amount = Double.parseDouble(amountField.getText());
                int day = Integer.parseInt(dayField.getText());

                Expense expense = new Expense(category, description, amount);
                expenseCalendar.addExpenseToCalendar(day, expense);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Expense added successfully!");
                alert.show();

                descriptionField.clear();
                amountField.clear();
                dayField.clear();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input! Please enter numeric amount and day.");
                alert.show();
            }
        });

        vbox.getChildren().addAll(
                titleLabel,
                calendarGrid,
                categoryChoiceBox,
                descriptionField,
                amountField,
                dayField,
                addButton
        );

        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setTitle("Expense Calendar");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// ---------- Mock classes for functionality ----------

class ExpenseCalendar {
    private final int year;
    private final int month;

    public ExpenseCalendar(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public List<List<Object>> getMonthCalendar() {
        List<List<Object>> calendar = new ArrayList<>();
        int day = 1;
        for (int i = 0; i < 5; i++) {
            List<Object> week = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                if (day <= 31) {
                    week.add(day++);
                } else {
                    week.add(null);
                }
            }
            calendar.add(week);
        }
        return calendar;
    }

    public boolean addExpenseToCalendar(int day, Expense expense) {
        System.out.println("Expense for day " + day + ": " + expense);
        return true;
    }
}

class Expense {
    private final String category;
    private final String description;
    private final double amount;

    public Expense(String category, String description, double amount) {
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return description + " - ₹" + amount + " (" + category + ")";
    }
}

class Categories {
    public List<String> getCategories() {
        return Arrays.asList("Food", "Travel", "Utilities", "Entertainment", "Others");
    }
}

class ExpenseManager {
    // Reserved for future logic
}
