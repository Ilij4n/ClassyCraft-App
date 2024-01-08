package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractClassyAction{


    public UndoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/undo.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("undo");
        ((ClassyDiagramView)((ClassyPackageView)MainFrame.getInstance().getSplitPane().getRightComponent()).getjTabbedPane().getSelectedComponent()).getCommandManager().undoCommand();
    }

}
