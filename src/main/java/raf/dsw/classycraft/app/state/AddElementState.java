package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.command.sveKomande.AddElementCommand;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Enum;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;
import raf.dsw.classycraft.app.gui.swing.painters.ClassPainter;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.painters.EnumPainter;
import raf.dsw.classycraft.app.gui.swing.painters.InterfacePainter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class AddElementState implements StateInterface{
    ClassyDiagramView classyDiagramView;

    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
        p.setLocation(p.getX()/c.getScale(),p.getY()/c.getScale());
        System.out.println(getClass().getSimpleName());
        classyDiagramView = c;
        // da ne bi moglo vise prozora da se otvori
        Rectangle2D rect = new Rectangle2D.Double(p.getX(),p.getY(), 100, 150);
        for(ElementPainter painter : classyDiagramView.getPainters()){
            if(rect.intersects(painter.getOblik())){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Poklapaju se", MessageType.INFO);
                return;
            }
        }


        if(ElementCreationView.pokazanSam())return;
        ElementCreationView e = new ElementCreationView(classyDiagramView,p);
        e.setVisible(true);
    }

    @Override
    public void misKliknut1(Point2D p, ElementCreationView e) {
        classyDiagramView = e.getClassyDiagramView();
        ElementPainter elementPainter = null;
        InterClass i = null;

        //Da bi proverio da li je uspesno dodat child u model (ako ime nije duplikat) gledam da li se velicina liste njegove dece promenila, zato uzimam sizePre
        /*
            dosta redudantnog koda: samo pravimo instance onih dijagramElemenata koji je selektovan u radiobuttonu, dodajemo ga u model,
            ako je uspesno dodat, onda ce sizePre liste razlicit  sizePosle liste i onda cemo dodati i njegov painter u listu paintera i zavrsiti posao
            ovo se radi u svakom od ovih ifova, samo se kod ponavlja.
         */
        if(e.getTfImeElementa().getText().isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Element mora imati ime", MessageType.INFO);
            return;
        }
        int sizePre = classyDiagramView.getDiagram().getChildren().size();
        if(e.getRadioBtnKlasa().isSelected()){
            i = new Klasa(classyDiagramView.getDiagram(),e.getTfImeElementa().getText(), p,e.vratiPoljaIMetode());
            elementPainter= new ClassPainter((Klasa) i);
            System.out.println(e.vratiPoljaIMetode());


            classyDiagramView.getDiagram().addChild(i);
            if(sizePre == classyDiagramView.getDiagram().getChildren().size()){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Duplikat Ime", MessageType.ERROR);
                return;
            }
            i.addSub(classyDiagramView);
            classyDiagramView.getPainters().add(elementPainter);
        }
        else if(e.getRadioBtnInterfejs().isSelected()){
            i = new Interfejs(classyDiagramView.getDiagram(),e.getTfImeElementa().getText(),p,e.vratiPoljaIMetode());
            elementPainter = new InterfacePainter((Interfejs) i);
            classyDiagramView.getDiagram().addChild(i);
            if(sizePre == classyDiagramView.getDiagram().getChildren().size()){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Duplikat Ime", MessageType.ERROR);
                return;
            }
            i.addSub(classyDiagramView);
            classyDiagramView.getPainters().add(elementPainter);
        }
        else if(e.getRadioBtnEnum().isSelected()){
            i = new Enum(classyDiagramView.getDiagram(),e.getTfImeElementa().getText(),p,e.vratiPoljaIMetode());
            elementPainter = new EnumPainter((Enum) i);
            classyDiagramView.getDiagram().addChild(i);
            if(sizePre == classyDiagramView.getDiagram().getChildren().size()){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Duplikat Ime", MessageType.ERROR);
                return;
            }
            i.addSub(classyDiagramView);
            classyDiagramView.getPainters().add(elementPainter);
        }

        //dfs
        ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());
        //Ova metoda pronalazi treenode koji odgovara selectovanom classynodeu i dodaje mu dete tako sto se rekurzivno krece kroz nas JTREE
        ClassyTreeItem diagramItem = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),classyDiagramView.getDiagram());
        //ovo je samo za dodavanje u jtree, u modelu je vec dodat
        MainFrame.getInstance().getClassyTree().addChild(diagramItem,false);
        //samo rasiri sve
        tree.getTreeView().expandPath(new TreePath(diagramItem.getPath()));
        System.out.println(classyDiagramView.getDiagram().getChildren());

        AddElementCommand command = new AddElementCommand(classyDiagramView,i,elementPainter,diagramItem);
        classyDiagramView.getCommandManager().addCommand(command);
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
