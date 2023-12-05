package raf.dsw.classycraft.app.state;

import javax.swing.plaf.nimbus.State;

public class StateManager {
    private AddElementState addElementState;
    private AddConnectionState addConnectionState;
    private SelectionState selectionState;
    private ZoomInOutState zoomInOutState;
    private DeleteState deleteState;
    private MoveState moveState;
    private EditState editState;

    private StateInterface currentState;

    public StateManager(){
        initialise();
    }

    private void initialise(){
        addElementState = new AddElementState();
        addConnectionState = new AddConnectionState();
        selectionState =  new SelectionState();
        deleteState = new DeleteState();
        zoomInOutState = new ZoomInOutState();
        moveState = new MoveState();
        editState = new EditState();

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

    public void setZoomInOutState(){
        currentState = zoomInOutState;
    }
}
