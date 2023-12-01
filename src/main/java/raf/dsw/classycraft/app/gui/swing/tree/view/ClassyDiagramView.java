package raf.dsw.classycraft.app.gui.swing.tree.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Setter
@Getter
public class ClassyDiagramView extends JPanel implements IPublisher, ISubscriber{
    private String name;
    private Diagram diagram;

    private List<ISubscriber> subscribers;
    public ClassyDiagramView(Diagram d){
        diagram = d;
        this.name = diagram.getName();
        subscribers = new ArrayList<>();
    }


    @Override
    public void update(Object o) {
        if(o instanceof Diagram){

        }
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
}
