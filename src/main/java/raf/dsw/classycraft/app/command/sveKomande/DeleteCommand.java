package raf.dsw.classycraft.app.command.sveKomande;

import raf.dsw.classycraft.app.command.AbstractCommand;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.state.DeleteState;

import javax.swing.tree.TreePath;
import java.util.ArrayList;

public class DeleteCommand extends AbstractCommand {
    private ClassyDiagramView c;
    private ArrayList<DiagramElement> vezeDaSeObrisu;

    private ArrayList<ElementPainter> oni;


    public DeleteCommand(ClassyDiagramView classyDiagramView,ArrayList<DiagramElement> vezeDaSeObrisu){
        this.c = classyDiagramView;
        this.vezeDaSeObrisu = vezeDaSeObrisu;
        this.oni = (ArrayList<ElementPainter>) c.getSviselectovani();
    }

    @Override
    public void doCommand() {
        DeleteState.brisanjeZaVise(c,vezeDaSeObrisu);
    }

    @Override
    public void undoCommand() {
        ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());
        for(ElementPainter elementPainter:oni){
            c.getDiagram().getChildren().add(elementPainter.getDiagramElement());
            c.getPainters().add(elementPainter);
            elementPainter.getDiagramElement().addSub(c);
             ClassyTreeItem diagramItem = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),c.getDiagram());
            //ovo je samo za dodavanje u jtree, u modelu je vec dodat
            MainFrame.getInstance().getClassyTree().addChild(diagramItem,false);
            //samo rasiri sve
            tree.getTreeView().expandPath(new TreePath(diagramItem.getPath()));
            c.repaint();
        }
    }
}
