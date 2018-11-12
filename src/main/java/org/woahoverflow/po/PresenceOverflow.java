package org.woahoverflow.po;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import org.woahoverflow.po.ui.Panel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Executors;

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
    private static DiscordEventHandlers HANDLERS = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> DiscordRPC.discordUpdatePresence(build())).build();

    public static void main(String... args)
    {
        Runtime.getRuntime().addShutdownHook(new Thread(DiscordRPC::discordShutdown));
        JFrame frame = new JFrame("PresenceOverflow");
        try {
            URLConnection con = new URL("https://raw.githubusercontent.com/woahoverflow/PresenceOverflow/master/icon.png").openConnection();
            frame.setIconImage(ImageIO.read(con.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setSize(850, 340);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel.go.addActionListener((ev) -> {
            if (panel.appIdValue.getText().equals(""))
            {
                panel.status.setText("Invalid App ID!");
                return;
            }
            panel.refresh.setVisible(true);
            panel.go.setVisible(false);
            initDiscord();
            refresh();
        });
        panel.refresh.addActionListener((ev) -> refresh());
    }
    private static void save()
    {
        if (new File(System.getenv("appdata") + "\\woahoverflow\\presenceoverflow\\data.json").exists())
        {
            // TODO do stuff
        }
        else {
            try {
                if (!new File(System.getenv("appdata") + "\\woahoverflow\\presenceoverflow").mkdirs())
                    panel.status.setText("Error Saving");
                if (!new File(System.getenv("appdata") + "\\woahoverflow\\presenceoverflow\\data.json").createNewFile())
                    panel.status.setText("Error Saving");
            } catch (IOException e)
            {
                panel.status.setText("Error Saving");
            }
            finally
            {
                // TODO do stuff
            }
        }
    }

    private static void refresh()
    {
        DiscordRPC.discordUpdatePresence(build());
    }

    private static DiscordRichPresence build()
    {
        update();
        if (STATE.equals(""))
        {
            panel.status.setText("Invalid State!");
            return null;
        }

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
        BIG_IMAGE_CAPTION = panel.bigImageCaption.getText().trim();
        BIG_IMAGE = panel.bigImageKeyValue.getText().trim();
        SMALL_IMAGE = panel.smallImageKeyValue.getText().trim();
        SMALL_IMAGE_CAPTION = panel.smallImageCaption.getText().trim();
        DETAILS = panel.detailsValue.getText().trim();
    }

    private static void initDiscord()
    {
        DiscordRPC.discordInitialize(panel.appIdValue.getText().trim(), HANDLERS, true);
        Executors.newFixedThreadPool(1).submit(PresenceOverflow::constant);
        panel.status.setText("Running...");
    }

    private static Runnable constant()
    {
        return () -> {
          while (ENABLED)
          {
              DiscordRPC.discordRunCallbacks();
              DiscordRPC.discordUpdatePresence(build());
              try {
                  Thread.sleep(5000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        };
    }
}
