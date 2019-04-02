package org.woahoverflow.po.v3.handle

import com.mashape.unirest.http.Unirest
import org.woahoverflow.po.v3.PresenceOverflow
import java.util.concurrent.TimeUnit

object ProfileHandler {
    data class Profile internal constructor(
            val state: String,
            val details: String,
            val big_image_key: String,
            val big_image_caption: String,
            val small_image_key: String,
            val small_image_caption: String,
            val pid: String,
            val clientid: Long,
            val name: String,
            val owner: Long?
    )

    fun createProfile(
            state: String,
            details: String,
            big_image_key: String,
            big_image_caption: String,
            small_image_key: String,
            small_image_caption: String,
            pid: String,
            clientid: Long,
            name: String
    ): Profile = Profile(state, details, big_image_key, big_image_caption, small_image_key, small_image_caption, pid, clientid, name, AccountHandler.id)

    /**
     * Gets all profiles
     */
    fun getProfiles(): ArrayList<Profile> {
        val wait = System.currentTimeMillis()
        /*while (!AccountHandler.loggedIn) {
            PresenceOverflow.LOGGER.warn("Waiting for Account Handler to be logged in... Waited ${System.currentTimeMillis()-wait}ms")
            TimeUnit.SECONDS.sleep(5)
        }*/

        val contents = Unirest.get("https://api.woahoverflow.org/presenceoverflow/profile/all")
                .queryString("token", AccountHandler.token)
                .asJson().body.`object`.getJSONObject("message").getJSONObject("contents")

        val ar = contents.getJSONArray("profiles")
        val size = contents.getInt("size")

        val list = arrayListOf<Profile>()
        for (i in 0 until size) {
            val obj = ar.getJSONObject(i)

            list.add(Profile(
                    obj.getString("state"),
                    obj.getString("details"),
                    obj.getString("big_image_key"),
                    obj.getString("big_image_caption"),
                    obj.getString("small_image_key"),
                    obj.getString("small_image_caption"),
                    obj.getString("pid"),
                    obj.getLong("clientid"),
                    obj.getString("name"),
                    null
            ))
        }

        return list
    }

    /**
     * Gets a profile through the PID
     *
     * This can be anyone's profile, as PID's are shareable. Though, if you're not a VIP you cannot import PIDs to your client.
     */
    fun getProfileByPID(pid: String): Profile? {
        val resp = Unirest.get("https://api.woahoverflow.org/presenceoverflow/profile/$pid")
                .queryString("token", AccountHandler.token)
                .asJson()

        if (resp.status != 200) return null

        val content = resp.body.`object`.getJSONObject("message").getJSONObject("contents")
        val data = content.getJSONObject("data")

        return Profile(
                data.getString("state"),
                data.getString("details"),
                data.getString("big_image_caption"),
                data.getString("big_image_key"),
                data.getString("small_image_caption"),
                data.getString("small_image_key"),
                content.getString("pid"),
                data.getLong("clientid"),
                content.getString("name"),
                content.getLong("owner")
        )
    }

    /**
     * This gets a local profile and uploads it to save to the cloud.
     */
    fun saveProfile(profile: Profile): String {
        val resp = Unirest.post("https://api.woahoverflow.org/presenceoverflow/profile")
                .field("state", profile.state)
                .field("details", profile.details)
                .field("big_image_key", profile.big_image_key)
                .field("big_image_caption", profile.big_image_caption)
                .field("small_image_key", profile.small_image_key)
                .field("small_image_caption", profile.small_image_caption)
                .field("clientid", profile.clientid)
                .field("name", profile.name)
                .field("token", AccountHandler.token)
                .asJson()

        if (resp.status != 200) PresenceOverflow.LOGGER.error("There was an issue creating a profile!")

        PresenceOverflow.PROFILES.add(getProfileByPID(resp.body.`object`.getJSONObject("message").getString("contents"))!!)

        PresenceOverflow.LOGGER.debug("Created new profile ${resp.body.`object`.getJSONObject("message").getString("contents")}")

        return resp.body.`object`.getJSONObject("message").getString("contents")
    }

    /**
     * Deletes a profile
     */
    fun deleteProfile(pid: String): Boolean {
        val resp = Unirest.delete("https://api.woahoverflow.org/presenceoverflow/profile/$pid")
                .field("token", AccountHandler.token)
                .asJson()

        PresenceOverflow.refreshProfiles()
        return resp.status == 200
    }

    /**
     * edits a profile
     */
    fun editProfile(pid: String, profile: Profile): Boolean {
        val resp = Unirest.put("https://api.woahoverflow.org/presenceoverflow/profile/$pid")
                .field("state", profile.state)
                .field("details", profile.details)
                .field("big_image_key", profile.big_image_key)
                .field("big_image_caption", profile.big_image_caption)
                .field("small_image_key", profile.small_image_key)
                .field("small_image_caption", profile.small_image_caption)
                .field("clientid", profile.clientid)
                .field("name", profile.name)
                .field("token", AccountHandler.token)
                .asJson()

        PresenceOverflow.refreshProfiles()
        return resp.status == 200
    }

    /**
     * Saves a profile from a PID
     */
    fun saveProfile(pid: String): Boolean {
        val profile = getProfileByPID(pid) ?: return false

        ProfileHandler.saveProfile(profile)

        return true
    }
}