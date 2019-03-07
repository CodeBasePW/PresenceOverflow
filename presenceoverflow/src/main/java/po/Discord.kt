package po

import net.arikia.dev.drpc.DiscordEventHandlers
import net.arikia.dev.drpc.DiscordRPC
import net.arikia.dev.drpc.DiscordRichPresence

private var handlers: DiscordEventHandlers? = null
private var running = false
private var previousId: String? = null

fun init() {
    PresenceOverflow.LOGGER.debug("Running init for Discord")
    handlers = DiscordEventHandlers.Builder().setReadyEventHandler {
        PresenceOverflow.LOGGER.debug("User {} is now connected.", it.username)
    }.setDisconnectedEventHandler { _, st ->
        PresenceOverflow.LOGGER.debug("User has disconnected. \n{}", st)
    }.build()
}

fun refresh(id: String, profile: Profile) {
    if (handlers == null) init()

    if (previousId != id) {
        if (running) DiscordRPC.discordShutdown()

        PresenceOverflow.LOGGER.debug("Running init for DiscordRPC")
        DiscordRPC.discordInitialize(id, handlers, true)
    }

    if (profile.state == "") {
        PresenceOverflow.LOGGER.debug("The state is empty, returning.")
        return
    }

    val presence = DiscordRichPresence.Builder(profile.state)

    if (profile.details != "") presence.setDetails(profile.details)
    if (profile.bigImageKey != "") presence.setBigImage(profile.bigImageKey, profile.bigImageCaption)
    if (profile.smallImageKey != "") presence.setSmallImage(profile.smallImageKey, profile.smallImageCaption)

    PresenceOverflow.LOGGER.debug("Updating presence")
    DiscordRPC.discordUpdatePresence(presence.build())
}