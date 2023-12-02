package raf.dsw.classycraft.app.gui.swing.painters;

import java.awt.*;
import java.awt.geom.Point2D;

public interface ElementPainter {
    void draw(Graphics2D g);
    // ova metoda ce biti korisna da proverimo da li je kad kliknemo misom kliknut neki element
    boolean elementAt(Point2D p);
}
