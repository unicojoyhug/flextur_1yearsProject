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
public class BestilFlexController implements Initializable {
    
    @FXML
    private Label label;
	private Flextur flextur;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    public void setMainApp(Flextur flextur) {
		this.flextur = flextur;
		
	}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
