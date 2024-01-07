package raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.NoArgsConstructor;

@JsonTypeName("attribute")
@NoArgsConstructor
public class Attribute extends ClassContent{
    public Attribute(String name, String vidljivost, String tip) {
        super(name, vidljivost,tip);
    }
}
