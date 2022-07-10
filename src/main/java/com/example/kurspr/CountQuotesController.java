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

public class CountQuotesController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static int id;
    @FXML
    private Button countButton;

    @FXML
    private Button goToQM;

    @FXML
    private Label res1;

    @FXML
    private Label res2;

    @FXML
    private Label res3;

    @FXML
    void countQuotes(ActionEvent event) {

    }

    @FXML
    void switchToQM(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("quotesManagingScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
