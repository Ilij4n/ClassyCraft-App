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

    private static int cnt = 1;
    public DiagramFactory(ClassyNode parent){}

    @Override
    public ClassyNode createNode(ClassyNode parent) {

            Diagram diagram = new Diagram(parent, "Diagram "+cnt);
            cnt++;

            return diagram;
    }
}
