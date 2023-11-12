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
    private int cnt;
    private Package aPackage;
    public PackageFactory(ClassyNode parent){
        createNode(parent);
    }
    public ClassyNode createNode(ClassyNode parent){
            aPackage = new Package(parent,"Package"+cnt);
            ((Project) parent).getPackages().add(aPackage);
            ClassyPackageView packageView = new ClassyPackageView();
            packageView.getLblProjectName().setText("Project name: "+ aPackage.projectName());
            packageView.getLblAuthorname().setText("Author: "+ aPackage.authorName());
            aPackage.setClassyPackageView(packageView);
            aPackage.addSub(packageView);
            return aPackage;
    }
}
