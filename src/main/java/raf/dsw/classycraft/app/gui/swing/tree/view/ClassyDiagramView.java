package raf.dsw.classycraft.app.gui.swing.tree.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.controller.mouseAdapters.ClassyMouseListener;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;
import raf.dsw.classycraft.app.gui.swing.painters.ClassPainter;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.painters.EnumPainter;
import raf.dsw.classycraft.app.gui.swing.painters.InterfacePainter;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

@Setter
@Getter
public class ClassyDiagramView extends JPanel implements ISubscriber{

    private String name;
    private Diagram diagram;
    private List<ElementPainter> painters = new ArrayList<>();
    //msm da cu ovo polje da setujem
    private ElementCreationView elementCreationView;
    private List<ElementPainter> sviselectovani;

    private List<ISubscriber> subscribers;

    private Rectangle2D laso;
    private Point2D prvaTacka;
    private Line2D linija;

    private double scale = 1.0;


    public ClassyDiagramView(Diagram d){
        diagram = d;
        this.name = diagram.getName();
        subscribers = new ArrayList<>();
        setBackground(Color.WHITE);
        sviselectovani = new ArrayList<>();
        addMouseListener(new ClassyMouseListener(this));
        addMouseMotionListener(new ClassyMouseListener(this));
        addMouseWheelListener(new ClassyMouseListener(this));
        this.linija = new Line2D.Double();
        this.laso = new Rectangle2D.Double();

    }


    @Override
    protected void paintComponent(Graphics g) {
        //TODO: nije dovrseno
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform af = new AffineTransform();
        af.setToScale(scale,scale);
        g2.setTransform(af);
        for(ElementPainter e:painters){
            e.draw(g2);
        }

        g2.setColor(Color.BLACK);
        g2.draw(linija);
        g2.draw(laso);
    }

    @Override
    public void update(Object o) {
            repaint();
    }


}
