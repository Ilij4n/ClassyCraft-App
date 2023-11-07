package raf.dsw.classycraft.app.core.model.composite;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
public abstract class ClassyNode {
    private ClassyNode parent;
    private String name;

    public ClassyNode(ClassyNode parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
