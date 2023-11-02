package raf.dsw.classycraft.app.core.model.implementation;

import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {

    public ProjectExplorer(String name) {
        super(null, name);
    }

    @Override
    public void addChild(ClassyNode child) {
        if(child instanceof Project){
            if(getChildren().contains(child)){
                /*TODO:
                    Ovde ce ici generacija poruke kad se napravi MessageGenerator
                    "Projekat vec postoji"
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
