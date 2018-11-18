package org.woahoverflow.po.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ConcurrentHashMap;

import static org.woahoverflow.po.Discord.initDiscord;
import static org.woahoverflow.po.Discord.refresh;

public class UIHandler
{
    private Panel panel = new Panel();
    private JFrame frame = new JFrame("PresenceOverflow");
    public UIHandler()
    {
        try {
            URLConnection con = new URL("https://raw.githubusercontent.com/woahoverflow/PresenceOverflow/master/icon.png").openConnection();
            frame.setIconImage(ImageIO.read(con.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setSize(850, 340);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.go.addActionListener((ev) -> {
            if (panel.appIdValue.getText().equals(""))
            {
                panel.status.setText("Invalid App ID!");
                return;
            }
            panel.refresh.setVisible(true);
            panel.go.setVisible(false);
            initDiscord();
            refresh();
        });
        panel.refresh.addActionListener((ev) -> refresh());
    }

    public ConcurrentHashMap<String, String> getValues()
    {
        ConcurrentHashMap<String, String> hash = new ConcurrentHashMap<>();
        hash.put("state", panel.stateValue.getText());
        hash.put("details", panel.detailsValue.getText());
        hash.put("app_id", panel.appIdValue.getText());
        hash.put("big_image_key", panel.bigImageKeyValue.getText());
        hash.put("small_image_key", panel.smallImageKeyValue.getText());
        hash.put("big_image_caption", panel.bigImageCaption.getText());
        hash.put("small_image_caption", panel.smallImageCaption.getText());
        return hash;
    }

    public void updateStatus(String status)
    {
        panel.status.setText(status);
    }
}
