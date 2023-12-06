package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.geom.Point2D;

public class EditState implements StateInterface{

    boolean nePokazujVisePutaObavestenje = true;

    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
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
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Edit element ne poseduje mogucnost promenu samog tipa elementa",MessageType.INFO);
                    nePokazujVisePutaObavestenje = false;
                }

                if(model instanceof InterClass){
                    e.postaviPolja((InterClass) model);
                    e.setTitle("Edit view");
                    e.setVisible(true);
                    break;

                }
                else if(model instanceof Connection){
                    //TODO za sad nista..
                    return;
                }

            }
        }

    }

    @Override
    public void misKliknut1(Point2D p, ElementCreationView e) {

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
                diagramElement = (InterClass)diagramElement;

                diagramElement.setName(e.getTfImeElementa().getText());
                ((InterClass) diagramElement).setContentSet(e.getClassContents());

                //za stablo
                ClassyTreeImplementation tree = (ClassyTreeImplementation) MainFrame.getInstance().getClassyTree();
                ClassyTreeItem item = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(), diagramElement);
                item.setName(e.getTfImeElementa().getText());
                SwingUtilities.updateComponentTreeUI(tree.getTreeView());
            }
        }
        c.repaint();
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
