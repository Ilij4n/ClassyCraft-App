package raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses;

import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.ClassContent;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Set;

public class Interfejs extends InterClass{
    public Interfejs(ClassyNode parent, String name, Point2D location, Set<ClassContent> contentSet) {
        super(parent, name, Color.blue, 2, location, contentSet);
    }
}
