package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Enum;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

@Getter
@Setter
public class EnumPainter implements ElementPainter{

    private Enum anEnum;
    private Shape oblik;

    public EnumPainter(Enum anEnum) {
        this.anEnum = anEnum;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(anEnum.getColor());
        g.setStroke(new BasicStroke(2));
        oblik = new Rectangle2D.Double(anEnum.getLocation().getX(),anEnum.getLocation().getY(),100,200);
        g.draw(oblik);
    }

    @Override
    public boolean elementAt(Point2D p) {
        return oblik.contains(p);
    }
}
