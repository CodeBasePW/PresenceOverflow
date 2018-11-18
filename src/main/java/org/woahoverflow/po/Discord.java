package org.woahoverflow.po;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

import static org.woahoverflow.po.PresenceOverflow.ui;

public class Discord
{
    private static String STATE = "";
    private static String DETAILS = "";
    private static String BIG_IMAGE_CAPTION = "";
    private static String BIG_IMAGE = "";
    private static String SMALL_IMAGE_CAPTION = "";
    private static String SMALL_IMAGE = "";
    private static DiscordEventHandlers HANDLERS = new DiscordEventHandlers.Builder().setReadyEventHandler((user) ->
    {
        ui.updateStatus("Running... " + user.username);
        DiscordRPC.discordUpdatePresence(build());
    }).build();

    public static void initDiscord()
    {
        DiscordRPC.discordInitialize(ui.getValues().get("app_id"), HANDLERS, true);
        ui.updateStatus("Running...");
        DiscordRPC.discordRunCallbacks();
    }

    private static void update()
    {
        ConcurrentHashMap<String, String> hash = ui.getValues();
        STATE = hash.get("state");
        DETAILS = hash.get("details");
        BIG_IMAGE_CAPTION = hash.get("big_image_caption");
        SMALL_IMAGE_CAPTION = hash.get("small_image_caption");
        BIG_IMAGE = hash.get("big_image_key");
        SMALL_IMAGE = hash.get("small_image_key");

    }

    public static void refresh()
    {
        DiscordRPC.discordUpdatePresence(build());
    }
    private static DiscordRichPresence build()
    {
        update();
        if (STATE.equals(""))
        {
            ui.updateStatus("Invalid State!");
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
}
