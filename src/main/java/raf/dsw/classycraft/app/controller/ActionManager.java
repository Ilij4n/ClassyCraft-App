package raf.dsw.classycraft.app.controller;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.controller.stateControllers.*;

@Getter
@Setter
public class ActionManager {
    //ovde ce nam biti dostupne sve vrste akcija koje imamo
    private ExitAction exitAction;
    private NewProjectAction newProjectAction;
    private OpenAboutUsAction openAboutUsAction;
    private DeleteChildAction deleteChildAction;
    private AuthorChangeAction authorChangeAction;
    private ButtonChangeAuthorAction buttonChangeAuthorAction;
    private AddConnectionAction addConnectionAction;
    private AddInterClassAction addInterClassAction;
    private DeleteElementAction deleteElementAction;
    private EditElementAction editElementAction;
    private MoveElementAction moveElementAction;
    private SelectElementAction selectElementAction;
    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;
    private DuplicateAction duplicateAction;
    private LoadProjectAction loadProjectAction;
    private SaveProjectAction saveProjectAction;
    private ExportSlikaAction exportSlikaAction;
    private UndoAction undoAction;
    private RedoAction redoAction;

    public ActionManager() {
        intialise();
    }
    //komentar
    private void intialise(){
        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        openAboutUsAction = new OpenAboutUsAction();
        deleteChildAction = new DeleteChildAction();
        authorChangeAction = new AuthorChangeAction();
        buttonChangeAuthorAction = new ButtonChangeAuthorAction();
        addConnectionAction = new AddConnectionAction();
        addInterClassAction = new AddInterClassAction();
        deleteElementAction = new DeleteElementAction();
        editElementAction = new EditElementAction();
        moveElementAction = new MoveElementAction();
        selectElementAction = new SelectElementAction();
        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();
        duplicateAction = new DuplicateAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        loadProjectAction = new LoadProjectAction();
        saveProjectAction = new SaveProjectAction();
        exportSlikaAction = new ExportSlikaAction();
    }

}
