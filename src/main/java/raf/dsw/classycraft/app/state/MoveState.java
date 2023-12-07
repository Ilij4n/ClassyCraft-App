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
    private List<Point2D> poslednjeTacke = new ArrayList<>();
    private Point2D poslednjaTacka;

    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
        System.out.println(c.getDiagram().getName());
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

            InterClass element = (InterClass) nabodeniPainter.getDiagramElement();
            element.setLocation(p);
        }
        else if(!c.getSviselectovani().isEmpty()){

            int misX = (int)p.getX();
            int misY = (int)p.getY();
        /*
            int deltaX = misX - (int)poslednjaTacka.getX();
            int deltaY = misY - (int)poslednjaTacka.getY();
                                                            */
            poslednjaTacka = new Point2D.Double(misX,misY);


            for(ElementPainter painter: c.getSviselectovani()){
                // TODO ovde ipak mora da se radi ono dx dy ahahah, her se u ovom stanju svi elementi setuju na jednu lokaciju i onda se poredjaju jedan na drugi kad ih movujem
                if(painter.getDiagramElement() instanceof InterClass){
                    InterClass element = (InterClass) painter.getDiagramElement();
                    Point2D poslednjaTacka1 = poslednjeTacke.get(c.getSviselectovani().indexOf(painter));

                    int deltaX = misX - (int)poslednjaTacka1.getX();
                    int deltaY = misY - (int)poslednjaTacka1.getY();

                    Point2D tackaDelta = new Point2D.Double(p.getX() + deltaX, p.getY() + deltaY);
                    element.setLocation(tackaDelta);
                    poslednjeTacke.set(c.getSviselectovani().indexOf(painter), tackaDelta);

                }
            }
        }
    }

    @Override
    public void misPritisnut(Point2D p, ClassyDiagramView c) {
        nabodeniPainter = null;
        if(c.getSviselectovani().isEmpty()){
            for(int i = c.getPainters().size()-1;i>=0;i--){
                ElementPainter painter = c.getPainters().get(i);
                if(painter.elementAt(p)){
                    nabodeniPainter = painter;
                    break;
                }
            }
        }
        else{
            poslednjeTacke.clear();
            for(ElementPainter painter: c.getSviselectovani()){
                if(painter.getDiagramElement() instanceof  InterClass)poslednjeTacke.add(((InterClass) painter.getDiagramElement()).getLocation());
            }
            //poslednjaTacka = p;
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
