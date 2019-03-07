package po.ui;

import po.PresenceOverflow;

import java.awt.*;
import javax.swing.*;

/**
 * @author sho
 */
public class Panel extends JPanel {
	public Panel() {
		initComponents();
	}

	private void initComponents() {
		JLabel clientID = new JLabel();
		stateLabel = new JLabel();
		detailsLabel = new JLabel();
		biglImageKeyLabel = new JLabel();
		smallImageKeyLabel = new JLabel();
		bigImageCaptionLabel = new JLabel();
		smallImageCaptionLabel = new JLabel();
		logo = new JLabel();
		reload = new JButton();
		exit = new JButton();
		profileLabel = new JLabel();
		profileOneLoad = new JButton();
		profileThreeLoad = new JButton();
		profileTwoLoad = new JButton();
		saveToOne = new JButton();
		saveToTwo = new JButton();
		saveToThree = new JButton();
		profileOneLabel = new JLabel();
		profileTwoLabel = new JLabel();
		profileThreeLabel = new JLabel();
		favicon = new JLabel();
		loggedIn = new JLabel();
		signIn = new JButton();
		detailsTextArea = new JFormattedTextField();
		stateTextArea = new JFormattedTextField();
		imageKeyTextArea = new JFormattedTextField();
		smallImageKeyTextArea = new JFormattedTextField();
		imageCaptionTextArea = new JFormattedTextField();
		smallImageCaptionTextArea = new JFormattedTextField();
		clientIdTextArea = new JFormattedTextField();

		//======== this ========
		setLayout(null);

		//---- clientID ----
		clientID.setText("Client ID");
		clientID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(clientID);
		clientID.setBounds(111, 5, 70, 20);

		//---- stateLabel ----
		stateLabel.setText("State");
		stateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(stateLabel);
		stateLabel.setBounds(135, 55, 40, 20);

		//---- detailsLabel ----
		detailsLabel.setText("Details");
		detailsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(detailsLabel);
		detailsLabel.setBounds(125, 30, 50, 20);

		//---- biglImageKeyLabel ----
		biglImageKeyLabel.setText("Big Image Key");
		biglImageKeyLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(biglImageKeyLabel);
		biglImageKeyLabel.setBounds(70, 80, 105, 20);

		//---- smallImageKeyLabel ----
		smallImageKeyLabel.setText("Small Image Key");
		smallImageKeyLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(smallImageKeyLabel);
		smallImageKeyLabel.setBounds(50, 105, 125, 20);

		//---- bigImageCaptionLabel ----
		bigImageCaptionLabel.setText("Big Image Caption");
		bigImageCaptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(bigImageCaptionLabel);
		bigImageCaptionLabel.setBounds(40, 130, 135, 20);

		//---- smallImageCaptionLabel ----
		smallImageCaptionLabel.setText("Small Image Caption");
		smallImageCaptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(smallImageCaptionLabel);
		smallImageCaptionLabel.setBounds(25, 155, 150, 20);

		//---- logo ----
		logo.setText("woahoverflow");
		logo.setFont(new Font("Tahoma", Font.PLAIN, 28));
		add(logo);
		logo.setBounds(new Rectangle(new Point(375, 245), logo.getPreferredSize()));

		//---- reload ----
		reload.setText("Reload");
		add(reload);
		reload.setBounds(375, 10, 65, 30);

		//---- exit ----
		exit.setText("Exit");
		add(exit);
		exit.setBounds(445, 10, 60, 30);

		//---- profileLabel ----
		profileLabel.setText("Profiles");
		profileLabel.setFont(profileLabel.getFont().deriveFont(profileLabel.getFont().getSize() + 6f));
		add(profileLabel);
		profileLabel.setBounds(new Rectangle(new Point(415, 75), profileLabel.getPreferredSize()));

		//---- profileOneLoad ----
		profileOneLoad.setText("Load");
		add(profileOneLoad);
		profileOneLoad.setBounds(new Rectangle(new Point(365, 120), profileOneLoad.getPreferredSize()));

		//---- profileThreeLoad ----
		profileThreeLoad.setText("Load");
		add(profileThreeLoad);
		profileThreeLoad.setBounds(365, 180, 55, 23);

		//---- profileTwoLoad ----
		profileTwoLoad.setText("Load");
		add(profileTwoLoad);
		profileTwoLoad.setBounds(365, 150, 55, 23);

		//---- saveToOne ----
		saveToOne.setText("Save To");
		add(saveToOne);
		saveToOne.setBounds(new Rectangle(new Point(425, 120), saveToOne.getPreferredSize()));

		//---- saveToTwo ----
		saveToTwo.setText("Save To");
		add(saveToTwo);
		saveToTwo.setBounds(425, 150, 71, 23);

		//---- saveToThree ----
		saveToThree.setText("Save To");
		add(saveToThree);
		saveToThree.setBounds(425, 180, 71, 23);

		//---- profileOneLabel ----
		profileOneLabel.setText("One");
		profileOneLabel.setFont(profileOneLabel.getFont().deriveFont(profileOneLabel.getFont().getSize() + 5f));
		add(profileOneLabel);
		profileOneLabel.setBounds(new Rectangle(new Point(505, 120), profileOneLabel.getPreferredSize()));

		//---- profileTwoLabel ----
		profileTwoLabel.setText("Two");
		profileTwoLabel.setFont(profileTwoLabel.getFont().deriveFont(profileTwoLabel.getFont().getSize() + 5f));
		add(profileTwoLabel);
		profileTwoLabel.setBounds(505, 150, 34, 20);

		//---- profileThreeLabel ----
		profileThreeLabel.setText("Three");
		profileThreeLabel.setFont(profileThreeLabel.getFont().deriveFont(profileThreeLabel.getFont().getSize() + 5f));
		add(profileThreeLabel);
		profileThreeLabel.setBounds(new Rectangle(new Point(505, 180), profileThreeLabel.getPreferredSize()));

		//---- favicon ----
		favicon.setIcon(new ImageIcon(PresenceOverflow.LARGE_ICON));
		favicon.setFont(favicon.getFont().deriveFont(favicon.getFont().getSize() + 10f));
		add(favicon);
		favicon.setBounds(335, 250, 40, 31);

		//---- loggedIn ----
		loggedIn.setText("ASDPFOKSDPFOKDSPFOKSDPOFKSDPOKFSDKFPSDFKPSDFKPSDOFKSDKFPSDKFPOSDKFPOSDKFPOSDKFOPKSDF");
		loggedIn.setText("Currently signed out");
		loggedIn.setFont(loggedIn.getFont().deriveFont(loggedIn.getFont().getSize() + 6f));
		add(loggedIn);
		loggedIn.setBounds(new Rectangle(new Point(60, 220), loggedIn.getPreferredSize()));

		//---- signIn ----
		signIn.setText("Sign In");
		add(signIn);
		signIn.setBounds(new Rectangle(new Point(85, 245), signIn.getPreferredSize()));
		add(clientIdTextArea);
		clientIdTextArea.setBounds(175, 5, 140, 20);
		add(detailsTextArea);
		detailsTextArea.setBounds(175, 30, 140, detailsTextArea.getPreferredSize().height);
		add(stateTextArea);
		stateTextArea.setBounds(175, 55, 140, 20);
		add(imageKeyTextArea);
		imageKeyTextArea.setBounds(175, 80, 140, 20);
		add(smallImageKeyTextArea);
		smallImageKeyTextArea.setBounds(175, 105, 140, 20);
		add(imageCaptionTextArea);
		imageCaptionTextArea.setBounds(175, 130, 140, 20);
		add(smallImageCaptionTextArea);
		smallImageCaptionTextArea.setBounds(175, 155, 140, 20);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < getComponentCount(); i++) {
				Rectangle bounds = getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			setMinimumSize(preferredSize);
			setPreferredSize(preferredSize);
		}
	}
	public JLabel stateLabel;
	public JLabel detailsLabel;
	public JLabel biglImageKeyLabel;
	public JLabel smallImageKeyLabel;
	public JLabel bigImageCaptionLabel;
	public JLabel smallImageCaptionLabel;
	public JLabel logo;
	public JButton reload;
	public JButton exit;
	public JLabel profileLabel;
	public JButton profileOneLoad;
	public JButton profileThreeLoad;
	public JButton profileTwoLoad;
	public JButton saveToOne;
	public JButton saveToTwo;
	public JButton saveToThree;
	public JLabel profileOneLabel;
	public JLabel profileTwoLabel;
	public JLabel profileThreeLabel;
	public JLabel favicon;
	public JLabel loggedIn;
	public JButton signIn;
	public JFormattedTextField clientIdTextArea;
	public JFormattedTextField detailsTextArea;
	public JFormattedTextField stateTextArea;
	public JFormattedTextField imageKeyTextArea;
	public JFormattedTextField smallImageKeyTextArea;
	public JFormattedTextField imageCaptionTextArea;
	public JFormattedTextField smallImageCaptionTextArea;
}