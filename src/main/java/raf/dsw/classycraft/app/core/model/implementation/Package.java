package raf.dsw.classycraft.app.core.model.implementation;

import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;

public class Package extends ClassyNodeComposite {

    public Package(ClassyNode parent, String name) {
        super(parent, name);
    }

    @Override
    public void addChild(ClassyNode child) {
        if(child instanceof Package || child instanceof Diagram){
            if(getChildren().contains(child)){
                /*TODO:
                    Ovde ce ici generacija poruke kad se napravi MessageGenerator
                    "Paket/diagram vec postoji"
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

    @Override
    public void deleteChild(ClassyNode child) {

    }
}
