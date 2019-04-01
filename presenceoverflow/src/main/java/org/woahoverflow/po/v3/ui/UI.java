package org.woahoverflow.po.v3.ui;

import javax.swing.*;

public class UI extends JFrame
{
    private JPanel rootPanel;
    private JButton exitButton;
    private JButton reloadButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JButton loadButton;
    private JButton saveButton;

    public UI() {
        add(rootPanel);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setTitle("PresenceOverflowUI");
        pack();

        setVisible(true);
    }

    public void setData(UI data) {
    }

    public void getData(UI data) {
    }

    public boolean isModified(UI data) {
        return false;
    }
}