package raf.dsw.classycraft.app.core.model.composite;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Project;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Project.class, name = "project"),
        @JsonSubTypes.Type(value = Package.class, name = "package"),
        @JsonSubTypes.Type(value = Diagram.class, name = "diagram"),
        @JsonSubTypes.Type(value = DiagramElement.class, name = "diagramElement")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")

public abstract class ClassyNode {
    @JsonBackReference
    private transient ClassyNode parent;
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
