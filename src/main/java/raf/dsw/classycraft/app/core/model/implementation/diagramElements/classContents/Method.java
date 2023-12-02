package raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Method extends ClassContent{
    private String povratnaVrednost;

    public Method(String name, Vidljivost vidljivost, String povratnaVrednost) {
        super(name, vidljivost);
        this.povratnaVrednost = povratnaVrednost;
    }
}
