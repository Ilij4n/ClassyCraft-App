package raf.dsw.classycraft.app.gui.swing.tree.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Setter
@Getter
public class ClassyDiagramView extends ImprovedJPanel implements IPublisher, ISubscriber{
    private String name;
    private Diagram diagram;
    private List<ElementPainter> painters = new ArrayList<>();

    private List<ISubscriber> subscribers;
    public ClassyDiagramView(Diagram d){
        diagram = d;
        this.name = diagram.getName();
        subscribers = new ArrayList<>();
        setBackground(Color.WHITE);
    }


    @Override
    protected void paintComponent(Graphics g) {
        //TODO: nije dovrseno
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(ElementPainter e : painters){
            e.draw(g2);
        }
    }

    @Override
    public void update(Object o) {

    }


    @Override
    public void addSub(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSub(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubs(Object o) {
        for(ISubscriber s:subscribers){
            s.update(o);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
