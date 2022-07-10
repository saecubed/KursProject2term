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

import static com.example.kurspr.MainApplication.table_users;

public class ChangeLoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button OkNewLogin;

    @FXML
    private Button backToProfile;

    @FXML
    private Label enterLoginText;

    @FXML
    private Label message;

    @FXML
    private TextField newLoginField;

    public static int id;

    @FXML
    void setNewLogin(ActionEvent event) {
        String new_login = newLoginField.getText();
        int count = -1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
            Statement statement = connection.createStatement();
            String query = "SELECT COUNT(id) FROM users WHERE login = '" + new_login + "';";
            ResultSet result = statement.executeQuery(query);
            result.next();
            count = result.getInt("COUNT(id)");
            if (count != 0) {
                message.setText("Этот логин уже занят");
            }
            else if (new_login.equals("")) {
                message.setText("Введите логин");
            }
            else{
                String upd_query = "UPDATE users SET login = ? WHERE id = " + id + ";";
                try {
                    PreparedStatement upd_statement = connection.prepareStatement(upd_query);
                    upd_statement.setString(1, new_login);
                    upd_statement.execute();
                    connection.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                message.setText("Логин успешно изменен");
                newLoginField.setText(" ");
                Statement new_statement = connection.createStatement();
                String new_query = "SELECT * FROM users WHERE id = " + id;
                ResultSet new_result = new_statement.executeQuery(new_query);
                String new_password = new_result.getString("password");
                int new_role_id = new_result.getInt("role_id");
                table_users.users.removeIf(Item -> (Item.id == id));
                table_users.users.add(new User (id, new_login, new_password, new_role_id));
            }

            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
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
