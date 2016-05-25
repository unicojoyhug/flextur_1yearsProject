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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import logic.Observable;
import logic.Tilstand;

/**
 *
 * @author Jonas Mørch
 */
public class OpretProfilController extends FSPane implements Initializable {
    
    @FXML
    private Label label;
	private FlexturGUI flextur;
	@FXML
	private PasswordField password;
	@FXML
	private TextField fornavn, efternavn, addresse, telefonnr, email, cprnummer;
	@FXML
	private ChoiceBox Kommune;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    public void setMainApp(FlexturGUI flextur) {
		this.flextur = flextur;
		
	}
    @FXML
    private void handleCancel(ActionEvent event) {
        flextur.showLogin();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
	@Override
	public void update(Observable observable, Tilstand tilstand) {
		// TODO Auto-generated method stub
		
	}
	@Override
	void postInitialize() {
		// TODO Auto-generated method stub
		
	}    
    
}
