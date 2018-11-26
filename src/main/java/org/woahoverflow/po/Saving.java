package org.woahoverflow.po;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import static org.woahoverflow.po.PresenceOverflow.ui;

public final class Saving
{
    public static void save()
    {
        if (new File(System.getenv("appdata") + "\\woahoverflow\\presenceoverflow\\data.json").exists())
        {
            // TODO do stuff
        }
        else {
            try {
                if (!new File(System.getenv("appdata") + "\\woahoverflow\\presenceoverflow").mkdirs())
                    ui.updateStatus("Error Saving");
                if (!new File(System.getenv("appdata") + "\\woahoverflow\\presenceoverflow\\data.json").createNewFile())
                    ui.updateStatus("Error Saving");
            } catch (IOException e)
            {
                ui.updateStatus("Error Saving");
            }
            finally
            {
                // TODO do stuff
            }
        }
    }

    public static ConcurrentHashMap<String, String> load()
    {
        if (!new File(System.getenv("appdata") + "\\woahoverflow\\presenceoverflow\\data.json").exists())
        {
            ui.updateStatus("No saved data!");
            return null;
        }

        // TODO: Do stuff
        return new ConcurrentHashMap<>();
    }
}
