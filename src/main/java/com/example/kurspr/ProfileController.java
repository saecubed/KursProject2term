package com.example.kurspr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button backToEnteredAuth;

    @FXML
    private Button changeLogin;

    @FXML
    private Button changePassword;

    public static int id = com.example.kurspr.EnteredAuthController.id;


    @FXML
    void switchToCL(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("changeLoginScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToCP(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("changePasswordScr.fxml"));
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

}
