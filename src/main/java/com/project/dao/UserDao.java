package com.project.dao;

import com.project.beans.User;
import com.project.database.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static final String INSERT_USER = "INSERT INTO registered_users(login, password, email, sex, age, comment)VALUES(?, ?, ?, ?, ?, ?) ";
    private static final String SELECT_USER_BY_LOGIN = "SELECT login, password, email, sex, age, comment FROM registered_users WHERE login IN(?) ;";
    private static final String SELECT_USER_BY_EMAIL = "SELECT login, password, email, sex, age, comment FROM registered_users WHERE email IN(?) ;";

    public void addUserToDatabase(User user){
        Connection connection = null;
        ConnectionManager connectionManager = new ConnectionManager();
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getSex());
            preparedStatement.setInt(5,user.getAge());
            preparedStatement.setString(6,user.getComment());

            int id = preparedStatement.executeUpdate();
            System.out.println("Connecting to database...");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public User getUserByLogin(String login){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ConnectionManager connectionManager = new ConnectionManager();
        ResultSet resultSet = null;
        User result = null;
        try {
            connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1,login);
            resultSet = preparedStatement.executeQuery();
            result = new User(resultSet.getString("login"),resultSet.getString("password"),resultSet.getString("email") ,
                    resultSet.getString("sex"), resultSet.getInt("age"), resultSet.getString("comment")  );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean checkIfUserExists(String login){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ConnectionManager connectionManager = new ConnectionManager();
        ResultSet resultSet = null;
        boolean result = false;
        try {
            connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1,login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean checkIfEmailRegistered(String email){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ConnectionManager connectionManager = new ConnectionManager();
        ResultSet resultSet = null;
        boolean result = false;
        try {
            connection = connectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            preparedStatement.setString(1,email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
