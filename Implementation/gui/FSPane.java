package gui;

import logic.FSController;
import logic.Observer;

public abstract class FSPane implements Observer{

	protected FSController fsController;
	
	public void setFSController (FSController fsController){
		this.fsController = fsController;
		this.fsController.tilmedObserver(this);
	}
	
}
