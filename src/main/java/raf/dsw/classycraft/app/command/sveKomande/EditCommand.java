package raf.dsw.classycraft.app.command.sveKomande;

import raf.dsw.classycraft.app.command.AbstractCommand;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.ClassContent;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.util.Set;

public class EditCommand extends AbstractCommand {
    private ClassyDiagramView c;
    private DiagramElement diagramElementOld;
    private DiagramElement diagramElementNew;

    public EditCommand(ClassyDiagramView classyDiagramView, DiagramElement old,DiagramElement news){
       this.diagramElementOld = old;
       this.diagramElementNew = news;
       this.c = classyDiagramView;

    }
    @Override
    public void doCommand() {

        ClassyTreeImplementation tree = (ClassyTreeImplementation) MainFrame.getInstance().getClassyTree();
        ClassyTreeItem item = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(), diagramElementNew);
        item.setName(diagramElementNew.getName());

        SwingUtilities.updateComponentTreeUI(tree.getTreeView());
        c.repaint();
    }

    @Override
    public void undoCommand() {

        ClassyTreeImplementation tree = (ClassyTreeImplementation) MainFrame.getInstance().getClassyTree();
        ClassyTreeItem item = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(), diagramElementOld);
        item.setName(diagramElementOld.getName());
        if(diagramElementOld instanceof InterClass){
            ((InterClass)item.getClassyNode()).setContentSet(((InterClass) diagramElementOld).getContentSet());
        }else if(diagramElementOld instanceof Connection){
            ((Connection)item.getClassyNode()).setKardinalnost(((Connection) diagramElementOld).getKardinalnost());
        }
        SwingUtilities.updateComponentTreeUI(tree.getTreeView());
        c.repaint();
    }
}
