package org.woahoverflow.po.v3.ui;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import org.woahoverflow.po.v3.PresenceOverflow;
import org.woahoverflow.po.v3.handle.DiscordHandler;
import org.woahoverflow.po.v3.handle.ProfileHandler;

import javax.swing.*;
import java.awt.*;

public class Tab
{
    public JPanel rootPanel;
    private JLabel clientIDLabel;
    private JLabel detailsLabel;
    private JLabel stateLabel;
    private JLabel bigImageKeyLabel;
    private JLabel smallImageKeyLabel;
    private JLabel bigImageCaptionLabel;
    private JLabel smallImageCaptionLabel;
    public JTextField detailsTextField;
    public JTextField clientIDTextField;
    public JTextField stateTextField;
    public JTextField bigImageKeyTextField;
    public JTextField smallImageKeyTextField;
    public JTextField bigImageCaptionTextField;
    public JTextField smallImageCaptionTextField;
    private JButton exitButton;
    private JButton exportButton;
    private JButton selectButton;

    public Tab(ProfileHandler.Profile profile)
    {
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

        selectButton.addActionListener(e ->
        {
            DiscordHandler.refresh(profile);
        });
    }
}
