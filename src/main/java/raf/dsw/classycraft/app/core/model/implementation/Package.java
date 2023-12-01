package raf.dsw.classycraft.app.core.model.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter

public class Package extends ClassyNodeComposite implements IPublisher{

    private List<ISubscriber> subscribers;


    public Package(ClassyNode parent, String name) {
        super(parent, name);
        subscribers = new ArrayList<>();
    }

    @Override
    public void addChild(ClassyNode child) {
        if(child instanceof Package || child instanceof Diagram){
            if(getChildren().contains(child)){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Paket/diagram vec postoji", MessageType.ERROR);
            }
            else{
                getChildren().add(child);
            }
        }
        else{
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Pogresen tip", MessageType.ERROR);
        }
    }

    @Override
    public void deleteChild(ClassyNode child) {
        ClassyPackageView cpv = null;
        if(child instanceof Package){
            for(ISubscriber s:getSubscribers()){
                cpv = (ClassyPackageView) s;
                notifySubs(cpv);
            }
        }else if (child instanceof Diagram){
            notifySubs(child);
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
        for ( ISubscriber i:subscribers){
            i.update(o);
        }
    }


    public Project realPapa(){
        ClassyNode project = getParent();
        while(true){
            if(project instanceof Project) {
                break;
            }else{
                project = project.getParent();
            }
        }
        return (Project) project;
    }

    public String authorName(){
        return realPapa().getAutor();
    }

    public String projectName(){
        return realPapa().getName();
    }

    public void ChangeNameOfDiagramView(Object o){
        notifySubs(o);
    }

    public void addingOfDiagramView(Object o){
        notifySubs(o);
    }


}
