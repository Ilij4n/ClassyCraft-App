package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class SelectionState implements StateInterface{
    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
        p.setLocation(p.getX()/c.getScale(),p.getY()/c.getScale());
        if (c.getSviselectovani().size() == 1){
            c.getSviselectovani().get(0).setSelected(false);
           // System.out.println("Usoo");
        }
        for(int j = c.getSviselectovani().size()-1;j>=0;j--){
            c.getPainters().get(j).setSelected(false);
           // System.out.println("usao");
        }

        c.repaint();
        for(ElementPainter painter: c.getPainters()){
            painter.setSelected(false);
        }
        c.getSviselectovani().clear();
        System.out.println("svi selektovani"+ c.getSviselectovani());
        for(int i = c.getPainters().size()-1;i>=0;i--){
            ElementPainter painter = c.getPainters().get(i);
            if(painter.elementAt(p)){
                painter.setSelected(true);
                c.getSviselectovani().add(painter);
                c.repaint();
                break;
            }
        }

        c.repaint();
    }

    @Override
    public void misKliknut1(Point2D p, ElementCreationView e) {

    }

    @Override
    public void misPovucen(Point2D p, ClassyDiagramView c) {
        int x = (int)(Math.min((int)c.getPrvaTacka().getX(),(int)p.getX())/c.getScale());
        int y = (int)(Math.min((int)c.getPrvaTacka().getY(),(int)p.getY())/c.getScale());
        int w = (int)(Math.abs((int)p.getX()-(int)c.getPrvaTacka().getX())/c.getScale());
        int h = (int) (Math.abs((int)p.getY()-(int)c.getPrvaTacka().getY())/c.getScale());
        c.setLaso(new Rectangle2D.Double(x,y,w,h));
        for(ElementPainter painter: c.getPainters()){
            if(c.getLaso().intersects(painter.getOblik()) && !(c.getSviselectovani().contains(painter))){
                //System.out.println("Usao");
                painter.setSelected(true);
                c.getSviselectovani().add(painter);
            }
            if (!(c.getLaso().intersects(painter.getOblik())) && c.getSviselectovani().contains(painter)) {
                painter.setSelected(false);
                c.getSviselectovani().remove(painter);
                c.repaint();
            }
        }
        c.repaint();
    }


    @Override
    public void misPritisnut(Point2D p, ClassyDiagramView c) {
        p.setLocation(p.getX()/c.getScale(),p.getY()/c.getScale());
        c.setPrvaTacka(p);
        for(int i =c.getPainters().size()-1;i>=0;i--){
            if(c.getPainters().get(i).elementAt(c.getPrvaTacka())){
                c.getLaso().setRect(c.getPrvaTacka().getX(), c.getPrvaTacka().getY(),0,0);
                c.repaint();
            }
        }

    }

    @Override
    public void misOtpusten(Point2D p, ClassyDiagramView c) {
        c.setLaso(new Rectangle2D.Double());
        c.repaint();
        System.out.println("svi selektovani"+ c.getSviselectovani());
    }

    @Override
    public void misOtpusten1(Point2D p, ElementCreationView e) {

    }

    @Override
    public void misSkrolovan(Point2D p, ClassyDiagramView c) {

    }
}
