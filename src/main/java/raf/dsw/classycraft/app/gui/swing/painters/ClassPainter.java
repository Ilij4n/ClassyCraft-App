package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.Attribute;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.ClassContent;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.Method;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Set;

@Getter
@Setter
public class ClassPainter extends ElementPainter {
    //polje modela
    private Klasa klasa;
    //ovo polje nameravam da koristim takodje i za poziv za contains u elementAt-u
    private Rectangle2D oblik;

    public ClassPainter(Klasa klasa) {
        super(klasa);
        this.klasa = klasa;
    }

    @Override
    public void draw(Graphics2D g) {

        FontMetrics fontMetrics = g.getFontMetrics();

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

        g.setColor(klasa.getColor());
        g.setStroke(new BasicStroke(2));
        oblik = new Rectangle2D.Double(klasa.getLocation().getX(),(int)klasa.getLocation().getY(),maxWidth,totalHeight+10);



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

        int textHeight = fontMetrics.getHeight();
        // Nacrtaj na vrhu
        int classNameX = (int) oblik.getCenterX() - fontMetrics.stringWidth(klasa.getName()) / 2;
        int classNameY = (int) oblik.getY() + textHeight;
        g.drawString(klasa.getName(), classNameX, classNameY);

        textHeight+= 5;
        for(ClassContent c: klasa.getContentSet()){
            textHeight+= fontMetrics.getHeight();
            g.drawString(c.toString(), (int) oblik.getX()+2, (int) (oblik.getY()+textHeight));
        }

    }

    @Override
    public boolean elementAt(Point2D p) {
        return oblik.contains(p);
    }
}
