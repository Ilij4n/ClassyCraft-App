package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.command.sveKomande.DuplicateCommand;
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
        p.setLocation(p.getX()/c.getScale(),p.getY()/c.getScale());
        ElementPainter izabraniPainter = null;
        for(int i = c.getPainters().size() -1 ; i>=0 ; i--){
            ElementPainter painter = c.getPainters().get(i);
            if(painter.elementAt(p)){
                izabraniPainter = painter;
                break;
            }
        }
        DuplicateCommand duplicateCommand = new DuplicateCommand(c,izabraniPainter);
        c.getCommandManager().addCommand(duplicateCommand);
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
