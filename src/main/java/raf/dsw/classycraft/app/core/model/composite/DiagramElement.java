package raf.dsw.classycraft.app.core.model.composite;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class DiagramElement extends ClassyNode implements IPublisher {
    private static int counter = 1;
    private Color color;
    private Integer stroke;
    private List<ISubscriber> subscribers;

    public DiagramElement(ClassyNode parent, String name, Color paint, Integer stroke) {
        super(parent, name);
        this.color = paint;
        this.stroke = stroke;
        super.setName(super.getName() /*+ String.valueOf(counter)*/);
        subscribers = new ArrayList<>();
        counter++;
    }
    @Override
    public void notifySubs(Object o){
        for (ISubscriber subscriber : subscribers){
            subscriber.update(o);
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
}
