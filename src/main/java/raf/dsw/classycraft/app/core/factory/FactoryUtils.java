package raf.dsw.classycraft.app.core.factory;

import raf.dsw.classycraft.app.core.model.composite.ClassyNode;

public class FactoryUtils {

    public static ClassyNodeFactory returnFactory(String string, ClassyNode parent){
        if (string.equals("DIAGRAM")) {
            return new DiagramFactory(parent);
        } else if (string.equals("PACKAGE")) {
            return new PackageFactory(parent);
        } else if (string.equals("PROJECT")) {
            return new ProjectFactory(parent);
        }
        return null;
    }
}
