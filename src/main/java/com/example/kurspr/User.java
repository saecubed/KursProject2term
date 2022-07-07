package com.example.kurspr;

public class User {
    public int id;
    public String login;
    public String password;
    public int role_id;
    public User(int id, String login, String password, int role_id) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role_id = role_id;
    }
}
