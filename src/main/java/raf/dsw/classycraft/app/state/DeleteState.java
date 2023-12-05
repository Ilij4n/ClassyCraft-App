package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.gui.swing.painters.*;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.tree.TreePath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class DeleteState implements StateInterface{
    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {

        ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());

        List<DiagramElement> vezeDaSeObrisu = new ArrayList<>();
        //prolazi kroz sve paintere unazad
        for(int i =c.getPainters().size()-1;i>=0;i--){
            //trenutni painter na kome smo
            ElementPainter painter = c.getPainters().get(i);
            //ako je painter na tacki
            if(painter.elementAt(p)){
                //prodji kroz sve paintere ponovo i
                for(int j =c.getPainters().size()-1;j>=0;j--){
                    if(c.getPainters().get(j) instanceof ConnectionPainter){
                        if(((ConnectionPainter) c.getPainters().get(j)).getVeza().getElement1().equals(painter.getDiagramElement())||((ConnectionPainter) c.getPainters().get(j)).getVeza().getElement2().equals(painter.getDiagramElement())){
                            //dodato zbog brisanja svih veza povezanih na element
                            vezeDaSeObrisu.add(c.getPainters().get(j).getDiagramElement());
                            c.getPainters().remove(c.getPainters().get(j));
                        }
                    }
                }

                //ZA BRISANJE VEZA
                for (DiagramElement d : vezeDaSeObrisu){
                    c.getDiagram().deleteChild(d);
                    ClassyTreeItem nodeVeze = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(), d);
                    tree.deleteChild(nodeVeze);
                }


                c.getDiagram().deleteChild(painter.getDiagramElement());
                //ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());
                //Ova metoda pronalazi treenode koji odgovara selectovanom dijagramu i dodaje mu dete tako sto se rekurzivno krece kroz nas JTREE
                ClassyTreeItem diagramItem = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),painter.getDiagramElement());
                //ovo je samo za dodavanje u jtree, u modelu je vec dodat
                MainFrame.getInstance().getClassyTree().deleteChild(diagramItem);
                //samo rasiri sve
                tree.getTreeView().expandPath(new TreePath(diagramItem.getPath()));

                c.getPainters().remove(painter);
                break;
            }
        }


    }

    @Override
    public void misKliknut1(Point2D p, ElementCreationView e) {

    }

    @Override
    public void misPovucen(Point2D p, ClassyDiagramView c) {

    }

    @Override
    public void misPritisnut(Point2D p, ClassyDiagramView c) {

    }

    @Override
    public void misOtpusten(Point2D p, ClassyDiagramView c) {

    }
}

