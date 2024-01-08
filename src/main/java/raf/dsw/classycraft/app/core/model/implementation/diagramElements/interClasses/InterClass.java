package raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses;


import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.ClassContent;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Klasa.class, name = "klasa"),
        @JsonSubTypes.Type(value = Interfejs.class, name = "interfejs"),
        @JsonSubTypes.Type(value = Enum.class, name = "enum"),
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonTypeName("interClass")
@Getter
@Setter
@ToString
public abstract class InterClass extends DiagramElement {
    @JsonIgnore
    private Point2D location = new Point2D.Double();
    private double xLocation;
    private double yLocation;
    private Set<ClassContent> contentSet;

    public InterClass(){

    }
    public void loadCoords(){
        location = new Point2D.Double(xLocation,yLocation);
    }

    public InterClass(ClassyNode parent, String name, Color paint, Integer stroke, Point2D location, Set<ClassContent>contentSet) {
        super(parent, name, paint, stroke);
        this.location = location;
        if(xLocation!=0 && yLocation != 0)setLocation(new Point2D.Double(xLocation,yLocation));
        xLocation = location.getX();
        yLocation = location.getY();
        this.contentSet = contentSet;
    }
    public void setLocation(Point2D location){
        this.location = location;
        xLocation = location.getX();
        yLocation = location.getY();
        notifySubs(null);
    }
    public void setLocation1(Point2D location){
        this.location = location;
    }
}
