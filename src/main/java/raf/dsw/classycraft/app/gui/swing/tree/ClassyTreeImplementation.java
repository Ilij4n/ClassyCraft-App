package raf.dsw.classycraft.app.gui.swing.tree;

import javafx.scene.Parent;
import lombok.Getter;
import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.factory.ClassyNodeFactory;
import raf.dsw.classycraft.app.core.factory.DiagramFactory;
import raf.dsw.classycraft.app.core.factory.FactoryUtils;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.core.model.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
@Getter
public class ClassyTreeImplementation implements ClassyTree {
    //ovaj treeView je prakticno pre-konstruisan JTree, nista spec
    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;


    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        /*
         * ovaj konstruktor ce iz maina dobiti projectExplorer putanjom
         * appframe.getins.getClasRepoImp.getRoot
         */
        //wrapujemo ga u treeItem
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        //instanciramo defaultModel sa nasim wrapovanim rootom
        treeModel = new DefaultTreeModel(root);
        //instanciranjem naseg JTree-a sa tim modelom zavrsavamo pravljenje
        treeView = new ClassyTreeView(treeModel);
        //vracamo selectujemo root i vracamo nasu Jtree instancu
        treeView.setSelectionRow(0);
        return treeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent,boolean pakOrDia) {
        //ako classyNode koji sadrzi nasa wrapper klasa nije kompozit, ne razmatrati dodavanje
        if (!(parent.getClassyNode() instanceof ClassyNodeComposite)){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Ne moze da se doda!", MessageType.ERROR);
            return;
        }

        ClassyNode child = createChild(parent.getClassyNode(),pakOrDia);
        //dodaje Childa parentu u prikazu tree-a, on sam ovo u pozadini sljaka
        parent.add(new ClassyTreeItem(child));
        //reflektuje date promene u modelu
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);

        System.out.println( ((ClassyNodeComposite) parent.getClassyNode()).getChildren().toString());
        //da lepo izgleda i da se refreshuje
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public ClassyTreeItem getSelectednode() {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void deleteChild(ClassyTreeItem item) {

        if (item.getClassyNode() instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Explorer se ne brise ! ", MessageType.ERROR);
            return;
        }
        //brisanje iz modela (valjda)
        ClassyNodeComposite parent = (ClassyNodeComposite)item.getClassyNode().getParent();
        ClassyNode node = item.getClassyNode();
        ClassyNode nodeParent = item.getClassyNode().getParent();

        if(node instanceof Diagram){
            ((Package)nodeParent).deleteChild(node);
        } else if (nodeParent instanceof Project) {
            ((Project)nodeParent).deleteChild(node);
        } else if (nodeParent instanceof Package) {
            ((Package)nodeParent).deleteChild(node);
        } else if (node instanceof Project) {
            for(ClassyNode c:((Project) node).getChildren()){
                ((Project) node).deleteChild(c);
            }
        }

        parent.getChildren().remove(item.getClassyNode());

        //za brisanje iz stabla
        treeModel.removeNodeFromParent(item);
    }



    private ClassyNode createChild(ClassyNode parent,boolean pakOrDia) {
        String string;
        ClassyNodeFactory classyNodeFactory;
        if (parent instanceof ProjectExplorer) {
            string = "PROJECT";
            classyNodeFactory = FactoryUtils.returnFactory(string,parent);
            Project project = (Project) classyNodeFactory.createNode(parent);

            return project;
        } else if(parent instanceof Project){
            string = "PACKAGE";
            classyNodeFactory = FactoryUtils.returnFactory(string,parent);
            Package package1 = (Package) classyNodeFactory.createNode(parent);
            ClassyPackageView packageView = new ClassyPackageView(package1);
            package1.realPapa().addSub(packageView);
            package1.addSub(packageView);

            return package1;
        } else if(parent instanceof Package){
            if(!pakOrDia) {
                string = "DIAGRAM";
                classyNodeFactory = FactoryUtils.returnFactory(string,parent);
                Diagram diagram = (Diagram) classyNodeFactory.createNode(parent);
                ClassyDiagramView diagramView = new ClassyDiagramView(diagram);
                diagram.addSub(diagramView);
                ((Package) parent).addingOfDiagramView(diagramView);



                return  diagram;
            }else{
                string = "PACKAGE";
                classyNodeFactory = FactoryUtils.returnFactory(string,parent);
                Package package1 = (Package) classyNodeFactory.createNode(parent);
                ClassyPackageView packageView = new ClassyPackageView(package1);
                package1.realPapa().addSub(packageView);
                package1.addSub(packageView);

                return package1;
            }
        }

        return  null;
    }
}
