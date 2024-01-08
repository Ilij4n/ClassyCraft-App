package raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.NoArgsConstructor;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.ClassContent;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Set;
@JsonTypeName("interfejs")
public class Interfejs extends InterClass{

    public Interfejs(){
        setColor(Color.BLUE);
    }

    public Interfejs(ClassyNode parent, String name, Point2D location, Set<ClassContent> contentSet) {
        super(parent, name, Color.blue, 2, location, contentSet);
    }
}
