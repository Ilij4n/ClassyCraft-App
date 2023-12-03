package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

@Getter
@Setter
public class ClassPainter implements ElementPainter{
    //polje modela
    private Klasa klasa;
    //ovo polje nameravam da koristim takodje i za poziv za contains u elementAt-u
    private Rectangle2D oblik;

    public ClassPainter(Klasa klasa) {
        this.klasa = klasa;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(klasa.getColor());
        g.setStroke(new BasicStroke(2));
        oblik = new Rectangle2D.Double(klasa.getLocation().getX(),(int)klasa.getLocation().getY(),100,200);



        // Boja za ivice
        g.setColor(Color.BLACK);

        // Nacrtaj ivice
        g.draw(oblik);

        // Fill boja
        g.setColor(klasa.getColor());

        // Nacrtaj fill
        g.fill(oblik);

        // Font
        Font font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);

        // Boja teksta
        g.setColor(Color.BLACK);

        // Placement teksta
        FontMetrics fontMetrics = g.getFontMetrics();
        int textHeight = fontMetrics.getHeight();
        // Nacrtaj na vrhu
        int classNameX = (int) oblik.getCenterX() - fontMetrics.stringWidth(klasa.getName()) / 2;
        int classNameY = (int) oblik.getY() + textHeight;
        g.drawString(klasa.getName(), classNameX, classNameY);
    }

    @Override
    public boolean elementAt(Point2D p) {
        return oblik.contains(p);
    }
}
