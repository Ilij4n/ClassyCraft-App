package raf.dsw.classycraft.app.controller.stateControllers;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;

import java.awt.event.ActionEvent;

public class AddConnectionAction extends AbstractClassyAction {

    public AddConnectionAction() {
        putValue(SMALL_ICON, loadIcon("/images/kantica.png"));
        putValue(NAME, "Add Connection");
        putValue(SHORT_DESCRIPTION, "Add Connection");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
