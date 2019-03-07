package po.ui

import po.*
import po.site.loggedIn
import po.site.login
import po.site.logout
import po.site.username
import java.awt.Color
import java.awt.SystemTray
import java.awt.event.ActionListener
import java.awt.image.BufferedImage
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.WindowConstants

/**
 * The main panel
 */
val panel: Panel = Panel()

/**
 * The main jframe
 */
val jFrame: JFrame = JFrame()

var usingTray: Boolean = false

/**
 * The sign in stuff
 */
val signIn: SignIn = SignIn()
val signInFrame: JFrame = JFrame()

/**
 * if it's using the tray
 */
private fun usingTray(): Boolean = usingTray

/**
 * Initializes the UI
 */
fun init() {
    if (!SystemTray.isSupported()) {
        PresenceOverflow.LOGGER.debug("Ignoring SystemTray")
    } else {
        usingTray = true
        TrayMenu.init()
    }

    val actionListener = ActionListener {
        if (usingTray()) TrayMenu.exit()

        System.exit(1)
    }

    TrayMenu.EXIT.addActionListener(actionListener)
    panel.exit.addActionListener(actionListener)

    val profileOne = getProfile(0)!!
    val profileTwo = getProfile(1)!!
    val profileThree = getProfile(2)!!

    // If the config states they're using one, mark it & use it
    if (profileOne.using) {
        panel.profileOneLabel.foreground = Color.GREEN
        useProfile(0)
    }
    else panel.profileOneLabel.foreground = Color.RED

    if (profileTwo.using) {
        panel.profileTwoLabel.foreground = Color.GREEN
        useProfile(1)
    }
    else panel.profileTwoLabel.foreground = Color.RED

    if (profileThree.using) {
        panel.profileThreeLabel.foreground = Color.GREEN
        useProfile(2)
    }
    else panel.profileThreeLabel.foreground = Color.RED

    // Saves the current configuration to profile one
    panel.saveToOne.addActionListener {
        panel.profileOneLabel.foreground = Color.YELLOW

        profileOne.state = panel.stateTextArea.text
        profileOne.details = panel.detailsTextArea.text
        profileOne.bigImageCaption = panel.imageCaptionTextArea.text
        profileOne.smallImageCaption = panel.smallImageCaptionTextArea.text
        profileOne.smallImageKey = panel.smallImageKeyTextArea.text
        profileOne.bigImageKey = panel.imageKeyTextArea.text
        profileOne.id = panel.clientIdTextArea.text

        updateProfile(0, profileOne)
        panel.profileOneLabel.foreground = Color.GREEN
        panel.profileThreeLabel.foreground = Color.RED
        panel.profileTwoLabel.foreground = Color.RED
    }

    // Saves the current configuration to profile two
    panel.saveToTwo.addActionListener {
        panel.profileTwoLabel.foreground = Color.YELLOW

        profileTwo.state = panel.stateTextArea.text
        profileTwo.details = panel.detailsTextArea.text
        profileTwo.bigImageCaption = panel.imageCaptionTextArea.text
        profileTwo.smallImageCaption = panel.smallImageCaptionTextArea.text
        profileTwo.smallImageKey = panel.smallImageKeyTextArea.text
        profileTwo.bigImageKey = panel.imageKeyTextArea.text
        profileOne.id = panel.clientIdTextArea.text

        updateProfile(1, profileTwo)
        panel.profileOneLabel.foreground = Color.RED
        panel.profileThreeLabel.foreground = Color.RED
        panel.profileTwoLabel.foreground = Color.GREEN
    }

    // Saves the current configuration to profile three
    panel.saveToThree.addActionListener {
        panel.profileThreeLabel.foreground = Color.YELLOW

        profileThree.state = panel.stateTextArea.text
        profileThree.details = panel.detailsTextArea.text
        profileThree.bigImageCaption = panel.imageCaptionTextArea.text
        profileThree.smallImageCaption = panel.smallImageCaptionTextArea.text
        profileThree.smallImageKey = panel.smallImageKeyTextArea.text
        profileThree.bigImageKey = panel.imageKeyTextArea.text
        profileOne.id = panel.clientIdTextArea.text

        updateProfile(2, profileThree)
        panel.profileOneLabel.foreground = Color.RED
        panel.profileThreeLabel.foreground = Color.GREEN
        panel.profileTwoLabel.foreground = Color.RED
    }

    // If they're loading a profile, use it
    panel.profileOneLoad.addActionListener {
        panel.profileOneLabel.foreground = Color.GREEN
        panel.profileThreeLabel.foreground = Color.RED
        panel.profileTwoLabel.foreground = Color.RED

        useProfile(0)
    }
    panel.profileTwoLoad.addActionListener {
        panel.profileOneLabel.foreground = Color.RED
        panel.profileThreeLabel.foreground = Color.RED
        panel.profileTwoLabel.foreground = Color.GREEN

        useProfile(1)
    }
    panel.profileThreeLoad.addActionListener {
        panel.profileOneLabel.foreground = Color.RED
        panel.profileThreeLabel.foreground = Color.GREEN
        panel.profileTwoLabel.foreground = Color.RED

        useProfile(2)
    }

    // If they're attempting to sign in
    panel.signIn.addActionListener {
        signInFrame.isVisible = true
    }

    signIn.exitButton.addActionListener {
        signInFrame.isVisible = false
        signIn.passwordField.text = ""
        signIn.usernameField.text = ""
    }

    panel.reload.addActionListener {
        refresh(
                panel.clientIdTextArea.text,
                Profile(
                        panel.clientIdTextArea.text,
                        panel.stateTextArea.text,
                        panel.detailsTextArea.text,
                        panel.smallImageKeyTextArea.text,
                        panel.imageKeyTextArea.text,
                        panel.imageCaptionTextArea.text,
                        panel.smallImageCaptionTextArea.text,
                        false
                )
        )
    }

    val image: BufferedImage
    try {
        val url = URL("https://assets.woahoverflow.org/favicon/dark-small.png")
        val con = url.openConnection()
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0 Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0")
        image = ImageIO.read(con.getInputStream())
    } catch (ex: Exception) {
        ex.printStackTrace()
        return
    }

    signIn.signInButton.addActionListener {
        if (loggedIn) {
            logout()
        } else {
            val user = signIn.usernameField.text
            val password = String(signIn.passwordField.password)

            PresenceOverflow.LOGGER.debug("Signing in with $user and $password...")

            if (login(user, password)) {
                signInFrame.isVisible = false
                signIn.status.isVisible = false
                panel.loggedIn.text = "Signed in as ${username!!}"
                panel.signIn.text = "Log out"
            }
        }
    }

    signInFrame.add(signIn)
    signInFrame.iconImage = ImageIcon(image).image
    signInFrame.title = "Sign In"
    signInFrame.setSize(227, 276)
    signInFrame.isVisible = false
    signInFrame.isResizable = false

    jFrame.title = "PresenceOverflow"
    jFrame.defaultCloseOperation = WindowConstants.HIDE_ON_CLOSE
    jFrame.add(panel)
    jFrame.iconImage = ImageIcon(image).image
    jFrame.setSize(574, 320)
    jFrame.isVisible = true
    jFrame.isResizable = false
}