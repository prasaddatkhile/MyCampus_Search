package com.example;

import com.example.DataServices.FirestoreService;

import javafx.application.Application;



public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        FirestoreService.initializeFb();
        Application.launch(SignUpLoginApp.class, args);
    }
}