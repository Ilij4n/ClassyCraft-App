package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.MessageGenerator.Message;
import raf.dsw.classycraft.app.MessageGenerator.MessageGeneratorImp;
import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.controller.ActionManager;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;

    //buduca polja za sve komponente view-a na glavnom prozoru
    private ActionManager actionManager;
    private AboutUsFrame aboutUsFrame;

    private MainFrame(){
        actionManager = new ActionManager();
        aboutUsFrame = new AboutUsFrame();
        //subujemo se na sve sto treba
        ((MessageGeneratorImp)ApplicationFramework.getInstance().getMessageGenerator()).addSub(this);
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

        //TODO: namestiti ovo da bude SplitPane

        MyMenyBar menu = new MyMenyBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);
        //TEST DUGME, IZBISTATI
        JButton button = new JButton();
        AbstractAction action = new AbstractAction("Button Action") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("HEEEJ",MessageType.INFO);
            }
        };

        button.setAction(action);
        add(button);
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

    @Override
    public void update(Object o) {
        if(o instanceof Message){
            Message message = (Message)o;
            if(message.getType()== MessageType.INFO){
                System.out.println(message.toString());
            }
            else if(message.getType()==MessageType.ERROR){

            }
            else if(message.getType()==MessageType.WARNING){

            }
        }
    }
}
