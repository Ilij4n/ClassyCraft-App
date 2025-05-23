package raf.dsw.classycraft.app.core.model.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
@JsonTypeName("diagram")
@NoArgsConstructor
@Getter
@Setter
public class Diagram extends ClassyNodeComposite implements IPublisher {
    @JsonIgnore
    private transient List<ISubscriber> subscribers = new ArrayList<>();

    public Diagram(ClassyNode parent, String name) {
        super(parent, name);
        subscribers = new ArrayList<>();
    }

    @Override
    public void addChild(ClassyNode child) {
        //dodajemo iskljucivo diagramElemente na diagram
        if(child instanceof DiagramElement){
            if(!(child instanceof Connection)&&getChildren().contains(child)){
                //ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Paket/diagram vec postoji", MessageType.ERROR);
                return;
            }
            else{
                getChildren().add(child);
                notifySubs(child);
            }
        }
        else{
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Pogresen tip", MessageType.ERROR);
        }
    }
    @Override
    public void deleteChild(ClassyNode classyNode){
        getChildren().remove(classyNode);
        notifySubs(classyNode);
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
