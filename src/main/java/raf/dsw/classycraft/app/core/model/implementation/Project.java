package raf.dsw.classycraft.app.core.model.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Project extends ClassyNodeComposite implements IPublisher {

    private String autor;
    private String filePath;
    private List<ISubscriber> subscribers;

    public Project(ClassyNode parent, String name, String autor, String filePath) {
        super(parent, name);
        this.autor = autor;
        this.filePath = filePath;
        subscribers = new ArrayList<>();
    }

    @Override
    public void addChild(ClassyNode child) {
        if(child instanceof Package){
            if(getChildren().contains(child)){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Paket vec postoji", MessageType.ERROR);
            }
            else{
                setAutorOfProject(autor);
                setNameOfProject(this);
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
        for(ISubscriber s:((Package)child).getSubscribers()){
            cpv = (ClassyPackageView) s;
            notifySubs(cpv);
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

    public void setAutorOfProject(Object o){
        autor = (String)o;
        this.notifySubs(o);
    }

    public void setNameOfProject(Object o){
        setName(((Project)o).getName());
        this.notifySubs(o);
    }

}
