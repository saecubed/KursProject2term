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


public class MainController {
    public FXMLLoader fxmlLoader;
    public Parent fxmlEdit;
    public Stage editDialogStage;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button authButton;

    @FXML
    private Button guestButton;

    @FXML
    private Button registerButton;

    @FXML
    public void switchToAuth(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("authScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToGuest(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("guestScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToRegister(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("registerScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}