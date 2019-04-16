package org.woahoverflow.po.v3;

import mdlaf.MaterialLookAndFeel;
import org.woahoverflow.po.v3.handle.ProfileHandler;
import org.woahoverflow.po.v3.ui.Login;
import org.woahoverflow.po.v3.ui.Profiles;

import javax.swing.UIManager;
import javax.swing.UIManager.*;

/**
 * The main instance for Presence Overflow.
 *
 * This holds the main function
 *
 * @author sho, codebasepw
 */
public class POInstance
{
    public static PresenceRotationThread rotationThread = new PresenceRotationThread();

    public static void main(String... args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                System.out.println("Found L&F: " + info.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try
        {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        new Login();

        keepAlive();
    }

    public static void loggedIn()
    {
        new Profiles();

        rotationThread.start();

        for (ProfileHandler.Profile profile : ProfileHandler.INSTANCE.getProfiles())
        {
            PresenceOverflow.getLOGGER().debug("Found profile - ({}, {})", profile.getName(), profile.getPid());
        }
    }

    private static void keepAlive() {
        while (true) {}
    }

    public static String longString(long val)
    {
        return Long.toString(val);
    }
}
