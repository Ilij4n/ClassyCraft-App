package raf.dsw.classycraft.app.controller.stateControllers;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;

import java.awt.event.ActionEvent;

public class EditElementAction extends AbstractClassyAction {

    public EditElementAction() {
        putValue(SMALL_ICON, loadIcon("/images/kantica.png"));
        putValue(NAME, "Edit Element");
        putValue(SHORT_DESCRIPTION, "Edit Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
