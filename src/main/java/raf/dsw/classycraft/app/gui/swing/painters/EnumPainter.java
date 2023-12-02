package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Enum;

import java.awt.*;
import java.awt.geom.Point2D;
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

    }

    @Override
    public boolean elementAt(Point2D p) {
        return false;
    }
}
