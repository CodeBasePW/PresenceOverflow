package org.woahoverflow.po.v3.handle

import net.arikia.dev.drpc.DiscordEventHandlers
import net.arikia.dev.drpc.DiscordRPC
import net.arikia.dev.drpc.DiscordRichPresence
import org.woahoverflow.po.v3.POInstance
import org.woahoverflow.po.v3.PresenceOverflow

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
        }.setDisconnectedEventHandler { _, st ->
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

        val debug = String.format("Updating presence: Profile(%s, %s, %s, %s, %s, %s, %s)",
                profile.clientid,
                profile.details,
                profile.state,
                profile.big_image_key,
                profile.small_image_key,
                profile.big_image_caption,
                profile.small_image_caption)
        PresenceOverflow.LOGGER.debug(debug)

        val presence = DiscordRichPresence.Builder(profile.state)

        if (profile.details != "") presence.setDetails(profile.details)
        if (profile.big_image_key != "") presence.setBigImage(profile.big_image_key, profile.big_image_caption)
        if (profile.small_image_key != "") presence.setSmallImage(profile.small_image_key, profile.small_image_caption)

        DiscordRPC.discordUpdatePresence(presence.build())
        PresenceOverflow.LOGGER.debug("Updated presence!")
    }
}