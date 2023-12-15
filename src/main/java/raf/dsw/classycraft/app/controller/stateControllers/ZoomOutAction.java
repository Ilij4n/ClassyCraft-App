package raf.dsw.classycraft.app.controller.stateControllers;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomOutAction extends AbstractClassyAction {
    public ZoomOutAction() {
        putValue(SMALL_ICON, loadIcon("/images/odZum.png"));
        putValue(NAME, "Zoom out");
        putValue(SHORT_DESCRIPTION, "Zoom out");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ((ClassyPackageView) MainFrame.getInstance().getSplitPane().getRightComponent()).startZoomOutState();
    }
}
