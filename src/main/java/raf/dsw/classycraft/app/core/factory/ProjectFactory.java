package raf.dsw.classycraft.app.core.factory;

import javafx.scene.Parent;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.Project;
@Getter
@Setter
public class ProjectFactory extends ClassyNodeFactory{

    private static int cnt = 1;
    public ProjectFactory(ClassyNode parent){}

    @Override
    public ClassyNode createNode(ClassyNode parent) {

        ClassyNode project = new Project(parent, "Project " + cnt, "Autor", "Path");
        cnt++;

        return project;
    }
}
