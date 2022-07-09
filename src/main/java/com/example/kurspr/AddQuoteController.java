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
            else{
                String query = "INSERT INTO quotes (id, quote, professor_id, subject_id, date, publisher_id) VALUES (?, ?, ?, ?, ?, ?);";
                try {
                    PreparedStatement statement = connection.prepareStatement(query,
                            Statement.RETURN_GENERATED_KEYS);
                    statement.setNull(1, Types.INTEGER);
                    statement.setString(2, quote);
                    statement.setInt(3, 1);
                    statement.setInt(4, 1);
                    statement.setString(5, date);
                    statement.setInt(6, id);
                    statement.execute();

                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    int genKey = -1;
                    if (generatedKeys.next()) {
                        genKey = generatedKeys.getInt(1);
                    }
                    table_quotes.quotes.add(new Quote(genKey,quote,1,1, date, id));
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

}
