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



        // Set color for the rectangle outline
        g.setColor(Color.BLACK);

        // Draw the class rectangle outline using Rectangle2D
        g.draw(oblik);

        // Set color for the rectangle fill
        g.setColor(Color.GREEN);

        // Fill the class rectangle
        g.fill(oblik);

        // Set font for text
        Font font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);

        // Set color for the text
        g.setColor(Color.BLACK);

        // Calculate the height of the text
        FontMetrics fontMetrics = g.getFontMetrics();
        int textHeight = fontMetrics.getHeight();

        // Draw class name at the top of the rectangle
        int classNameX = (int) oblik.getCenterX() - fontMetrics.stringWidth(klasa.getName()) / 2;
        int classNameY = (int) oblik.getY() + textHeight;
        g.drawString(klasa.getName(), classNameX, classNameY);
    }

    @Override
    public boolean elementAt(Point2D p) {
        return oblik.contains(p);
    }
}
