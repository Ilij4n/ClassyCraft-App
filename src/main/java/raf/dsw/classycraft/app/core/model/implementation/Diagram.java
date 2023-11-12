package raf.dsw.classycraft.app.core.model.implementation;

import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class Diagram extends ClassyNode implements IPublisher {
    private List<ISubscriber> subs;
    private ClassyDiagramView classyDiagramView;

    public Diagram(ClassyNode parent, String name) {
        super(parent, name);
        subs = new ArrayList<>();
    }

    @Override
    public void addSub(ISubscriber subscriber) {
        subs.add(subscriber);
    }

    @Override
    public void removeSub(ISubscriber subscriber) {
        subs.remove(subscriber);
    }

    @Override
    public void notifySubs(Object o) {
        for(ISubscriber s:subs){
            s.update(o);
        }
    }

    public ClassyDiagramView getClassyDiagramView() {
        return classyDiagramView;
    }

    public void setClassyDiagramView(ClassyDiagramView classyDiagramView) {
        this.classyDiagramView = classyDiagramView;
    }
}
