package com.example.kurspr;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.kurspr.MainApplication.table_quotes;

public class DeleteQuoteController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button backToQM;

    @FXML
    private TableColumn<Quote, String> date;

    @FXML
    private TableColumn<Quote, Integer> id_quote;

    @FXML
    private Label message;

    @FXML
    private TableColumn<Quote, Integer> professor;

    @FXML
    private TableColumn<Quote, String> quote;

    @FXML
    private TableColumn<Quote, Integer> subject;

    @FXML
    private TableView<Quote> table;


    public static int id;
    ObservableList<Quote> id_quotes = FXCollections.observableArrayList(table_quotes.choose_by_user_id(id));


    @FXML
    void switchToQM(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("quotesManagingScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_quote.setCellValueFactory(new PropertyValueFactory<Quote, Integer>("id"));
        quote.setCellValueFactory(new PropertyValueFactory<Quote, String>("quote"));
        professor.setCellValueFactory(new PropertyValueFactory<Quote, Integer>("professor_id"));
        subject.setCellValueFactory(new PropertyValueFactory<Quote, Integer>("subject_id"));
        date.setCellValueFactory(new PropertyValueFactory<Quote, String>("date"));
        //id_quote.setCellValueFactory(cellInteger -> cellInteger.getValue().getId());
        table.setItems(id_quotes);
    }
}
