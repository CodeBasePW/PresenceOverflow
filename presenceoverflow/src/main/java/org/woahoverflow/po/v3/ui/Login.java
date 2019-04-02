package org.woahoverflow.po.v3.ui;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import org.woahoverflow.po.v3.PresenceOverflow;
import org.woahoverflow.po.v3.handle.AccountHandler;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Login extends JFrame
{
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JButton loginButton;
    private JButton guestButton;
    private JButton exitButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPanel rootPanel;

    public Login()
    {
        add(rootPanel);

        Font roboto = new Font("Roboto Regular", Font.PLAIN, 12);

        // label fonts
        usernameLabel.setFont(roboto);
        passwordLabel.setFont(roboto);

        // button fonts
        loginButton.setFont(roboto);
        guestButton.setFont(roboto);
        exitButton.setFont(roboto);

        // text field fonts
        usernameTextField.setFont(roboto);
        passwordTextField.setFont(roboto);

        loginButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        loginButton.setForeground(Color.WHITE);

        guestButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        guestButton.setForeground(Color.WHITE);

        exitButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        exitButton.setForeground(Color.WHITE);

        MaterialUIMovement.add(loginButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(guestButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(exitButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);

        loginButton.addActionListener(e ->
        {
            AccountHandler.INSTANCE.login(usernameTextField.getText(), passwordTextField.getText());

            while (!AccountHandler.INSTANCE.getLoggedIn())
            {
                PresenceOverflow.getLOGGER().warn("Waiting for login...");
                try { TimeUnit.SECONDS.sleep(5); } catch (Exception ex) { ex.printStackTrace(); }
            }

            setVisible(false);
            //new UI();
            new Profiles();
        });

        exitButton.addActionListener(e ->
        {
            System.exit(0);
        });

        setLocationRelativeTo(null);

        pack();
        setVisible(true);
    }
}
