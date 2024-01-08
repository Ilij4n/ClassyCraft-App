package raf.dsw.classycraft.app.command.sveKomande;

import raf.dsw.classycraft.app.command.AbstractCommand;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.ClassContent;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.util.Set;

public class EditCommand extends AbstractCommand {
    private ClassyDiagramView c;
    private ElementCreationView e;
    private DiagramElement diagramElement;
    private  String name;
    private String content;
    private Set<ClassContent> classContents;
    public EditCommand(ClassyDiagramView classyDiagramView, ElementCreationView e, String name ,String content , Set<ClassContent>classContents, DiagramElement diagramElement){
        this.c = classyDiagramView;
        this.e = e;
        this.diagramElement = diagramElement;
        this.name = name;
        this.content = content;
        this.classContents = classContents;
    }
    @Override
    public void doCommand() {

        ClassyTreeImplementation tree = (ClassyTreeImplementation) MainFrame.getInstance().getClassyTree();
        ClassyTreeItem item = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(), diagramElement);
        item.setName(e.getTfImeElementa().getText());

        SwingUtilities.updateComponentTreeUI(tree.getTreeView());
        c.repaint();
    }

    @Override
    public void undoCommand() {

        ClassyTreeImplementation tree = (ClassyTreeImplementation) MainFrame.getInstance().getClassyTree();
        ClassyTreeItem item = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(), diagramElement);
        item.setName(name);
        if(diagramElement instanceof InterClass){
            ((InterClass) diagramElement).setContentSet(classContents);
        }else if(diagramElement instanceof Connection){
            ((Connection) diagramElement).setKardinalnost(content);
        }
        SwingUtilities.updateComponentTreeUI(tree.getTreeView());
        c.repaint();
    }
}
