package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.ExitAction;
import raf.dsw.classycraft.app.controller.NewProjectAction;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MyToolBar extends JToolBar {
    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);
        add(MainFrame.getInstance().getActionManager().getExitAction());
        add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        add(MainFrame.getInstance().getActionManager().getDeleteChildAction());
        add(MainFrame.getInstance().getActionManager().getAuthorChangeAction());
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
        add(MainFrame.getInstance().getActionManager().getLoadProjectAction());
        add(MainFrame.getInstance().getActionManager().getExportSlikaAction());
    }
}
