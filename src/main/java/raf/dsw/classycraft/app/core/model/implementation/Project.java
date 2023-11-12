package raf.dsw.classycraft.app.core.model.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;

import java.util.List;

@Getter
@Setter
public class Project extends ClassyNodeComposite {
    private String autor;
    private String filePath;

    public Project(ClassyNode parent, String name, String autor, String filePath) {
        super(parent, name);
        this.autor = autor;
        this.filePath = filePath;
    }

    @Override
    public void addChild(ClassyNode child) {
        if(child instanceof Package){
            if(getChildren().contains(child)){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Paket vec postoji", MessageType.ERROR);
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
