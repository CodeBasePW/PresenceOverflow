package org.woahoverflow.po;

import net.arikia.dev.drpc.DiscordRPC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.woahoverflow.po.ui.UIManagerKt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLConnection;

/**
 * The main class for PresenceOverflow
 *
 * TODO Fix multiple image requests
 * TODO Make it look better (center text better)
 * TODO Only allow with login
 * TODO Save the token in file
 * TODO Make the exit button in the top right
 * TODO Make auto updating
 * TODO
 */
public class PresenceOverflow {
    public static final Logger LOGGER = LoggerFactory.getLogger("PresenceOverflow");
    public static final String VERSION = "v0.2.0";
    public static Image LARGE_ICON = null;
    public static Image SMALL_ICON = null;

    public static void main(String... args) {
        LOGGER.info("Starting PresenceOverflow " + VERSION);

        BufferedImage image;
        try {
            URL url = new URL("https://assets.woahoverflow.org/favicon/default.png");
            URLConnection con = url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0 Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0");
            image = ImageIO.read(con.getInputStream());

            LARGE_ICON = new ImageIcon(image).getImage();

            url = new URL("https://assets.woahoverflow.org/favicon/dark-small.png");
            con = url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0 Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0");
            image = ImageIO.read(con.getInputStream());

            SMALL_ICON = new ImageIcon(image).getImage();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ProfileLoaderKt.initFiles();
        UIManagerKt.init();

        Runtime.getRuntime().addShutdownHook(new Thread(DiscordRPC::discordShutdown));
    }
}
