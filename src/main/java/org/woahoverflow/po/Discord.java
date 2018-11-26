package org.woahoverflow.po;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

import java.util.concurrent.ConcurrentHashMap;

import static org.woahoverflow.po.PresenceOverflow.ui;

public final class Discord
{
    private static String state = "";
    private static String details = "";
    private static String bigImageCaption = "";
    private static String bigImage = "";
    private static String smallImageCaption = "";
    private static String smallImage = "";
    private static final DiscordEventHandlers HANDLERS = new DiscordEventHandlers.Builder().setReadyEventHandler((user) ->
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
        state = hash.get("state");
        details = hash.get("details");
        bigImageCaption = hash.get("bigImageCaption");
        smallImageCaption = hash.get("smallImageCaption");
        bigImage = hash.get("big_image_key");
        smallImage = hash.get("small_image_key");

    }

    public static void refresh()
    {
        DiscordRPC.discordUpdatePresence(build());
    }
    private static DiscordRichPresence build()
    {
        update();
        if (state.isEmpty())
        {
            ui.updateStatus("Invalid State!");
            return null;
        }

        DiscordRichPresence.Builder rp = new DiscordRichPresence.Builder(state);

        if (!bigImage.isEmpty()) {
            rp.setBigImage(bigImage, bigImageCaption);
        }

        if (!smallImage.isEmpty()) {
            rp.setSmallImage(smallImage, smallImageCaption);
        }

        if (!details.isEmpty()) {
            rp.setDetails(details);
        }

        return rp.build();
    }
}
