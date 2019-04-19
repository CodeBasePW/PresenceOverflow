package org.woahoverflow.po.v3.ui;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import net.arikia.dev.drpc.DiscordRPC;
import org.woahoverflow.po.v3.PresenceRotationThread;
import org.woahoverflow.po.v3.Util;
import org.woahoverflow.po.v3.handle.ProfileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RotationManager extends JFrame
{
    public JList rotationList;
    private JPanel rootPanel;
    private JButton moveUpButton;
    private JButton moveDownButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton refreshButton;
    private JSpinner secondsSpinner;

    public static DefaultListModel model;

    public static ArrayList<ProfileHandler.Profile> getRotation()
    {
        ArrayList<ProfileHandler.Profile> list = new ArrayList<>();
        for (int i = 0; i < model.getSize(); i++)
        {
            list.add((ProfileHandler.Profile)model.get(i));
        }
        return list;
    }

    public RotationManager()
    {
        add(rootPanel);

        setMinimumSize(new Dimension(300, 300));
        setPreferredSize(new Dimension(300, 300));
        setMaximumSize(new Dimension(900, 900));

        model = new DefaultListModel();
        rotationList.setModel(model);
        rotationList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        rotationList.setLayoutOrientation(JList.VERTICAL);
        rotationList.setVisibleRowCount(-1);

        Font roboto = new Font("Roboto Regular", Font.PLAIN, 12);

        moveUpButton.setFont(roboto);
        moveDownButton.setFont(roboto);
        addButton.setFont(roboto);
        removeButton.setFont(roboto);
        refreshButton.setFont(roboto);

        moveUpButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        moveUpButton.setForeground(Color.WHITE);

        moveDownButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        moveDownButton.setForeground(Color.WHITE);

        addButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        addButton.setForeground(Color.WHITE);

        removeButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        removeButton.setForeground(Color.WHITE);

        refreshButton.setBackground(MaterialColors.LIGHT_BLUE_400);
        refreshButton.setForeground(Color.WHITE);

        MaterialUIMovement.add(moveUpButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(moveDownButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(addButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(removeButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);
        MaterialUIMovement.add(refreshButton, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel();
        secondsSpinner.setModel(spinnerModel);

        addButton.addActionListener(e ->
        {
            new SelectProfile(this);
        });

        removeButton.addActionListener(e ->
        {
            int index = rotationList.getSelectedIndex();
            if (index < 0)
                return;
            model.remove(rotationList.getSelectedIndex());
            rotationList.setSelectedIndex(index);
            PresenceRotationThread.setRotation(null, index);
        });

        moveUpButton.addActionListener(e ->
        {
            int index = rotationList.getSelectedIndex();
            if (index <= 0)
                return;
            swapElements(index, index - 1);
            rotationList.setSelectedIndex(index - 1);
        });

        moveDownButton.addActionListener(e ->
        {
            int index = rotationList.getSelectedIndex();
            if (index >= model.getSize() - 1)
                return;
            swapElements(index, index + 1);
            rotationList.setSelectedIndex(index + 1);
        });

        refreshButton.addActionListener(e ->
        {
            try
            {
                PresenceRotationThread.rotationDelay = (int)secondsSpinner.getValue();
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }

            for (int i = 0; i < model.getSize(); i++)
            {
                PresenceRotationThread.setRotation((ProfileHandler.Profile)model.get(i), i);
            }

            boolean empty = true;
            for (ProfileHandler.Profile p : PresenceRotationThread.rotation)
                if (p != null)
                    empty = false;
            if (empty)
                DiscordRPC.discordClearPresence();

            JOptionPane.showMessageDialog(null, "Successfully refreshed the presence rotation.", "Success!", JOptionPane.INFORMATION_MESSAGE);
        });

        if (PresenceRotationThread.rotation.length >= 1)
        {
            for (ProfileHandler.Profile p : PresenceRotationThread.rotation)
            {
                if (p != null)
                    model.addElement(p);
            }
        }

        spinnerModel.setMinimum(1);
        spinnerModel.setMaximum(1000);
        spinnerModel.setValue(PresenceRotationThread.rotationDelay);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void swapElements(int pos1, int pos2)
    {
        Object tmp = model.get(pos1);
        model.set(pos1, model.get(pos2));
        model.set(pos2, tmp);
    }
}
