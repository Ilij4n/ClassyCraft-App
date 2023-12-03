package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;

import java.awt.geom.Point2D;

public class DeleteState implements StateInterface{
    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
        for(ElementPainter painter:c.getPainters()){
            if(painter.elementAt(p)){
                c.getPainters().remove(painter);
                //TODO naci resenje za brisanje da se povuce Model iz painetera
                c.getDiagram().deleteChild(null);
            }
        }
    }

    @Override
    public void misKliknut1(Point2D p, ElementCreationView e) {

    }
}

