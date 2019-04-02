package org.woahoverflow.po.v3.ui;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import org.woahoverflow.po.v3.PresenceOverflow;
import org.woahoverflow.po.v3.handle.ProfileHandler;

import javax.swing.*;
import java.awt.*;

public class Profiles extends JFrame
{
    public JPanel rootPanel;
    public JTabbedPane profiles;
    public JButton exportButton;
    public JButton exitButton;
    public JTextField clientIDTextField;
    public JTextField detailsTextField;
    public JTextField stateTextField;
    public JTextField bigImageKeyTextField;
    public JTextField bigImageCaptionTextField;
    public JTextField smallImageCaptionTextField;
    public JTextField smallImageKeyTextField;
    public JPanel primaryTab;
    public JButton selectButton;
    public JLabel clientIDLabel;
    public JLabel detailsLabel;
    public JLabel stateLabel;
    public JLabel bigImageKeyLabel;
    public JLabel smallImageKeyLabel;
    public JLabel bigImageCaptionLabel;
    public JLabel smallImageCaptionLabel;

    public Profiles()
    {
        add(rootPanel);

        Font roboto = new Font("Roboto Regular", Font.PLAIN, 12);

        // set fonts
        exportButton.setFont(roboto);
        exitButton.setFont(roboto);
        clientIDTextField.setFont(roboto);
        detailsTextField.setFont(roboto);
        stateTextField.setFont(roboto);
        bigImageKeyTextField.setFont(roboto);
        bigImageCaptionTextField.setFont(roboto);
        smallImageCaptionTextField.setFont(roboto);
        smallImageKeyTextField.setFont(roboto);
        selectButton.setFont(roboto);
        clientIDLabel.setFont(roboto);
        detailsLabel.setFont(roboto);
        stateLabel.setFont(roboto);
        bigImageKeyLabel.setFont(roboto);
        smallImageKeyLabel.setFont(roboto);
        bigImageCaptionLabel.setFont(roboto);
        smallImageCaptionLabel.setFont(roboto);

        // prep material ui stuffs
        exitButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        exitButton.setForeground(Color.WHITE);

        exportButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        exportButton.setForeground(Color.WHITE);

        selectButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        selectButton.setForeground(Color.WHITE);

        MaterialUIMovement.add(exitButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(exportButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(selectButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);

        exitButton.addActionListener(e ->
        {
            System.exit(0);
        });

        exportButton.addActionListener(e ->
        {
            PresenceOverflow.getLOGGER().debug("export button pressed");
        });

        for (ProfileHandler.Profile profile : PresenceOverflow.getPROFILES())
        {
            clientIDTextField.setText(Long.toString(profile.getClientid()));
            detailsTextField.setText(profile.getDetails());
            stateTextField.setText(profile.getState());
            bigImageKeyTextField.setText(profile.getBig_image_key());
            bigImageCaptionTextField.setText(profile.getBig_image_caption());
            smallImageCaptionTextField.setText(profile.getSmall_image_caption());
            smallImageKeyTextField.setText(profile.getSmall_image_key());

            profiles.add(profile.getName(), new Tab(null, profile).panel);
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
