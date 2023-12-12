package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.Attribute;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.ClassContent;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.Method;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ClassPainter extends ElementPainter {
    //polje modela
    private Klasa klasa;
    //ovo polje nameravam da koristim takodje i za poziv za contains u elementAt-u
    //polje shapea je na kraju prebaceno u nadklasu
    private boolean isSelected = false;

    public ClassPainter(Klasa klasa) {
        super(klasa);
        this.klasa = klasa;
    }

    @Override
    public void draw(Graphics2D g) {

        FontMetrics fontMetrics = g.getFontMetrics();

        //odredjivanje sirine i duzine teksta

        int maxWidth = 0;
        int totalHeight = 0;
        for (ClassContent c: klasa.getContentSet()) {
            int stringWidth = fontMetrics.stringWidth(c.toString());
            maxWidth = Math.max(maxWidth, stringWidth);
            totalHeight += fontMetrics.getHeight();
        }

        maxWidth = Math.max(maxWidth, fontMetrics.stringWidth(klasa.getName()));
        totalHeight += fontMetrics.getHeight();
        if(maxWidth < 100)maxWidth = 100;
        if(totalHeight<150)totalHeight = 150;

        //oblik je polje nadklase, tako je zbog pristupa metodi elementaAt

        g.setColor(klasa.getColor());
        g.setStroke(new BasicStroke(2));
        oblik = new Rectangle2D.Double(klasa.getLocation().getX(),(int)klasa.getLocation().getY(),maxWidth,totalHeight+10);

        //racunanje tacaka

        Rectangle2D bounds = oblik.getBounds2D();
        Point2D p1 = new Point2D.Double(bounds.getCenterX(),bounds.getMinY());
        Point2D p2 = new Point2D.Double(bounds.getMinX(), bounds.getCenterY());
        Point2D p3 = new Point2D.Double(bounds.getMaxX(),bounds.getCenterY());
        Point2D p4 = new Point2D.Double(bounds.getCenterX(),bounds.getMaxY());

        //svaka klasa ima listu svojih tacaka

        getListOfPoints().add((Point2D.Double) p1);
        getListOfPoints().add((Point2D.Double) p2);
        getListOfPoints().add((Point2D.Double) p3);
        getListOfPoints().add((Point2D.Double) p4);


        // Boja za ivice
        g.setColor(Color.BLACK);
        //ako je selected nacrtaj ga crveno
        if(isSelected)g.setColor(Color.red);
        // Nacrtaj ivice
        g.draw(oblik);

        // Fill boja
        g.setColor(klasa.getColor());
        // Nacrtaj fill
        g.fill(oblik);

        /*g.drawRect((int)p1.getX(),(int)p1.getY(),20,20);
        g.drawRect((int)p2.getX(),(int)p2.getY(),20,20);
        g.drawRect((int)p3.getX(),(int)p3.getY(),20,20);
        g.drawRect((int)p4.getX(),(int)p4.getY(),20,20);*/

        // Font
        Font font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);

        // Boja teksta
        g.setColor(Color.BLACK);


        //centriranje naslova i postalvjanje na vrh

        int textHeight = fontMetrics.getHeight();
        int classNameX = (int) oblik.getCenterX() - fontMetrics.stringWidth(klasa.getName()) / 2;
        int classNameY = (int) oblik.getY() + textHeight;
        g.drawString(klasa.getName(), classNameX, classNameY);

        g.drawLine((int)oblik.getX(), (int)oblik.getY()+textHeight+5, (int)(oblik.getX()+oblik.getWidth()), (int)oblik.getY()+textHeight+5);

        //ispis svega unutar

        textHeight+= 5;
        for(ClassContent c: klasa.getContentSet()){
            textHeight+= fontMetrics.getHeight();
            g.drawString(c.toString(), (int) oblik.getX()+2, (int) (oblik.getY()+textHeight));
        }



    }

    public List<Point2D> getListOfPoints(){
        listOfPoints.clear();
        Rectangle2D bounds = oblik.getBounds2D();
        Point2D p1 = new Point2D.Double(bounds.getCenterX(),bounds.getMinY());
        Point2D p2 = new Point2D.Double(bounds.getMinX(), bounds.getCenterY());
        Point2D p3 = new Point2D.Double(bounds.getMaxX(),bounds.getCenterY());
        Point2D p4 = new Point2D.Double(bounds.getCenterX(),bounds.getMaxY());

        //svaka klasa ima listu svojih tacaka

        listOfPoints.add((Point2D.Double) p1);
        listOfPoints.add((Point2D.Double) p2);
        listOfPoints.add((Point2D.Double) p3);
        listOfPoints.add((Point2D.Double) p4);

        return listOfPoints;
    }

    @Override
    public boolean elementAt(Point2D p) {
        return getOblik().contains(p);
    }
}
