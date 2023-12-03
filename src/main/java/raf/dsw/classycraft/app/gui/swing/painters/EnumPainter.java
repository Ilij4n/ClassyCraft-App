package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Enum;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

@Getter
@Setter
public class EnumPainter extends ElementPainter {

    private Enum anEnum;
    private Rectangle2D oblik;

    public EnumPainter(Enum anEnum) {
        super(anEnum);
        this.anEnum = anEnum;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(anEnum.getColor());
        g.setStroke(new BasicStroke(2));
        oblik = new Rectangle2D.Double(anEnum.getLocation().getX(),(int)anEnum.getLocation().getY(),100,200);

        // Boja za ivice
        g.setColor(Color.BLACK);

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
        FontMetrics fontMetrics = g.getFontMetrics();
        int textHeight = fontMetrics.getHeight();
        // Nacrtaj na vrhu
        int classNameX = (int) oblik.getCenterX() - fontMetrics.stringWidth(anEnum.getName()) / 2;
        int classNameY = (int) oblik.getY() + textHeight;
        g.drawString(anEnum.getName(), classNameX, classNameY);
    }

    @Override
    public boolean elementAt(Point2D p) {
        return oblik.contains(p);
    }
}
