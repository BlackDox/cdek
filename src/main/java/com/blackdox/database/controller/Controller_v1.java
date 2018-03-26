package com.blackdox.database.controller;

import com.blackdox.database.model.DBAccess;
import com.blackdox.database.model.DBWorker;
import com.blackdox.database.model.User;
import com.blackdox.database.view.View;
import com.blackdox.database.view.ViewWindow;

import java.util.ArrayList;

public class Controller_v1 implements Controller {
    private View view;
    private DBAccess dbAccess;

    public Controller_v1() {
        view = new ViewWindow(this);
        view.showFrame();
        dbAccess = new DBWorker(this);
    }

    public ArrayList<User> getData() {
        return dbAccess.getData(view.getFilter());
    }

    @Override
    public void insertData(String username) {
        dbAccess.insertData(username);
    }

    @Override
    public void logIt(String message) {
        view.logIt(message);
    }

    public void exit() {
        dbAccess.disconnect();
        System.exit(0);
    }
}
