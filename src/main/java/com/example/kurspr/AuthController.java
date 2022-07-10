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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import static com.example.kurspr.MainApplication.table_users;


public class AuthController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label authFailed;
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

    public static int id;


    @FXML
    public void enterButtonClicked(ActionEvent event) throws IOException {

        String login = loginField.getText();
        String password = passwordField.getText();
        id = -1;
        /*
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
            Statement statement = connection.createStatement();
            String query = "SELECT id FROM users WHERE login = '" + login + "' AND password = '" + password + "';";
            ResultSet result = statement.executeQuery(query);
            result.next();
            id = result.getInt("id");
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        if (id == -1) {
            authFailed.setText("Введенные данные некорректны");
        }
        else {
            com.example.kurspr.EnteredAuthController.id = id;
            switchToEntered(event);
        }

         */
        boolean access = true;
        for (int i = 0; i < table_users.users.size(); i++) {
            if (table_users.users.get(i).login.equals(login) && table_users.users.get(i).hash_password.equals(String.valueOf(password.hashCode()))) {
                access = true;
                id = table_users.users.get(i).id;
                authFailed.setText("true");
                break;
            }
            else {
                access = false;

            }
        }
        if (access == false) {
            authFailed.setText("Введенные данные некорректны ");
        }
        else {
            com.example.kurspr.EnteredAuthController.id = id;
            switchToEntered(event);
        }
    }

    @FXML
    void switchToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToEntered(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("enteredAuthScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
