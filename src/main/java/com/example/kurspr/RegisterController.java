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

public class RegisterController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button RegisterButton;

    @FXML
    private Button backToMain;

    @FXML
    private Label regOk;

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

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
//
            String query = "INSERT INTO users (id, login, password, role_id) VALUES (?, ?, ?, ?);";
            try {
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);
                statement.setNull(1, Types.INTEGER);
                statement.setString(2, login);
                statement.setString(3, password);
                statement.setInt(4, 3);
                statement.execute();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                int genKey = -1;
                if (generatedKeys.next()) {
                    genKey = generatedKeys.getInt(1);
                }

                //////////
                Statement get_ver_id = connection.createStatement();
                String query_ver = "SELECT verificator_id FROM \n" +
                        "(SELECT verificator_id, COUNT(controlled_id) FROM verificators GROUP BY verificator_id DESC LIMIT 1) AS ver;";
                ResultSet result;
                result = get_ver_id.executeQuery(query_ver);
                result.next();
                int ver_id = result.getInt("verificator_id");
                //////////

                //"INSERT INTO verificators (verificator_id, controlled_id) VALUES (?,?);"
                Statement statement1 = connection.createStatement();
                statement1.executeUpdate("INSERT INTO verificators (verificator_id, controlled_id) VALUES (" + ver_id + ", " + genKey + ");");
                /////////
                connection.close();
                table_users.users.add(new User(genKey,login,password,3));

            } catch (SQLException e) {
                e.printStackTrace();
            }


            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        regOk.setText("Вы успешно зарегистрированы! Войдите в аккаунт через главное меню");
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
