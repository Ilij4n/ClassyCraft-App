package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;

import java.awt.*;
import java.awt.geom.Point2D;
@Getter
@Setter
public abstract class ConnectionPainter{
    //slican fazon kao i u ostlaim klasama
    private Connection veza;
    private Shape oblik;

    public ConnectionPainter(Connection veza) {
        this.veza = veza;
    }

}
