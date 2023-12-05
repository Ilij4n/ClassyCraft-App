package raf.dsw.classycraft.app.gui.swing.painters;

import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;

import java.awt.*;
import java.awt.geom.Point2D;

public class GeneralizacijaPainter extends ConnectionPainter{
    public GeneralizacijaPainter(Connection veza) {
        super(veza);
    }

    public void draw(Graphics2D g) {
        InterClass diagramElement = getVeza().getElement1();
        InterClass diagramElement1 = getVeza().getElement2();
        g.setColor(Color.BLACK);
        Point2D pointPocetak = getListOfPoints().get(0);
        Point2D pointKraj = getListOfPoints().get(1);
//        g.drawLine((int)diagramElement.getLocation().getX(),(int) diagramElement.getLocation().getY(), (int)diagramElement1.getLocation().getX(),(int)diagramElement1.getLocation().getY());
        g.drawLine((int)pointPocetak.getX(),(int)pointPocetak.getY(),(int)pointKraj.getX(),(int)pointKraj.getY());

    }

}
