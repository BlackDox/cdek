package com.blackdox.database.model;

import com.blackdox.database.controller.Controller;

import java.sql.*;
import java.util.ArrayList;

public class DBWorker implements DBAccess {
    private static final String URL = "jdbc:mysql://localhost:3306/dbsdek";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String INSERT_COMMAND = "INSERT INTO users (name) VALUES(?)";

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private Controller controller;

    public DBWorker(Controller controller) {
        this.controller = controller;
        connect();
    }

    @Override
    public ArrayList<User> getData(String filter) {
        ArrayList<User> users = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            if (filter.trim().isEmpty() || filter.equals("Filter")) {
                resultSet = getStatement().executeQuery("SELECT * FROM users");
            } else {
                resultSet = getStatement().executeQuery("SELECT * FROM users WHERE name LIKE '%" + filter + "%'");
            }
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                users.add(user);
            }
        } catch (SQLException e) {
            controller.logIt("Access to MySQL-database FAILED.");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void insertData(String username) {
        try {
            getPreparedStatement().setString(1, username);
            getPreparedStatement().execute();
        } catch (SQLException e) {
            controller.logIt("Access to MySQL-database FAILED.");
            e.printStackTrace();
        }
    }

    @Override
    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(INSERT_COMMAND);
            System.out.println("Access to MySQL-database successfully.");
            controller.logIt("Access to MySQL-database successfully.");
        } catch (SQLException e) {
            controller.logIt("Access to MySQL-database FAILED.");
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            close();
        } catch (Exception e) {
            controller.logIt("Access to MySQL-database FAILED.");
            e.printStackTrace();
        }
    }

    private PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public Statement getStatement() {
        return statement;
    }

    @Override
    public void close() {
        try {
            if (preparedStatement != null) preparedStatement.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        controller.logIt("Access to MySQL-database is closed.");
        System.out.println("Access to MySQL-database is closed.");
    }
}
