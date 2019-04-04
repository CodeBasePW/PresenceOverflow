package org.woahoverflow.po.v3.ui;

import javax.swing.*;
import java.util.Random;

public class RotationManager extends JFrame
{
    private JList rotationList;
    private JPanel rootPanel;
    private JButton moveUpButton;
    private JButton moveDownButton;
    private JButton addButton;
    private JButton removeButton;

    private DefaultListModel model;

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
            rotationList.setSelectedIndex(0);
            model.addElement("debug-" + new Random().nextInt(1000));
        });

        removeButton.addActionListener(e ->
        {
            int index = rotationList.getSelectedIndex();
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
