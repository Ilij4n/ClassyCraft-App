package raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class ClassContent {
    private String name;
    private String vidljivost;
    private String tip;

    public ClassContent(String vidljivost,String tip, String name ) {
        this.name = name;
        this.vidljivost = vidljivost;
        this.tip = tip;
    }

    @Override
    public String toString() {
        return vidljivost + tip + " " + name;
    }
}
