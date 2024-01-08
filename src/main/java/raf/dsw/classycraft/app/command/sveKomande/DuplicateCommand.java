package raf.dsw.classycraft.app.command.sveKomande;

import raf.dsw.classycraft.app.command.AbstractCommand;
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
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.tree.TreePath;
import java.awt.geom.Point2D;

public class DuplicateCommand extends AbstractCommand {


    private static int copyCounter = 0;
    private ElementPainter izabraniPainter;
    private ClassyTreeItem diagramItem;
    private ElementPainter copy;
    private ClassyDiagramView c;


    public DuplicateCommand(ClassyDiagramView classyDiagramView, ElementPainter elementPainter){
        c = classyDiagramView;
        izabraniPainter = elementPainter;
    }

    @Override
    public void doCommand() {
        if (izabraniPainter != null && izabraniPainter.getDiagramElement() instanceof InterClass) {
            DiagramElement model = izabraniPainter.getDiagramElement();

            Point2D oldPoint = ((InterClass) model).getLocation();

            int pointMoveX = 0;
            int pointMoveY = 0;
            pointMoveX+=10;
            pointMoveY+=10;


            Point2D newPoint = new Point2D.Double(oldPoint.getX() + pointMoveX, oldPoint.getY() + pointMoveY);

            if (model instanceof Klasa) {

                Klasa klasa = new Klasa(c.getDiagram(), model.getName() + "(Copy " + copyCounter + ")", newPoint, ((Klasa) model).getContentSet());
                copy = new ClassPainter(klasa);
                c.getDiagram().addChild(klasa);

                klasa.addSub(c);
                c.getPainters().add(copy);
            } else if (model instanceof Interfejs) {
                Interfejs interfejs = new Interfejs(c.getDiagram(), model.getName() + "(Copy " + copyCounter + ")", newPoint, ((Interfejs) model).getContentSet());
                copy = new InterfacePainter(interfejs);
                c.getDiagram().addChild(interfejs);

                interfejs.addSub(c);
                c.getPainters().add(copy);
            } else if (model instanceof Enum) {
                Enum enumncina = new Enum(c.getDiagram(), model.getName() + "(Copy " + copyCounter + ")", newPoint, ((Enum) model).getContentSet());
                copy= new EnumPainter(enumncina);
                c.getDiagram().addChild(enumncina);

                enumncina.addSub(c);
                c.getPainters().add(copy);
            }

            copyCounter++;

            ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());
            //Ova metoda pronalazi treenode koji odgovara selectovanom classynodeu i dodaje mu dete tako sto se rekurzivno krece kroz nas JTREE
            diagramItem = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(), c.getDiagram());
            //ovo je samo za dodavanje u jtree, u modelu je vec dodat
            MainFrame.getInstance().getClassyTree().addChild(diagramItem, false);
            //samo rasiri sve
            tree.getTreeView().expandPath(new TreePath(diagramItem.getPath()));


        }
    }

    @Override
    public void undoCommand() {
        copyCounter--;
        MainFrame.getInstance().getClassyTree().deleteChild(((ClassyTreeItem)diagramItem.getLastChild()));
        c.getDiagram().deleteChild(copy.getDiagramElement());
        c.getPainters().remove(copy);
        c.repaint();
    }
}
