package raf.dsw.classycraft.app.gui.swing.painters;

import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;

import java.awt.*;
import java.awt.geom.Point2D;

public class GeneralizacijaPainter extends ConnectionPainter{
    public GeneralizacijaPainter(Connection veza,ElementPainter elementPainter1, ElementPainter elementPainter2) {
        super(veza,elementPainter1,elementPainter2);
    }

    public void draw(Graphics2D g) {
        super.draw(g);
        //dalje crtanje
    }

}
