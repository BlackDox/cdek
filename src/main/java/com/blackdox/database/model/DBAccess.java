package com.blackdox.database.model;

import java.sql.Statement;
import java.util.ArrayList;

public interface DBAccess extends AutoCloseable {
    void connect();
    Statement getStatement();
    void disconnect();
    ArrayList<User> getData(String filter);
    void insertData(String username);
}
