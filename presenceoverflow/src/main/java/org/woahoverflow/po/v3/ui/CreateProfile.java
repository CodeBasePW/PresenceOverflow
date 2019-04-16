package org.woahoverflow.po.v3.ui;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import org.woahoverflow.po.v3.Util;
import org.woahoverflow.po.v3.handle.ProfileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateProfile extends JDialog
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameTextField;

    private Profiles profiles;

    public CreateProfile(Profiles profiles)
    {
        this.profiles = profiles;

        Font roboto = new Font("Roboto Regular", Font.PLAIN, 12);

        buttonOK.setFont(roboto);
        buttonCancel.setFont(roboto);
        nameTextField.setFont(roboto);

        buttonOK.setBackground(MaterialColors.LIGHT_BLUE_400);
        buttonOK.setForeground(Color.WHITE);

        buttonCancel.setBackground(MaterialColors.LIGHT_BLUE_400);
        buttonCancel.setForeground(Color.WHITE);

        MaterialUIMovement.add(buttonOK, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(buttonCancel, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e ->
        {
            onOK();
        });

        buttonCancel.addActionListener(e ->
        {
            onCancel();
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e ->
        {
            onCancel();
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setLocationRelativeTo(null);
    }

    private void onOK()
    {
        ProfileHandler.Profile newProfile = Util.createBlankProfile(nameTextField.getText());
        Tab tab = new Tab(profiles, newProfile);
        profiles.profiles.add(nameTextField.getText(), tab.rootPanel);

        dispose();
    }

    private void onCancel()
    {
        // add your code here if necessary
        dispose();
    }
}
