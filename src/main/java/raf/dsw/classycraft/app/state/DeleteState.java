package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
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
        if(!c.getSviselectovani().isEmpty()){
            brisanjeZaVise(c,vezeDaSeObrisu);
            return;
        }
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
                if(diagramItem != null){
                    MainFrame.getInstance().getClassyTree().deleteChild(diagramItem);
                    //samo rasiri sve
                    tree.getTreeView().expandPath(new TreePath(diagramItem.getPath()));
                }


                c.getPainters().remove(painter);
                break;
            }
        }
        c.getSviselectovani().clear();
    }

    private void brisanjeZaVise(ClassyDiagramView c, List<DiagramElement> vezeDaSeObrisu){
        List<ElementPainter> listaSelektovanih = c.getSviselectovani();
        ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());

        //ne mogu vise, idemo da spavam
        for(int i = c.getPainters().size()-1;i>=0;i--){
            ElementPainter painter = c.getPainters().get(i);
            if(painter.getDiagramElement() instanceof  Connection && c.getSviselectovani().contains(painter))c.getPainters().remove(painter);
        }

        for(ElementPainter painter : listaSelektovanih){


            if(painter.getDiagramElement() instanceof InterClass){
                for (int i =c.getPainters().size()-1;i>=0;i--){
                    ElementPainter painter1 = c.getPainters().get(i);
                    if(painter1.getDiagramElement() instanceof Connection){
                        Connection connection = (Connection) painter1.getDiagramElement();
                        if((connection.getElement1().equals(painter.getDiagramElement())||connection.getElement2().equals(painter.getDiagramElement()))){
                            c.getPainters().remove(painter1);
                            c.getDiagram().getChildren().remove(connection);
                            tree.deleteChild(tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),connection));
                        }
                    }
                }
            }

            c.getPainters().remove(painter);
            c.getDiagram().getChildren().remove(painter.getDiagramElement());
            tree.deleteChild(tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),painter.getDiagramElement()));
            c.repaint();
        }
        listaSelektovanih.clear();
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

    @Override
    public void misOtpusten1(Point2D p, ElementCreationView e) {

    }

    @Override
    public void misSkrolovan(Point2D p, ClassyDiagramView c) {

    }
}

