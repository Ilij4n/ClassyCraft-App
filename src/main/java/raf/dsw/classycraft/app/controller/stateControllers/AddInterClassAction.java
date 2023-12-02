package raf.dsw.classycraft.app.controller.stateControllers;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.core.model.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddInterClassAction extends AbstractClassyAction {

    public AddInterClassAction() {
        putValue(SMALL_ICON, loadIcon("/images/kantica.png"));
        putValue(NAME, "Add InterClass");
        putValue(SHORT_DESCRIPTION, "Add InterClass");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((ClassyPackageView)MainFrame.getInstance().getSplitPane().getRightComponent()).startAddElementState();
    }
}
