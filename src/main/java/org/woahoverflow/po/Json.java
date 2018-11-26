package org.woahoverflow.po;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class Json
{
    public static String get(File file, String entry)
    {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject  = (JSONObject) obj;
            return (String) jsonObject.get(entry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void set(File file, String object, String input) throws IOException
    {
        String jsonString = com.google.common.io.Files.toString(file, Charsets.UTF_8);
        JsonElement jelement = new JsonParser().parse(jsonString);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject.addProperty(object, input);
        Gson gson = new Gson();

        String resultingJson = gson.toJson(jelement);

        Files.write(resultingJson, file, Charsets.UTF_8);
    }
}
