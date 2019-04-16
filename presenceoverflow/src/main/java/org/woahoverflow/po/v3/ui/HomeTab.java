package org.woahoverflow.po.v3.ui;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import org.woahoverflow.po.v3.Util;
import org.woahoverflow.po.v3.handle.AccountHandler;
import org.woahoverflow.po.v3.handle.ProfileHandler;

import javax.swing.*;
import java.awt.*;

public class HomeTab extends JPanel
{
    public JPanel rootPanel;
    private JButton newProfileButton;
    private JLabel label1;
    private JLabel label2;
    private JButton exitButton;
    private JButton importButton;

    private Profiles profiles;

    public HomeTab(Profiles profiles)
    {
        this.profiles = profiles;

        add(rootPanel);

        Font roboto = new Font("Roboto Regular", Font.PLAIN, 12);

        newProfileButton.setFont(roboto);
        importButton.setFont(roboto);
        exitButton.setFont(roboto);
        label1.setFont(roboto);
        label2.setFont(roboto);

        newProfileButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        newProfileButton.setForeground(Color.WHITE);

        importButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        importButton.setForeground(Color.WHITE);

        exitButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        exitButton.setForeground(Color.WHITE);

        MaterialUIMovement.add(newProfileButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(importButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(exitButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);

        newProfileButton.addActionListener(e ->
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

        importButton.addActionListener(e ->
        {
            try
            {
                String importPID = JOptionPane.showInputDialog("Enter a PID to import.");
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

        exitButton.addActionListener(e ->
        {
            if (!Util.areProfilesSaved(profiles))
            {
                if (Util.askConfirmation("There are unsaved profiles!\n\nAre you sure you want to do this?"))
                    System.exit(0);
            } else {
                if (Util.askExit())
                    System.exit(0);
            }
        });
    }
}
