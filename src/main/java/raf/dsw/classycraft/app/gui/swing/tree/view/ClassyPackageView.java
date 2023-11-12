package raf.dsw.classycraft.app.gui.swing.tree.view;

import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class ClassyPackageView extends JPanel implements ISubscriber {
    private String authorName = "";
    private String projectName;
    private JLabel lblProjectName;
    private JLabel lblAuthorname;
    private JTabbedPane jTabbedPane;

    public ClassyPackageView(Package package1){

        lblProjectName = new JLabel("Project: "+package1.projectName());
        lblAuthorname = new JLabel("Author: "+package1.authorName());
        jTabbedPane = new JTabbedPane();

        BorderLayout b = new BorderLayout();

        setLayout(b);

        JPanel jPanel = new JPanel();
        jPanel.add(lblProjectName);
        jPanel.add(lblAuthorname);

        add(jPanel,BorderLayout.NORTH);
        add(jTabbedPane,BorderLayout.CENTER);

    }

    public JLabel getLblProjectName() {
        return lblProjectName;
    }

    public void setLblProjectName(JLabel name) {
        this.lblProjectName = lblProjectName;
    }

    public JTabbedPane getjTabbedPane() {
        return jTabbedPane;
    }

    public void setjTabbedPane(JTabbedPane jTabbedPane) {
        this.jTabbedPane = jTabbedPane;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public JLabel getLblAuthorname() {
        return lblAuthorname;
    }

    public void setLblAuthorname(JLabel lblAuthorname) {
        this.lblAuthorname = lblAuthorname;
    }

    @Override
    public void update(Object o) {
        if(o instanceof Project){
            this.getLblProjectName().setText("Project: "+((Project) o).getName());
            this.getLblAuthorname().setText("Author: "+((Project) o).getAutor());
            for(ClassyNode classyNode: ((Project) o).getChildren()){
                for(ClassyNode c: ((Package) classyNode).getChildren()){
                    ((Package)c).getClassyPackageView().getLblAuthorname().setText("Author: "+((Project) o).getAutor());
                    ((Package)c).getClassyPackageView().getLblProjectName().setText("Project: "+((Project) o).getName());

                }
            }
        } else if (o instanceof String) {

        } else if (o instanceof Diagram) {
            for(Component c:jTabbedPane.getComponents()){
                if(c.equals(((Diagram) o).getClassyDiagramView())){
                    remove(c);
                    break;
                }
            }
        }
    }
}
