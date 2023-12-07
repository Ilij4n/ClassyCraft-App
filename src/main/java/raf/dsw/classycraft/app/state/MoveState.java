package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;

import java.awt.geom.Point2D;

public class MoveState implements StateInterface{

    private ElementPainter nabodeniPainter;

    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
        System.out.println(c.getDiagram().getName());
    }

    @Override
    public void misKliknut1(Point2D p, ElementCreationView e) {

    }

    @Override
    public void misPovucen(Point2D p, ClassyDiagramView c) {
        if(nabodeniPainter != null && nabodeniPainter.getDiagramElement() instanceof InterClass){
            InterClass element = (InterClass) nabodeniPainter.getDiagramElement();
            element.setLocation(p);
        }
    }

    @Override
    public void misPritisnut(Point2D p, ClassyDiagramView c) {
        nabodeniPainter = null;
        for(int i = c.getPainters().size()-1;i>=0;i--){
            ElementPainter painter = c.getPainters().get(i);
            if(painter.elementAt(p)){
                nabodeniPainter = painter;
                break;
            }
        }
    }

    @Override
    public void misOtpusten(Point2D p, ClassyDiagramView c) {
        if(nabodeniPainter != null && nabodeniPainter.getDiagramElement() instanceof InterClass){
            InterClass element = (InterClass) nabodeniPainter.getDiagramElement();
            element.setLocation(p);
        }
    }

    @Override
    public void misOtpusten1(Point2D p, ElementCreationView e) {

    }

    @Override
    public void misSkrolovan(Point2D p, ClassyDiagramView c) {

    }
}
