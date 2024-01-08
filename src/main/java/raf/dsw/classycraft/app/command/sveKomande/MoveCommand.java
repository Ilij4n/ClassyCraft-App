package raf.dsw.classycraft.app.command.sveKomande;


import raf.dsw.classycraft.app.command.AbstractCommand;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.Connection;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MoveCommand extends AbstractCommand {
    private ClassyDiagramView classyDiagramView;
    private List<Point2D> stareTacke;
    private List<Point2D> noveTacke;
    private List<ElementPainter>  painters= new ArrayList<>();


    public MoveCommand(ClassyDiagramView classyDiagramView,List<Point2D> stareTacke,List<Point2D> noveTacke){
        this.classyDiagramView = classyDiagramView;
        this.stareTacke = stareTacke;
        this.noveTacke = noveTacke;
        System.out.println(classyDiagramView.getSviselectovani());
        this.painters.addAll(classyDiagramView.getSviselectovani());
        System.out.println(painters);
    }
    @Override
    public void doCommand() {
        int i = 0;
        for(ElementPainter e:painters){
            if(painters.size()!=i){
                if(e.getDiagramElement() instanceof InterClass){
                    ((InterClass)e.getDiagramElement()).setLocation(noveTacke.get(i));
                    i++;
                }
            }
        }

        classyDiagramView.repaint();
    }

    @Override
    public void undoCommand() {
        int i = 0;
        for(ElementPainter e:painters){
            if(painters.size()!=i) {
                if (e.getDiagramElement() instanceof InterClass){
                    ((InterClass) e.getDiagramElement()).setLocation(stareTacke.get(i));
                    i++;
                }
            }
        }
        classyDiagramView.repaint();
    }
}
