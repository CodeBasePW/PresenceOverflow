package org.woahoverflow.po.v3;

import mdlaf.MaterialLookAndFeel;
import org.woahoverflow.po.v3.ui.UI;

import javax.swing.UIManager;
import javax.swing.UIManager.*;

/**
 * The main instance for Presence Overflow.
 *
 * This holds the main function
 *
 * @author sho
 */
public class POInstance {
    public static void main(String... args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                System.out.println("Found L&F: " + info.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try
        {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        new UI();
        //new Profiles();

        keepAlive();
    }

    private static void keepAlive() {
        while (true) {}
    }
}
