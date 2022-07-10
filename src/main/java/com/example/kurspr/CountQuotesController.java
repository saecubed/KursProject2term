package com.example.kurspr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class CountQuotesController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static int id;
    @FXML
    private Button countButton;
    @FXML
    private Button goToQM;
    @FXML
    private Label res1;
    @FXML
    private Label res2;
    @FXML
    private Label res3;

    @FXML
    void countQuotes(ActionEvent event) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
            Statement statement1 = connection.createStatement();
            String query1 = "SELECT COUNT(id) as res FROM quotes WHERE publisher_id = " + id + ";";
            ResultSet result1 = statement1.executeQuery(query1);
            result1.next();
            int count1 = result1.getInt("res");
            res1.setText(String.valueOf(count1));

            Statement statement2 = connection.createStatement();
            String query2 = "SELECT SUM(IF (publisher_id = " + id + ", 1, 0)) as res FROM quotes WHERE publisher_id = " + id + " GROUP BY publisher_id;";
            ResultSet result2 = statement2.executeQuery(query2);
            result2.next();
            int count2 = result2.getInt("res");
            res2.setText(String.valueOf(count2));

            Statement statement3 = connection.createStatement();
            String query3 = "SELECT COUNT(id) as res FROM quotes WHERE publisher_id IN (SELECT publisher_id FROM quotes WHERE publisher_id = " + id + ");";
            ResultSet result3 = statement3.executeQuery(query3);
            result3.next();
            int count3 = result3.getInt("res");
            res3.setText(String.valueOf(count3));

            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void switchToQM(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("quotesManagingScr.fxml"));
        stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
