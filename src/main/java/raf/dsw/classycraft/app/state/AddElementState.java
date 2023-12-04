package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Enum;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;
import raf.dsw.classycraft.app.gui.swing.painters.ClassPainter;
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

public class AddElementState implements StateInterface{
    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {
        System.out.println(getClass().getSimpleName());
        // da ne bi moglo vise prozora da se otvori
        if(ElementCreationView.pokazanSam())return;
        ElementCreationView e = new ElementCreationView(c,p);
    }

    @Override
    public void misKliknut1(Point2D p, ElementCreationView e) {
        ClassyDiagramView c = e.getClassyDiagramView();
        //Da bi proverio da li je uspesno dodat child u model (ako ime nije duplikat) gledam da li se velicina liste njegove dece promenila, zato uzimam sizePre
        /*
            dosta redudantnog koda: samo pravimo instance onih dijagramElemenata koji je selektovan u radiobuttonu, dodajemo ga u model,
            ako je uspesno dodat, onda ce sizePre liste razlicit  sizePosle liste i onda cemo dodati i njegov painter u listu paintera i zavrsiti posao
            ovo se radi u svakom od ovih ifova, samo se kod ponavlja.
         */
        int sizePre = c.getDiagram().getChildren().size();
        if(e.getRadioBtnKlasa().isSelected()){
            Klasa klasa = new Klasa(c.getDiagram(),e.getTfImeElementa().getText(), p,e.vratiPoljaIMetode()); //staviti umesto poljaIMetode null ako ne radi
            ClassPainter classPainter = new ClassPainter(klasa);
            System.out.println(e.vratiPoljaIMetode());


            c.getDiagram().addChild(klasa);
            if(sizePre == c.getDiagram().getChildren().size()){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Duplikat Ime", MessageType.ERROR);
                return;
            }
            c.getPainters().add(classPainter);
        }
        else if(e.getRadioBtnInterfejs().isSelected()){
            Interfejs interfejs = new Interfejs(c.getDiagram(),e.getTfImeElementa().getText(),p,e.vratiPoljaIMetode());
            InterfacePainter interfacePainter = new InterfacePainter(interfejs);
            c.getDiagram().addChild(interfejs);
            if(sizePre == c.getDiagram().getChildren().size()){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Duplikat Ime", MessageType.ERROR);
                return;
            }
            c.getPainters().add(interfacePainter);
        }
        else{
            Enum enumncina = new Enum(c.getDiagram(),e.getTfImeElementa().getText(),p,e.vratiPoljaIMetode());
            EnumPainter enumPainter = new EnumPainter(enumncina);
            c.getDiagram().addChild(enumncina);
            if(sizePre == c.getDiagram().getChildren().size()){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Duplikat Ime", MessageType.ERROR);
                return;
            }
            c.getPainters().add(enumPainter);
        }
        //TODO ovo mozda radi napisacu DFS staticku metodu (za prolazak kroz celo stablo) u ClassyTreeimpu koja ce proci kroz bukvalno celo stablo i vratiti mi instancu dijagrama koji je aktivan hahahahah
        ClassyTreeImplementation tree = ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree());
        //Ova metoda pronalazi treenode koji odgovara selectovanom dijagramu i dodaje mu dete tako sto se rekurzivno krece kroz nas JTREE
        ClassyTreeItem diagramItem = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),c.getDiagram());
        //ovo je samo za dodavanje u jtree, u modelu je vec dodat
        MainFrame.getInstance().getClassyTree().addChild(diagramItem,false);
        //samo rasiri sve
        tree.getTreeView().expandPath(new TreePath(diagramItem.getPath()));
        System.out.println(c.getDiagram().getChildren());
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
}
