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
import java.sql.*;


public class RegisterController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button RegisterButton;

    @FXML
    private Button backToMain;

    @FXML
    private TextField loginField;

    @FXML
    private Label loginText;

    @FXML
    private TextField passwordField;

    @FXML
    private Label passwordText;

    @FXML
    void register(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();
        /*
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
//
            //Statement statement = connection.createStatement();


            String query = new StringBuilder()
                    .append("INSERT INTO users (");
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setNull(1, Types.INTEGER);
            ResultSet result = statement.executeQuery(query);
//
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

         */
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
