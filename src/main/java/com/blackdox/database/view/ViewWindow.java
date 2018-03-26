package com.blackdox.database.view;

import com.blackdox.database.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class ViewWindow extends JFrame implements View {
    private Controller controller;
    private JButton buttonAdd, buttonShow;
    private JScrollPane scrollPane;
    JTextField addTextField;
    JTextArea showTextArea;
    private Font fontText = new Font(Font.MONOSPACED, Font.BOLD, 12);
    private Font fontButtons = new Font(Font.DIALOG, Font.CENTER_BASELINE, 15);
    private int xSize;

    public ViewWindow(Controller controller) {
        super("SDEK-DB v0.0001");
        this.controller = controller;
    }

    @Override
    public void showFrame() {
        setFrame();
        setButtonAdd();
        setButtonShow();
        setAddTextField();
        setShowTextArea();
        setScrollPane();
        setVisible(true);
    }

    Controller getController() {
        return controller;
    }

    private void setFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        xSize = (screenSize.width - 600) / 2 - 10;
        setBounds((screenSize.width - 600) / 2, (screenSize.height - 400) / 2, xSize + 30, 500);
        setLayout(null);
        setResizable(false);
        addWindowListener(new ListenerButtonClose(controller));
    }

    private void setButtonAdd() {
        buttonAdd = new JButton("ADD to DB");
        buttonAdd.setBounds(10, 80, xSize, 40);
        buttonAdd.setForeground(Color.RED);
        buttonAdd.setFont(fontButtons);
        buttonAdd.setOpaque(true);
        buttonAdd.addActionListener(new ListenerButtonAdd(this));
        add(buttonAdd, 0);
    }

    private void setButtonShow() {
        buttonShow = new JButton("SHOW DB");
        buttonShow.setBounds(10, 130, xSize, 40);
        buttonShow.setForeground(Color.RED);
        buttonShow.setFont(fontButtons);
        buttonShow.setOpaque(true);
        buttonShow.addActionListener(new ListenerButtonShow(this));
        add(buttonShow, 0);
    }

    private void setAddTextField() {
        addTextField = new JTextField();
        addTextField.setBounds(10, 10, xSize, 50);
        addTextField.setBorder(BorderFactory.createEtchedBorder());
        addTextField.setFont(fontText);
        addTextField.setHorizontalAlignment(JTextField.CENTER);
        add(addTextField);
    }

    private void setShowTextArea() {
        showTextArea = new JTextArea();
        switchShowTextArea(false);
    }

    private void setScrollPane() {
        scrollPane = new JScrollPane(showTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 180, xSize, 280);
        scrollPane.setBorder(BorderFactory.createEtchedBorder());
        scrollPane.setFont(fontText);
        add(scrollPane);
    }

    void switchShowTextArea(boolean show) {
        if (show) {
            showTextArea.setBackground(Color.white);
            showTextArea.setEditable(true);
        } else {
            showTextArea.setBackground(Color.LIGHT_GRAY);
            showTextArea.setEditable(false);
        }
    }
}
