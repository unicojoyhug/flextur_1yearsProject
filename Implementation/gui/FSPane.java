package gui;

import logic.FSController;
import logic.Observer;
import sats.Sats;

public abstract class FSPane implements Observer{

	protected FSController fsController;
	protected FlexturGUI flexturGUI;
	
	public void setFSController (FSController fsController){
		this.fsController = fsController;
		this.fsController.tilmedObserver(this);
	}

	public void setMainApp(FlexturGUI flexturGUI) {
		this.flexturGUI = flexturGUI;
	}
	
	public String [] getKommune() {
		return Sats.i().getKommuner();
	}
}
