package raf.dsw.classycraft.app.core.model;

import lombok.Getter;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.ProjectExplorer;

@Getter
public class ClassyRepositoryImp implements ClassyRepository {

    private final ClassyNode root;

    public ClassyRepositoryImp() {
        root = new ProjectExplorer("ProjectExplorer");
    }

    @Override
    public ClassyNode getRoot() {
        return root;
    }
}
