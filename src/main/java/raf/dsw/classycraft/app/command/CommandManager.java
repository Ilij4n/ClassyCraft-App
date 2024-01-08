package raf.dsw.classycraft.app.command;

import javafx.scene.web.HTMLEditorSkin;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.MessageGenerator.Message;
import raf.dsw.classycraft.app.MessageGenerator.MessageGenerator;
import raf.dsw.classycraft.app.MessageGenerator.MessageGeneratorImp;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class CommandManager {

    private List<AbstractCommand> commands;
    private int ptr = 0;

    public CommandManager(){
        commands = new ArrayList<>();
    }

    public void addCommand(AbstractCommand command){
        while(ptr<commands.size()){
            commands.remove(ptr);
        }
        commands.add(ptr,command);
        doCommand();
    }

    public void doCommand(){
        if(ptr<commands.size()){
            commands.get(ptr++).doCommand();
        }
        if(ptr==commands.size()){
            System.out.println("Na kraju si");
            ptr=commands.size();
        }
    }

    public void undoCommand(){
        if(ptr>0){
            commands.get(--ptr).undoCommand();
        }
        if(ptr<=0){
            System.out.println("Na kraju si pocetka");
            ptr=0;
        }
    }
}

