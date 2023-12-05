package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class ElementPainter {

    private DiagramElement diagramElement;
    private boolean selected = false;
    private List<Point2D> listOfPoints;
    private Rectangle2D rectangle2D;

    public ElementPainter(DiagramElement diagramElement){
        this.diagramElement = diagramElement;
        this.listOfPoints = new ArrayList<>();
    }

       public void draw(Graphics2D g) {

        }

    // ova metoda ce biti korisna da proverimo da li je kad kliknemo misom kliknut neki element
    public boolean elementAt(Point2D p) {
        return false;
    }
}
