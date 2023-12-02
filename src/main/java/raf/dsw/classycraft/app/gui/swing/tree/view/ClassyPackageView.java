package raf.dsw.classycraft.app.gui.swing.tree.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.Project;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
@Getter
@Setter

public class ClassyPackageView extends JPanel implements ISubscriber {

    private String authorName;
    private String projectName;
    private JLabel lblProjectName;
    private JLabel lblAuthorname;
    private JTabbedPane jTabbedPane;

    private Package aPackage;

    private StateManager stateManager;

    public ClassyPackageView(Package p){
        aPackage = p;
        lblProjectName = new JLabel("Project: "+ aPackage.projectName());
        lblAuthorname = new JLabel("Author: "+ aPackage.authorName());
        jTabbedPane = new JTabbedPane();
        stateManager = new StateManager();

        BorderLayout b = new BorderLayout();

        setLayout(b);

        JPanel jPanel = new JPanel();
        jPanel.add(lblProjectName);
        jPanel.add(lblAuthorname);

        add(jPanel,BorderLayout.NORTH);
        add(jTabbedPane,BorderLayout.CENTER);
        PackageViewToolbar toolbar = new PackageViewToolbar();
        add(toolbar,BorderLayout.EAST);
    }

    public void startAddElementState(){
        stateManager.setAddElementState();
    }

    public void startAddConnectionState(){
        stateManager.setAddConnectionState();
    }

    public void startDeleteState(){
        stateManager.setDeleteState();
    }

    public void startMoveState(){
        stateManager.setMoveState();
    }

    public void startZoomInOutState(){
        stateManager.setZoomInOutState();
    }

    public void startSelectionState(){
        stateManager.setSelectionState();
    }

    public void startEditState(){
        stateManager.setEditState();
    }

    public void misKliknut(Point2D p,ClassyDiagramView classyDiagramView){
        stateManager.getCurrentState().misKliknut(p,classyDiagramView);
    }

    @Override
    public void update(Object o) {
        if(o instanceof Project){
            getLblProjectName().setText("Project: "+ ((Project) o).getName());
        } else if (o instanceof String) {
            getLblAuthorname().setText("Author: "+ o);
        } else if (o instanceof Diagram) {
            Diagram d = (Diagram) o;
            for (Component c:this.getjTabbedPane().getComponents()){
                if (((Diagram) o).getName().equals(c.getName())){
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
        } else if (o instanceof Package) {//ovo nicemu ne sluzi
            getLblProjectName().setText("Project: " + ((Package) o).projectName());
            getLblAuthorname().setText("Author: " + ((Package) o).authorName());
        } else if(o instanceof ClassyDiagramView){
            this.getjTabbedPane().add((JPanel)o);
        }else if(o instanceof ClassyPackageView){
            MainFrame.getInstance().getSplitPane().setRightComponent(new JPanel());
        }
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
}
