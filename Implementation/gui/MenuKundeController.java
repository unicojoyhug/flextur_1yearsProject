/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import logic.Observable;
import logic.Tilstand;

/**
 * GUI controller klasse til Menu for admin (kunde)
 * @author Jonas Mørch
 */
public class MenuKundeController extends FSPane implements Initializable {
    
	private FlexturGUI flexturGUI;
    
	
	public void setMainApp(FlexturGUI flexturGUI) {
		this.flexturGUI = flexturGUI;
	}
	
    @FXML
    private void handleBestilFlextur(ActionEvent event) {
    	flexturGUI.showBestilFlex();
    }
    @FXML
    private void handleAendreProfil(ActionEvent event){
    	flexturGUI.showAendreProfil();
    }
    @FXML
    private void handleSeHistorik(ActionEvent event){
    	flexturGUI.showHistorikKunde();
    }
    @FXML
    private void handleLogUd(ActionEvent event){
    	flexturGUI.showLogin();
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

	@Override
	public void update(Observable observable, Tilstand tilstand) {
		// TODO Auto-generated method stub
		
	}
	@Override
	void postInitialize() {
		
	}    
    
}
