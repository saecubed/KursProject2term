package com.example.kurspr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.kurspr.MainApplication.mainStage;

public class AuthController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button enterButton;

    @FXML
    private Button backToMain;

    @FXML
    private TextField loginField;

    @FXML
    private Label loginText;

    @FXML
    private TextField passwordField;


    @FXML
    public void enterButtonClicked(ActionEvent actionEvent){
        String login = loginField.getText();
        String password = passwordField.getText();
    }

    @FXML
    void switchToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
