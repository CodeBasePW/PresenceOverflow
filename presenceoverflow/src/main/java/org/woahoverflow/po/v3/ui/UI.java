package org.woahoverflow.po.v3.ui;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame
{
    private JPanel rootPanel;
    private JButton exitButton;
    private JButton reloadButton;
    private JTextField clientIDTextField;
    private JTextField detailsTextField;
    private JTextField stateTextField;
    private JTextField bigImageKeyTextField;
    private JTextField smallImageKeyTextField;
    private JTextField bigImageCaptionTextField;
    private JTextField smallImageCaptionTextField;
    private JTextField importPIDTextField;
    private JTextField getPIDTextField;
    private JButton loadButton;
    private JButton saveButton;
    private JButton load2Button;
    private JButton load3Button;
    private JButton save2Button;
    private JButton save3Button;
    private JLabel clientIDLabel;
    private JLabel detailsLabel;
    private JLabel stateLabel;
    private JLabel bigImageKeyLabel;
    private JLabel smallImageKeyLabel;
    private JLabel bigImageCaptionLabel;
    private JLabel smallImageCaptionLabel;
    private JLabel importPIDLabel;
    private JLabel getPIDLabel;
    private JLabel oneLabel;
    private JLabel twoLabel;
    private JLabel threeLabel;
    private JLabel profilesLabel;

    public UI() {
        add(rootPanel);

        setTitle("PresenceOverflowUI");
        pack();

        Font roboto = new Font("Roboto Regular", Font.PLAIN, 12);
        clientIDLabel.setFont(roboto);
        detailsLabel.setFont(roboto);
        stateLabel.setFont(roboto);
        bigImageKeyLabel.setFont(roboto);
        smallImageKeyLabel.setFont(roboto);
        bigImageCaptionLabel.setFont(roboto);
        smallImageCaptionLabel.setFont(roboto);
        importPIDLabel.setFont(roboto);
        getPIDLabel.setFont(roboto);
        oneLabel.setFont(roboto);
        twoLabel.setFont(roboto);
        threeLabel.setFont(roboto);
        profilesLabel.setFont(roboto);
        clientIDTextField.setFont(roboto);
        detailsTextField.setFont(roboto);
        stateTextField.setFont(roboto);
        bigImageKeyTextField.setFont(roboto);
        smallImageKeyTextField.setFont(roboto);
        bigImageCaptionTextField.setFont(roboto);
        smallImageCaptionTextField.setFont(roboto);
        importPIDTextField.setFont(roboto);
        getPIDTextField.setFont(roboto);
        loadButton.setFont(roboto);
        load2Button.setFont(roboto);
        load3Button.setFont(roboto);
        saveButton.setFont(roboto);
        save2Button.setFont(roboto);
        save3Button.setFont(roboto);
        exitButton.setFont(roboto);
        reloadButton.setFont(roboto);

        loadButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        loadButton.setForeground(Color.WHITE);

        load2Button.setBackground(MaterialColors.LIGHT_BLUE_400);
        load2Button.setForeground(Color.WHITE);

        load3Button.setBackground(MaterialColors.LIGHT_BLUE_400);
        load3Button.setForeground(Color.WHITE);

        saveButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        saveButton.setForeground(Color.WHITE);

        save2Button.setBackground(MaterialColors.LIGHT_BLUE_400);
        save2Button.setForeground(Color.WHITE);

        save3Button.setBackground(MaterialColors.LIGHT_BLUE_400);
        save3Button.setForeground(Color.WHITE);

        exitButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        exitButton.setForeground(Color.WHITE);

        reloadButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        reloadButton.setForeground(Color.WHITE);

        MaterialUIMovement.add(loadButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(saveButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(exitButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(reloadButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);

        setVisible(true);
    }

    public void setData(UI data) {
    }

    public void getData(UI data) {
    }

    public boolean isModified(UI data) {
        return false;
    }
}