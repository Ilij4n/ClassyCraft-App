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

    private String prvoVeliko(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    @Override
    public String toString() {
        if(this instanceof Method) return vidljivost +" "+ name +"()" + " : " + prvoVeliko(tip);
        return vidljivost +" "+ name + " : " + prvoVeliko(tip);
    }
}
