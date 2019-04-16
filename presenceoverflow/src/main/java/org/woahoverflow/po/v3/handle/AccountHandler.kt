package org.woahoverflow.po.v3.handle

import com.mashape.unirest.http.Unirest
import org.slf4j.LoggerFactory
import org.woahoverflow.po.v3.PresenceOverflow

object AccountHandler {
    var token: String? = null
    var loggedIn: Boolean = false
    var vip: Boolean = false
    var name: String = ""
    var id: Long = 0L

    init {
        // TODO remove
        //PresenceOverflow.LOGGER.debug(login("X", "X") + " : Login Result")
    }
    /**
     * Logs in and unlocks some features
     *
     * Returning NULL means success
     */
    fun login(user: String, password: String): Boolean? {
        LoggerFactory.getLogger("PresenceOverflow").debug("performing login: $user, <REDACTED>")

        when {
            user.length >= 30 || 3 > user.length -> return false
            password.length >= 1000 || 8 > password.length -> return false
        }

        val resp = Unirest.post("https://api.woahoverflow.org/account/login")
                .field("username", user)
                .field("password", password)
                .asJson()

        if (resp.status != 200) return false

        token = resp.body.`object`.getJSONObject("message").getJSONObject("contents").getString("token")
        vip = resp.body.`object`.getJSONObject("message").getJSONObject("contents").getJSONObject("scope").has("vip")

        PresenceOverflow.LOGGER.debug("User VIP: $vip")

        val username = Unirest.get("https://api.woahoverflow.org/account/get")
                .queryString("token", token)
                .queryString("value", "username/id")
                .asJson()


        name = username.body.`object`.getJSONObject("message").getJSONObject("contents").getString("username")
        id = username.body.`object`.getJSONObject("message").getJSONObject("contents").getLong("id")
        loggedIn = true

        PresenceOverflow.LOGGER.debug("token: $token")

        return true
    }

    /**
     * Log out of the client
     */
    fun logout() {
        token = null
        loggedIn = false
    }
}