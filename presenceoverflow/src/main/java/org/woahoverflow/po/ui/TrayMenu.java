package org.woahoverflow.po.ui;

import org.woahoverflow.po.PresenceOverflow;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Manages the PresenceOverflow tray icon
 *
 * @author sho
 */
public class TrayMenu {
    private static final MenuItem SHOW = new MenuItem("Show");
    public static final MenuItem EXIT = new MenuItem("Exit");
    private static TrayIcon TRAYICON = null;

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
        SHOW.addActionListener((ev) -> UIManagerKt.getJFrame().setVisible(true));

        Font font = Font.getFont("Segoe UI");
        SHOW.setFont(font);
        EXIT.setFont(font);

        //Add components to popup menu
        popup.add(SHOW);
        popup.add(EXIT);

        TRAYICON = trayIcon;
        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            PresenceOverflow.LOGGER.error("There was an issue adding the Tray, ignoring.");
        }
    }
}