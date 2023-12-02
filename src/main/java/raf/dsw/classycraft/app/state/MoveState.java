package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;

import java.awt.geom.Point2D;

public class MoveState implements StateInterface{
    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
        System.out.println(c.getDiagram().getName());
    }
}
