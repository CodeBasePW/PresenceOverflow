package org.woahoverflow.po.v3.ui;

import org.woahoverflow.po.v3.PresenceOverflow;
import org.woahoverflow.po.v3.handle.ProfileHandler;

import javax.swing.*;

public class Profiles extends JFrame {

    private JPanel rootPanel;
    private JTabbedPane profiles;
    private JButton exportButton;
    private JButton exitButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField5;
    private JPanel Name;
    private JButton selectButton;

    public Profiles() {
        add(rootPanel);

        for (ProfileHandler.Profile profile : PresenceOverflow.getPROFILES()) {
            JPanel newProfile = new JPanel();
            newProfile.setName(profile.getName());
            profiles.add(newProfile);
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setTitle("PresenceOverflowUI");
        pack();

        setVisible(true);
    }

    public void setData(Profiles data) {
    }

    public void getData(Profiles data) {
    }

    public boolean isModified(Profiles data) {
        return false;
    }
}
