package raf.dsw.classycraft.app.controller.stateControllers;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;

import java.awt.event.ActionEvent;

public class ZoomInOutAction extends AbstractClassyAction {

    public ZoomInOutAction() {
        putValue(SMALL_ICON, loadIcon("/images/kantica.png"));
        putValue(NAME, "Zoom in/out");
        putValue(SHORT_DESCRIPTION, "Zoom in/out");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
