package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.gui.swing.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MoveState implements StateInterface{

    private ElementPainter nabodeniPainter;
    private List<Point2D> prvobitneTacke = new ArrayList<>();
    private Point2D poslednjaDraggedTacka;

    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
        //System.out.println(c.getDiagram().getName());
    }

    @Override
    public void misKliknut1(Point2D p, ElementCreationView e) {

    }

    @Override
    public void misPovucen(Point2D p, ClassyDiagramView c) {
        if(nabodeniPainter != null && nabodeniPainter.getDiagramElement() instanceof InterClass && c.getSviselectovani().isEmpty()){

            for(ElementPainter painter : c.getPainters()){

                InterClass element = (InterClass) nabodeniPainter.getDiagramElement();
                element.setLocation(p);
                // za refreshovanje konekcija
                if(painter instanceof ConnectionPainter){
                    if(((ConnectionPainter) painter).getElementPainter1().equals(nabodeniPainter)){
                        ((ConnectionPainter) painter).setElementPainter1(nabodeniPainter);
                    }
                    else if(((ConnectionPainter) painter).getElementPainter2().equals(nabodeniPainter)){
                        ((ConnectionPainter) painter).setElementPainter2(nabodeniPainter);
                    }
                }
            }
            //System.out.println("usao");
            InterClass element = (InterClass) nabodeniPainter.getDiagramElement();
            element.setLocation(p);
        }
        else if(nabodeniPainter != null && nabodeniPainter.getDiagramElement() instanceof InterClass && !c.getSviselectovani().isEmpty()){
            //trenutna tacka misa
            int misX = (int)p.getX();
            int misY = (int)p.getY();
            // delta je razlika pointa izmedju prethodnog i sadasnjeg dragged poziva
            int deltaX = misX - (int)poslednjaDraggedTacka.getX();
            int deltaY = misY - (int)poslednjaDraggedTacka.getY();

           // poslednjaTacka = new Point2D.Double(misX,misY);


            for(ElementPainter painter: c.getSviselectovani()){

                if(painter.getDiagramElement() instanceof InterClass){
                    InterClass element = (InterClass) painter.getDiagramElement();
                    Point2D tackaElementa = element.getLocation();


                    Point2D tackaDelta = new Point2D.Double(tackaElementa.getX() + deltaX, tackaElementa.getY() + deltaY);
                    element.setLocation(tackaDelta);
                   // poslednjeTacke.set(c.getSviselectovani().indexOf(painter), tackaDelta);

                }
            }
            poslednjaDraggedTacka = p;
        }
    }

    @Override
    public void misPritisnut(Point2D p, ClassyDiagramView c) {
        nabodeniPainter = null;
        int counterKlasa = 0;
            for(int i = c.getPainters().size()-1;i>=0;i--){
                ElementPainter painter = c.getPainters().get(i);
                if(painter.elementAt(p)){
                    nabodeniPainter = painter;
                    break;
                }
            }
        if(!c.getSviselectovani().isEmpty()){
            prvobitneTacke.clear();
            for(ElementPainter painter: c.getSviselectovani()){
                if(painter.getDiagramElement() instanceof  InterClass){
                    prvobitneTacke.add(((InterClass) painter.getDiagramElement()).getLocation());
                    counterKlasa++;
                }
                poslednjaDraggedTacka = p;
            }
            if(counterKlasa == 0){
                c.getSviselectovani().clear();
                c.repaint();
            }
        }
    }

    @Override
    public void misOtpusten(Point2D p, ClassyDiagramView c) {
        if(nabodeniPainter != null && nabodeniPainter.getDiagramElement() instanceof InterClass && c.getSviselectovani().isEmpty()){
            InterClass element = (InterClass) nabodeniPainter.getDiagramElement();
            element.setLocation(p);
        }
        c.repaint();
    }

    @Override
    public void misOtpusten1(Point2D p, ElementCreationView e) {

    }

    @Override
    public void misSkrolovan(Point2D p, ClassyDiagramView c) {

    }
}
