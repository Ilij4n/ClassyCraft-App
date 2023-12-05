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

            Connection connection = new Generalizacija(c.getDiagram(),"nesto", (InterClass)elementPainterPocetni.getDiagramElement(),(InterClass) elementPainterKrajnji.getDiagramElement(),"0..1","promenjiva");

            GeneralizacijaPainter g = new GeneralizacijaPainter(connection,elementPainterPocetni,elementPainterKrajnji);

            c.getPainters().add(g);

            //c.repaint();
        }

        c.setLinija(new Line2D.Double());
        c.repaint();
    }
}
