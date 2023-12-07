package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
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
        /*ElementPainter izabraniPainter = null;
        for(int i = c.getPainters().size() -1 ; i>=0 ; i--){
            ElementPainter painter = c.getPainters().get(i);
            if(painter.elementAt(p)) izabraniPainter = painter;
        }
        if(izabraniPainter!=null && izabraniPainter.getDiagramElement() instanceof InterClass){
            //TODO nastaviti sutra...
            DiagramElement model =

            Interfejs interfejs = new Interfejs(c.getDiagram(),,p,);
            InterfacePainter interfacePainter = new InterfacePainter(interfejs);
            c.getDiagram().addChild(interfejs);
            c.getPainters().add(interfacePainter);

            ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());
            //Ova metoda pronalazi treenode koji odgovara selectovanom classynodeu i dodaje mu dete tako sto se rekurzivno krece kroz nas JTREE
            ClassyTreeItem diagramItem = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),c.getDiagram());
            //ovo je samo za dodavanje u jtree, u modelu je vec dodat
            MainFrame.getInstance().getClassyTree().addChild(diagramItem,false);
            //samo rasiri sve
            tree.getTreeView().expandPath(new TreePath(diagramItem.getPath()));
            System.out.println(c.getDiagram().getChildren());
        }*/
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
