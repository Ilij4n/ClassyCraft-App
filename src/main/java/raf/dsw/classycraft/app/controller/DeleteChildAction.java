package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class DeleteChildAction extends AbstractClassyAction{

    public DeleteChildAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/kantica.png"));
        putValue(NAME, "Delete Project");
        putValue(SHORT_DESCRIPTION, "Delete Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectednode();
        MainFrame.getInstance().getClassyTree().deleteChild(selected);
        MainFrame.getInstance().refreshDivider();
        /*TODO :Svaki put kad se child obrise selected item bi trebalo da postane parent,
            za sad se samo vraca na explorer. Valjalo bi da se uvede do observera sa tabbedPaneom...
         */
        ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).getTreeView().setSelectionRow(0);
    }

}
