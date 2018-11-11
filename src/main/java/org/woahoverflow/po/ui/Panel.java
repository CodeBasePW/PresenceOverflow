package org.woahoverflow.po.ui;

import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("all")
public class Panel extends JPanel
{
    public JTextArea detailsValue;
    public JTextArea stateValue;
    public JTextArea bigImageKeyValue;
    public JTextArea bigImageCaption;

    public Panel()
    {
        Font font = new Font("Roboto", Font.PLAIN, 25);

        setLayout(null);
        JLabel detailsText = new JLabel("Details :");
        JLabel stateText = new JLabel("State : ");
        JLabel bigImageKeyText = new JLabel("Big Image Key :");
        JLabel bigImageCaptionText = new JLabel("Big Image Caption :");

        // DETAILS
        detailsText.setFont(font);
        detailsText.setBounds(20, 20, detailsText.getPreferredSize().width, detailsText.getPreferredSize().height);
        add(detailsText);

        detailsValue = new JTextArea();
        detailsValue.setFont(font);
        detailsValue.setText("...");
        detailsValue.setBounds(120, 20, 350, detailsText.getPreferredSize().height);
        add(detailsValue);


        // STATE
        stateText.setFont(font);
        stateText.setBounds(39, 60, stateText.getPreferredSize().width, stateText.getPreferredSize().height);
        add(stateText);

        stateValue = new JTextArea();
        stateValue.setFont(font);
        stateValue.setText("...");
        stateValue.setBounds(120, 60, 350, stateText.getPreferredSize().height);
        add(stateValue);


        // BIG IMAGE KEY
        bigImageKeyText.setFont(font);
        bigImageKeyText.setBounds(20, 100, bigImageKeyText.getPreferredSize().width, bigImageKeyText.getPreferredSize().height);
        add(bigImageKeyText);

        bigImageKeyValue = new JTextArea();
        bigImageKeyValue.setFont(font);
        bigImageKeyValue.setText("...");
        bigImageKeyValue.setBounds(210, 100, 2603, bigImageKeyText.getPreferredSize().height);
        add(bigImageKeyValue);


        // BIG IMAGE CAPTION
        stateText.setFont(font);
        stateText.setBounds(39, 60, stateText.getPreferredSize().width, stateText.getPreferredSize().height);
        add(stateText);

        stateValue = new JTextArea();
        stateValue.setFont(font);
        stateValue.setText("...");
        stateValue.setBounds(120, 60, 350, stateText.getPreferredSize().height);
        add(stateValue);
    }
}
