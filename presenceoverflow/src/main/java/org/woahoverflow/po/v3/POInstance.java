package org.woahoverflow.po.v3;

import org.woahoverflow.po.v3.ui.Profiles;
import org.woahoverflow.po.v3.ui.UI;

/**
 * The main instance for Presence Overflow.
 *
 * This holds the main function
 *
 * @author sho
 */
public class POInstance {
    public static void main(String... args) {
        new UI();
        new Profiles();

        keepAlive();
    }

    private static void keepAlive() {
        while (true) {}
    }
}
