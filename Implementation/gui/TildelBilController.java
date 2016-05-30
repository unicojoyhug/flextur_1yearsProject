/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;

import domain.Bil;
import domain.Flextur;
import exception.TildelogGodkendBilFejException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
	private ComboBox<Bil> bilValg;

	private Flextur flextur;
	private Stage window;




	public void setMainApp(FlexturGUI flexturGUI) {
		this.flexturGUI = flexturGUI;
	}

	@FXML
	private void handleGodkend(ActionEvent event) {
		try{
			fsController.tildelBil(flextur.getFlexturId(), bilValg.getSelectionModel().getSelectedItem().getId());



		}catch(TildelogGodkendBilFejException e){
			DialogueBox alert = new DialogueBoxImpl(window);

			alert.visFejl();
		}

		flexturGUI.showBestillingsOversigt();
	}	
	@FXML
	private void handleAnnuller(ActionEvent event) {
		flexturGUI.showBestillingsOversigt();
	}	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		bilValg.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setBilOplysning(newValue));
	}

	private void setBilOplysning(Bil bil) {
		bilPersoner.clear();
		bilPersoner.setText(String.valueOf(bil.getMaxAntalPersoner()));


	}

	@Override
	public void update(Observable observable, Tilstand tilstand) {
		DialogueBox alert = new DialogueBoxImpl(window);

		if(tilstand.equals(Tilstand.TILDEL_BIL)){
			try{
				fsController.godkendKørsel(flextur.getFlexturId(), kommentarFelt.getText());
				alert.visGodkendt();
			} catch (TildelogGodkendBilFejException e){
				alert.visFejl();
			}
		}
		


	}
	@Override
	void postInitialize() {
		bilValg.setItems(FXCollections.observableArrayList(fsController.getBilListe())); //?	
		this.flextur = fsController.getFlextur();
		turPersoner.setText(String.valueOf(flextur.getAntalPersoner()));
		turAutostole.setText(String.valueOf(flextur.getAutostole()));
		turBaggage.setText(String.valueOf(flextur.getBaggage()));
		turBarnevogne.setText(String.valueOf(flextur.getBarnevogne()));
		turKoerestole.setText(String.valueOf(flextur.getKoerestole()));
		kommentarFelt.setText(flextur.getKommentar());

	}    

}
