package org.woahoverflow.po.ui;

import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel
{
    public JTextArea detailsValue;
    public JTextArea stateValue;
    public JTextArea bigImageKeyValue;
    public JTextArea bigImageCaption;
    public JTextArea smallImageKeyValue;
    public JTextArea smallImageCaption;
    public JLabel status;
    public JButton refresh;
    public JTextArea appIdValue;
    public JButton go;

    public Panel()
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Font font = new Font("Roboto", Font.PLAIN, 25);

        setLayout(null);
        JLabel detailsText = new JLabel("Details");
        JLabel stateText = new JLabel("State");
        JLabel bigImageKeyText = new JLabel("Big Image Key");
        JLabel bigImageCaptionText = new JLabel("Big Image Caption");
        JLabel smallImageKeyText = new JLabel("Small Image Key");
        JLabel smallImageCaptionText = new JLabel("Small Image Caption");
        JLabel appIdText = new JLabel("App ID");

        // DETAILS
        detailsText.setFont(font);
        detailsText.setBounds(20, 20, detailsText.getPreferredSize().width, detailsText.getPreferredSize().height);
        add(detailsText);

        detailsValue = new JTextArea();
        detailsValue.setFont(font);
        detailsValue.setText("");
        detailsValue.setBounds(280, 20, 350, detailsText.getPreferredSize().height);
        add(detailsValue);


        // STATE
        stateText.setFont(font);
        stateText.setBounds(20, 60, stateText.getPreferredSize().width, stateText.getPreferredSize().height);
        add(stateText);

        stateValue = new JTextArea();
        stateValue.setFont(font);
        stateValue.setText("");
        stateValue.setBounds(280, 60, 350, stateText.getPreferredSize().height);
        add(stateValue);


        // BIG IMAGE KEY
        bigImageKeyText.setFont(font);
        bigImageKeyText.setBounds(20, 100, bigImageKeyText.getPreferredSize().width, bigImageKeyText.getPreferredSize().height);
        add(bigImageKeyText);

        bigImageKeyValue = new JTextArea();
        bigImageKeyValue.setFont(font);
        bigImageKeyValue.setText("");
        bigImageKeyValue.setBounds(280, 100, 350, bigImageKeyText.getPreferredSize().height);
        add(bigImageKeyValue);


        // BIG IMAGE CAPTION
        bigImageCaptionText.setFont(font);
        bigImageCaptionText.setBounds(20, 140, bigImageCaptionText.getPreferredSize().width, bigImageCaptionText.getPreferredSize().height);
        add(bigImageCaptionText);

        bigImageCaption = new JTextArea();
        bigImageCaption.setFont(font);
        bigImageCaption.setText("");
        bigImageCaption.setBounds(280, 140, 350, bigImageCaptionText.getPreferredSize().height);
        add(bigImageCaption);

        // SMALL IMAGE KEY
        smallImageKeyText.setFont(font);
        smallImageKeyText.setBounds(20, 180, smallImageKeyText.getPreferredSize().width, smallImageKeyText.getPreferredSize().height);
        add(smallImageKeyText);

        smallImageKeyValue = new JTextArea();
        smallImageKeyValue.setFont(font);
        smallImageKeyValue.setText("");
        smallImageKeyValue.setBounds(280, 180, 350, smallImageKeyText.getPreferredSize().height);
        add(smallImageKeyValue);


        // SMALL IMAGE CAPTION
        smallImageCaptionText.setFont(font);
        smallImageCaptionText.setBounds(20, 220, smallImageCaptionText.getPreferredSize().width, smallImageCaptionText.getPreferredSize().height);
        add(smallImageCaptionText);

        smallImageCaption = new JTextArea();
        smallImageCaption.setFont(font);
        smallImageCaption.setText("");
        smallImageCaption.setBounds(280, 220, 350, smallImageCaptionText.getPreferredSize().height);
        add(smallImageCaption);

        // application ID
        appIdText.setFont(font);
        appIdText.setBounds(20, 260, appIdText.getPreferredSize().width, appIdText.getPreferredSize().height);
        add(appIdText);

        appIdValue = new JTextArea();
        appIdValue.setFont(font);
        appIdValue.setText("");
        appIdValue.setBounds(280, 260, 350, appIdText.getPreferredSize().height);
        add(appIdValue);

        // STATUS
        status = new JLabel("Enter APP ID");
        status.setFont(font);
        status.setBounds(650, 20, 200, status.getPreferredSize().height);
        add(status);

        // REFRESH
        refresh = new JButton("Reload");
        refresh.setFont(font);
        refresh.setBounds(680, 260, refresh.getPreferredSize().width, refresh.getPreferredSize().height);
        refresh.setVisible(false);
        add(refresh);

        go = new JButton("Go");
        go.setFont(font);
        go.setBounds(680, 260, go.getPreferredSize().width, go.getPreferredSize().height);
        go.setText("Go");
        add(go);
    }
}
