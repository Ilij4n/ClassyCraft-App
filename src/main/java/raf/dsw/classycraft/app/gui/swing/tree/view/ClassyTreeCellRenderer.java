package raf.dsw.classycraft.app.gui.swing.tree.view;

import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.core.model.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class ClassyTreeCellRenderer extends DefaultTreeCellRenderer {
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
        URL imageURL = null;
        //TODO ovde naci neke *jezivo* cool ikonice
        if (((ClassyTreeItem)value).getClassyNode() instanceof ProjectExplorer) {
            imageURL = getClass().getResource("/images/projectExplorer.png");
        }
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Project) {
            imageURL = getClass().getResource("/images/project.png");
        }
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Package) {
            imageURL = getClass().getResource("/images/package.png");
        }
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Diagram) {
            imageURL = getClass().getResource("/images/diagram.png");
        }
        else if(((ClassyTreeItem)value).getClassyNode() instanceof DiagramElement){
            imageURL = getClass().getResource("/images/diagram.png");
        }

        Icon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        setIcon(icon);

        return this;
    }
}
