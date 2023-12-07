package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
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

public class DuplicateState implements StateInterface{
    int copyCounter = 1;
    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
        ElementPainter izabraniPainter = null;
        for(int i = c.getPainters().size() -1 ; i>=0 ; i--){
            ElementPainter painter = c.getPainters().get(i);
            if(painter.elementAt(p)){
                izabraniPainter = painter;
                break;
            }
        }
        if(izabraniPainter!=null && izabraniPainter.getDiagramElement() instanceof InterClass){
            //TODO nastaviti sutra...
            DiagramElement model = izabraniPainter.getDiagramElement();

            Point2D oldPoint = ((InterClass)model).getLocation();

            int pointMoveX = 0;
            int pointMoveY = 0;


            Point2D newPoint = new Point2D.Double(oldPoint.getX()+10,oldPoint.getY()+10);

            if(model instanceof Klasa){

                Klasa klasa = new Klasa(c.getDiagram(), model.getName() + "(Copy " + copyCounter + ")",newPoint,((Klasa) model).getContentSet());
                ClassPainter classPainter = new ClassPainter(klasa);
                //TODO logika za dodavanje moze i da se koristi u addElementStateu
                /*while(){
                    for (ElementPainter painter : c.getPainters()){

                    }
                }*/


                c.getDiagram().addChild(klasa);

                klasa.addSub(c);
                c.getPainters().add(classPainter);
            }
            else if(model instanceof Interfejs){
                Interfejs interfejs = new Interfejs(c.getDiagram(), model.getName() + "(Copy " + copyCounter + ")",newPoint,((Interfejs) model).getContentSet());
                InterfacePainter interfacePainter = new InterfacePainter(interfejs);
                c.getDiagram().addChild(interfejs);
                interfejs.addSub(c);
                c.getPainters().add(interfacePainter);
            }

            else if(model instanceof Enum){
                Enum enumncina= new Enum(c.getDiagram(), model.getName() + "(Copy " + copyCounter + ")",newPoint,((Enum) model).getContentSet());
                EnumPainter enumPainter = new EnumPainter(enumncina);
                c.getDiagram().addChild(enumncina);

                enumncina.addSub(c);
                c.getPainters().add(enumPainter);
            }


            ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());
            //Ova metoda pronalazi treenode koji odgovara selectovanom classynodeu i dodaje mu dete tako sto se rekurzivno krece kroz nas JTREE
            ClassyTreeItem diagramItem = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),c.getDiagram());
            //ovo je samo za dodavanje u jtree, u modelu je vec dodat
            MainFrame.getInstance().getClassyTree().addChild(diagramItem,false);
            //samo rasiri sve
            tree.getTreeView().expandPath(new TreePath(diagramItem.getPath()));

            copyCounter++;

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

    @Override
    public void misOtpusten1(Point2D p, ElementCreationView e) {

    }

    @Override
    public void misSkrolovan(Point2D p, ClassyDiagramView c) {

    }
}
