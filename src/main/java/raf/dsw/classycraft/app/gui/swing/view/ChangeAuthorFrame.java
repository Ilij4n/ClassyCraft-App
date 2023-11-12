package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.ActionManager;
import raf.dsw.classycraft.app.controller.AuthorChangeAction;
import raf.dsw.classycraft.app.controller.ButtonChangeAuthorAction;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.model.composite.ClassyNode;
import raf.dsw.classycraft.app.core.model.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.model.implementation.Package;
import raf.dsw.classycraft.app.core.model.implementation.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChangeAuthorFrame extends JFrame {
    private JLabel jLabel;
    private JTextField  textField;
    private JButton button;

    public ChangeAuthorFrame(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 3, screenHeight / 3);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setTitle("Change author");

        jLabel = new JLabel("Change author name:");

        textField = new JTextField();

        button = new JButton("Change author!");


        MainFrame.getInstance().getActionManager().getButtonChangeAuthorAction().setAuthorFrame(this);
        button.setAction(MainFrame.getInstance().getActionManager().getButtonChangeAuthorAction());

        JPanel jPanel = new JPanel();

        jPanel.setLayout(new GridLayout(3,1));

        jPanel.add(jLabel);
        jPanel.add(textField);
        jPanel.add(button);

        add(jPanel);
    }

    public JLabel getjLabel() {
        return jLabel;
    }

    public void setjLabel(JLabel jLabel) {
        this.jLabel = jLabel;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }
}
