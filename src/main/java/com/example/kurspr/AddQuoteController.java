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

public class AddQuoteController  {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public static int id = com.example.kurspr.AuthController.id;

    @FXML
    private Button backToAuth;

    @FXML
    private TextField dateField;

    @FXML
    private Label dateText;

    @FXML
    private TextField profField;

    @FXML
    private Label profText;

    @FXML
    private Button publish_button;

    @FXML
    private TextField quoteField;

    @FXML
    private Label quoteText;

    @FXML
    private Label message;

    @FXML
    private TextField subjectField;

    @FXML
    private Label subjectText;

    @FXML
    void publish(ActionEvent event) {
        String quote = quoteField.getText();
        String professor = profField.getText();
        String subject = subjectField.getText();
        String date = dateField.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
//
            if (quote.equals("") || professor.equals("") || subject.equals("") || date.equals("")) {
                message.setText("Заполните все поля");
            }
            else if (getProf(professor) == -1) {
                message.setText("Такого преподавателя нет");
            }
            else if (getSub(subject) == -1) {
                message.setText("Такого предмета нет");
            }
            else{
                String query = "INSERT INTO quotes (id, quote, professor_id, subject_id, date, publisher_id) VALUES (?, ?, ?, ?, ?, ?);";
                try {
                    PreparedStatement statement = connection.prepareStatement(query,
                            Statement.RETURN_GENERATED_KEYS);
                    statement.setNull(1, Types.INTEGER);
                    statement.setString(2, quote);
                    statement.setInt(3, getProf(professor));
                    statement.setInt(4, getSub(subject));
                    statement.setString(5, date);
                    statement.setInt(6, id);
                    statement.execute();

                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    int genKey = -1;
                    if (generatedKeys.next()) {
                        genKey = generatedKeys.getInt(1);
                    }
                    table_quotes.quotes.add(new Quote(genKey,quote,getProf(professor),getSub(subject), date, id));
                    message.setText("Цитата успешно добавлена");
                    quoteField.setText("");
                    profField.setText("");
                    subjectField.setText("");
                    dateField.setText("");

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
    void switchToEnteredAuth(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("enteredAuthScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
