package raf.dsw.classycraft.app.gui.swing.tree;


import lombok.Getter;
import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.factory.ClassyNodeFactory;
import raf.dsw.classycraft.app.core.factory.DiagramFactory;
import raf.dsw.classycraft.app.core.factory.FactoryUtils;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.model.composite.DiagramElement;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.core.model.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.connections.*;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Enum;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.InterClass;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Interfejs;
import raf.dsw.classycraft.app.core.model.implementation.diagramElements.interClasses.Klasa;
import raf.dsw.classycraft.app.gui.swing.painters.*;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyDiagramView;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ClassyTreeImplementation implements ClassyTree {
    //ovaj treeView je prakticno pre-konstruisan JTree, nista spec
    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;

    public ClassyTreeItem dfsSearch(ClassyTreeItem root, Object targetModel) {
        // Base case, ako root sadrzi model koji trazimo vrati ga
        if (root.getClassyNode() == targetModel) {
            return root;
        }

        // ako nije root vidi decu
        for (int i = 0; i < root.getChildCount(); i++) {
            ClassyTreeItem child = (ClassyTreeItem) root.getChildAt(i);
            ClassyTreeItem result = dfsSearch(child, targetModel);
            if (result != null) {
                // ako je u podstablu vrati ga
                return result;
            }
        }
        return null;
    }

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
        if (!(parent.getClassyNode() instanceof ClassyNodeComposite) /*|| parent.getClassyNode() instanceof Diagram*/ ){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Ne moze da se doda!", MessageType.ERROR);
            return;
        }
        //ako je parent instanca dijagrama ovaj createchild ce samo vratiti poslednjeg dodatog s liste
        ClassyNode child = createChild(parent.getClassyNode(),pakOrDia);
        //dodaje Childa parentu u prikazu tree-a, on sam ovo u pozadini sljaka
        parent.add(new ClassyTreeItem(child));
        //reflektuje date promene u modelu

        if(!(parent.getClassyNode() instanceof Diagram))((ClassyNodeComposite) parent.getClassyNode()).addChild(child);

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

    public void dfs2(ClassyNodeComposite currNode, ClassyTreeItem parent){
        // TODO - logika za pravljenja kompozita, pitati se sa instanceofom sta je currentNode, i u zavisnosti od toga praviti sta treba (subovi itd), u sustini prekopiraj logiku iz factorya gde se prave nodeovi i bice fine
        System.out.println(currNode);
        currNode.setParent(parent.getClassyNode());

        if(currNode instanceof Package){
            ClassyPackageView packageView = new ClassyPackageView((Package) currNode);
            ((Package)currNode).realPapa().addSub(packageView);
            ((Package)currNode).addSub(packageView);
        }
        if(currNode instanceof Diagram){
            ClassyDiagramView diagramView = new ClassyDiagramView((Diagram)currNode);
            ((Diagram)currNode).addSub(diagramView);

            ((Package) currNode.getParent()).addingOfDiagramView(diagramView);

        }

        ClassyTreeItem newtreeItem = new ClassyTreeItem(currNode);
        parent.add(newtreeItem);

        // Recursively traverse each child
        if (currNode.getChildren() != null) {
            for (ClassyNode child : currNode.getChildren()) {
                if (child instanceof ClassyNodeComposite) {
                    dfs2((ClassyNodeComposite) child, newtreeItem);
                } else {
                    //TODO - logika za pravljenja leafova, pitati se sa instanceofom sta je currentNode, i u zavisnosti od toga praviti sta treba (subovi itd) - takodje pogledati kako se prave tamo gde se prave i super

                    //kako dobaviti classyDiagramView na kome je painter
                    Package p = (Package) currNode.getParent();
                    ClassyPackageView p1 = (ClassyPackageView) p.getSubscribers().get(0);
                    ClassyDiagramView c = null;
                    for(Component d : p1.getjTabbedPane().getComponents()){
                        ClassyDiagramView view = (ClassyDiagramView)d;
                        if(view.getDiagram().equals(currNode)) c = view;

                    }

                    if(child instanceof InterClass){
                        //ovde moze da se pita za instancu interclassa i da se na osnovu toga samo setuje boja, jer se boja ne serijalizuje
                        ((InterClass) child).loadCoords();

                        if(child instanceof Klasa){
                            ClassPainter classPainter = new ClassPainter((Klasa) child);
                            ((Klasa)child).addSub(c);
                            c.getPainters().add(classPainter);
                        }
                        else if(child instanceof Interfejs){
                            InterfacePainter interfacePainter = new InterfacePainter((Interfejs) child);
                            ((Interfejs)child).addSub(c);
                            c.getPainters().add(interfacePainter);
                        }
                        else if(child instanceof Enum){
                            EnumPainter enumPainter = new EnumPainter((Enum) child);
                            ((Enum)child).addSub(c);
                            c.getPainters().add(enumPainter);
                        }
                    }
                    else if(child instanceof Connection){
                        ElementPainter painter1 = null;
                        ElementPainter painter2 = null;
                        ConnectionPainter connectionPainter = null;
                        for(ElementPainter painter: c.getPainters()){
                            if(painter.getDiagramElement().equals(((Connection) child).getElement1())) painter1 = painter;
                            if(painter.getDiagramElement().equals(((Connection) child).getElement2())) painter2 = painter;
                        }

                        if(child instanceof Generalizacija){
                            connectionPainter = new GeneralizacijaPainter((Connection) child,painter1,painter2);
                        }
                        if(child instanceof Agregacija){
                            connectionPainter = new AgregacijaPainter((Connection) child,painter1,painter2);
                        }
                        if(child instanceof Kompozicija){
                            connectionPainter = new KompozicijaPainter((Connection) child,painter1,painter2);
                        }
                        if(child instanceof Zavisnost){
                            connectionPainter = new ZavisnostPainter((Connection) child,painter1,painter2);
                        }
                        ((Connection)child).addSub(c);
                        c.getPainters().add(connectionPainter);
                    }
                    child.setParent(currNode);
                    ClassyTreeItem newtreeItem1 = new ClassyTreeItem(child);
                    dfsSearch((ClassyTreeItem) getTreeModel().getRoot(),currNode).add(newtreeItem1);
                    System.out.println(child);
                }
            }
        }
    }

    public void loadProject(ClassyNodeComposite node) {
        //TODO ovde sad treba proci kroz sve elemente, nakaciti ih na stablo, napraviti im viewove i povezati im subove
       //ClassyTreeItem loadedProject = new ClassyTreeItem(node);
        ClassyTreeItem root = (ClassyTreeItem)treeModel.getRoot();
       // root.add(loadedProject);

        ClassyNodeComposite rootModel = (ClassyNodeComposite) root.getClassyNode();
        rootModel.addChild(node);
        dfs2(node,root);
    /*
        for(ClassyNode d1 : ((ClassyNodeComposite)node.getChildren().get(0)).getChildren()){
            ClassyNodeComposite d2 = (ClassyNodeComposite) d1;
            for(ClassyNode d3: d2.getChildren()){ //TODO ovde se na slican fazon moze setovati i boja, samo treba pomocu dfsa proci kroz ceo model i uraditi onaj gornji todo za svaki element
                if(d3 instanceof InterClass)((InterClass) d3).loadCoords();
                System.out.println(d3);
            }
        }
    */
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
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
        else if(parent instanceof Diagram){
            //Vraca poslednji element u listi childova, posto smo dole u stejtu vec dodali child, ovde ga samo pronalazi i prosledjuje da se doda u tree
            List<ClassyNode> lista = ((Diagram) parent).getChildren();
            System.out.println("Usao");
            return lista.get(lista.size()-1);
        }

        return  null;
    }
}
