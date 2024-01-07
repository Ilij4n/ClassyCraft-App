package raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.NoArgsConstructor;
import lombok.ToString;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.ClassContent;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Set;
@JsonTypeName("klasa")
public class Klasa extends InterClass{

    public Klasa(){
        setColor(Color.GREEN);
    }

    public Klasa(ClassyNode parent, String name, Point2D location, Set<ClassContent> contentSet) {
        super(parent, name, Color.GREEN, 2, location, contentSet);
    }

}
