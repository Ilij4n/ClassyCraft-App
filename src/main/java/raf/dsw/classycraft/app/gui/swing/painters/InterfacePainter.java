package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

@Getter
@Setter
public class InterfacePainter extends ElementPainter {
    //polje modela
    private Interfejs interfejs;
    //ovo polje nameravam da koristim takodje i za poziv za contains u elementAt-u
    private Rectangle2D oblik;

    public InterfacePainter(Interfejs interfejs) {
        super(interfejs);
        this.interfejs = interfejs;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(interfejs.getColor());
        g.setStroke(new BasicStroke(2));
        oblik = new Rectangle2D.Double(interfejs.getLocation().getX(),(int)interfejs.getLocation().getY(),100,200);



        // Boja za ivice
        g.setColor(Color.BLACK);

        // Nacrtaj ivice
        g.draw(oblik);

        // Fill boja
        g.setColor(interfejs.getColor());

        // Nacrtaj fill
        g.fill(oblik);

        // Font
        Font font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);

        // Boja teksta
        g.setColor(Color.WHITE);

        // Placement teksta
        FontMetrics fontMetrics = g.getFontMetrics();
        int textHeight = fontMetrics.getHeight();
        // Nacrtaj na vrhu
        int classNameX = (int) oblik.getCenterX() - fontMetrics.stringWidth(interfejs.getName()) / 2;
        int classNameY = (int) oblik.getY() + textHeight;
        g.drawString(interfejs.getName(), classNameX, classNameY);
    }

    @Override
    public boolean elementAt(Point2D p) {
        return oblik.contains(p);
    }
}
