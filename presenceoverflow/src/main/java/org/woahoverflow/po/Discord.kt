package org.woahoverflow.po

import net.arikia.dev.drpc.DiscordEventHandlers
import net.arikia.dev.drpc.DiscordRPC
import net.arikia.dev.drpc.DiscordRichPresence
import org.woahoverflow.po.ui.panel
import java.awt.Color

private var handlers: DiscordEventHandlers? = null
private var running = false
private var previousId: String? = null

fun init() {
    PresenceOverflow.LOGGER.debug("Running init for Discord")
    handlers = DiscordEventHandlers.Builder().setReadyEventHandler {
        PresenceOverflow.LOGGER.debug("User {} is now connected.", it.username)
        panel.statusLabel.text = "User connected"
        panel.statusLabel.foreground = Color.GREEN
    }.setDisconnectedEventHandler { _, st ->
        panel.statusLabel.text = "User disconnected"
        panel.statusLabel.foreground = Color.RED
        PresenceOverflow.LOGGER.debug("User has disconnected. \n{}", st)
    }.build()
}

fun refresh(id: String, profile: Profile) {
    panel.statusLabel.text = "Loading..."
    panel.statusLabel.foreground = Color.YELLOW

    if (handlers == null) init()

    if (previousId != id) {
        if (running) DiscordRPC.discordShutdown()

        PresenceOverflow.LOGGER.debug("Running init for DiscordRPC")
        DiscordRPC.discordInitialize(id, handlers, true)
    }

    if (profile.state == "") {
        PresenceOverflow.LOGGER.debug("The state is empty, returning.")
        panel.statusLabel.text = "Invalid State!"
        panel.statusLabel.foreground = Color.RED
        return
    }

    val presence = DiscordRichPresence.Builder(profile.state)

    if (profile.details != "") presence.setDetails(profile.details)
    if (profile.bigImageKey != "") presence.setBigImage(profile.bigImageKey, profile.bigImageCaption)
    if (profile.smallImageKey != "") presence.setSmallImage(profile.smallImageKey, profile.smallImageCaption)

    DiscordRPC.discordUpdatePresence(presence.build())
    panel.statusLabel.text = "Updated!"
    panel.statusLabel.foreground = Color.GREEN
}