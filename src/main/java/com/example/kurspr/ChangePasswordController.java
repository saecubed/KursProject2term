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


public class ChangePasswordController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button OkNewPassword;

    @FXML
    private Button backToProfile;

    @FXML
    private Label enterPasswordText;

    @FXML
    private Label message;

    @FXML
    private TextField newPasswordField;

    public static int id;

    @FXML
    void setNewPassword(ActionEvent event) {
        String new_password = newPasswordField.getText();
        if (new_password.equals("")) {
            message.setText("Введите пароль");
        }
        else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
//
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                        "std_1987_kurpr", "12345678");
//
                String upd_query = "UPDATE users SET password = ? WHERE id = " + id + ";";
                try {
                    PreparedStatement upd_statement = connection.prepareStatement(upd_query);
                    upd_statement.setString(1, new_password);
                    upd_statement.execute();
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                message.setText("Пароль успешно изменен");
                newPasswordField.setText(" ");
                connection.close();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }

    @FXML
    void switchToProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("profileScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
