package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.ChangeAuthorFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AuthorChangeAction extends AbstractClassyAction{

    public AuthorChangeAction(){
        putValue(SMALL_ICON,loadIcon("/images/changeauthor.png"));
        putValue(NAME,"Change author");
        putValue(SHORT_DESCRIPTION,"Change author");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyNode item = MainFrame.getInstance().getClassyTree().getSelectednode().getClassyNode();
        if( item instanceof Project){
            ChangeAuthorFrame changeAuthorFrame = new ChangeAuthorFrame();
            changeAuthorFrame.show();
        }else {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Izaberite projekat kome zelite da promenite autora ! ", MessageType.ERROR);
        }
    }
}
