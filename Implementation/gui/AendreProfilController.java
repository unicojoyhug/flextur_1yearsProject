/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import domain.Bruger;
import domain.BrugerImpl;
import domain.Kunde;
import exception.MissingOplysningExcpetion;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Observable;
import logic.Tilstand;

/**
 * GUI controller for ændreProfil
 * @author Jonas Mørch & Juyoung Choi
 */
public class AendreProfilController extends FSPane implements Initializable {

	@FXML
	private CheckBox checkBox;
	
	@FXML
	private Label label;

	@FXML
	private TextField efternavn;

	@FXML
	private TextField adresse;

	@FXML
	private TextField postnummer;

	@FXML
	private TextField email;
	
	@FXML
	private PasswordField password;
	@FXML
	private TextField kodeord;

	@FXML
	private TextField cpr;

	@FXML
	private TextField fornavn;

	@FXML
	private TextField telefon;

	@FXML
	private ComboBox<String> kommune;

	private FlexturGUI flextur;
	private Stage window;
	private Kunde kunde;

	@FXML
	private void handleToMenu(ActionEvent event) {
		flextur.showMenuKunde();
	}
	public void setMainApp(FlexturGUI flextur) {
		this.flextur = flextur;

	}
	@FXML
	private void handleRetProfil(ActionEvent event) {
		DialogueBox alert = new DialogueBoxImpl(window);
		Bruger bruger = new BrugerImpl();
		try{
			kunde.setFornavn(fornavn.getText());
			kunde.setEfternavn(efternavn.getText());
			kunde.setAdress(adresse.getText());
			kunde.setCprNummer(cpr.getText());
			kunde.setKommune(kommune.getValue());
			kunde.setTelefon(telefon.getText());
			kunde.setEmail(email.getText());
			bruger.setAndEncryptPassword(kodeord.getText());
			kunde.setKodeord(bruger.getEncryptedKodeord());
			kunde.setPostnummer(Integer.parseInt(postnummer.getText()));
			fsController.retKundeProfil(kunde);
		}catch(MissingOplysningExcpetion | NoSuchAlgorithmException e){
			alert.visOplysningManglerAdvarselDialog();
		}


	}
	@FXML
	private void handleSletProfil(ActionEvent event) {
		DialogueBox alert = new DialogueBoxImpl(window);
		alert.visKontaktOs();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		showPassword();
	}
	@Override
	public void update(Observable observable, Tilstand tilstand) {

		if(tilstand.equals(Tilstand.KUNDE_RETTET)){
			new DialogueBoxImpl(window).visProfilRettet();	
		}

	}
	
	private void showPassword() {
		kodeord.managedProperty().bind(checkBox.selectedProperty());
		kodeord.visibleProperty().bind(checkBox.selectedProperty());
		password.managedProperty().bind(checkBox.selectedProperty().not());
		password.visibleProperty().bind(checkBox.selectedProperty().not());
		kodeord.textProperty().bindBidirectional(password.textProperty());
	}
	@Override
	void postInitialize() {

		this.kunde = fsController.readKundeProfil(fsController.getBruger().getLoginId());
		efternavn.setText(kunde.getEfternavn());
		fornavn.setText(kunde.getFornavn());
		telefon.setText(kunde.getTelefon());
		adresse.setText(kunde.getAdress());
		cpr.setText(kunde.getCprNummer());
		email.setText(kunde.getEmail());
		postnummer.setText(String.valueOf(kunde.getPostnummer()));
		kommune.setItems(FXCollections.observableArrayList(fsController.getKommuneListe()));
		kommune.setValue(kunde.getKommune());
	}    

}
