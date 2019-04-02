package org.woahoverflow.po.v3.ui;

import org.woahoverflow.po.v3.handle.ProfileHandler;

import javax.swing.*;

public class Tab
{
    public JPanel panel;

    private ProfileHandler.Profile profile;

    public Tab(JPanel origin, ProfileHandler.Profile profile)
    {
        panel = new JPanel();
        panel.setName(profile.getName());

        this.profile = profile;
    }
}
