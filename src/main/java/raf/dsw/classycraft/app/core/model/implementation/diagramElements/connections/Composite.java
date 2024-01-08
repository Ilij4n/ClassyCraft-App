package raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections;

import lombok.NoArgsConstructor;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;

import java.awt.*;
@NoArgsConstructor
public class Composite extends Connection{

    public Composite(ClassyNode parent, String name, InterClass element1, InterClass element2, String kardinalnost, String imePromenljive) {
        super(parent, name, Color.black, 2, element1, element2, kardinalnost, imePromenljive);
    }
}
