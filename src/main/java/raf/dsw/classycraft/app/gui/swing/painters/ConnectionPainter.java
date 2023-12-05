package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
@Getter
@Setter
public abstract class ConnectionPainter extends ElementPainter{
    //slican fazon kao i u ostlaim klasama
    private Connection veza;
    private Shape oblik = new Line2D.Double();

    public ConnectionPainter(Connection veza) {
        super(veza);
        this.veza = veza;
    }

    public void draw(Graphics2D g) {

    }

}
