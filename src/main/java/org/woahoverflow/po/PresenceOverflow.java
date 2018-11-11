package org.woahoverflow.po;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import org.woahoverflow.po.ui.Panel;

import javax.swing.*;

public class PresenceOverflow
{
    private static Panel panel = new Panel();
    private static String STATE = "";
    private static String DETAILS = "";
    private static String BIG_IMAGE_CAPTION = "";
    private static String BIG_IMAGE = "";
    private static String SMALL_IMAGE_CAPTION = "";
    private static String SMALL_IMAGE = "";
    private static boolean ENABLED = true;

    public static void main(String... args)
    {
        JFrame frame = new JFrame("PresenceOverflow");
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setSize(1000, 1000);
        initDiscord();
    }

    private static DiscordRichPresence build()
    {
        update();
        if (STATE.equals(""))
            return null;

        DiscordRichPresence.Builder rp = new DiscordRichPresence.Builder(STATE);

        if (!BIG_IMAGE.equals(""))
            rp.setBigImage(BIG_IMAGE, BIG_IMAGE_CAPTION);

        if (!SMALL_IMAGE.equals(""))
            rp.setSmallImage(SMALL_IMAGE, SMALL_IMAGE_CAPTION);

        if (!DETAILS.equals(""))
            rp.setDetails(DETAILS);

        return rp.build();
    }

    private static void update()
    {
        STATE = panel.stateValue.getText().trim();
        DETAILS = panel.detailsValue.getText().trim();
    }

    private static void initDiscord()
    {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down DiscordHook.");
            DiscordRPC.discordShutdown();
        }));
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
            DiscordRPC.discordRunCallbacks();
            System.out.println(user.username + " (began rp)");
            DiscordRPC.discordUpdatePresence(build());
        }).build();
        DiscordRPC.discordInitialize("511091069824270352", handlers, true);
        new Thread(constant()).run();
    }

    private static Runnable constant()
    {
        return () -> {
          while (ENABLED)
          {
              DiscordRPC.discordRunCallbacks();
              DiscordRPC.discordUpdatePresence(build());
          }
        };
    }
}
