package org.woahoverflow.po.v3.ui;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import org.woahoverflow.po.v3.PresenceOverflow;
import org.woahoverflow.po.v3.Util;
import org.woahoverflow.po.v3.handle.AccountHandler;
import org.woahoverflow.po.v3.handle.DiscordHandler;
import org.woahoverflow.po.v3.handle.ProfileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

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
    private JButton exportButton;
    private JButton selectButton;
    private JButton deleteButton;
    private JButton newButton;
    private JButton saveButton;
    private JButton managerButton;
    private JButton importButton;

    public boolean isNew = false;

    private ProfileHandler.Profile profile;

    public Tab(Profiles profiles, ProfileHandler.Profile profile)
    {
        this.profile = profile;

        clientIDTextField.setText(Long.toString(profile.getClientid()));
        detailsTextField.setText(Util.handle_control_string(profile.getDetails()));
        stateTextField.setText(Util.handle_control_string(profile.getState()));
        bigImageKeyTextField.setText(Util.handle_control_string(profile.getBig_image_key()));
        bigImageCaptionTextField.setText(Util.handle_control_string(profile.getBig_image_caption()));
        smallImageKeyTextField.setText(Util.handle_control_string(profile.getSmall_image_key()));
        smallImageCaptionTextField.setText(Util.handle_control_string(profile.getSmall_image_caption()));

        Font roboto = new Font("Roboto Regular", Font.PLAIN, 12);

        // set fonts
        importButton.setFont(roboto);
        exportButton.setFont(roboto);
        saveButton.setFont(roboto);
        selectButton.setFont(roboto);
        managerButton.setFont(roboto);
        deleteButton.setFont(roboto);
        newButton.setFont(roboto);

        clientIDTextField.setFont(roboto);
        detailsTextField.setFont(roboto);
        stateTextField.setFont(roboto);
        bigImageKeyTextField.setFont(roboto);
        bigImageCaptionTextField.setFont(roboto);
        smallImageCaptionTextField.setFont(roboto);
        smallImageKeyTextField.setFont(roboto);
        clientIDLabel.setFont(roboto);
        detailsLabel.setFont(roboto);
        stateLabel.setFont(roboto);
        bigImageKeyLabel.setFont(roboto);
        smallImageKeyLabel.setFont(roboto);
        bigImageCaptionLabel.setFont(roboto);
        smallImageCaptionLabel.setFont(roboto);

        // prep material ui stuffs
        importButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        importButton.setForeground(Color.WHITE);

        exportButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        exportButton.setForeground(Color.WHITE);

        saveButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        saveButton.setForeground(Color.WHITE);

        selectButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        selectButton.setForeground(Color.WHITE);

        managerButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        managerButton.setForeground(Color.WHITE);

        deleteButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        deleteButton.setForeground(Color.WHITE);

        newButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        newButton.setForeground(Color.WHITE);

        MaterialUIMovement.add(importButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(exportButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(saveButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(selectButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(managerButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(deleteButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(newButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);

        importButton.addActionListener(e ->
        {
            try
            {
                String importPID = JOptionPane.showInputDialog("Enter a PID to import:");
                ProfileHandler.Profile imported = ProfileHandler.INSTANCE.getProfileByPID(importPID);
                Tab importTab = new Tab(profiles, imported);
                profiles.profiles.add(imported.getName(), importTab.rootPanel);
                ProfileHandler.INSTANCE.saveProfile(imported);
                Util.notify(String.format("Successfully imported profile '%s'.", imported.getName()), "Done");
            } catch (Exception ex) {
                ex.printStackTrace();
                Util.notify("Failed to import profile!\n\nError:\n" + ex.getMessage(), "Oh noes!", JOptionPane.ERROR);
            }
        });

        exportButton.addActionListener(e ->
        {
            StringSelection selection = new StringSelection(profile.getPid());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);

            Util.notify("Copied PID to clipboard!\n\nYou can send it to other people for them to import.", "Done");
        });

        selectButton.addActionListener(e ->
        {
            ProfileHandler.Profile newProfile = ProfileHandler.INSTANCE.createProfile(
                    stateTextField.getText(),
                    detailsTextField.getText(),
                    bigImageKeyTextField.getText(),
                    bigImageCaptionTextField.getText(),
                    smallImageKeyTextField.getText(),
                    smallImageCaptionTextField.getText(),
                    Long.parseLong(clientIDTextField.getText()),
                    profile.getName());

            DiscordHandler.refresh(newProfile);

            Util.notify(String.format("Your presence was updated.\nUsing profile '%s'.", newProfile.getName()), "Info");
        });

        newButton.addActionListener(e ->
        {
            if (!AccountHandler.INSTANCE.getVip() && ProfileHandler.INSTANCE.getProfiles().size() >= 3)
            {
                if (Util.denyPremiumOnly())
                    Util.openWebpage("https://woahoverflow.org/premium");
                return;
            }

            CreateProfile dialog = new CreateProfile(profiles);
            dialog.pack();
            dialog.setVisible(true);
        });

        deleteButton.addActionListener(e ->
        {
            if (Util.askConfirmation())
            {
                try
                {
                    ProfileHandler.INSTANCE.deleteProfile(profile.getPid());
                } catch (Exception ex) {}
                profiles.profiles.remove(rootPanel);
            }
        });

        saveButton.addActionListener(e ->
        {
            if (clientIDTextField.getText().isEmpty())
                clientIDTextField.setText("0");

            ProfileHandler.Profile newProfile1 = ProfileHandler.INSTANCE.createProfile(
                    stateTextField.getText(),
                    detailsTextField.getText(),
                    bigImageKeyTextField.getText(),
                    bigImageCaptionTextField.getText(),
                    smallImageKeyTextField.getText(),
                    smallImageCaptionTextField.getText(),
                    Long.parseLong(clientIDTextField.getText()),
                    profile.getName());

            ProfileHandler.Profile newProfile = Util.fixProfile(newProfile1);

            try
            {
                isNew = profile.getPid() == null;

                if (!isNew)
                {
                    ProfileHandler.INSTANCE.editProfile(profile.getPid(), newProfile);
                } else {
                    String pid = ProfileHandler.INSTANCE.saveProfile(newProfile);
                    this.profile = ProfileHandler.INSTANCE.getProfileByPID(pid);
                }

                Util.notify("Your profile has been saved!", "Success!");
            } catch (Exception ex) {
                Util.notify("There was an error while saving your profile!", "Oh noes!", JOptionPane.ERROR_MESSAGE);
            }
        });

        managerButton.addActionListener(e ->
        {
            if (!AccountHandler.INSTANCE.getVip() && ProfileHandler.INSTANCE.getProfiles().size() >= 3)
            {
                if (Util.denyPremiumOnly())
                    Util.openWebpage("https://woahoverflow.org/premium");
                return;
            }

            new RotationManager();
        });

        PresenceOverflow.getLOGGER().debug("Tab created: PID = {}", profile.getPid());
    }

    public boolean isSaved()
    {
        if (profile.getPid() == null)
            return false;
        return true;
    }
}
