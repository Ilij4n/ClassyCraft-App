package raf.dsw.classycraft.app.command.sveKomande;

import raf.dsw.classycraft.app.command.AbstractCommand;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.gui.swing.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.tree.TreePath;

public class AddConnectionCommand extends AbstractCommand {
    private ConnectionPainter connectionPainter;
    private ClassyDiagramView c;
    private Connection connection;
    private ClassyTreeItem diagramNode;

    public AddConnectionCommand(ClassyDiagramView classyDiagramView, ConnectionPainter connectionPainter, Connection connection){
        c = classyDiagramView;
        this.connectionPainter = connectionPainter;
        this.connection = connection;
    }
    @Override
    public void doCommand() {
        c.getDiagram().addChild(connection);
        ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());
        //Ova metoda pronalazi treenode koji odgovara selectovanom dijagramu i dodaje mu dete tako sto se rekurzivno krece kroz nas JTREE
        diagramNode = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),c.getDiagram());
        //ovo je samo za dodavanje u jtree, u modelu je vec dodat
        c.getPainters().add(connectionPainter);
        MainFrame.getInstance().getClassyTree().addChild(diagramNode,false);
        //samo rasiri sve
        tree.getTreeView().expandPath(new TreePath(diagramNode.getPath()));
        System.out.println(c.getDiagram().getChildren());
    }

    @Override
    public void undoCommand() {
        MainFrame.getInstance().getClassyTree().deleteChild((ClassyTreeItem) diagramNode.getLastChild());
        c.getDiagram().deleteChild(connection);
        c.getPainters().remove(connectionPainter);
        c.repaint();
    }
}

