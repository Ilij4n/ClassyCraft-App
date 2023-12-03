package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;

import java.awt.*;
import java.awt.geom.Point2D;
@Getter
@Setter
public abstract class ElementPainter {
    private DiagramElement diagramElement;

    public ElementPainter(DiagramElement diagramElement){
        this.diagramElement = diagramElement;
    }

   public void draw(Graphics2D g) {

    }

    // ova metoda ce biti korisna da proverimo da li je kad kliknemo misom kliknut neki element
    public boolean elementAt(Point2D p) {
        return false;
    }
}
