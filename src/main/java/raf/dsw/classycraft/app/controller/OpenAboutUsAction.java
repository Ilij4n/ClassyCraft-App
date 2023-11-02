package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.view.AboutUsFrame;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class OpenAboutUsAction extends AbstractClassyAction{
    public OpenAboutUsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "About us");
        putValue(SHORT_DESCRIPTION, "Nesto o nama");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getAboutUsFrame().setVisible(true);
    }
}
