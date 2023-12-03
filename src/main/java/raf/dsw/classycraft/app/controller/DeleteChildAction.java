package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
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
        if(selected.getClassyNode() instanceof DiagramElement)return;
        MainFrame.getInstance().getClassyTree().deleteChild(selected);
        MainFrame.getInstance().refreshDivider();

        ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).getTreeView().setSelectionRow(0);
    }

}
