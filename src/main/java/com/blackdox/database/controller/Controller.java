package com.blackdox.database.controller;

import com.blackdox.database.model.User;

import java.util.ArrayList;

public interface Controller {
    void exit();
    ArrayList<User> getData();
    void insertData(String username);
    void logIt(String message);
}
