package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExportSlikaAction extends AbstractClassyAction{

    public ExportSlikaAction(){
        //bitno
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/slidza.png"));
        putValue(NAME, "Exit");
        putValue(SHORT_DESCRIPTION, "Exit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getSplitPane().getRightComponent() instanceof ClassyPackageView){
            JTabbedPane pane = ((ClassyPackageView) MainFrame.getInstance().getSplitPane().getRightComponent()).getjTabbedPane();
            if(pane.getSelectedComponent() != null && pane.getSelectedComponent() instanceof ClassyDiagramView){
                ((ClassyDiagramView) pane.getSelectedComponent()).exportPanelAsImage();
            }
        }
    }
}
