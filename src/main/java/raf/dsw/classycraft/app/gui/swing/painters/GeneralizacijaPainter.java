package raf.dsw.classycraft.app.gui.swing.painters;

import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;

import java.awt.*;

public class GeneralizacijaPainter extends ConnectionPainter{
    public GeneralizacijaPainter(Connection veza) {
        super(veza);
    }

    public void draw(Graphics2D g) {
        InterClass diagramElement = getVeza().getElement1();
        InterClass diagramElement1 = getVeza().getElement2();
        g.setColor(Color.BLACK);
        g.drawLine((int)diagramElement.getLocation().getX(),(int) diagramElement.getLocation().getY(), (int)diagramElement1.getLocation().getX(),(int)diagramElement1.getLocation().getY());

    }

}
