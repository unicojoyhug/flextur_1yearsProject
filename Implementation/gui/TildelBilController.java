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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.Observable;
import logic.Tilstand;

/**
 *
 * @author Jonas Mørch
 */
public class TildelBilController extends FSPane implements Initializable {
    
    @FXML
    private FlexturGUI flexturGUI;
    @FXML
    private TextField turPersoner, turAutostole, turBarnevogne, turKoerestole, turBaggage, bilPersoner;
    @FXML
    private TextArea kommentarFelt;
    @FXML
    private ComboBox<String> bilValg;
	
    
	
	public void setMainApp(FlexturGUI flexturGUI) {
		this.flexturGUI = flexturGUI;
	}
	
    @FXML
    private void handleGodkend(ActionEvent event) {
    	
    }
    @FXML
    private void handleAnnuller(ActionEvent event) {
    	
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
