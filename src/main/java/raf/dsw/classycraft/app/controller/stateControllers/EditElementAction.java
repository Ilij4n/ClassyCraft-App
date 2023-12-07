package raf.dsw.classycraft.app.controller.stateControllers;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class EditElementAction extends AbstractClassyAction {

    public EditElementAction() {
        putValue(SMALL_ICON, loadIcon("/images/editovanje.png"));
        putValue(NAME, "Edit Element");
        putValue(SHORT_DESCRIPTION, "Edit Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((ClassyPackageView) MainFrame.getInstance().getSplitPane().getRightComponent()).startEditState();
    }
}
