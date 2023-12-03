package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

@Getter
@Setter
public class InterfacePainter implements ElementPainter{
    //polje modela
    private Interfejs interfejs;
    //ovo polje nameravam da koristim takodje i za poziv za contains u elementAt-u
    private Shape oblik;

    public InterfacePainter(Interfejs interfejs) {
        this.interfejs = interfejs;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(interfejs.getColor());
        g.setStroke(new BasicStroke(2));
        oblik = new Rectangle2D.Double(interfejs.getLocation().getX(),interfejs.getLocation().getY(),100,200);
        g.draw(oblik);
    }

    @Override
    public boolean elementAt(Point2D p) {
        return oblik.contains(p);
    }
}
