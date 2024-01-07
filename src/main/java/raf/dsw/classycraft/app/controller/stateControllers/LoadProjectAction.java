package raf.dsw.classycraft.app.controller.stateControllers;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class LoadProjectAction extends AbstractClassyAction {
    public LoadProjectAction() {
        putValue(SMALL_ICON, loadIcon("/images/editovanje.png"));
        putValue(NAME, "Load Project");
        putValue(SHORT_DESCRIPTION, "Load Project");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jfc.getSelectedFile();
                Project p = ApplicationFramework.getInstance().getMySerializer().loadProject(file);

                ClassyTreeImplementation tree = (ClassyTreeImplementation) MainFrame.getInstance().getClassyTree();
                tree.loadProject(p);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
