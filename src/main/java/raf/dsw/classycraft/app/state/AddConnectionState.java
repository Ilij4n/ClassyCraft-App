package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.*;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Enum;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;
import raf.dsw.classycraft.app.gui.swing.painters.*;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.tree.TreePath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class AddConnectionState implements StateInterface{
    private boolean moze = false;
    private ElementPainter elementPainterPocetni = null;
    private  ElementPainter elementPainterKrajnji = null;
    private boolean nePokazujVisePutaObavestenje = true;

    @Override
    public void misKliknut(Point2D p, ClassyDiagramView c) {

    }

    @Override
    public void misKliknut1(Point2D p, ElementCreationView e) {

    }

    @Override
    public void misPovucen(Point2D p, ClassyDiagramView c) {
        if(moze){
            c.getLinija().setLine(c.getPrvaTacka(),p);
            //System.out.println("vuci1");
            c.repaint();
        }
    }

    @Override
    public void misPritisnut(Point2D p, ClassyDiagramView c) {
        c.setPrvaTacka(p);
        moze = false;
        for(int i =c.getPainters().size()-1;i>=0;i--){
            if(c.getPainters().get(i).elementAt(c.getPrvaTacka())){
                c.getLinija().setLine(p,p);
                moze = true;
                c.repaint();
            }
        }
    }

    @Override
    public void misOtpusten(Point2D p, ClassyDiagramView c) {
        moze = false;


        for (int i = c.getPainters().size() - 1; i >= 0; i--) {
            if (c.getPainters().get(i).elementAt(p)) {
                moze = true;
                elementPainterKrajnji = c.getPainters().get(i);
                break;
            }
        }
        if(elementPainterPocetni==elementPainterKrajnji)moze = false;

        if (moze) {

            for (int i = c.getPainters().size() - 1; i >= 0; i--) {
                if (c.getPainters().get(i).elementAt(c.getPrvaTacka())) {
                    elementPainterPocetni = c.getPainters().get(i);
                    break;
                }
            }
            if(elementPainterPocetni==null||elementPainterKrajnji==null)return;
            if(ElementCreationView.pokazanSam())return;

            for(ElementPainter painter : c.getPainters()){
                if(painter instanceof ConnectionPainter){
                    ElementPainter painter1 = ((ConnectionPainter) painter).getElementPainter1();
                    ElementPainter painter2 = ((ConnectionPainter) painter).getElementPainter2();
                    if(elementPainterPocetni.equals(painter1) && elementPainterKrajnji.equals(painter2) || elementPainterKrajnji.equals(painter1) && elementPainterKrajnji.equals(painter2)){
                        c.setLinija(new Line2D.Double());
                        c.repaint();
                        return;
                    }
                }
            }

            if(nePokazujVisePutaObavestenje){
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Za vezu zavisnosti ne birati radioButton\n U textAreu upisati prvo podatke polja pa onda kardinalitet u redu ispod",MessageType.INFO);
                nePokazujVisePutaObavestenje = false;
            }
            //ako prodje sve provere ovde se dodaje veza
            ElementCreationView e = new ElementCreationView(c,p);
            e.getRadioBtnKlasa().setText("General.");
            e.getRadioBtnInterfejs().setText("Agreg.");
            e.getRadioBtnEnum().setText("Kompo.");
            e.addPlaceholder("/*Primer inputa*/\n- ime\n1..1\nmoguci kardinaliteti:\n1..1, 1..n\nn..1, n..m\n/*veze generalizacije i zavisnosti\nne cuvaju polja*/",e.getTextAreaElementi());


            e.setVisible(true);
        }

        c.setLinija(new Line2D.Double());
        c.repaint();
    }

    @Override
    public void misOtpusten1(Point2D p, ElementCreationView e) {

        ClassyDiagramView c = e.getClassyDiagramView();
       // Connection connection = new Generalizacija(c.getDiagram(),"nesto", (InterClass)elementPainterPocetni.getDiagramElement(),(InterClass) elementPainterKrajnji.getDiagramElement(),"0..1","promenjiva");
        //GeneralizacijaPainter g = new GeneralizacijaPainter(connection,elementPainterPocetni,elementPainterKrajnji);

        Connection connection = null;
        ConnectionPainter connectionPainter = null;

        //todo nastaviti sa parsovanjem podataka i napraviti editState za konekcije
        System.out.println(e.parsujUVezu());
        String polje = "";
        String kardinalnost = "";

        for(String s : e.parsujUVezu()){
            if(s.split(" ").length==2)polje = s;
            else kardinalnost = s;
        }

        if(e.getTfImeElementa().getText().isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Element mora imati ime", MessageType.INFO);
            return;
        }
        if(e.getRadioBtnKlasa().isSelected()){
            //ovde praviti specificne paitnere
            connection = new Generalizacija(c.getDiagram(),e.getTfImeElementa().getText(), (InterClass) elementPainterPocetni.getDiagramElement(),(InterClass) elementPainterKrajnji.getDiagramElement(),"",""); //staviti umesto poljaIMetode null ako ne radi
            connectionPainter = new GeneralizacijaPainter(connection,elementPainterPocetni,elementPainterKrajnji);

        }
        else if(e.getRadioBtnInterfejs().isSelected()){
            connection = new Agregacija(c.getDiagram(),e.getTfImeElementa().getText(), (InterClass) elementPainterPocetni.getDiagramElement(),(InterClass) elementPainterKrajnji.getDiagramElement(),kardinalnost,polje); //staviti umesto poljaIMetode null ako ne radi
            connectionPainter = new AgregacijaPainter(connection,elementPainterPocetni,elementPainterKrajnji);
        }
        else if(e.getRadioBtnEnum().isSelected()){
            connection = new Kompozicija(c.getDiagram(),e.getTfImeElementa().getText(), (InterClass) elementPainterPocetni.getDiagramElement(),(InterClass) elementPainterKrajnji.getDiagramElement(),kardinalnost,polje); //staviti umesto poljaIMetode null ako ne radi
            connectionPainter = new KompozicijaPainter(connection,elementPainterPocetni,elementPainterKrajnji);
        }
        else{
            connection = new Zavisnost(c.getDiagram(),e.getTfImeElementa().getText(), (InterClass) elementPainterPocetni.getDiagramElement(),(InterClass) elementPainterKrajnji.getDiagramElement(),"",""); //staviti umesto poljaIMetode null ako ne radi
            connectionPainter = new ZavisnostPainter(connection,elementPainterPocetni,elementPainterKrajnji);
        }

        if(!c.getPainters().contains(connectionPainter) && !elementPainterPocetni.equals(elementPainterKrajnji)){

            c.getDiagram().addChild(connection);

            ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());
            //Ova metoda pronalazi treenode koji odgovara selectovanom dijagramu i dodaje mu dete tako sto se rekurzivno krece kroz nas JTREE
            ClassyTreeItem diagramNode = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),c.getDiagram());
            //ovo je samo za dodavanje u jtree, u modelu je vec dodat
            c.getPainters().add(connectionPainter);
            MainFrame.getInstance().getClassyTree().addChild(diagramNode,false);
            //samo rasiri sve
            tree.getTreeView().expandPath(new TreePath(diagramNode.getPath()));
            System.out.println(c.getDiagram().getChildren());

        }
    }

    @Override
    public void misSkrolovan(Point2D p, ClassyDiagramView c) {

    }
}
