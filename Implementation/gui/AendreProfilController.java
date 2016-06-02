/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;

import domain.Bruger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.Observable;
import logic.Tilstand;

/**
 *
 * @author Jonas Mørch
 */
public class AendreProfilController extends FSPane implements Initializable {
	private Bruger bruger;
    
    @FXML
    private Label label;
    
    @FXML
    private TextField efternavn;
    
    @FXML
    private TextField adresse;
    
    @FXML
    private TextField email;
    
    @FXML
    private TextField kodeord;
    
    @FXML
    private TextField cpr;
    
    @FXML
    private TextField fornavn;
    
    @FXML
    private TextField telefon;
    
    @FXML
    private ChoiceBox<String> kommune;
    
	private FlexturGUI flextur;
    
    @FXML
    private void handleToMenu(ActionEvent event) {
        flextur.showMenuKunde();
    }
    public void setMainApp(FlexturGUI flextur) {
		this.flextur = flextur;
		
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
		this.bruger = fsController.getBruger();
		set
	}    
    
}
