package raf.dsw.classycraft.app.controller.stateControllers;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;

import java.awt.event.ActionEvent;

public class SelectElementAction extends AbstractClassyAction {

    public SelectElementAction() {
        putValue(SMALL_ICON, loadIcon("/images/kantica.png"));
        putValue(NAME, "Select Element");
        putValue(SHORT_DESCRIPTION, "Select element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
