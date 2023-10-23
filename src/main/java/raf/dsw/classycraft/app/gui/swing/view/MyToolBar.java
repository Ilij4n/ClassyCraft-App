package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.ExitAction;
import raf.dsw.classycraft.app.controller.NewProjectAction;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MyToolBar extends JToolBar {
    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        ExitAction ea = new ExitAction();
        add(ea);
        NewProjectAction npa = new NewProjectAction();
        add(npa);
    }
}
