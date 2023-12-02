package raf.dsw.classycraft.app.controller.stateControllers;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;

import java.awt.event.ActionEvent;

public class DeleteElementAction extends AbstractClassyAction {

    public DeleteElementAction() {
        putValue(SMALL_ICON, loadIcon("/images/kantica.png"));
        putValue(NAME, "Delete Element");
        putValue(SHORT_DESCRIPTION, "Delete Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
