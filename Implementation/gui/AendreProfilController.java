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
public class AendreProfilController implements Initializable {
    
    @FXML
    private Label label;
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
    
}