package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.Method;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;
import raf.dsw.classycraft.app.gui.swing.painters.ClassPainter;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

public class CreateInterClassAction extends AbstractClassyAction{

    private ElementCreationView elementCreationView;

    public CreateInterClassAction(ElementCreationView elementCreationView){
        this.elementCreationView = elementCreationView;
        putValue(NAME,"Create!!!");
        putValue(SHORT_DESCRIPTION,"Create!!!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* fixme vraticu ovo ako ne ide
        elementCreationView.getClassyDiagramView().setName(elementCreationView.getTfImeElementa().getText());
        elementCreationView.setGoNext(false);
        elementCreationView.dispose();*/

        //OVDE MOZDA DA ZOVEMO mediator.ad

        if(elementCreationView.vratiPoljaIMetode().contains(new Method("error","error","error"))){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Format unosa nije ispravan, pokusajte ponovo", MessageType.INFO);
            return;
        }
        ((ClassyPackageView)MainFrame.getInstance().getSplitPane().getRightComponent()).misKliknut1(elementCreationView.getPoint2D(),elementCreationView);
        elementCreationView.dispose();

    }
}
