package raf.dsw.classycraft.app.gui.swing.tree.controller;

import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLOutput;

public class ClassyTreeCellOpen implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2&&((ClassyTreeItem)MainFrame.getInstance().getProjectExplorerTree().getLastSelectedPathComponent()).getClassyNode() instanceof Package) {
                MainFrame.getInstance().getSplitPane()
                        .setRightComponent(((Package) ((ClassyTreeItem) MainFrame.getInstance().getProjectExplorerTree().
                                getLastSelectedPathComponent()).getClassyNode()).getClassyPackageView());
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
