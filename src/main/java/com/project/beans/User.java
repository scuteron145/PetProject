package com.project.beans;

import javax.websocket.Session;

public class User implements java.lang.Comparable {
    private String login;
    private String password;
    private String sex;
    private int age;
    private String comment;
    private String email;
    private Session session;

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.sex = null;
        this.comment = null;

    }

    public User(User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.sex = user.getSex();
        this.comment = user.getComment();
        this.email = user.getComment();
    }

    public User(String login, String password,  String email,  String sex, int age, String comment) {
        this.login = login;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.comment = comment;
        this.email = email;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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


    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        if (user.getPassword().equals(this.password) && user.getLogin().equals(this.login)) {
            return 0;
        } else if (user.getAge() > this.getAge()) {
            return -1;
        } else {
            return 1;
        }
    }
}
