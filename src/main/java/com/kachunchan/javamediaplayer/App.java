package com.kachunchan.javamediaplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * This starts the JavaFX application.
 */
public class App extends Application {

    // These class fields sets the initial width and height of the Java FX screen.
    private static int INITIALWIDTH = 1024;
    private static int INTIALHEIGHT = 768;

    // This opens the Java FX window using FXML file as the layout of the GUI.
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Java Media Player");
        primaryStage.setScene(new Scene(root, INITIALWIDTH, INTIALHEIGHT));
        primaryStage.show();
    }

    // Starts the application.
    public static void main(String[] args) {
        launch();
    }
}