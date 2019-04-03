package org.woahoverflow.po.v3.ui;

import org.woahoverflow.po.v3.PresenceOverflow;
import org.woahoverflow.po.v3.handle.ProfileHandler;

import javax.swing.*;

public class Profiles extends JFrame
{
    public JPanel rootPanel;
    public JTabbedPane profiles;

    public Profiles()
    {
        add(rootPanel);

        for (ProfileHandler.Profile profile : PresenceOverflow.getPROFILES())
        {
            Tab tab = new Tab(profile);

            tab.clientIDTextField.setText(Long.toString(profile.getClientid()));
            tab.detailsTextField.setText(profile.getDetails());
            tab.stateTextField.setText(profile.getState());
            tab.bigImageKeyTextField.setText(profile.getBig_image_key());
            tab.bigImageCaptionTextField.setText(profile.getBig_image_caption());
            tab.smallImageCaptionTextField.setText(profile.getSmall_image_caption());
            tab.smallImageKeyTextField.setText(profile.getSmall_image_key());

            profiles.add(profile.getName(), tab.rootPanel);
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
