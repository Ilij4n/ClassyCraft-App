package raf.dsw.classycraft.app.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {
    //ovde ce nam biti dostupne sve vrste akcija koje imamo
    private ExitAction exitAction;
    private NewProjectAction newProjectAction;
    private OpenAboutUsAction openAboutUsAction;

    public ActionManager() {
        intialise();
    }

    private void intialise(){
        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        openAboutUsAction = new OpenAboutUsAction();
    }

}
