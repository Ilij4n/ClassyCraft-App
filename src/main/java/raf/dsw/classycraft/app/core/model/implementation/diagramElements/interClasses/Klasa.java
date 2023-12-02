package raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses;

import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.ClassContent;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class Klasa extends InterClass{
    public Klasa(ClassyNode parent, String name, Point2D location, List<ClassContent> contentList) {
        super(parent, name, Color.GREEN, 2, location, contentList);
    }
}
