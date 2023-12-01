package raf.dsw.classycraft.app.core.factory;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
@Getter
@Setter

public class PackageFactory extends ClassyNodeFactory{

    private static int cnt = 1;

    public PackageFactory(ClassyNode parent){}

    @Override
    public ClassyNode createNode(ClassyNode parent) {

            Package aPackage = new Package(parent,"Package "+cnt);
            cnt++;

            return aPackage;


    }
}
