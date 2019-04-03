package org.woahoverflow.po.v3.ui;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import org.woahoverflow.po.v3.PresenceOverflow;
import org.woahoverflow.po.v3.handle.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class UI extends JFrame
{
    private JPanel rootPanel;
    private JButton exitButton;
    private JButton reloadButton;
    private JTextField clientIDTextField;
    private JTextField detailsTextField;
    private JTextField stateTextField;
    private JTextField bigImageKeyTextField;
    private JTextField smallImageKeyTextField;
    private JTextField bigImageCaptionTextField;
    private JTextField smallImageCaptionTextField;
    private JTextField importPIDTextField;
    private JTextField getPIDTextField;
    private JButton loadButton;
    private JButton saveButton;
    private JButton load2Button;
    private JButton load3Button;
    private JButton save2Button;
    private JButton save3Button;
    private JLabel clientIDLabel;
    private JLabel detailsLabel;
    private JLabel stateLabel;
    private JLabel bigImageKeyLabel;
    private JLabel smallImageKeyLabel;
    private JLabel bigImageCaptionLabel;
    private JLabel smallImageCaptionLabel;
    private JLabel importPIDLabel;
    private JLabel getPIDLabel;
    private JLabel oneLabel;
    private JLabel twoLabel;
    private JLabel threeLabel;
    private JLabel profilesLabel;

    public UI()
    {
        add(rootPanel);

        setTitle("PresenceOverflowUI");

        Font roboto = new Font("Roboto Regular", Font.PLAIN, 12);
        clientIDLabel.setFont(roboto);
        detailsLabel.setFont(roboto);
        stateLabel.setFont(roboto);
        bigImageKeyLabel.setFont(roboto);
        smallImageKeyLabel.setFont(roboto);
        bigImageCaptionLabel.setFont(roboto);
        smallImageCaptionLabel.setFont(roboto);
        importPIDLabel.setFont(roboto);
        getPIDLabel.setFont(roboto);
        oneLabel.setFont(roboto);
        twoLabel.setFont(roboto);
        threeLabel.setFont(roboto);
        profilesLabel.setFont(roboto);
        clientIDTextField.setFont(roboto);
        detailsTextField.setFont(roboto);
        stateTextField.setFont(roboto);
        bigImageKeyTextField.setFont(roboto);
        smallImageKeyTextField.setFont(roboto);
        bigImageCaptionTextField.setFont(roboto);
        smallImageCaptionTextField.setFont(roboto);
        importPIDTextField.setFont(roboto);
        getPIDTextField.setFont(roboto);
        loadButton.setFont(roboto);
        load2Button.setFont(roboto);
        load3Button.setFont(roboto);
        saveButton.setFont(roboto);
        save2Button.setFont(roboto);
        save3Button.setFont(roboto);
        exitButton.setFont(roboto);
        reloadButton.setFont(roboto);

        loadButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        loadButton.setForeground(Color.WHITE);

        load2Button.setBackground(MaterialColors.LIGHT_BLUE_400);
        load2Button.setForeground(Color.WHITE);

        load3Button.setBackground(MaterialColors.LIGHT_BLUE_400);
        load3Button.setForeground(Color.WHITE);

        saveButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        saveButton.setForeground(Color.WHITE);

        save2Button.setBackground(MaterialColors.LIGHT_BLUE_400);
        save2Button.setForeground(Color.WHITE);

        save3Button.setBackground(MaterialColors.LIGHT_BLUE_400);
        save3Button.setForeground(Color.WHITE);

        exitButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        exitButton.setForeground(Color.WHITE);

        reloadButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        reloadButton.setForeground(Color.WHITE);

        MaterialUIMovement.add(loadButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(saveButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(exitButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(reloadButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);

        loadButton.addActionListener(e ->
        {
            ProfileHandler.Profile profile = PresenceOverflow.getPROFILES().get(0);

            if (profile == null)
                return;

            clientIDTextField.setText(Long.toString(profile.getClientid()));
            detailsTextField.setText(profile.getDetails());
            stateTextField.setText(profile.getState());
            bigImageKeyTextField.setText(profile.getBig_image_key());
            bigImageCaptionTextField.setText(profile.getBig_image_caption());
            smallImageKeyTextField.setText(profile.getSmall_image_key());
            smallImageCaptionTextField.setText(profile.getSmall_image_caption());
        });

        saveButton.addActionListener(e ->
        {
            Random random = new Random();

            ProfileHandler.Profile profile = ProfileHandler.INSTANCE.createProfile
                    (
                            stateTextField.getText(),
                            detailsTextField.getText(),
                            bigImageKeyTextField.getText(),
                            bigImageCaptionTextField.getText(),
                            smallImageKeyTextField.getText(),
                            smallImageCaptionTextField.getText(),
                            Long.parseLong(clientIDTextField.getText()),
                            Long.toString(random.nextLong())
                    );

            //ProfileHandler.INSTANCE.saveProfile(profile.getPid());
        });

        load2Button.addActionListener(e ->
        {
            ProfileHandler.Profile profile = PresenceOverflow.getPROFILES().get(1);

            if (profile == null)
                return;

            clientIDTextField.setText(Long.toString(profile.getClientid()));
            detailsTextField.setText(profile.getDetails());
            stateTextField.setText(profile.getState());
            bigImageKeyTextField.setText(profile.getBig_image_key());
            bigImageCaptionTextField.setText(profile.getBig_image_caption());
            smallImageKeyTextField.setText(profile.getSmall_image_key());
            smallImageCaptionTextField.setText(profile.getSmall_image_caption());
        });

        load3Button.addActionListener(e ->
        {
            ProfileHandler.Profile profile = PresenceOverflow.getPROFILES().get(2);

            if (profile == null)
                return;

            clientIDTextField.setText(Long.toString(profile.getClientid()));
            detailsTextField.setText(profile.getDetails());
            stateTextField.setText(profile.getState());
            bigImageKeyTextField.setText(profile.getBig_image_key());
            bigImageCaptionTextField.setText(profile.getBig_image_caption());
            smallImageKeyTextField.setText(profile.getSmall_image_key());
            smallImageCaptionTextField.setText(profile.getSmall_image_caption());
        });

        reloadButton.addActionListener(e ->
                DiscordHandler.refresh(new ProfileHandler.Profile
                (
                    stateTextField.getText(),
                    detailsTextField.getText(),
                    bigImageKeyTextField.getText(),
                    bigImageCaptionTextField.getText(),
                    smallImageKeyTextField.getText(),
                    smallImageCaptionTextField.getText(),
                    getPIDTextField.getText(),
                    Long.parseLong(clientIDTextField.getText()),
                    AccountHandler.INSTANCE.getName(),
                    AccountHandler.INSTANCE.getId()
                )));

        exitButton.addActionListener(e ->
        {
            TrayMenu.exit();
            System.exit(0);
        });

        setLocationRelativeTo(null);

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