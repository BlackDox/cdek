package com.blackdox.database.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerButtonAdd implements ActionListener {
    private ViewWindow viewWindow;

    public ListenerButtonAdd(ViewWindow viewWindow) {
        this.viewWindow = viewWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        viewWindow.switchShowTextArea(false);
        if (viewWindow.addTextField.getText().trim().isEmpty()) {
            viewWindow.showTextArea.setText("Not null!");
            return;
        } else {
            viewWindow.getController().insertData(viewWindow.addTextField.getText().trim());
            viewWindow.showTextArea.setText("The record is added to the database");
        }
    }
}
