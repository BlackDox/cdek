package com.blackdox.database.model;

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

    public DBWorker() {
        connect();
    }

    @Override
    public ArrayList<User> getData() {
        ArrayList<User> users = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                users.add(user);
            }
        } catch (SQLException e) {
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
            e.printStackTrace();
        }
    }

    @Override
    public boolean isConnectionClosed() {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        } return true;
    }

    @Override
    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(INSERT_COMMAND);
            System.out.println("Access to MySQL-database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            close();
        } catch (Exception e) {
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
    public void close() throws Exception {
        preparedStatement.close();
        statement.close();
        connection.close();
        System.out.println("Access to MySQL-database is closed.");
    }
}
