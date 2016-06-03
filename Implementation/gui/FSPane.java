package gui;

import logic.FSController;
import logic.Observer;

/**
 * 'abstract' klasse for GUI Controllers : GUI controllers arver fra den klasse
 * for at set FSController, set MainApp for at skifte sider, tilmeld observer here
 * 
 * postInitialize :
 * 
 * 'abstract' metode (package scope) bruges for at hente oplysninger fra FSController 
 * efter initialize i GUI controllers
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
