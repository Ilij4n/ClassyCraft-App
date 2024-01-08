package raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.NoArgsConstructor;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;

import java.awt.*;
@NoArgsConstructor
@JsonTypeName("agregacija")
public class Agregacija extends Connection{
    public Agregacija(ClassyNode parent, String name, InterClass element1, InterClass element2, String kardinalnost, String imePromenljive) {
        super(parent, name, Color.pink, 2, element1, element2, kardinalnost, imePromenljive);
    }
}
