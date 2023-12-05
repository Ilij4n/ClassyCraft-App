package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Generalizacija;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.gui.swing.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.painters.GeneralizacijaPainter;
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

        ElementPainter elementPainterPocetni = null;
        ElementPainter elementPainterKrajnji = null;

        for (int i = c.getPainters().size() - 1; i >= 0; i--) {
            if (c.getPainters().get(i).elementAt(p)) {
                moze = true;
                elementPainterKrajnji = c.getPainters().get(i);
                break;
            }
        }


        if (moze) {

            for (int i = c.getPainters().size() - 1; i >= 0; i--) {
                if (c.getPainters().get(i).elementAt(c.getPrvaTacka())) {
                    elementPainterPocetni = c.getPainters().get(i);
                    break;
                }
            }
            if(elementPainterPocetni==null||elementPainterKrajnji==null)return;
            //TODO ovde naci nacin da proveris kako da pravis razlicite veze, verovatno kao miskliknut1 u dodajElementStateu
            Connection connection = new Generalizacija(c.getDiagram(),"nesto", (InterClass)elementPainterPocetni.getDiagramElement(),(InterClass) elementPainterKrajnji.getDiagramElement(),"0..1","promenjiva");
            GeneralizacijaPainter g = new GeneralizacijaPainter(connection,elementPainterPocetni,elementPainterKrajnji);







            if(!c.getPainters().contains(g) && !elementPainterPocetni.equals(elementPainterKrajnji)){

               c.getDiagram().addChild(connection);

                ClassyTreeImplementation tree = ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree());
                //Ova metoda pronalazi treenode koji odgovara selectovanom dijagramu i dodaje mu dete tako sto se rekurzivno krece kroz nas JTREE
                ClassyTreeItem diagramNode = tree.dfsSearch((ClassyTreeItem) tree.getTreeModel().getRoot(),c.getDiagram());
                //ovo je samo za dodavanje u jtree, u modelu je vec dodat
                c.getPainters().add(g);
                MainFrame.getInstance().getClassyTree().addChild(diagramNode,false);
                //samo rasiri sve
                tree.getTreeView().expandPath(new TreePath(diagramNode.getPath()));
                System.out.println(c.getDiagram().getChildren());

            }


            //c.repaint();
        }

        c.setLinija(new Line2D.Double());
        c.repaint();
    }

    @Override
    public void misSkrolovan(Point2D p, ClassyDiagramView c) {

    }
}
