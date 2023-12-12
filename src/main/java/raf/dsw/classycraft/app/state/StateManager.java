package raf.dsw.classycraft.app.state;

public class StateManager {
    private AddElementState addElementState;
    private AddConnectionState addConnectionState;
    private SelectionState selectionState;
    private ZoomInState zoomInState;
    private ZoomOutState zoomOutState;
    private DeleteState deleteState;
    private MoveState moveState;
    private EditState editState;
    private DuplicateState duplicateState;

    private StateInterface currentState;

    public StateManager(){
        initialise();
    }

    private void initialise(){
        addElementState = new AddElementState();
        addConnectionState = new AddConnectionState();
        selectionState =  new SelectionState();
        deleteState = new DeleteState();
        zoomInState = new ZoomInState();
        zoomOutState = new ZoomOutState();
        moveState = new MoveState();
        editState = new EditState();
        duplicateState = new DuplicateState();

        currentState = selectionState;
    }
    public StateInterface getCurrentState(){
        return currentState;
    }

    public void setEditState(){currentState = editState;}

    public void setAddElementState(){
        currentState = addElementState;
    }

    public void setAddConnectionState(){
        currentState = addConnectionState;
    }

    public void setSelectionState(){
        currentState = selectionState;
    }

    public void setDeleteState(){
        currentState = deleteState;
    }

    public void setMoveState(){
        currentState = moveState;
    }

    public void setZoomInState(){
        currentState = zoomInState;
    }
    public void setZoomOutState(){
        currentState = zoomOutState;
    }

    public void setDuplicateState(){
        currentState = duplicateState;
    }
}
