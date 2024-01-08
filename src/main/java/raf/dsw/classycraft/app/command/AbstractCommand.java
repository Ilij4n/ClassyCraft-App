package raf.dsw.classycraft.app.command;

import raf.dsw.classycraft.app.gui.swing.view.ElementCreationView;

import java.awt.geom.Point2D;

public abstract class AbstractCommand {

    public abstract void doCommand();

    public abstract void undoCommand();
}

