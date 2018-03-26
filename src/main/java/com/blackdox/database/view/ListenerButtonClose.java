package com.blackdox.database.view;

import com.blackdox.database.controller.Controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ListenerButtonClose implements WindowListener {
    private Controller controller;

    public ListenerButtonClose(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        controller.exit();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
