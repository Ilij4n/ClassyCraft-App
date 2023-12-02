package raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections;

import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;

import java.awt.*;

public class Generalizacija extends Connection{

    public Generalizacija(ClassyNode parent, String name, Paint paint, int stroke, InterClass element1, InterClass element2, String kardinalnost, String imePromenljive) {
        super(parent, name, paint, stroke, element1, element2, kardinalnost, imePromenljive);
    }
}
