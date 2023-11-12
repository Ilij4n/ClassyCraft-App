package raf.dsw.classycraft.app.gui.swing.tree.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.util.Objects;
@Setter
@Getter
public class ClassyDiagramView extends JPanel implements ISubscriber{
    private String name;
    private Diagram diagram =  null;

    public ClassyDiagramView(){

    }


    @Override
    public void update(Object o) {
        if (true){}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassyDiagramView that = (ClassyDiagramView) o;
        return Objects.equals(name, that.name) && Objects.equals(diagram, that.diagram);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, diagram);
    }
}
