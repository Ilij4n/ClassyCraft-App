package raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections;

import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;

import java.awt.*;

public class Zavisnost extends Connection{

    public Zavisnost(ClassyNode parent, String name, InterClass element1, InterClass element2, String kardinalnost, String imePromenljive) {
        super(parent, name, Color.orange, 2, element1, element2, kardinalnost, imePromenljive);
    }
}
