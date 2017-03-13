package com.project.beans;

import javax.websocket.Session;

public class User {
    private String login;
    private String password;
    private String email;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.email = null;

    }

    public User(User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();

    }

    public User(String login, String password, boolean isOnline, String sex, int age, String comment, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(Object obj) {
        User user = (User) obj;
        if (user.getPassword().equals(this.password) && user.getLogin().equals(this.login)) {
            return true;
        } else {
            return false;
        }
    }
}
