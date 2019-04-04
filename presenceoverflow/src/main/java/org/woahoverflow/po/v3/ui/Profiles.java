package org.woahoverflow.po.v3.ui;

import org.woahoverflow.po.v3.POInstance;
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
            Tab tab = new Tab(this, profile);

            tab.clientIDTextField.setText(Long.toString(profile.getClientid()).replace(POInstance.control_hash, ""));
            tab.detailsTextField.setText(profile.getDetails().replace(POInstance.control_hash, ""));
            tab.stateTextField.setText(profile.getState().replace(POInstance.control_hash, ""));
            tab.bigImageKeyTextField.setText(profile.getBig_image_key().replace(POInstance.control_hash, ""));
            tab.bigImageCaptionTextField.setText(profile.getBig_image_caption().replace(POInstance.control_hash, ""));
            tab.smallImageCaptionTextField.setText(profile.getSmall_image_caption().replace(POInstance.control_hash, ""));
            tab.smallImageKeyTextField.setText(profile.getSmall_image_key().replace(POInstance.control_hash, ""));

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
