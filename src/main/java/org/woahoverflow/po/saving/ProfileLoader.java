package org.woahoverflow.po.saving;

import org.woahoverflow.po.Json;

import java.io.File;

public final class ProfileLoader
{
    public static class Profile
    {
        String profileName;
        String lastSaved;
        String state;
        String details;
        String bigImageCaption;
        String bigImage;
        String smallImageCaption;
        String smallImage;

        Profile(String profileName, String time, String state, String details,
            String bigImageCaption, String bigImage, String smallImageCaption, String smallImage)
        {
            this.profileName = profileName;
            lastSaved = time;
            this.state = state;
            this.details = details;
            this.bigImageCaption = bigImageCaption;
            this.bigImage = bigImage;
            this.smallImage = smallImage;
            this.smallImageCaption = smallImageCaption;
        }
    }

    public static Profile loadProfile(int i)
    {
        if (i > 3) {
            return null;
        }

        File file = new File(System.getenv("appdata") + "\\woahoverflow\\profile\\profile_" + i + ".json");
        return new Profile(
            Json.get(file, "profile_name"), Json.get(file, "lastSaved"), Json.get(file, "state"), Json
            .get(file, "details"), Json.get(file, "bigImageCaption"), Json.get(file, "big_image_key"), Json
            .get(file, "smallImageCaption"),  Json.get(file, "small_image_key"));
    }

    public static void saveProfile(Profile profile)
    {
        //TODO save profile
    }
}
