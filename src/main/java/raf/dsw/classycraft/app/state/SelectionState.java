package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;

import javax.swing.*;
import java.awt.geom.Point2D;

public class SelectionState implements StateInterface{
    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
        if (c.getSviselectovani().size() == 1){
            c.getSviselectovani().get(0).setSelected(false);
            System.out.println("Usoo");
        }
        for(int j = c.getSviselectovani().size()-1;j>=0;j--){
            c.getPainters().get(j).setSelected(false);
            System.out.println("usao");
        }
        c.repaint();
        c.getSviselectovani().clear();
        System.out.println(c.getSviselectovani());
        for(int i = c.getPainters().size()-1;i>=0;i--){
            ElementPainter painter = c.getPainters().get(i);
            if(painter.elementAt(p)){
                painter.setSelected(true);
                c.getSviselectovani().add(painter);
                c.repaint();
                break;
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
}
