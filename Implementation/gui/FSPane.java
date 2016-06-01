package gui;

import logic.FSController;
import logic.Observer;

/**
 * 
 * @author Juyoung Choi
 *
 */
public abstract class FSPane implements Observer{

	protected FSController fsController;
	protected FlexturGUI flexturGUI;
	
	public void setFSController (FSController fsController){
		this.fsController = fsController;
		this.fsController.tilmeldObserver(this);
	}

	public void setMainApp(FlexturGUI flexturGUI) {
		this.flexturGUI = flexturGUI;
	}
	
	abstract void postInitialize();
}
