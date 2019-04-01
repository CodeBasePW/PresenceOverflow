package org.woahoverflow.po;

import org.woahoverflow.po.ui.UIManagerKt;

import java.util.ArrayList;
import java.util.List;

public class PresenceRotationThread implements Runnable
{
    private static List<String> presences = new ArrayList<String>();
    private static int index = -1;

    static
    {
        presences.add("Download now!");
        presences.add("It's great!");
    }

    @Override
    public void run()
    {
        PresenceOverflow.LOGGER.debug("PresenceRotationThread");

        if (index >= presences.size() - 1)
            index = -1;

        DiscordKt.refresh(UIManagerKt.getPanel().clientIdTextArea.getText(), new Profile
        (
            UIManagerKt.getPanel().clientIdTextArea.getText(),
            presences.get(++index),
            UIManagerKt.getPanel().detailsTextArea.getText(),
            UIManagerKt.getPanel().smallImageKeyTextArea.getText(),
            UIManagerKt.getPanel().imageKeyTextArea.getText(),
            UIManagerKt.getPanel().imageCaptionTextArea.getText(),
            UIManagerKt.getPanel().smallImageCaptionTextArea.getText(),
            false
        ));
    }
}
