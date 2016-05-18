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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

/**
 *
 * @author Jonas Mørch
 */
public class BestillingsOversigtController implements Initializable {
    
    @FXML
    private TableView tableView;
    @FXML
    private DatePicker fraDato, tilDato;
	private FlexturGUI Flextur;
   
    @FXML
    private void handleNyBestilling(ActionEvent event) {
        Flextur.showBestilFlex();
       
    }
    @FXML
    private void handleSeHistorik(ActionEvent event) {
        Flextur.showHistorikAdmin();
       
    }
    @FXML
    private void handleTildelBil(ActionEvent event) {
        Flextur.showBestilFlex();
       
    }
    public void setMainApp(FlexturGUI flextur) {
		this.Flextur = flextur;
		
	}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
