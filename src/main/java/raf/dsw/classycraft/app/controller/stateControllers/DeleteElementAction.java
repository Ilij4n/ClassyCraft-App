package raf.dsw.classycraft.app.controller.stateControllers;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class DeleteElementAction extends AbstractClassyAction {

    public DeleteElementAction() {
        putValue(SMALL_ICON, loadIcon("/images/kantica.png"));
        putValue(NAME, "Delete Element");
        putValue(SHORT_DESCRIPTION, "Delete Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((ClassyPackageView) MainFrame.getInstance().getSplitPane().getRightComponent()).startDeleteState();
    }
}
