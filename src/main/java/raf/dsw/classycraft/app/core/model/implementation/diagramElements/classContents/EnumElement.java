package raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EnumElement extends ClassContent{
    public EnumElement(String name) {
        super(name, "+","Enum");
    }
}
