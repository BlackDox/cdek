package com.blackdox.database.view;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListenerSearchArea implements MouseListener {
    private JTextField searchTextField;
    private boolean oneRun = true;

    public ListenerSearchArea(JTextField searchTextField) {
        this.searchTextField = searchTextField;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (oneRun) {
            oneRun = false;
            searchTextField.setText("");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
