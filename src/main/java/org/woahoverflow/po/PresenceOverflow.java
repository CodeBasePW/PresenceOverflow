package org.woahoverflow.po;

import net.arikia.dev.drpc.DiscordRPC;
import org.woahoverflow.po.ui.UIHandler;

public class PresenceOverflow
{
    static final UIHandler ui = new UIHandler();

    public static void main(String... args)
    {
        System.out.println("Began");
        Runtime.getRuntime().addShutdownHook(new Thread(DiscordRPC::discordShutdown));
    }
}
