package org.woahoverflow.po.v3;

import mdlaf.MaterialLookAndFeel;
import org.woahoverflow.po.v3.handle.ProfileHandler;
import org.woahoverflow.po.v3.ui.Login;
import org.woahoverflow.po.v3.ui.Profiles;

import javax.swing.UIManager;
import javax.swing.UIManager.*;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * The main instance for Presence Overflow.
 *
 * This holds the main function
 *
 * @author sho
 */
public class POInstance {
    public static String control_hash = sha256("i am the greatest programmer that ever lived");

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
        //new UI();
        //new Profiles();

        keepAlive();
    }

    public static void loggedIn()
    {
        new Profiles();

        rotationThread.start();

        /*int index = 0;
        for (ProfileHandler.Profile profile : ProfileHandler.INSTANCE.getProfiles())
        {
            PresenceRotationThread.setRotation(profile, index);
            index++;
        }*/
    }

    private static void keepAlive() {
        while (true) {}
    }

    public static String longString(long val)
    {
        return Long.toString(val);
    }

    public static String sha256(String str)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "";
    }

    public static String bytesToHex(byte[] hash)
    {
        return DatatypeConverter.printHexBinary(hash);
    }
}
