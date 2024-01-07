package raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Attribute.class, name = "attribute"),
        @JsonSubTypes.Type(value = Method.class, name = "method"),
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
@NoArgsConstructor
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
