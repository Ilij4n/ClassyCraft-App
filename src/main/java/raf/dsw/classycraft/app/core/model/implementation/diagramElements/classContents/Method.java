package raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@JsonTypeName("method")
@NoArgsConstructor
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
