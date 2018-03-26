package com.blackdox.database.view;

import com.blackdox.database.model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListenerButtonShow implements ActionListener {
    private ViewWindow viewWindow;

    public ListenerButtonShow(ViewWindow viewWindow) {
        this.viewWindow = viewWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<User> users = viewWindow.getController().getData();
        viewWindow.switchShowTextArea(true);
        viewWindow.showTextArea.setText("   id \t\t name");
        for (User user:users) {
            String resultString = String.format("\n %4d %57s", user.getId(), user.getUserName());
            viewWindow.showTextArea.append(resultString);
        }
    }
}
