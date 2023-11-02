package raf.dsw.classycraft.app.core.model.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
@Getter
@Setter
public class Project extends ClassyNodeComposite {
    private String autor;

    public Project(ClassyNode parent, String name, String autor) {
        super(parent, name);
        this.autor = autor;
    }

    @Override
    public void addChild(ClassyNode child) {
        if(child instanceof Package){
            if(getChildren().contains(child)){
                /*TODO:
                    Ovde ce ici generacija poruke kad se napravi MessageGenerator
                    "Package vec postoji"
                 */
            }
            else{
                getChildren().add(child);
            }
        }
        else{
            /*TODO:
                Ovde ce ici generacija poruke kad se napravi MessageGenerator
                "Pogresen tip" ili tako nesto
             */
        }
    }
}
