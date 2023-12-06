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
import raf.dsw.classycraft.app.state.AddConnectionState;

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
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Format unosa nije ispravan, koristite razmak izmedju vrednosti metode i polja", MessageType.INFO);
            return;
        }
        ClassyPackageView mediator = ((ClassyPackageView)MainFrame.getInstance().getSplitPane().getRightComponent());

        if(!(mediator.getStateManager().getCurrentState() instanceof AddConnectionState)){
            if(!elementCreationView.getRadioBtnKlasa().isSelected() && !elementCreationView.getRadioBtnEnum().isSelected() && !elementCreationView.getRadioBtnInterfejs().isSelected()){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Izaberite jednu od opcija", MessageType.INFO);
                return;
            }
            mediator.misKliknut1(elementCreationView.getPoint2D(),elementCreationView);
        }
        else if(elementCreationView.parsujUVezu().contains("//neispravan format//")){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Ne valja format", MessageType.INFO);
            return;
        }
        else mediator.misOtpusten1(elementCreationView.getPoint2D(),elementCreationView);
        elementCreationView.dispose();

    }
}
