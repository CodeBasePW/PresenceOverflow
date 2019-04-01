package org.woahoverflow.po.v3.handle

import com.mashape.unirest.http.Unirest
import org.woahoverflow.po.v3.PresenceOverflow

/**
 * Manages if the client needs to update
 */
object UpdateHandler {
    @JvmStatic
    fun requiresUpdate(): Boolean =
            Unirest.get("https://api.woahoverflow.org/app/2/update?version=${PresenceOverflow.VERSION}").asJson().body.`object`.getJSONObject("message").getBoolean("contents")

    fun cnaUpdate() {
        if (!requiresUpdate()) return
    }
}