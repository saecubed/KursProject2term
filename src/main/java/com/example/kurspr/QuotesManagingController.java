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
    public static int id = com.example.kurspr.EnteredAuthController.id;
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
        root = FXMLLoader.load(getClass().getResource("addQuoteScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToChangeQuote(ActionEvent event) {

    }

    @FXML
    void switchToDeleteQuote(ActionEvent event) {

    }

    @FXML
    void switchToEnteredAuth(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("enteredAuthScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
