package raf.dsw.classycraft.app.core.model.composite;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Getter
public abstract class ClassyNodeComposite extends ClassyNode {
    @JsonManagedReference
    private List<ClassyNode> children = new ArrayList<>();

    public ClassyNodeComposite(ClassyNode parent, String name) {
        super(parent, name);
    }

    public abstract void addChild(ClassyNode child);

    public void deleteChild(ClassyNode child){
        children.remove(child);
    }
}
