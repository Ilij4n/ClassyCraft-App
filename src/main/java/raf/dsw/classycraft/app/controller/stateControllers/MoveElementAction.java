package raf.dsw.classycraft.app.controller.stateControllers;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;

import java.awt.event.ActionEvent;

public class MoveElementAction extends AbstractClassyAction {

    public MoveElementAction() {
        putValue(SMALL_ICON, loadIcon("/images/kantica.png"));
        putValue(NAME, "Move Element");
        putValue(SHORT_DESCRIPTION, "Move Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
