package raf.dsw.classycraft.app.gui.swing.painters;

import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;

import java.awt.*;

public class KompozicijaPainter extends ConnectionPainter{
    public KompozicijaPainter(Connection veza,ElementPainter elementPainter1,ElementPainter elementPainter2) {
        super(veza,elementPainter1,elementPainter2);
    }

    public void draw(Graphics2D g) {


        double najkraci = Double.MAX_VALUE;
        double najkraciXPocetni = Double.MIN_VALUE;
        double najkraciYPocetni = Double.MIN_VALUE;
        double najkraciXKrajnji = Double.MIN_VALUE;
        double najkraciYKrajnji = Double.MIN_VALUE;

        //System.out.println(elementPainter1.getListOfPoints().get(0));


        for(int i = getElementPainter1().getListOfPoints().size() - 1; i >= 0; i--){
            for(int j = getElementPainter2().getListOfPoints().size() - 1; j >= 0; j--){

                double xPocetni = getElementPainter1().getListOfPoints().get(i).getX();
                double xKrajnji = getElementPainter2().getListOfPoints().get(j).getX();
                double yPocetni  = getElementPainter1().getListOfPoints().get(i).getY();
                double yKrajnji  = getElementPainter2().getListOfPoints().get(j).getY();

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

        g.setColor(Color.black);
        g.setStroke(new BasicStroke(2));

        if(isSelected()){
            g.setColor(Color.RED);
        }

        double ugao = Math.atan2(najkraciYKrajnji-najkraciYPocetni,najkraciXKrajnji-najkraciXPocetni);
        double strelicaugao = 20* Math.PI /180;

        g.setStroke(new BasicStroke(2));
        int duzinaStrlice = 15;

        int xPrveLinije  = (int) (najkraciXKrajnji - duzinaStrlice * Math.cos(ugao + strelicaugao));
        int yPrveLinije = (int) (najkraciYKrajnji - duzinaStrlice * Math.sin(ugao + strelicaugao));

        g.drawLine((int) najkraciXKrajnji,(int) najkraciYKrajnji,xPrveLinije,yPrveLinije);

        int xDrugeLinije  = (int) (najkraciXKrajnji - duzinaStrlice * Math.cos(ugao - strelicaugao));;
        int yDrugeLinije = (int) (najkraciYKrajnji - duzinaStrlice * Math.sin(ugao - strelicaugao));;

        g.drawLine((int) najkraciXKrajnji,(int) najkraciYKrajnji,xDrugeLinije,yDrugeLinije);

        g.setStroke(new BasicStroke(2));

        double ugao2 = Math.atan2(najkraciYKrajnji-najkraciYPocetni,najkraciXKrajnji-najkraciXPocetni);
        int promenaPovezivanja = 16;

        int xnov  = (int) (najkraciXPocetni + promenaPovezivanja * Math.cos(ugao2));
        int ynov = (int) (najkraciYPocetni + promenaPovezivanja * Math.sin(ugao2));

        int duzina = 8;
        int sirina = 2*duzina;

        int prvaTackaX = (int) (xnov + duzina * Math.cos(ugao2 + Math.PI/2));
        int prvaTackaY = (int) (ynov + duzina * Math.sin(ugao2 + Math.PI/2));

        int drugaTackaX = (int) (xnov + duzina * Math.cos(ugao2 - Math.PI/2));
        int drugaTackaY = (int) (ynov + duzina * Math.sin(ugao2 - Math.PI/2));;

        int trecaTackaX = (int) (xnov + sirina * Math.cos(ugao2));
        int trecaTackaY = (int) (ynov + sirina * Math.sin(ugao2));;;

        int cetvrtaTackaX = (int) (xnov - sirina * Math.cos(ugao2));
        int cetvrtaTackaY = (int) (ynov - sirina * Math.sin(ugao2));;

        Polygon sejpofju = new Polygon();
        sejpofju.addPoint(cetvrtaTackaX,cetvrtaTackaY);
        sejpofju.addPoint(prvaTackaX,prvaTackaY);
        sejpofju.addPoint(trecaTackaX,trecaTackaY);
        sejpofju.addPoint(drugaTackaX,drugaTackaY);

        g.drawLine((int)najkraciXPocetni,(int)najkraciYPocetni,(int)najkraciXKrajnji,(int)najkraciYKrajnji);

        g.setColor(Color.black);
        g.fill(sejpofju);

        g.setColor(Color.black);
        g.drawPolygon(sejpofju);




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
}
