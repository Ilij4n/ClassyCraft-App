package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

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
        if (selected.getClassyNode() instanceof Package) {
            //true je paket, false je diagram
            switch (options()){
                case 1:pakOrDia = true;break;
                case 2:pakOrDia = false;break;
                case 3:return;
            }
        }
        if(selected.getClassyNode() instanceof Diagram){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Dodajte na dijagram preko menija levo", MessageType.INFO);
            return;
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
