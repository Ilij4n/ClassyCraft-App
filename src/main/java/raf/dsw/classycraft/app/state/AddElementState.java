package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;
import raf.dsw.classycraft.app.gui.swing.painters.ClassPainter;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;

import java.awt.*;
import java.awt.geom.Point2D;

public class AddElementState implements StateInterface{
    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
        System.out.println(getClass().getSimpleName());
        // da ne bi moglo vise prozora da se otvori
        if(ElementCreationView.jedini())return;

        ElementCreationView e = new ElementCreationView(c,p);
        //wait(20.00);

        if(e.getRadioBtnKlasa().isSelected()){
            Klasa klasa = new Klasa(c.getDiagram(),e.getName(), p,null);
            ClassPainter classPainter = new ClassPainter(klasa);
            c.getPainters().add(classPainter);
            c.getDiagram().addChild(klasa);
        }


    }
}
