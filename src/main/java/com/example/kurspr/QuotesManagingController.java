package com.example.kurspr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class QuotesManagingController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public static int id;
    @FXML
    private Button CountButton;
    @FXML
    private Button addButton;
    @FXML
    private Button backToEnteredAuth;
    @FXML
    private Button changeButton;
    @FXML
    private Button deleteButton;
    @FXML
    void switchToAddQuote(ActionEvent event) throws IOException {
        com.example.kurspr.AddQuoteController.id = id;
        root = FXMLLoader.load(getClass().getResource("addQuoteScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToChangeQuote(ActionEvent event) throws IOException {
        com.example.kurspr.ChangeQuoteController.id = id;
        root = FXMLLoader.load(getClass().getResource("changeQuoteScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToDeleteQuote(ActionEvent event) throws IOException {
        com.example.kurspr.DeleteQuoteController.id = id;
        root = FXMLLoader.load(getClass().getResource("deleteQuoteScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToEnteredAuth(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("enteredAuthScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToCountQuotes(ActionEvent event) throws IOException {
        com.example.kurspr.CountQuotesController.id = id;
        root = FXMLLoader.load(getClass().getResource("countQuotesScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
