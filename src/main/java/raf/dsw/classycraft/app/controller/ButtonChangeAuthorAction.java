package raf.dsw.classycraft.app.controller;

import lombok.Setter;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.ChangeAuthorFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
@Setter
public class ButtonChangeAuthorAction extends AbstractClassyAction{
    private ChangeAuthorFrame authorFrame;
    public ButtonChangeAuthorAction(){
        putValue(NAME,"Change author !");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String string = authorFrame.getTextField().getText();
        ((Project) MainFrame.getInstance().getClassyTree().getSelectednode().getClassyNode()).setAutor(string);
        for(ClassyNode node :(((Project) MainFrame.getInstance().getClassyTree().getSelectednode().getClassyNode()).getChildren())){
            ((Package)node).notifySubs((Project) MainFrame.getInstance().getClassyTree().getSelectednode().getClassyNode());
        }
        authorFrame.show(false);
    }
}
