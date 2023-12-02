package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;

import java.awt.*;
import java.awt.geom.Point2D;

@Getter
@Setter
public class ClassPainter implements ElementPainter{
    //polje modela
    private Klasa klasa;
    //ovo polje nameravam da koristim takodje i za poziv za contains u elementAt-u
    private Shape oblik;

    public ClassPainter(Klasa klasa) {
        this.klasa = klasa;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(klasa.getColor());
        g.setStroke(new BasicStroke(2));
        g.drawRect((int)klasa.getLocation().getX(),(int)klasa.getLocation().getY(),100,200);

    }

    @Override
    public boolean elementAt(Point2D p) {
        return false;
    }
}
