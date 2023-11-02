package raf.dsw.classycraft.app.core.model.composite;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public abstract class ClassyNodeComposite extends ClassyNode {
    private List<ClassyNode> children = new ArrayList<ClassyNode>();

    public ClassyNodeComposite(ClassyNode parent, String name) {
        super(parent, name);
    }

    public abstract void addChild(ClassyNode child);
}
