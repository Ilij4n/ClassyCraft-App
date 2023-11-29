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
        if(parent instanceof Package){
            Package aPackage = new Package(parent,"Package"+cnt);
            ((Package) parent).realPapa().getPackages().add(aPackage);
            ClassyPackageView packageView = new ClassyPackageView();
            packageView.getLblProjectName().setText("Project name: "+ aPackage.projectName());
            packageView.getLblAuthorname().setText("Author: "+ aPackage.authorName());
            aPackage.setClassyPackageView(packageView);
            aPackage.addSub(packageView);
            cnt++;
            return aPackage;
        }else{
            Package aPackage = new Package(parent,"Package"+cnt);
            ((Project) parent).getPackages().add(aPackage);
            ClassyPackageView packageView = new ClassyPackageView();
            packageView.getLblProjectName().setText("Project name: "+ aPackage.projectName());
            packageView.getLblAuthorname().setText("Author: "+ aPackage.authorName());
            aPackage.setClassyPackageView(packageView);
            aPackage.addSub(packageView);
            cnt++;
            return aPackage;
        }
    }
}
