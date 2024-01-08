package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.core.model.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;

public class NewProjectAction extends AbstractClassyAction{

    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectednode();
        boolean pakOrDia = false;
        boolean usao = false;
        if (selected.getClassyNode() instanceof Package) {
            //true je paket, false je diagram
            usao = true;
            switch (options()){
                case 1:pakOrDia = true;break;
                case 2:pakOrDia = false;break;
                case 3:return;
            }
        }
        if(selected.getClassyNode() instanceof Diagram){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Dodajte na dijagram preko menija desno", MessageType.INFO);
            return;
        }

        if(!pakOrDia && usao){
            int dialogResult = JOptionPane.showConfirmDialog(null, "Zelite li da iskoristite neki od sablona?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                JFileChooser jfc = new JFileChooser();
                jfc.setCurrentDirectory(new File("src\\main\\resources\\sabloni\\"));

                if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = jfc.getSelectedFile();
                        System.out.println(file);
                        Diagram dia = ApplicationFramework.getInstance().getMySerializer().loadDiagram(file);
                        System.out.println(dia);
                        dia.setId();
                        dia.setParent(selected.getClassyNode());
                        ((Package) selected.getClassyNode()).addChild(dia);
                        System.out.println("Parent je:"+dia.getParent());
                        ClassyTreeImplementation tree = (ClassyTreeImplementation) MainFrame.getInstance().getClassyTree();
                        tree.loadProject(dia);

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                MainFrame.getInstance().refreshDivider();
                return;
            } else {
                System.out.println("Nista");
                // Perform actions for "No" option
            }
        }


        MainFrame.getInstance().getClassyTree().addChild(selected, pakOrDia);
        MainFrame.getInstance().refreshDivider();
    }

    private int options(){
        // Custom button labels
        Object[] options = {"Paket", "Diagram"};

        // Show a dialog with custom button labels
        int choice = JOptionPane.showOptionDialog(
                null,
                "Os paket il diagram?",
                "Biraj",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        // Ako izabere diagram
        if (choice == JOptionPane.NO_OPTION) {
            return 2;
        } else if(choice == JOptionPane.YES_OPTION){
            //ako izabere paket
            return 1;
        }
        return 3;
    }
}
