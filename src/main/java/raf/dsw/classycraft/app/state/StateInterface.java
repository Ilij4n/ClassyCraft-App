package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;

import java.awt.geom.Point2D;

public interface StateInterface {
    void misKliknut(Point2D p, ClassyDiagramView c);
    void misKliknut1(Point2D p, ElementCreationView e);

    void misPovucen(Point2D p, ClassyDiagramView c);

    void misPritisnut(Point2D p, ClassyDiagramView c);

    void misOtpusten(Point2D p, ClassyDiagramView c);

    void misSkrolovan(Point2D p, ClassyDiagramView c);



}
