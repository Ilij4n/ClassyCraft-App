package raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ClassContent {
    private String name;
    private Vidljivost vidljivost;

    public ClassContent(String name, Vidljivost vidljivost) {
        this.name = name;
        this.vidljivost = vidljivost;
    }
}
