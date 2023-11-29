package raf.dsw.classycraft.app.core.factory;

import raf.dsw.classycraft.app.core.model.composite.ClassyNode;

public abstract class ClassyNodeFactory {
    public ClassyNodeFactory(){}
    public abstract ClassyNode createNode(ClassyNode parent);
}
