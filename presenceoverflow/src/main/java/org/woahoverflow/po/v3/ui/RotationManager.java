package org.woahoverflow.po.v3.ui;

import org.woahoverflow.po.v3.PresenceRotationThread;
import org.woahoverflow.po.v3.handle.ProfileHandler;

import javax.swing.*;
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

        model = new DefaultListModel();
        rotationList.setModel(model);
        rotationList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        rotationList.setLayoutOrientation(JList.VERTICAL);
        rotationList.setVisibleRowCount(-1);

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
            for (int i = 0; i < model.getSize(); i++)
            {
                PresenceRotationThread.setRotation((ProfileHandler.Profile)model.get(i), i);
            }
        });

        pack();
        setVisible(true);
    }

    private void swapElements(int pos1, int pos2)
    {
        Object tmp = model.get(pos1);
        model.set(pos1, model.get(pos2));
        model.set(pos2, tmp);
    }
}
