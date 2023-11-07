package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.ExitAction;
import raf.dsw.classycraft.app.controller.NewProjectAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MyMenyBar extends JMenuBar {

    public MyMenyBar(){
        //File menu opicja
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F); // setuje keyboard shortcut za otvaranje file menija na alt+F
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction()); //dodajemo ExitAction u File dropdown
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteChildAction());
        add(fileMenu); //dodajemu file opciju u menu bar


        //Edit meni opcija
        JMenu editMenu  = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        editMenu.add(MainFrame.getInstance().getActionManager().getOpenAboutUsAction());
        add(editMenu);
    }

}
