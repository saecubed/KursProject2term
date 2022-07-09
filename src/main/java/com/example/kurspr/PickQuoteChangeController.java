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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.kurspr.MainApplication.table_quotes;

public class PickQuoteChangeController implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button backToQC;

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
    private Button quoteButton;
    @FXML
    private Button profButton;
    @FXML
    private Button subButton;
    @FXML
    private Button dateButton;
    @FXML
    private TextField quoteField;
    @FXML
    private TextField profField;
    @FXML
    private TextField subField;
    @FXML
    private DatePicker datePicker;


    public static int id;
    public static int qt_id;
    ObservableList<Quote> id_quotes = FXCollections.observableArrayList(table_quotes.choose_by_quote_id(qt_id));

    @FXML
    void switchToQC(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("changeQuoteScr.fxml"));
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
    void changeDate(ActionEvent event) {
        String date = String.valueOf(datePicker.getValue());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
//
            if (date.equals("")) {
                message.setText("Заполните поле");
            }
            else {
                String query = "UPDATE quotes SET date = ? WHERE id = " + qt_id;
                try {
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, date);
                    statement.execute();
                    subField.setText("");
                    message.setText("Изменения сохранены");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void changeProf(ActionEvent event) {
        String name = profField.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
//
            if (name.equals("")) {
                message.setText("Заполните поле");
            }
            else if (getProf(name) == -1) {
                message.setText("Такого преподавателя нет");
            }
            else {
                String query = "UPDATE quotes SET professor_id = ? WHERE id = " + qt_id;
                try {
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, getProf(name));
                    statement.execute();
                    profField.setText("");
                    message.setText("Изменения сохранены");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    @FXML
    void changeQuote(ActionEvent event) {
        String new_quote = quoteField.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
//
            if (new_quote.equals("")) {
                message.setText("Заполните поле");
            }

            else {
                String query = "UPDATE quotes SET quote = ? WHERE id = " + qt_id;
                try {
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, new_quote);
                    statement.execute();
                    subField.setText("");
                    message.setText("Изменения сохранены");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void changeSub(ActionEvent event) {
        String name = subField.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
//
            if (name.equals("")) {
                message.setText("Заполните поле");
            }
            else if (getSub(name) == -1) {
                message.setText("Такого предмета нет");
            }
            else {
                String query = "UPDATE quotes SET subject_id = ? WHERE id = " + qt_id;
                try {
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, getSub(name));
                    statement.execute();
                    subField.setText("");
                    message.setText("Изменения сохранены");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    int getProf(String full_name) {
        int id = -1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
//
            Statement statement = connection.createStatement();
            String query = "SELECT id FROM professors WHERE full_name = '" + full_name + "';";
            ResultSet result = statement.executeQuery(query);
            result.next();
            id = result.getInt("id");
            /*
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, full_name);
                statement.execute();
                ResultSet result = statement.executeQuery(query);
                int id = -1;
                id = result.getInt("id");
                if (id == -1) {
                    res = false;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

             */

            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return id;
    }

    int getSub(String subject_name) {
        int id = -1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
//
            Statement statement = connection.createStatement();
            String query = "SELECT id FROM subjects WHERE subject_name = '" + subject_name + "';";
            ResultSet result = statement.executeQuery(query);
            result.next();
            id = result.getInt("id");
            /*
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, subject_name);
                statement.execute();
                ResultSet result = statement.executeQuery(query);
                int id = -1;
                id = result.getInt("id");
                if (id == -1) {
                    res = false;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

         */

            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return id;
    }
}
