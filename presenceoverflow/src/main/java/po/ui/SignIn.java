package po.ui;

import po.PresenceOverflow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author sho
 */
public class SignIn extends JPanel {
	public SignIn() {
		initComponents();
	}

	private void initComponents() {
		logo = new JLabel();
		favicon = new JLabel();
		usernameField = new JFormattedTextField();
		usernameText = new JLabel();
		passwordField = new JPasswordField();
		passwordText = new JLabel();
		signInButton = new JButton();
		exitButton = new JButton();
		status = new JLabel();
		label4 = new JLabel();

		setLayout(null);


        //---- logo ----
        logo.setText("Sign in");
        logo.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        add(logo);
        logo.setBounds(new Rectangle(new Point(65, 35), logo.getPreferredSize()));

        //---- favicon ----
        favicon.setIcon(new ImageIcon(PresenceOverflow.LARGE_ICON));
        favicon.setFont(favicon.getFont().deriveFont(favicon.getFont().getSize() + 2f));
        add(favicon);
        favicon.setBounds(90, 15, 40, 30);
        add(usernameField);
        usernameField.setBounds(40, 100, 135, usernameField.getPreferredSize().height);

        //---- usernameText ----
        usernameText.setText("Username");
        add(usernameText);
        usernameText.setBounds(new Rectangle(new Point(40, 85), usernameText.getPreferredSize()));
        add(passwordField);
        passwordField.setBounds(40, 145, 135, passwordField.getPreferredSize().height);

        //---- passwordText ----
        passwordText.setText("Password");
        add(passwordText);
        passwordText.setBounds(new Rectangle(new Point(40, 130), passwordText.getPreferredSize()));

        //---- signInButton ----
        signInButton.setText("Sign In");
        add(signInButton);
        signInButton.setBounds(new Rectangle(new Point(125, 190), signInButton.getPreferredSize()));

        //---- exitButton ----
        exitButton.setText("Exit");
        add(exitButton);
        exitButton.setBounds(30, 190, 65, exitButton.getPreferredSize().height);
        status.setText("Hello! This needs to be really long so it can be edited. If you're reading this, have a good day!");
        status.setVisible(false);
        add(status);
        status.setBounds(new Rectangle(new Point(41, 165), status.getPreferredSize()));

		//---- label4 ----
		label4.setText("text");
	}

	public JLabel logo;
    public JLabel favicon;
    public JFormattedTextField usernameField;
    public JLabel usernameText;
    public JPasswordField passwordField;
    public JLabel passwordText;
    public JButton signInButton;
    public JButton exitButton;
    public JLabel status;
    public JLabel label4;
}
