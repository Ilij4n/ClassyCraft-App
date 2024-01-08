package raf.dsw.classycraft.app.core.model.composite;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@EqualsAndHashCode
//@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Project.class, name = "project"),
        @JsonSubTypes.Type(value = raf.dsw.classycraft.app.core.model.implementation.Package.class, name = "package"),
        @JsonSubTypes.Type(value = Diagram.class, name = "diagram"),
        @JsonSubTypes.Type(value = DiagramElement.class, name = "diagramElement")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public abstract class ClassyNode {
    @JsonBackReference
    private transient ClassyNode parent;
    private String name;
    protected int id;
    protected static final List<Integer> zauzetiId = new ArrayList<>();

    public ClassyNode(){
            int id1 = new Random().nextInt(Integer.MAX_VALUE);
            while(zauzetiId.contains(id1))id1 =  new Random().nextInt(Integer.MAX_VALUE);
            zauzetiId.add(id1);
            id = id1;
    }


    public ClassyNode(ClassyNode parent, String name) {
        this.parent = parent;
        this.name = name;
        int id1 = new Random().nextInt(Integer.MAX_VALUE);
        while(zauzetiId.contains(id1))id1 =  new Random().nextInt(Integer.MAX_VALUE);
        zauzetiId.add(id1);
        id = id1;

    }

    public void setId(){
        int id1 = new Random().nextInt(Integer.MAX_VALUE);
        while(zauzetiId.contains(id1))id1 =  new Random().nextInt(Integer.MAX_VALUE);
        zauzetiId.add(id1);
        id = id1;
    }

    @Override
    public String toString() {
        return name;
    }
}
