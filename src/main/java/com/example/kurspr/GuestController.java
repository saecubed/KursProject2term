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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GuestController implements Initializable {
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

    Quotes table_quotes = new Quotes();
    ObservableList<Quote> guest_quotes = FXCollections.observableArrayList(table_quotes.quotes);

    @FXML
    void switchToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainScr.fxml"));
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
