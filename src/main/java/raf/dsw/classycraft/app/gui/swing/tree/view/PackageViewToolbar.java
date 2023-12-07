package raf.dsw.classycraft.app.gui.swing.tree.view;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;

public class PackageViewToolbar extends JToolBar {
    public PackageViewToolbar() {
        super(SwingConstants.VERTICAL);
        setFloatable(false);
        setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
        add(MainFrame.getInstance().getActionManager().getAddInterClassAction());
        add(Box.createVerticalStrut(10));
        add(MainFrame.getInstance().getActionManager().getAddConnectionAction());
        add(Box.createVerticalStrut(10));
        add(MainFrame.getInstance().getActionManager().getDeleteElementAction());
        add(Box.createVerticalStrut(10));
        add(MainFrame.getInstance().getActionManager().getSelectElementAction());
        add(Box.createVerticalStrut(10));
        add(MainFrame.getInstance().getActionManager().getMoveElementAction());
        add(Box.createVerticalStrut(10));
        add(MainFrame.getInstance().getActionManager().getZoomInOutAction());
        add(Box.createVerticalStrut(10));
        add(MainFrame.getInstance().getActionManager().getEditElementAction());
        add(Box.createVerticalStrut(10));
        add(MainFrame.getInstance().getActionManager().getDuplicateAction());
    }
}
