package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.controller.ActionManager;

import javax.swing.*;
import java.awt.*;
@Getter
@Setter
public class MainFrame extends JFrame {
    private static MainFrame instance;
    /* TODO sve frameove koje budemo pravili, ukljucujuci i ovaj novi postojeci
    TODO open about us frame, treba dodavati kao polja u mainframeu*/
    //buduca polja za sve komponente view-a na glavnom prozoru
    private ActionManager actionManager;
    private AboutUsFrame aboutUsFrame;

    private MainFrame(){
        actionManager = new ActionManager();
        aboutUsFrame = new AboutUsFrame();
    }

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        MyMenyBar menu = new MyMenyBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);
    }

    public static MainFrame getInstance()
    {
        if(instance == null)
        {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }
}
