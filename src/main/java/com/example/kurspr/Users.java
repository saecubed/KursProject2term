package com.example.kurspr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Users {
    public ArrayList<User> users = new ArrayList<>();

    public Users(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
//
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet result = statement.executeQuery(query);
//
            while(result.next()){
                int id = result.getInt("id");
                String login = result.getString("login");
                String password = result.getString("password");
                int role_id = result.getInt("role_id");
                User user = new User(id, login, password, role_id);
                users.add(user);
            }
//
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
