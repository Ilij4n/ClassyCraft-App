package raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Enum;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;

import java.awt.*;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Agregacija.class, name = "agregacija"),
        @JsonSubTypes.Type(value = Kompozicija.class, name = "kompozicija"),
        @JsonSubTypes.Type(value = Generalizacija.class, name = "generalizacija"),
        @JsonSubTypes.Type(value = Zavisnost.class, name = "zavisnost"),
})
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonTypeName("connection")
@Getter
@Setter
public abstract class Connection extends DiagramElement {
    @JsonIdentityReference(alwaysAsId = true)
    private InterClass element1;
    @JsonIdentityReference(alwaysAsId = true)
    private InterClass element2;
    private String kardinalnost;
    private String imePromenljive;
    private int id;
    protected static int idGen = 1;
    //mozda treba i staviti vidljivost te promenljiv ali za sad nek bude ovako

    public Connection(ClassyNode parent, String name, Color paint, int stroke, InterClass element1, InterClass element2, String kardinalnost, String imePromenljive) {
        super(parent, name, paint, stroke);
        this.element1 = element1;
        this.element2 = element2;
        this.kardinalnost = kardinalnost;
        this.imePromenljive = imePromenljive;
        id = idGen++;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "element1=" + element2 +
                '}';
    }
}
