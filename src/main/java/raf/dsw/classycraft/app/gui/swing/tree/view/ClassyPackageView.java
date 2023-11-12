package raf.dsw.classycraft.app.gui.swing.tree.view;

import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClassyPackageView extends JPanel implements ISubscriber {
    private String authorName = "";
    private String projectName;
    private JLabel lblProjectName;
    private JLabel lblAuthorname;
    private JTabbedPane jTabbedPane;

    public ClassyPackageView(){

        lblProjectName = new JLabel("Project: ");
        lblAuthorname = new JLabel("Author: ");
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
        } else if (o instanceof Package) {

        } else if (o instanceof Diagram) {
            System.out.println("Uso");
            Diagram d = (Diagram) o;
            for (Component c:this.getjTabbedPane().getComponents()){
                if (((Diagram) o).getName().equals(c.getName())){
                    System.out.println("Uso");
                    this.getjTabbedPane().remove(c);
                }
            }
        } else if (o instanceof List) {
            for (Component c:this.getjTabbedPane().getComponents()){
                if(c.getName().equals(((List<String>) o).get(0))){
                    int i = this.getjTabbedPane().indexOfComponent(c);
                    this.getjTabbedPane().remove(c);
                    c.setName(((List<String>) o).get(1));
                    this.getjTabbedPane().add(c,i);
                }
            }
        }
    }
}
