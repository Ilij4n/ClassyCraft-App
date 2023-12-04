package raf.dsw.classycraft.app.controller.mouseAdapters;

import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;

public class ClassyMouseListener extends MouseAdapter implements MouseMotionListener {

    ClassyDiagramView classyDiagramView;

    public ClassyMouseListener(ClassyDiagramView classyDiagramView) {
        this.classyDiagramView = classyDiagramView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        ((ClassyPackageView) MainFrame.getInstance().getSplitPane().getRightComponent()).misKliknut((Point2D) e.getPoint(),classyDiagramView);
//        classyDiagramView.getDiagram().addChild(new Klasa());

    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        ((ClassyPackageView) MainFrame.getInstance().getSplitPane().getRightComponent()). misPritisnut((Point2D) e.getPoint(),classyDiagramView);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        ((ClassyPackageView) MainFrame.getInstance().getSplitPane().getRightComponent()).misOtpusten((Point2D) e.getPoint(),classyDiagramView);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        super.mouseDragged(e);
        System.out.println("vuci");
        ((ClassyPackageView) MainFrame.getInstance().getSplitPane().getRightComponent()).misPovucen((Point2D) e.getPoint(),classyDiagramView);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }
}
