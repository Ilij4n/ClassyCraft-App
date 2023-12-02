package raf.dsw.classycraft.app.core.model.composite;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public abstract class DiagramElement extends ClassyNode{

    private Paint paint;
    //videcemo kako cemo ovo da pretvorimo u stroke
    private int stroke;
    //TODO razmisljao sam da za svaku implementacionu klasu njen paint i stroke ne uzimamo kao argument u konstruktoru vec da je settujemo automatski u zavisnosti od implementacije interklase koja se instancira
    public DiagramElement(ClassyNode parent, String name, Paint paint, int stroke) {
        super(parent, name);
        this.paint = paint;
        this.stroke = stroke;
    }
}
