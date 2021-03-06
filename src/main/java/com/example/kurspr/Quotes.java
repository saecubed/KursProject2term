package com.example.kurspr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Quotes {
    public ArrayList<Quote> quotes = new ArrayList<>();

    public Quotes(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
//
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM quotes";
            ResultSet result = statement.executeQuery(query);
//
            while(result.next()){
                int id = result.getInt("id");
                String quote_text = result.getString("quote");
                int professor_id = result.getInt("professor_id");
                int subject_id = result.getInt("subject_id");
                String date = result.getString("date");
                int publisher_id = result.getInt("publisher_id");
                Quote quote = new Quote(id, quote_text, professor_id, subject_id, date, publisher_id);
                quotes.add(quote);
            }
//
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public ArrayList<Quote> choose_by_user_id (int user_id) {
        ArrayList<Quote> id_quotes = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
//
            Statement statement = connection.createStatement();
            String query = "SELECT id, quote, professor_id, subject_id, date, publisher_id FROM (SELECT *, IF (access_to_quote(quotes.id, " + user_id + ") = 1, 1, 0) as res FROM quotes WHERE access_to_quote(quotes.id, " + user_id + ") = 1) tmp;" ;
            ResultSet result = statement.executeQuery(query);
//
            while(result.next()){
                int id = result.getInt("id");
                String quote_text = result.getString("quote");
                int professor_id = result.getInt("professor_id");
                int subject_id = result.getInt("subject_id");
                String date = result.getString("date");
                int publisher_id = result.getInt("publisher_id");
                Quote quote = new Quote(id, quote_text, professor_id, subject_id, date, publisher_id);
                id_quotes.add(quote);
            }
//
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return id_quotes;
    }

    public ArrayList<Quote> choose_by_quote_id (int quote_id) {
        ArrayList<Quote> id_quotes = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1987_kurpr",
                    "std_1987_kurpr", "12345678");
//
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM quotes WHERE id = " + quote_id + ";";
            ResultSet result = statement.executeQuery(query);
//
            while(result.next()){
                int id = result.getInt("id");
                String quote_text = result.getString("quote");
                int professor_id = result.getInt("professor_id");
                int subject_id = result.getInt("subject_id");
                String date = result.getString("date");
                int publisher_id = result.getInt("publisher_id");
                Quote quote = new Quote(id, quote_text, professor_id, subject_id, date, publisher_id);
                id_quotes.add(quote);
            }
//
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return id_quotes;
    }
}
