package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.ClassContent;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Enum;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EnumPainter extends ElementPainter {

    private Enum anEnum;
    private Rectangle2D oblik;
    private boolean isSelected = false;


    public EnumPainter(Enum anEnum) {
        super(anEnum);
        this.anEnum = anEnum;
    }

    @Override
    public void draw(Graphics2D g) {
        FontMetrics fontMetrics = g.getFontMetrics();

        int maxWidth = 0;
        int totalHeight = 0;
        for (ClassContent c: anEnum.getContentSet()) {
            int stringWidth = fontMetrics.stringWidth(c.toString());
            maxWidth = Math.max(maxWidth, stringWidth);
            totalHeight += fontMetrics.getHeight();
        }

        maxWidth = Math.max(maxWidth, fontMetrics.stringWidth(anEnum.getName()));
        totalHeight += fontMetrics.getHeight();
        if(maxWidth < 100)maxWidth = 100;
        if(totalHeight<150)totalHeight = 150;

        g.setColor(anEnum.getColor());
        g.setStroke(new BasicStroke(2));
        oblik = new Rectangle2D.Double(anEnum.getLocation().getX(),(int)anEnum.getLocation().getY(),maxWidth,totalHeight+10);

        Point2D p1 = new Point2D.Double(oblik.getCenterX(),oblik.getY());
        Point2D p2 = new Point2D.Double(oblik.getX(),oblik.getCenterY());
        Point2D p3 = new Point2D.Double(oblik.getCenterX(),oblik.getY()+oblik.getHeight());
        Point2D p4 = new Point2D.Double(oblik.getX()+oblik.getWidth(), oblik.getCenterY());

        getListOfPoints().add((Point2D.Double) p1);
        getListOfPoints().add((Point2D.Double) p2);
        getListOfPoints().add((Point2D.Double) p3);
        getListOfPoints().add((Point2D.Double) p4);


        // Boja za ivice
        g.setColor(Color.BLACK);
        if(isSelected)g.setColor(Color.red);

        // Nacrtaj ivice
        g.draw(oblik);



        // Fill boja
        g.setColor(anEnum.getColor());



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
        int classNameX = (int) oblik.getCenterX() - fontMetrics.stringWidth(anEnum.getName()) / 2;
        int classNameY = (int) oblik.getY() + textHeight;
        g.drawString(anEnum.getName(), classNameX, classNameY);

        textHeight+= 5;
        for(ClassContent c: anEnum.getContentSet()){
            textHeight+= fontMetrics.getHeight();
            g.drawString(c.toString(), (int) oblik.getX()+2, (int) (oblik.getY()+textHeight));
        }
    }

    @Override
    public boolean elementAt(Point2D p) {
        return oblik.contains(p);
    }
}
