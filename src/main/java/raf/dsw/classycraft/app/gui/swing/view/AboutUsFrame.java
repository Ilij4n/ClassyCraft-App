package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class AboutUsFrame extends JFrame {

    public AboutUsFrame(){
        //klasika
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 5, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Info");
        //pravimo panel kome zadajemo gridlayout kao layout
        JPanel panel = new JPanel(new GridLayout(2,2));

        //ovako se dodaje ikonica na panel
        panel.add(new JLabel(loadIcon("/images/exit.png")));
        panel.add(new JLabel("Ilijan Kojovic RN 81/23"));
        panel.add(new JLabel(loadIcon("/images/exit.png")));
        panel.add(new JLabel("Todor Bozovic RN 124/23"));

        add(panel);
        //this.setVisible(true);
    }

    private Icon loadIcon(String fileName){
        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;

        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
        }
        else {
            System.err.println("Resource not found: " + fileName);
        }
        return icon;
    }
}
