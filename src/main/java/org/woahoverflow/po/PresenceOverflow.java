package org.woahoverflow.po;

import net.arikia.dev.drpc.DiscordRPC;
import org.json.JSONObject;
import org.woahoverflow.po.ui.UIHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author sho
 */
public class PresenceOverflow
{
    static final UIHandler ui = new UIHandler();

    public static void main(String... args)
    {
        File woahoverflowFolder = new File(System.getenv("appdata") + "\\woahoverflow\\presenceoverflow\\");

        if (!woahoverflowFolder.exists()) {
            System.out.println(woahoverflowFolder.mkdirs());
        }

        File profileFolder = new File(
            System.getenv("appdata") + "\\woahoverflow\\presenceoverflow\\profile\\");
        if (!profileFolder.exists()) {
            System.out.println(profileFolder.mkdir());
        }

        try {
            File presenceOverflowFile = new File(
                System.getenv("appdata") + "\\woahoverflow\\presenceoverflow\\config.json");
            if (!presenceOverflowFile.exists())
            {
                System.out.println(presenceOverflowFile.createNewFile());
                JSONObject obj = new JSONObject();
                obj.put("", "");
                try (FileWriter filew = new FileWriter(presenceOverflowFile)) {
                    filew.write(obj.toString());
                    filew.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            File profileOne = new File(System.getenv("appdata")
                + "\\woahoverflow\\presenceoverflow\\profile\\profile_1.json");
            if (!profileOne.exists())
            {
                System.out.println(profileOne.createNewFile());
                JSONObject obj = new JSONObject();
                obj.put("profile_name", "blank");
                obj.put("profile_key", 1);
                obj.put("set", "false");
                obj.put("state", "empty");
                obj.put("big_image_key", "empty");
                obj.put("bigImageCaption", "empty");
                obj.put("small_image_key", "empty");
                obj.put("smallImageCaption", "empty");
                obj.put("details", "empty");
                obj.put("lastSaved", "empty");
                try (FileWriter filew = new FileWriter(profileOne)) {
                    filew.write(obj.toString());
                    filew.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            File profileThree = new File(System.getenv("appdata")
                + "\\woahoverflow\\presenceoverflow\\profile\\profile_3.json");
            if (!profileThree.exists())
            {
                System.out.println(profileThree.createNewFile());
                JSONObject obj = new JSONObject();
                obj.put("profile_name", "blank");
                obj.put("profile_key", 3);
                obj.put("set", "false");
                obj.put("state", "empty");
                obj.put("big_image_key", "empty");
                obj.put("bigImageCaption", "empty");
                obj.put("small_image_key", "empty");
                obj.put("smallImageCaption", "empty");
                obj.put("details", "empty");
                obj.put("lastSaved", "empty");
                try (FileWriter filew = new FileWriter(profileThree)) {
                    filew.write(obj.toString());
                    filew.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            File profileTwo = new File(System.getenv("appdata")
                + "\\woahoverflow\\presenceoverflow\\profile\\profile_2.json");
            if (!profileTwo.exists())
            {
                System.out.println(profileTwo.createNewFile());
                JSONObject obj = new JSONObject();
                obj.put("profile_name", "blank");
                obj.put("profile_key", 2);
                obj.put("set", "false");
                obj.put("state", "empty");
                obj.put("big_image_key", "empty");
                obj.put("bigImageCaption", "empty");
                obj.put("small_image_key", "empty");
                obj.put("smallImageCaption", "empty");
                obj.put("details", "empty");
                obj.put("lastSaved", "empty");
                try (FileWriter filew = new FileWriter(profileTwo)) {
                    filew.write(obj.toString());
                    filew.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // begin filling files
        System.out.println("Began");
        Runtime.getRuntime().addShutdownHook(new Thread(DiscordRPC::discordShutdown));
    }
}
