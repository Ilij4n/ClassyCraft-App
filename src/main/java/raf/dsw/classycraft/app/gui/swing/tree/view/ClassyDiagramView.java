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

    public ClassyDiagramView(){}


    @Override
    public void update(Object o) {

    }


}
