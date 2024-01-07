package raf.dsw.classycraft.app.core.model.composite;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InterClass.class, name = "interClass"),
        @JsonSubTypes.Type(value = Connection.class, name = "connection"),
})
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
@JsonTypeName("diagramElement")
@Getter
@Setter
public abstract class DiagramElement extends ClassyNode implements IPublisher {
    private static int counter = 1;
    @JsonIgnore
    private Color color;
    private Integer stroke;
    @JsonIgnore
    private transient List<ISubscriber> subscribers = new ArrayList<>();

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
