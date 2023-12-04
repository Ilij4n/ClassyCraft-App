package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Generalizacija;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.gui.swing.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.painters.GeneralizacijaPainter;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class AddConnectionState implements StateInterface{
    private boolean moze = false;
    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {

    }

    @Override
    public void misKliknut1(Point2D p, ElementCreationView e) {

    }

    @Override
    public void misPovucen(Point2D p, ClassyDiagramView c) {
        if(moze){
            c.getLinija().setLine(c.getPrvaTacka(),p);
            //System.out.println("vuci1");
            c.repaint();
        }
    }

    @Override
    public void misPritisnut(Point2D p, ClassyDiagramView c) {
        c.setPrvaTacka(p);
        moze = false;
        for(int i =c.getPainters().size()-1;i>=0;i--){
            if(c.getPainters().get(i).elementAt(c.getPrvaTacka())){
                c.getLinija().setLine(p,p);
                moze = true;
                c.repaint();
            }
        }
    }

    @Override
    public void misOtpusten(Point2D p, ClassyDiagramView c) {
        moze = false;

        ElementPainter elementPainterPocetni = null;
        ElementPainter elementPainterKrajnji = null;

        for (int i = c.getPainters().size() - 1; i >= 0; i--) {
            if (c.getPainters().get(i).elementAt(p)) {
                moze = true;
                elementPainterKrajnji = c.getPainters().get(i);
                break;
            }
        }


        if (moze) {

            for (int i = c.getPainters().size() - 1; i >= 0; i--) {
                if (c.getPainters().get(i).elementAt(c.getPrvaTacka())) {
                    elementPainterPocetni = c.getPainters().get(i);
                    break;
                }
            }
            double najkraci = 100000;
            double najkraciXPocetni = 0;
            double najkraciYPocetni = 0;
            double najkraciXKrajnji = 0;
            double najkraciYKrajnji = 0;

            Connection connection = new Generalizacija(c.getDiagram(),"nesto", (InterClass)elementPainterPocetni.getDiagramElement(),(InterClass) elementPainterKrajnji.getDiagramElement(),"0..1","promenjiva");
            for(int i = elementPainterPocetni.getListOfPoints().size() - 1; i >= 0; i--){
                for(int j = elementPainterKrajnji.getListOfPoints().size() - 1; j >= 0; j--){

                    double xPocetni = elementPainterPocetni.getListOfPoints().get(i).getX();
                    double xKrajnji = elementPainterKrajnji.getListOfPoints().get(j).getX();
                    double yPocetni  = elementPainterPocetni.getListOfPoints().get(i).getY();
                    double yKrajnji  = elementPainterKrajnji.getListOfPoints().get(j).getY();

                    double d = Math.sqrt(Math.pow(xKrajnji-xPocetni,2)+Math.pow(yKrajnji-yPocetni,2));
                    if(d<najkraci){
                        najkraci = d;
                        najkraciXPocetni = xPocetni;
                        najkraciYPocetni = yPocetni;
                        najkraciXKrajnji = xKrajnji;
                        najkraciYKrajnji = yKrajnji;
                    }

                }
            }

            GeneralizacijaPainter g = new GeneralizacijaPainter(connection);
            g.getListOfPoints().add(new Point2D.Double(najkraciXPocetni,najkraciYPocetni));
            g.getListOfPoints().add(new Point2D.Double(najkraciXKrajnji,najkraciYKrajnji));

            c.getPainters().add(g);

            c.getLinija().setLine(c.getPrvaTacka(), p);

            c.repaint();
        }
        else {
            c.setLinija(new Line2D.Double());
            c.repaint();
        }
        c.setLinija(new Line2D.Double());
        c.repaint();
    }
}
