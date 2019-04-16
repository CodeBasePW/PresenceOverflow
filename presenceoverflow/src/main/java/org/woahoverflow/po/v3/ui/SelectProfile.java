package org.woahoverflow.po.v3.ui;

import org.woahoverflow.po.v3.handle.ProfileHandler;

import javax.swing.*;
import java.awt.event.*;

public class SelectProfile extends JDialog
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox profiles;

    private RotationManager manager;

    public SelectProfile(RotationManager manager)
    {
        this.manager = manager;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        for (ProfileHandler.Profile profile : ProfileHandler.INSTANCE.getProfiles())
        {
            profiles.addItem(profile);
        }

        buttonOK.setEnabled(false);
        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onCancel();
            }
        });

        profiles.addPropertyChangeListener(e ->
        {
            buttonOK.setEnabled(true);
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onOK()
    {
        manager.model.addElement(profiles.getSelectedItem());

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
