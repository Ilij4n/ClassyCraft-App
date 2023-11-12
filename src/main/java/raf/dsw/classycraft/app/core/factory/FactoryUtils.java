package raf.dsw.classycraft.app.core.factory;

import raf.dsw.classycraft.app.core.model.composite.ClassyNode;

public class FactoryUtils {
    private DiagramFactory diagramFactory;
    private PackageFactory packageFactory;
    private ProjectFactory projectFactory;
    public static ClassyNodeFactory returnFactory(String string, ClassyNode parent,Boolean pakOrDia){
        if (string.equals("DIAGRAM")) {
            return new DiagramFactory(parent,pakOrDia);
        } else if (string.equals("PACKAGE")) {
            return new PackageFactory(parent);
        } else if (string.equals("PROJECT")) {
            return new ProjectFactory(parent);
        }
        return null;
    }
}
