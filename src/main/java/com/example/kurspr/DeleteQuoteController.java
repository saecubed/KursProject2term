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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
    @FXML
    private Button deleteButton;
    @FXML
    private TextField deleting_id;


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
        table.setItems(id_quotes);
    }

    @FXML
    void deleteQuote(ActionEvent event) {
        String del_id = deleting_id.getText();
        if (del_id.equals("")) {
            message.setText("Введите ID");
        }
        else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                        "std_1987_kurpr", "12345678");
                String check = "SELECT access_to_quote(" + del_id + "," + id + ") AS res;";
                Statement check_statement = connection.createStatement();
                ResultSet res = check_statement.executeQuery(check);
                res.next();
                int result = res.getInt("res");

                if (result != 1) {
                    message.setText("Введите корректное значение ID для доступной вам записи");
                }
                else {
                    String query = "DELETE FROM quotes WHERE id = ?";
                    try {
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setInt(1,  Integer.parseInt(del_id));
                        statement.execute();
                        message.setText("Запись удалена");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                connection.close();
            }
            catch(Exception e){
                System.out.println(e);
            }
            table_quotes.quotes.removeIf(Item -> (Item.id == Integer.parseInt(del_id)));
        }
    }
}
