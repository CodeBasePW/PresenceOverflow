package po

import org.apache.commons.lang3.SystemUtils
import java.io.File
import java.io.IOException
import org.json.JSONArray
import org.json.JSONObject
import po.ui.panel
import java.io.FileWriter


class Profile(
        var id: String,
        var state: String,
        var details: String,
        var smallImageKey: String,
        var bigImageKey: String,
        var bigImageCaption: String,
        var smallImageCaption: String,
        var using: Boolean
)

var profileFile = File(System.getenv("appdata") + "\\woahoverflow\\presenceoverflow\\profiles.json")
var attempts: Int = 0

fun initFiles() {
    // Versions I assume which can support. (only windows 10 tested)
    if (SystemUtils.IS_OS_WINDOWS_10 || SystemUtils.IS_OS_WINDOWS_7 || SystemUtils.IS_OS_WINDOWS_8) {
        if (!File(System.getenv("appdata") + "\\woahoverflow\\presenceoverflow\\profiles.json").exists()) {
            if (attempts >= 10) {
                PresenceOverflow.LOGGER.error("There was an issue with retrieving files. Please report this!")
                System.exit(1)
            }

            attempts++

            val folders = File(System.getenv("appdata") + "\\woahoverflow\\presenceoverflow")
            if (!folders.exists() && !folders.mkdirs()) {
                PresenceOverflow.LOGGER.error("There was an issue creating the folders, retrying")
                Runnable {
                    initFiles()
                }.run()
                return
            }

            val file = File(System.getenv("appdata") + "\\woahoverflow\\presenceoverflow\\profiles.json")
            if (!file.exists() && !file.createNewFile()) {
                PresenceOverflow.LOGGER.error("There was an issue creating profile.json, retrying")
                Runnable {
                    initFiles()
                }.run()
                return
            }

            profileFile = file

            val obj = JSONObject()
            val profiles = JSONArray()

            val profileOne = JSONObject()
            profileOne.put("using", false)
            profileOne.put("state", "")
            profileOne.put("details", "")
            profileOne.put("bigImageKey", "")
            profileOne.put("smallImageKey", "")
            profileOne.put("bigImageCaption", "")
            profileOne.put("smallImageCaption", "")
            profileOne.put("id", "")

            profiles.put(profileOne)
            profiles.put(profileOne)
            profiles.put(profileOne)

            obj.put("profiles", profiles)
            try {
                FileWriter(file).use { fileWriter ->
                    fileWriter.write(obj.toString())
                    fileWriter.flush()
                }
            } catch (e: IOException) {
                PresenceOverflow.LOGGER.error("There was an issue writing the JSON, retrying")
                Runnable {
                    initFiles()
                }.run()
                return
            }
            return
        } else {
            return
        }
    }

    PresenceOverflow.LOGGER.error("Unsupported operating system, exiting.")
    System.exit(1)
}

fun getProfile(num: Int): Profile? {
    if (0 > num || num > 3) return null

    val obj = JSONObject(String(profileFile.inputStream().readBytes())).getJSONArray("profiles").getJSONObject(num)

    return Profile(obj.getString("id"), obj.getString("state"), obj.getString("details"), obj.getString("smallImageKey"), obj.getString("bigImageKey"), obj.getString("bigImageCaption"), obj.getString("smallImageCaption"), obj.getBoolean("using"))
}

fun updateProfile(num: Int, profile: Profile) {
    if (0 > num || num > 3) return

    val obj = JSONObject(String(profileFile.inputStream().readBytes()))

    obj.getJSONArray("profiles").getJSONObject(num).put("state", profile.state)
    obj.getJSONArray("profiles").getJSONObject(num).put("details", profile.details)
    obj.getJSONArray("profiles").getJSONObject(num).put("bigImageCaption", profile.bigImageCaption)
    obj.getJSONArray("profiles").getJSONObject(num).put("bigImageKey", profile.bigImageKey)
    obj.getJSONArray("profiles").getJSONObject(num).put("smallImageCaption", profile.smallImageCaption)
    obj.getJSONArray("profiles").getJSONObject(num).put("smallImageKey", profile.smallImageKey)
    obj.getJSONArray("profiles").getJSONObject(num).put("using", profile.using)
    obj.getJSONArray("profiles").getJSONObject(num).put("id", profile.id)

    FileWriter(profileFile).use { fileWriter ->
        fileWriter.write(
                obj.toString()
        )
        fileWriter.flush()
    }
}

fun useProfile(num: Int) {
    if (0 > num || num > 3) return

    val profile = getProfile(num)!!
    panel.stateTextArea.text = profile.state
    panel.detailsTextArea.text = profile.details
    panel.imageCaptionTextArea.text = profile.bigImageCaption
    panel.smallImageCaptionTextArea.text = profile.smallImageCaption
    panel.smallImageKeyTextArea.text = profile.smallImageKey
    panel.imageKeyTextArea.text = profile.bigImageKey
    panel.clientIdTextArea.text = profile.id
}