package org.woahoverflow.po.v3.ui;

import org.woahoverflow.po.v3.PresenceOverflow;
import org.woahoverflow.po.v3.Util;
import org.woahoverflow.po.v3.handle.ProfileHandler;

import javax.swing.*;
import java.awt.*;

public class Profiles extends JFrame
{
    public JPanel rootPanel;
    public JTabbedPane profiles;

    public Profiles()
    {
        add(rootPanel);

        setMinimumSize(new Dimension(500, 350));

        HomeTab htab = new HomeTab(this);
        profiles.add("Home", htab.rootPanel);

        for (ProfileHandler.Profile profile : PresenceOverflow.getPROFILES())
        {
            Tab tab = new Tab(this, profile);

            String clientId = Util.filter_string(Long.toString(profile.getClientid()));
            String details = Util.filter_string(profile.getDetails());
            String state = Util.filter_string(profile.getState());
            String bigImageKey = Util.filter_string(profile.getBig_image_key());
            String bigImageCaption = Util.filter_string(profile.getBig_image_caption());
            String smallImageKey = Util.filter_string(profile.getSmall_image_key());
            String smallImageCaption = Util.filter_string(profile.getSmall_image_caption());

            tab.clientIDTextField.setText(clientId);
            tab.detailsTextField.setText(details);
            tab.stateTextField.setText(state);
            tab.bigImageKeyTextField.setText(bigImageKey);
            tab.bigImageCaptionTextField.setText(bigImageCaption);
            tab.smallImageKeyTextField.setText(smallImageKey);
            tab.smallImageCaptionTextField.setText(smallImageCaption);

            profiles.add(profile.getName(), tab.rootPanel);
        }

        setTitle("PresenceOverflow");
        setLocationRelativeTo(null);
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
