package raf.dsw.classycraft.app.command.sveKomande;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.command.AbstractCommand;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Enum;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;
import raf.dsw.classycraft.app.gui.swing.painters.ClassPainter;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.painters.EnumPainter;
import raf.dsw.classycraft.app.gui.swing.painters.InterfacePainter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.tree.TreePath;
import java.awt.geom.Point2D;

public class AddElementCommand extends AbstractCommand {

    private ClassyDiagramView c;
    private InterClass i;
    private ElementPainter elementPainter;

    private ClassyTreeItem diagramItem;



    public AddElementCommand(ClassyDiagramView classyDiagramView,InterClass i,ElementPainter elementPainter,ClassyTreeItem diagramItem){
        this.c = classyDiagramView;
        this.i = i;
        this.elementPainter = elementPainter;
        this.diagramItem = diagramItem;
    }

    @Override
    public void doCommand() {
        if(!c.getDiagram().getChildren().contains(i)){
            c.getDiagram().addChild(i);
            ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());
            //Ova metoda pronalazi treenode koji odgovara selectovanom dijagramu i dodaje mu dete tako sto se rekurzivno krece kroz nas JTREE
            diagramItem = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),c.getDiagram());
            //ovo je samo za dodavanje u jtree, u modelu je vec dodat
            c.getPainters().add(elementPainter);
            MainFrame.getInstance().getClassyTree().addChild(diagramItem,false);
            //samo rasiri sve
            tree.getTreeView().expandPath(new TreePath(diagramItem.getPath()));
            System.out.println(c.getDiagram().getChildren());
        }
    }

    @Override
    public void undoCommand() {
        MainFrame.getInstance().getClassyTree().deleteChild(((ClassyTreeItem)diagramItem.getLastChild()));
        c.getDiagram().deleteChild(elementPainter.getDiagramElement());
        c.getPainters().remove(elementPainter);
        c.repaint();
    }

}
