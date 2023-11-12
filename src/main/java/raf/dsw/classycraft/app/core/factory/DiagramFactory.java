package raf.dsw.classycraft.app.core.factory;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
@Getter
@Setter
public class DiagramFactory extends ClassyNodeFactory{
    private Diagram diagram;
    private Package aPackage;
    private int cnt;
    public DiagramFactory(ClassyNode parent,boolean pakOrDia){
        createNode(parent,pakOrDia);
    }
    public ClassyNode createNode(ClassyNode parent, boolean pakOrDia){
        if(!pakOrDia){
            diagram = new Diagram(parent, "Diagram"+cnt);
            ClassyDiagramView diagramView = new ClassyDiagramView();
            diagramView.setName(diagram.getName());
            ((Package) parent).getClassyPackageView().getjTabbedPane().add(diagramView);
            diagram.addSub(diagramView);
            cnt++;
            return diagram;
        }else{
            FactoryUtils.returnFactory("PACKAGE",parent,null);
            return null;
        }
    }
}
