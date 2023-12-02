package raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses;


import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.ClassContent;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public abstract class InterClass extends DiagramElement {
    private Point2D location;
    private List<ClassContent> contentList;

    public InterClass(ClassyNode parent, String name, Color paint, Integer stroke, Point2D location, List<ClassContent>contentList) {
        super(parent, name, paint, stroke);
        this.location = location;
        //TODO: prosledjivanje liste contenta ce verovatno trebati priv pravljenju samog interklas elementa
        this.contentList = contentList;
    }
}
