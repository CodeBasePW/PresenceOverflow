package po.site

import com.mashape.unirest.http.Unirest
import po.ui.panel
import po.ui.signIn

var token: String? = null
var loggedIn: Boolean = false
var username: String? = null

fun login(user: String, password: String): Boolean {
    if (user.length >= 30 || 3 > user.length) {
        signIn.status.text = "Invalid username!"
        signIn.status.isVisible = true
        return false
    }

    if (password.length >= 1000 || 8 > password.length) {
        signIn.status.text = "Invalid password!"
        signIn.status.isVisible = true
        return false
    }

    val resp = Unirest.post("https://api.woahoverflow.org/account/login")
            .field("username", user)
            .field("password", password)
            .asJson()

    if (resp.status != 200) {
        signIn.status.text = "Invalid username or password!"
        signIn.status.isVisible = true
        return false
    }

    val content = resp.body.`object`.getJSONObject("message").getJSONObject("contents")

    token = content.getString("token")

    val resp2 = Unirest.get("https://api.woahoverflow.org/account/get")
            .queryString("value", "username")
            .queryString("token", token)
            .asJson()

    username = resp2.body.`object`.getJSONObject("message").getJSONObject("contents").getString("username")
    return true
}

fun logout() {
    token = null
    username = null
    loggedIn = false

    panel.loggedIn.text = "Currently signed out"
}