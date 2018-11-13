package org.woahoverflow.po;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import org.woahoverflow.po.ui.Panel;
import org.woahoverflow.po.ui.UIHandler;

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
    static final UIHandler ui = new UIHandler();

    public static void main(String... args)
    {
        Runtime.getRuntime().addShutdownHook(new Thread(DiscordRPC::discordShutdown));
    }
}
