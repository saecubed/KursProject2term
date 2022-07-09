package com.example.kurspr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.kurspr.MainApplication.table_quotes;

public class EnteredAuthController implements Initializable {
    @FXML
    private Button backToAuth;
    @FXML
    private TableView<Quote> table;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableColumn<Quote, String> date;

    @FXML
    private TableColumn<Quote, Integer> professor_id;

    @FXML
    private TableColumn<Quote, String> quote;

    @FXML
    private TableColumn<Quote, Integer> subject_id;

    @FXML
    private Button quotesManagement;

    @FXML
    private Button profileManagement;

    public static int id = com.example.kurspr.AuthController.id;

    ObservableList<Quote> guest_quotes = FXCollections.observableArrayList(table_quotes.quotes);

    @FXML
    void switchToAuth(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("authScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToQM(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("quotesManagingScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToPM(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("profileScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        date.setCellValueFactory(new PropertyValueFactory<Quote, String>("date"));
        professor_id.setCellValueFactory(new PropertyValueFactory<Quote, Integer>("professor_id"));
        quote.setCellValueFactory(new PropertyValueFactory<Quote, String>("quote"));
        subject_id.setCellValueFactory(new PropertyValueFactory<Quote, Integer>("subject_id"));
        table.setItems(guest_quotes);
    }
}
