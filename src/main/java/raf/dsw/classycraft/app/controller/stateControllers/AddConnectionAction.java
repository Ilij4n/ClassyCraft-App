package raf.dsw.classycraft.app.controller.stateControllers;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddConnectionAction extends AbstractClassyAction {

    public AddConnectionAction() {
        putValue(SMALL_ICON, loadIcon("/images/strelica.png"));
        putValue(NAME, "Add Connection");
        putValue(SHORT_DESCRIPTION, "Add Connection");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((ClassyPackageView)MainFrame.getInstance().getSplitPane().getRightComponent()).startAddConnectionState();
    }
}
