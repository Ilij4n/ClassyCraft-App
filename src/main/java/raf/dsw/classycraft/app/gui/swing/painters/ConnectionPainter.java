package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

@Getter
@Setter
public abstract class ConnectionPainter extends ElementPainter{
    //slican fazon kao i u ostlaim klasama
    private ElementPainter elementPainter1;
    private ElementPainter elementPainter2;
    private Connection veza;
    private boolean isSelected;

    public ConnectionPainter(Connection veza,ElementPainter elementPainter1,ElementPainter elementPainter2) {
        super(veza);
        this.veza = veza;
        this.elementPainter1 = elementPainter1;
        this.elementPainter2 = elementPainter2;
    }

    public void draw(Graphics2D g) {


        double najkraci = 1000;
        double najkraciXPocetni = 0;
        double najkraciYPocetni = 0;
        double najkraciXKrajnji = 0;
        double najkraciYKrajnji = 0;

        for(int i = elementPainter1.getListOfPoints().size() - 1; i >= 0; i--){
            for(int j = elementPainter2.getListOfPoints().size() - 1; j >= 0; j--){

                double xPocetni = elementPainter1.getListOfPoints().get(i).getX();
                double xKrajnji = elementPainter2.getListOfPoints().get(j).getX();
                double yPocetni  = elementPainter1.getListOfPoints().get(i).getY();
                double yKrajnji  = elementPainter2.getListOfPoints().get(j).getY();

                double d = Math.sqrt(Math.pow(xKrajnji-xPocetni,2)+Math.pow(yKrajnji-yPocetni,2));
                if(d<najkraci){
                    najkraci = d;
                    najkraciXPocetni = xPocetni;
                    najkraciYPocetni = yPocetni;
                    najkraciXKrajnji = xKrajnji;
                    najkraciYKrajnji = yKrajnji;
                }

            }
        }
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));

        if(isSelected){
            g.setColor(Color.RED);
        }



        g.drawLine((int)najkraciXPocetni,(int)najkraciYPocetni,(int)najkraciXKrajnji,(int)najkraciYKrajnji);

        Color transparentColor = new Color(255,0,0,0);
        g.setColor(transparentColor);
        int x = Math.min((int)najkraciXKrajnji,(int)najkraciXPocetni);
        int y = Math.min((int)najkraciYKrajnji,(int)najkraciYPocetni);
        int width = Math.abs((int)najkraciXKrajnji-(int)najkraciXPocetni);
        int height = Math.abs((int)najkraciYKrajnji-(int)najkraciYPocetni);
        oblik.setRect(x,y,width,height);
//        oblik = new Rectangle2D.Double(x,y,width,height);

        g.draw(oblik);

    }

    @Override
    public boolean elementAt(Point2D p) {
        return getOblik().contains(p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionPainter that = (ConnectionPainter) o;
        return Objects.equals(elementPainter1, that.elementPainter1) && Objects.equals(elementPainter2, that.elementPainter2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementPainter1, elementPainter2);
    }
}
