package po.ui;

import po.PresenceOverflow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLConnection;

/**
 * Manages the PresenceOverflow tray icon
 *
 * @author sho
 */
public class TrayMenu {
    public static final MenuItem SHOW = new MenuItem("Show");
    public static final MenuItem EXIT = new MenuItem("Exit");
    private static TrayIcon TRAYICON = null;
    private static PopupMenu POPUPMENU = null;

    /**
     * Instantiates the tray icon
     */
    public static void init() {
        createAndShowGUI();
    }

    /**
     * Removes the tray icon
     */
    public static void exit() {
        SystemTray.getSystemTray().remove(TRAYICON);
    }

    /**
     * Creates and sets up the tray icon
     */
    private static void createAndShowGUI() {
        if (!SystemTray.isSupported()) return;

        final PopupMenu popup = new PopupMenu();
        BufferedImage image;

        final TrayIcon trayIcon =
                new TrayIcon(
                        PresenceOverflow.SMALL_ICON, "PresenceOverflow", popup
                );
        final SystemTray tray = SystemTray.getSystemTray();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        // Create a popup menu components
        MenuItem show = new MenuItem("Show");
        MenuItem exit = new MenuItem("Exit");

        show.addActionListener((ev) -> UIManagerKt.getJFrame().setVisible(true));
        exit.addActionListener((ev) -> System.exit(1));

        Font font = Font.getFont("Segoe UI");
        show.setFont(font);
        exit.setFont(font);

        //Add components to popup menu
        popup.add(show);
        popup.add(exit);

        TRAYICON = trayIcon;
        POPUPMENU = popup;

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            PresenceOverflow.LOGGER.error("There was an issue adding the Tray, ignoring.");
        }
    }
}