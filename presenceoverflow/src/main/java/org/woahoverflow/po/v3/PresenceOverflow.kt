package org.woahoverflow.po.v3

import org.slf4j.LoggerFactory
import org.woahoverflow.po.v3.handle.DiscordHandler
import org.woahoverflow.po.v3.handle.ProfileHandler
import java.util.concurrent.TimeUnit

object PresenceOverflow {
    @JvmStatic
    val LOGGER = LoggerFactory.getLogger("PresenceOverflow")!!

    const val VERSION = "0.3.0"

    @JvmStatic
    var PROFILES = ArrayList<ProfileHandler.Profile>()

    @JvmStatic
    fun refreshProfiles() {
        val startingAmount = PROFILES.size

        PROFILES.clear()
        PROFILES.addAll(ProfileHandler.getProfiles())

        LOGGER.debug("Refreshed profiles! {} => {}", startingAmount, PROFILES.size)
    }

    init {
        refreshProfiles()
    }
}