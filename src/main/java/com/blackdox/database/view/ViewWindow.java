package com.blackdox.database.view;

import com.blackdox.database.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class ViewWindow extends JFrame implements View {
    private Controller controller;
    private JButton buttonAdd, buttonShow;
    private JScrollPane scrollPane;
    JTextField addTextField, searchTextField;
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
        initFrame();
        initButtonAdd();
        initButtonShow();
        initAddTextField();
        initShowTextArea();
        initScrollPane();
        initSearchTextField();
        setVisible(true);
    }

    @Override
    public String getFilter() {
        return searchTextField.getText();
    }

    Controller getController() {
        return controller;
    }

    @Override
    public void logIt(String message) {
        switchShowTextArea(false);
        showTextArea.setText(message);
    }

    private void initFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        xSize = (screenSize.width - 600) / 2;
        setBounds((screenSize.width - 600) / 2, (screenSize.height - 400) / 2, xSize+25, 500);
        setLayout(null);
        setResizable(false);
        addWindowListener(new ListenerButtonClose(controller));
    }

    private void initButtonAdd() {
        buttonAdd = new JButton("ADD to DB");
        buttonAdd.setBounds(10, 80, xSize, 40);
        buttonAdd.setForeground(Color.RED);
        buttonAdd.setFont(fontButtons);
        buttonAdd.setOpaque(true);
        buttonAdd.addActionListener(new ListenerButtonAdd(this));
        add(buttonAdd, 0);
    }

    private void initButtonShow() {
        buttonShow = new JButton("SHOW DB");
        buttonShow.setBounds(10, 130, xSize-250, 40);
        buttonShow.setForeground(Color.RED);
        buttonShow.setFont(fontButtons);
        buttonShow.setOpaque(true);
        buttonShow.addActionListener(new ListenerButtonShow(this));
        add(buttonShow, 0);
    }

    private void initAddTextField() {
        addTextField = new JTextField();
        addTextField.setBounds(10, 10, xSize, 50);
        addTextField.setBorder(BorderFactory.createEtchedBorder());
        addTextField.setFont(fontText);
        addTextField.setHorizontalAlignment(JTextField.CENTER);
        add(addTextField);
    }

    private void initSearchTextField() {
        searchTextField = new JTextField("Filter");
        searchTextField.setBounds(buttonShow.getWidth()+20, 130, this.getWidth()-buttonShow.getWidth()-35, 40);
        searchTextField.setBorder(BorderFactory.createEtchedBorder());
        searchTextField.setFont(fontText);
        searchTextField.setHorizontalAlignment(JTextField.CENTER);
        searchTextField.addMouseListener(new ListenerSearchArea(searchTextField));
        add(searchTextField);
    }

    private void initShowTextArea() {
        showTextArea = new JTextArea();
        switchShowTextArea(false);
    }

    private void initScrollPane() {
        scrollPane = new JScrollPane(showTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 180, xSize, 280);
        scrollPane.setBorder(BorderFactory.createEtchedBorder());
        scrollPane.setFont(fontText);
        add(scrollPane);
    }

    void switchShowTextArea(boolean show) {
        if (show) {
            showTextArea.setEditable(true);
            showTextArea.setBackground(Color.white);
        } else {
            showTextArea.setBackground(Color.LIGHT_GRAY);
            showTextArea.setEditable(false);
        }
    }
}
