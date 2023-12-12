package raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Method extends ClassContent{
    public Method(String name, String vidljivost, String tip) {
        super(name, vidljivost,tip);
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
