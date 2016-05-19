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
import javafx.scene.control.Label;

/**
 *
 * @author Jonas Mørch
 */
public class MenuAdminController implements Initializable {
    
    @FXML
    private Label usernameL;
	private FlexturGUI Flextur;
	private String user = "Den der bruger systemet";
    
    @FXML
    private void handleRegistrerFlextur(ActionEvent event) {
    	System.out.println("Ikke impletementeret");
    }
    @FXML
    private void handleSeBestillinger(ActionEvent event){
    	Flextur.showBestillingsOversigt();
    }
    @FXML
    private void handleSeHistorik(ActionEvent event){
    	Flextur.showHistorikAdmin();
    }
    @FXML
    private void handleLogUd(ActionEvent event){
    	System.out.println("Ikke impletementeret");
    }
    public void setMainApp(FlexturGUI flextur) {
		this.Flextur = flextur;
		
	}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameL.setText(user);
    }    
    
}