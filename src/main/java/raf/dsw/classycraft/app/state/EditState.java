package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.command.sveKomande.EditCommand;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.classContents.ClassContent;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Generalizacija;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Zavisnost;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.HashSet;

public class EditState implements StateInterface{

    boolean nePokazujVisePutaObavestenje = true;

    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
        p.setLocation(p.getX()/c.getScale(),p.getY()/c.getScale());
        //System.out.println(getClass().getSimpleName());
        // da ne bi moglo vise prozora da se otvori
        if(ElementCreationView.pokazanSam())return;

        for(int i = c.getPainters().size() - 1 ; i>=0 ; i--){
            ElementPainter painter = c.getPainters().get(i);
            if(painter.elementAt(p)){
                DiagramElement model = painter.getDiagramElement();
                ElementCreationView e = new ElementCreationView(c,p);
                e.isAlwaysOnTop();

                if(nePokazujVisePutaObavestenje){
                    //ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Edit element ne poseduje mogucnost promenu samog tipa elementa",MessageType.INFO);
                    nePokazujVisePutaObavestenje = false;
                }

                if(model instanceof InterClass){
                    e.postaviPolja((InterClass) model);
                    e.setTitle("Edit view");
                    e.sakrijZaEdit();
                    e.setVisible(true);
                    break;

                }
                else if(model instanceof Connection){
                    e.setTitle("Edit view");
                    e.getRadioBtnKlasa().setText("General.");
                    e.getRadioBtnInterfejs().setText("Agreg.");
                    e.getRadioBtnEnum().setText("Kompo.");

                    if(model instanceof Zavisnost || model instanceof Generalizacija){
                        e.getRadioBtnKlasa().setSelected(true);
                        e.getTextAreaElementi().hide();
                        e.getLabelNapisati().hide();
                    }
                    else e.getRadioBtnInterfejs().setSelected(true);

                    e.sakrijZaEdit();

                    e.getTfImeElementa().setText(model.getName());
                    System.out.println(((Connection) model).getImePromenljive());
                    e.getTextAreaElementi().setText(((Connection) model).getImePromenljive()+"\n"+((Connection) model).getKardinalnost());
                    e.setVisible(true);
                    return;
                }

            }
        }

    }

    @Override
    public void misKliknut1(Point2D p, ElementCreationView e) {
        String name = null;
        HashSet<ClassContent> set = null;
        String content = null;
        if(e.getTfImeElementa().getText().isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Element mora imati ime", MessageType.INFO);
            return;
        }
        ClassyDiagramView c = e.getClassyDiagramView();
        DiagramElement diagramElement = null;

        for(int i = c.getPainters().size()-1 ; i>=0; i--){
            ElementPainter painter = c.getPainters().get(i);
            if(painter.elementAt(p)){
                diagramElement = painter.getDiagramElement();
                break;
            }
        }
        if(diagramElement!=null){
            if(diagramElement instanceof InterClass){
                //za model
                for(ElementPainter painter: c.getPainters()){
                    if(painter.getDiagramElement().getName().equals(e.getTfImeElementa().getText()) && painter.getDiagramElement() != diagramElement){
                        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Duplikat ime", MessageType.INFO);
                        return;
                    }
                }
                name = diagramElement.getName();
                set = (HashSet)((InterClass) diagramElement).getContentSet();
                diagramElement.setName(e.getTfImeElementa().getText());
                ((InterClass) diagramElement).setContentSet(e.getClassContents());
            }
            else if(diagramElement instanceof Connection){
                diagramElement.setName(e.getTfImeElementa().getText());

                String polje = "";
                String kardinalnost = "";
                for(String s : e.parsujUVezu()){
                    if(s.split(" ").length==2)polje = s;
                    else kardinalnost = s;
                }
                name = diagramElement.getName();
                content = ((Connection) diagramElement).getKardinalnost();
                ((Connection) diagramElement).setImePromenljive(polje);
                ((Connection) diagramElement).setKardinalnost(kardinalnost);
            }
        }
        EditCommand command = new EditCommand(c,e,name,content,set,diagramElement);
        c.getCommandManager().addCommand(command);

    }

    @Override
    public void misPovucen(Point2D p, ClassyDiagramView c) {

    }

    @Override
    public void misPritisnut(Point2D p, ClassyDiagramView c) {

    }

    @Override
    public void misOtpusten(Point2D p, ClassyDiagramView c) {

    }

    @Override
    public void misOtpusten1(Point2D p, ElementCreationView e) {

    }

    @Override
    public void misSkrolovan(Point2D p, ClassyDiagramView c) {

    }


}
