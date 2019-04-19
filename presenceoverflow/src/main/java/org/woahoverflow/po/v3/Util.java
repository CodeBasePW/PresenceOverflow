package org.woahoverflow.po.v3;

import org.woahoverflow.po.v3.handle.ProfileHandler;
import org.woahoverflow.po.v3.ui.Profiles;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Util
{
    public static void logProfile(ProfileHandler.Profile profile)
    {
        PresenceOverflow.getLOGGER().debug("Profile({}, {}, {}, {}, {}, {}, {}, {})",
                handle_control_string(profile.getState()),
                handle_control_string(profile.getDetails()),
                handle_control_string(profile.getBig_image_key()),
                handle_control_string(profile.getBig_image_caption()),
                handle_control_string(profile.getSmall_image_key()),
                handle_control_string(profile.getSmall_image_caption()),
                profile.getClientid(),
                profile.getName()
        );
    }

    public static ProfileHandler.Profile fixProfile(ProfileHandler.Profile input)
    {
        ProfileHandler.Profile output = new ProfileHandler.Profile(
                handle_control_string(input.getState()),
                handle_control_string(input.getDetails()),
                handle_control_string(input.getBig_image_key()),
                handle_control_string(input.getBig_image_caption()),
                handle_control_string(input.getSmall_image_key()),
                handle_control_string(input.getSmall_image_caption()),
                null,
                input.getClientid(),
                handle_control_string(input.getName()),
                null
        );

        return output;
    }

    public static String handle_control_string(String input)
    {
        if (input.isEmpty())
        {
            return getControlHash();
        } else if (input.contains(getControlHash())) {
            return "";
        }

        return input;
    }

    public static void notify(String msg, String title)
    {
        notify(msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void notify(String msg, String title, int type)
    {
        JOptionPane.showMessageDialog(null, msg, title, type);
    }

    public static boolean openWebpage(URI uri)
    {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
        {
            try
            {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebpage(URL url)
    {
        try
        {
            return openWebpage(url.toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean openWebpage(String url)
    {
        try
        {
            return openWebpage(new URL(url));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean areProfilesSaved(Profiles profiles)
    {
        if (profiles.profiles.getComponents().length-1 > ProfileHandler.INSTANCE.getProfiles().size())
            return false;
        return true;
    }

    public static boolean askExit()
    {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit?", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION)
            return true;
        else
            return false;
    }

    public static boolean askConfirmation()
    {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to do this?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION)
            return true;
        else
            return false;
    }

    public static boolean askConfirmation(String msg)
    {
        int result = JOptionPane.showConfirmDialog(null, msg, "Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION)
            return true;
        else
            return false;
    }

    public static boolean denyPremiumOnly()
    {
        int result = JOptionPane.showConfirmDialog(null, "This feature is premium only!\nYou can purchase premium at https://woahoverflow.org/premium.\n\nWould you like to open the page?", "Restricted Feature", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION)
            return true;
        else
            return false;
    }

    public static String filter_string(String input)
    {
        return input.replace(getControlHash(), "");
    }

    public static ProfileHandler.Profile createBlankProfile()
    {
        return ProfileHandler.INSTANCE.createProfile(getControlHash(), getControlHash(), getControlHash(), getControlHash(), getControlHash(), getControlHash(), 0, getControlHash());
    }

    public static ProfileHandler.Profile createBlankProfile(String name)
    {
        return ProfileHandler.INSTANCE.createProfile(getControlHash(), getControlHash(), getControlHash(), getControlHash(), getControlHash(), getControlHash(), 0, name);
    }

    public static String getControlString()
    {
        return sha256("--p_ctrl_str");
    }

    public static String getControlHash()
    {
        return sha256("i am the greatest programmer that ever lived");
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
        StringBuilder sb = new StringBuilder();
        for (byte b : hash)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
