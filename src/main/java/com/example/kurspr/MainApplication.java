package com.example.kurspr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class MainApplication extends Application {
    //public static Stage mainStage;
    public static Quotes table_quotes = new Quotes();
    public static Users table_users = new Users();

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainScr.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Курсовой проект");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}