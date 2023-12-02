package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;
import raf.dsw.classycraft.app.gui.swing.painters.ClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;

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
        if(elementCreationView.getRadioBtnKlasa().isSelected()){
            Klasa klasa = new Klasa(elementCreationView.getClassyDiagramView().getDiagram(),elementCreationView.getClassyDiagramView().getName(), elementCreationView.getLocation(),null);
            ClassPainter classPainter = new ClassPainter(klasa);
            elementCreationView.getClassyDiagramView().getPainters().add(classPainter);
            elementCreationView.getClassyDiagramView().getDiagram().addChild(klasa);
        } else if (elementCreationView.getRadioBtnInterfejs().isSelected()) {

        }else {

        }
        elementCreationView.dispose();
    }
}
