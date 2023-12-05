package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;

import javax.swing.*;
import java.awt.geom.Point2D;

public class EditState implements StateInterface{

    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
        System.out.println(getClass().getSimpleName());
        // da ne bi moglo vise prozora da se otvori
        if(ElementCreationView.pokazanSam())return;
       // ElementCreationView e = new ElementCreationView(c,p);

        for(int i = c.getPainters().size() - 1 ; i>=0 ; i--){
            ElementPainter painter = c.getPainters().get(i);
            if(painter.elementAt(p)){
                //TODO Provera da li je selectovana veza ili Interclass
                DiagramElement model = painter.getDiagramElement();
                if(model instanceof InterClass){
                    ElementCreationView e = new ElementCreationView(c,p);
                    e.postaviPolja((InterClass) model);
                    e.setVisible(true);

                }
                else if(model instanceof Connection){
                    //TODO za sad nista..
                    return;
                }

            }
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
    public void misSkrolovan(Point2D p, ClassyDiagramView c) {

    }


}
