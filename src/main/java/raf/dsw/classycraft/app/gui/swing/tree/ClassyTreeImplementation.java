package raf.dsw.classycraft.app.gui.swing.tree;

import lombok.Getter;
import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.core.ApplicationFramework;
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
        //fixme test print za proveru modela (izgleda ok)
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
        //TODO Boga pitaj (zapravo izgleda ok)
        if (item.getClassyNode() instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Explorer se ne brise ! ", MessageType.ERROR);
            return;
        }
        //brisanje iz modela (valjda)
        ClassyNodeComposite parent = (ClassyNodeComposite)item.getClassyNode().getParent();
        ClassyNode node = item.getClassyNode();
        ClassyNode nodeParent = item.getClassyNode().getParent();

        if(node instanceof Diagram){
            ((Package)nodeParent).getClassyPackageView().getjTabbedPane().remove(((Diagram) node).getClassyDiagramView());
            ((Package) nodeParent).notifySubs((Diagram)node);
        } else if (node instanceof Project) {
            MainFrame.getInstance().getSplitPane().setRightComponent(new JPanel());
        }else if(node instanceof Package){
            MainFrame.getInstance().getSplitPane().setRightComponent(new JPanel());
        }

        parent.getChildren().remove(item.getClassyNode());


        //za brisanje iz stabla
        treeModel.removeNodeFromParent(item);


    }
    //fixme random assigner indexa
    int cnt = 0;
    //TODO ovo dole raditi pomocu factoryMethod sablonu po parentu a ne ovako
    private ClassyNode createChild(ClassyNode parent,boolean pakOrDia) {
        cnt++;
        if (parent instanceof ProjectExplorer) {

            return new Project(parent, "Project" + cnt, "Autor", "Path");
        } else if(parent instanceof Project){
            Package package1 = new Package(parent,"Package"+cnt);
            ClassyPackageView packageView = new ClassyPackageView(package1);
            package1.setClassyPackageView(packageView);
            package1.addSub(packageView);
            return package1;
        } else if(parent instanceof Package){
            if(!pakOrDia){
                Diagram diagram = new Diagram(parent, "Diagram"+cnt);
                ClassyDiagramView diagramView = new ClassyDiagramView();
                ((Package) parent).getClassyPackageView().getjTabbedPane().add(diagramView);

                diagram.addSub(diagramView);
                return  diagram;
            }
            Package package1 = new Package(parent,"Package"+cnt);
            ClassyPackageView packageView = new ClassyPackageView(package1);
            package1.setClassyPackageView(packageView);
            package1.addSub(packageView);
            return package1;
        }
        cnt++;
        return null;
    }
}
