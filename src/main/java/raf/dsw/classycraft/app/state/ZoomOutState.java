package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;

import java.awt.geom.Point2D;

public class ZoomOutState implements StateInterface{
    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
        c.setScale(c.getScale()-0.1);
        if(c.getScale()<0.5){
            c.setScale(0.5);
        }
        c.repaint();
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
