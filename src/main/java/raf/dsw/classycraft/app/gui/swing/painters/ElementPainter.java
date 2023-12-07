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
import java.util.Objects;

@Getter
@Setter
public abstract class ElementPainter {

    private DiagramElement diagramElement;
    private boolean selected = false;
    protected List<Point2D> listOfPoints;
    protected Rectangle2D oblik = new Rectangle2D.Double();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementPainter that = (ElementPainter) o;
        return Objects.equals(diagramElement, that.diagramElement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diagramElement);
    }

    @Override
    public String toString() {
        return diagramElement.toString();
    }
}
