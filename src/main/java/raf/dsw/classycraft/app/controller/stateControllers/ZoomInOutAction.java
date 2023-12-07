package raf.dsw.classycraft.app.controller.stateControllers;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomInOutAction extends AbstractClassyAction {

    public ZoomInOutAction() {
        putValue(SMALL_ICON, loadIcon("/images/zum.png"));
        putValue(NAME, "Zoom in/out");
        putValue(SHORT_DESCRIPTION, "Zoom in/out");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((ClassyPackageView) MainFrame.getInstance().getSplitPane().getRightComponent()).startZoomInOutState();
    }
}
