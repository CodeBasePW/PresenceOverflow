package po;

import net.arikia.dev.drpc.DiscordRPC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import po.ui.UIManagerKt;

import javax.swing.*;

/**
 * The main class for PresenceOverflow
 *
 * TODO Fix multiple image requests
 * TODO Make it look better (center text better)
 * TODO Only allow with login :)
 */
public class PresenceOverflow {
    public static final Logger LOGGER = LoggerFactory.getLogger("PresenceOverflow");
    public static final String VERSION = "v0.2.0";

    public static void main(String... args) {
        LOGGER.info("Starting PresenceOverflow " + VERSION);

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
