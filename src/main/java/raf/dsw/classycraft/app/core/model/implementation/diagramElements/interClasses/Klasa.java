package raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses;

import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.ClassContent;

import java.awt.*;
import java.util.List;

public class Klasa extends InterClass{
    public Klasa(ClassyNode parent, String name, Paint paint, int stroke, Point location, List<ClassContent> contentList) {
        super(parent, name, paint, stroke, location, contentList);
    }
}
