package org.woahoverflow.po.v3;

import net.arikia.dev.drpc.DiscordRPC;
import org.woahoverflow.po.v3.handle.DiscordHandler;
import org.woahoverflow.po.v3.handle.ProfileHandler;

import java.util.concurrent.TimeUnit;

public class PresenceRotationThread extends Thread
{
    public static int rotationDelay = 5;
    public static ProfileHandler.Profile[] rotation = new ProfileHandler.Profile[20];

    public static void setRotation(ProfileHandler.Profile profile, int position)
    {
        if (position > -1 && position < 1000)
        {
            rotation[position] = profile;
        }
    }

    private boolean running = false;

    @Override
    public void run()
    {
        running = true;

        int index = 0;
        while (running)
        {
            ProfileHandler.Profile profile = rotation[index];
            if (profile != null)
                DiscordHandler.refresh(profile);
            else  {
                index = 0;
                continue;
            }
            index++;
            try { TimeUnit.SECONDS.sleep(rotationDelay); } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

    public void end()
    {
        running = false;
        try { this.join(); } catch (Exception ex) { ex.printStackTrace(); }
    }
}
