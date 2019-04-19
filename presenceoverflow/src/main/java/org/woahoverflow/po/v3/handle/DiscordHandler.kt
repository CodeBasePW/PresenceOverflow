package org.woahoverflow.po.v3.handle

import net.arikia.dev.drpc.DiscordEventHandlers
import net.arikia.dev.drpc.DiscordRPC
import net.arikia.dev.drpc.DiscordRichPresence
import org.woahoverflow.po.v3.POInstance
import org.woahoverflow.po.v3.PresenceOverflow
import org.woahoverflow.po.v3.Util

/**
 * Manages the Discord RP
 */
object DiscordHandler {
    private var handlers: DiscordEventHandlers? = null
    private var running = false
    private var previousId: Long? = null

    @JvmStatic
    private fun init() {
        PresenceOverflow.LOGGER.debug("Running init for Discord")
        handlers = DiscordEventHandlers.Builder().setReadyEventHandler {
            PresenceOverflow.LOGGER.debug("User {} is now connected.", it.username)
        }.setDisconnectedEventHandler { _, _ ->
            PresenceOverflow.LOGGER.debug("User has disconnected.")
        }.build()
    }

    // TODO add more information here
    @JvmStatic
    fun refresh(profile: ProfileHandler.Profile) {
        if (handlers == null) init()

        if (previousId != profile.clientid) {
            if (running) DiscordRPC.discordShutdown()

            PresenceOverflow.LOGGER.debug("Running init for DiscordRPC")
            DiscordRPC.discordInitialize(POInstance.longString(profile.clientid), handlers, true)
        }

        if (profile.state == "") {
            PresenceOverflow.LOGGER.error("The state is empty, returning.")
            return
        }

        var state: String = profile.state
        var details: String = profile.details
        var big_image_key: String = profile.big_image_key
        var big_image_caption: String = profile.big_image_caption
        var small_image_key: String = profile.small_image_key
        var small_image_caption: String = profile.small_image_caption

        if (details.equals(Util.getControlHash()))
            details = ""
        if (big_image_key.equals(Util.getControlHash()))
            big_image_key = ""
        if (big_image_caption.equals(Util.getControlHash()))
            big_image_caption = ""
        if (small_image_key.equals(Util.getControlHash()))
            small_image_key = ""
        if (small_image_caption.equals(Util.getControlHash()))
            small_image_caption = ""

        val presence = DiscordRichPresence.Builder(state)

        if (details != "") presence.setDetails(details)
        if (big_image_key != "") presence.setBigImage(big_image_key, big_image_caption)
        if (small_image_key != "") presence.setSmallImage(small_image_key, small_image_caption)

        DiscordRPC.discordUpdatePresence(presence.build())
        PresenceOverflow.LOGGER.debug("Updated presence!")
        //Util.logProfile(profile)
    }
}