package raf.dsw.classycraft.app.gui.swing.tree.controller;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.core.model.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedOn = null;

    private JTextField edit = null;

    public ClassyTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }
    @Override
    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        clickedOn = arg1;
        /*
            arg1 kod nas vrv predstavlja Treenode koji je kliknut
            samim tim mi njega zamenjujemo u ovoj metodi textfieldom sa njegovim
            imenom. Onda na taj textfield dodajemo actionPerformed listener overrideovan dole
            (pogledati opis u Acition listeneru)
         */
        edit = new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }


    @Override
    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3){
                return true;
            }
        return false;
    }


    @Override
    public void actionPerformed(ActionEvent e){
        /*
            proveravamo da li je polje koje menjamo s textboom zapravo maptreeItem
            ako jeste onda ga slobodno kastujemo, i setujemo ime kako pise (nzm zasto je tako)
         */
        if (!(clickedOn instanceof ClassyTreeItem)||((ClassyTreeItem) clickedOn).getClassyNode() instanceof DiagramElement)
            return;

        if(((ClassyTreeItem) clickedOn).getClassyNode() instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Ne menja se ime", MessageType.ERROR);
            return;
        }

        ClassyTreeItem clicked = (ClassyTreeItem) clickedOn;
        for(ClassyNode node : ((ClassyNodeComposite)clicked.getClassyNode().getParent()).getChildren()){
            if(node.getName().equals(e.getActionCommand())){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Ime vec postoji", MessageType.ERROR);
                return;
            }
        }
        //postavlja ime iz textfielda
        String diaName = clicked.getClassyNode().getName();

        clicked.setName(e.getActionCommand());

        if (clicked.getClassyNode() instanceof Project){
            Project projekat = (Project) clicked.getClassyNode();
            projekat.setNameOfProject(projekat);
        }

        else if (clicked.getClassyNode() instanceof Diagram) {
            Diagram diagram = (Diagram)clicked.getClassyNode();
            List<String> list = new ArrayList<>();
            list.add(diaName);
            list.add(diagram.getName());
            ((Package)diagram.getParent()).ChangeNameOfDiagramView(list);
        }
        ClassyTreeView treeView = ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).getTreeView();
        treeView.expandPath(treeView.getSelectionPath());
        //samo izlazi iz edita
        this.stopCellEditing();
    }
}
