package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.MessageGenerator.Message;
import raf.dsw.classycraft.app.MessageGenerator.MessageGeneratorImp;
import raf.dsw.classycraft.app.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.controller.ActionManager;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.implementation.Diagram;
import raf.dsw.classycraft.app.core.model.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTree;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyPackageView;
import raf.dsw.classycraft.app.observer.ISubscriber;
import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;

    //buduca polja za sve komponente view-a na glavnom prozoru
    private ActionManager actionManager;
    private AboutUsFrame aboutUsFrame;
    //classyTree moze stajati i kao pole na ApplicationFramework ali posto je controller, i ovo je ok
    private ClassyTree classyTree;
    //ova polja sam dodao radi pristupa u refreshDivider metodi
    private JSplitPane splitPane;
    private JTree projectExplorerTree;


    private MainFrame(){
        actionManager = new ActionManager();
        aboutUsFrame = new AboutUsFrame();
        classyTree = new ClassyTreeImplementation();
        //subujemo se na sve sto treba
        ((MessageGeneratorImp)ApplicationFramework.getInstance().getMessageGenerator()).addSub(this);
    }

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize((int)(screenWidth / 1.5), (int)(screenHeight / 1.5));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        MyMenyBar menu = new MyMenyBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);

        //SplitPane setup

        JPanel jTreePanel = new JPanel();
        jTreePanel.setBackground(Color.white);

        projectExplorerTree = classyTree.generateTree((ProjectExplorer)ApplicationFramework.getInstance().getRepository().getRoot());
        jTreePanel.add(projectExplorerTree);


        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jTreePanel,new JPanel());
        splitPane.setDividerLocation(projectExplorerTree.getPreferredSize().width);
        add(splitPane);

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

    public void refreshDivider(){
        splitPane.setDividerLocation(projectExplorerTree.getPreferredSize().width);
    }

    @Override
    public void update(Object o) {
        /*
            Svaki IPUBLISHER imace svoju posebnu Notification klasu (u donjem slucaju klasa Message)
            Svaki ISUBSCRIBER ce imati provere za svaku od tih posebnih klasa u zavnisnosti od toga na koga je subovan
            Metodama notifySubs i update je bas iz tog razloga tada sloboda da primaju argument tipa Object sa jedinim problemom
            da je kast u specificnu notification klasu neophodan da bi imali pristup poljima iste.
         */
        if(o instanceof Message){
            Message message = (Message)o;
            if(message.getType()== MessageType.INFO){
                JOptionPane.showMessageDialog(this,message.toString(),"Informacija",JOptionPane.INFORMATION_MESSAGE);
            }
            else if(message.getType()==MessageType.ERROR){
                JOptionPane.showMessageDialog(this,message.toString(),"Greska",JOptionPane.ERROR_MESSAGE);
            }
            else if(message.getType()==MessageType.WARNING){
                JOptionPane.showMessageDialog(this,message.toString(),"Upozorenje",JOptionPane.WARNING_MESSAGE);
            }
        }

        if(o instanceof Diagram){

        }

        if(o instanceof Package){

        }
    }


}
