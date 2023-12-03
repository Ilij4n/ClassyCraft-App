package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
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

public class DeleteState implements StateInterface{
    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {

        for(int i = c.getPainters().size()-1;i>=0;i--){
            if(c.getPainters().get(i).elementAt(p)){
                ElementPainter painter = c.getPainters().get(i);

                //TODO naci resenje za brisanje da se povuce Model iz painetera
                if(painter instanceof ClassPainter){
                    c.getDiagram().deleteChild(((ClassPainter) painter).getKlasa());
                    ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());
                    //Ova metoda pronalazi treenode koji odgovara selectovanom dijagramu i dodaje mu dete tako sto se rekurzivno krece kroz nas JTREE
                    ClassyTreeItem diagramItem = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),((ClassPainter) painter).getKlasa());
                    //ovo je samo za dodavanje u jtree, u modelu je vec dodat
                    MainFrame.getInstance().getClassyTree().deleteChild(diagramItem);
                    //samo rasiri sve
                    tree.getTreeView().expandPath(new TreePath(diagramItem.getPath()));
                } else if (painter instanceof InterfacePainter) {
                    c.getDiagram().deleteChild(((InterfacePainter) painter).getInterfejs());
                    ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());
                    //Ova metoda pronalazi treenode koji odgovara selectovanom dijagramu i dodaje mu dete tako sto se rekurzivno krece kroz nas JTREE
                    ClassyTreeItem diagramItem = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),((InterfacePainter) painter).getInterfejs());
                    //ovo je samo za dodavanje u jtree, u modelu je vec dodat
                    MainFrame.getInstance().getClassyTree().deleteChild(diagramItem);
                    //samo rasiri sve
                    tree.getTreeView().expandPath(new TreePath(diagramItem.getPath()));
                } else if (painter instanceof EnumPainter) {
                    c.getDiagram().deleteChild(((EnumPainter) painter).getAnEnum());
                    ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());
                    //Ova metoda pronalazi treenode koji odgovara selectovanom dijagramu i dodaje mu dete tako sto se rekurzivno krece kroz nas JTREE
                    ClassyTreeItem diagramItem = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),((EnumPainter) painter).getAnEnum());
                    //ovo je samo za dodavanje u jtree, u modelu je vec dodat
                    MainFrame.getInstance().getClassyTree().deleteChild(diagramItem);
                    //samo rasiri sve
                    tree.getTreeView().expandPath(new TreePath(diagramItem.getPath()));
                }


                c.getPainters().remove(painter);
                break;
            }
        }
    }

    @Override
    public void misKliknut1(Point2D p, ElementCreationView e) {

    }
}

