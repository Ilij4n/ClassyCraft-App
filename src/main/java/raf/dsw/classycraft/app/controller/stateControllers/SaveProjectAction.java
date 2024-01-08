package raf.dsw.classycraft.app.controller.stateControllers;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTree;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveProjectAction extends AbstractClassyAction {

    public SaveProjectAction() {
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Save Project/Diagram");
        putValue(SHORT_DESCRIPTION, "Save Project/Diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if ((MainFrame.getInstance().getClassyTree().getSelectednode().getClassyNode() instanceof Project)){

            Project project = (Project) MainFrame.getInstance().getClassyTree().getSelectednode().getClassyNode();
            File projectFile = null;

           /*
            TODO Ovo ce biti za ono da ne moze da se menja ako je isti, videti kako to
            if (!project.isChanged()) {
                return;
            }*/
            //Ovaj deo moze biti koriscen kao saveAs, jer filepath ne postoji tj nije sacuvan nijednom projekat
            if (project.getFilePath() == null || project.getFilePath().isEmpty()) {
                if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    projectFile = jfc.getSelectedFile();
                    project.setFilePath(projectFile.getPath()+".json");
                }
            }
            else{
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("File sacuvan", MessageType.INFO);
            }

            ApplicationFramework.getInstance().getMySerializer().saveProject(project);
            // project.setChanged(false); isto za menjanje vise puta
        }
        else if ((MainFrame.getInstance().getClassyTree().getSelectednode().getClassyNode() instanceof Diagram)) {
            Diagram diagram = (Diagram) MainFrame.getInstance().getClassyTree().getSelectednode().getClassyNode();
            ApplicationFramework.getInstance().getMySerializer().saveDiagram(diagram);
        }

    }

}
