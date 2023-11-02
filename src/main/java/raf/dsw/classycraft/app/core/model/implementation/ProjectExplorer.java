package raf.dsw.classycraft.app.core.model.implementation;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
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
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Projekat vec postoji", MessageType.ERROR);
            }
            else{
                getChildren().add(child);
            }
        }
        else{
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Pogresen tip", MessageType.ERROR);
        }
    }

    @Override
    public void deleteChild(ClassyNode child) {

    }
}
