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
import domain.KundeImpl;
import exception.MissingOplysningExcpetion;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Observable;
import logic.Tilstand;

/**
 *
 * GUI controller klasse for at oprette profil (bestillingsmodtagelse)
 * @author Jonas Mørch & Juyoung Choi
 */
public class OpretProfilController extends FSPane implements Initializable {

	@FXML
	private Label cprfejl;
	private FlexturGUI flextur;
	@FXML
	private TextField fornavn, efternavn, addresse, telefonnr, email, cprnummer, password, postnummer;
	@FXML
	private ChoiceBox<String> kommuneCombo;
	private Kunde kunde = new KundeImpl();
	private Bruger bruger = new BrugerImpl();
	private Stage window;

	public void setMainApp(FlexturGUI flextur) {
		this.flextur = flextur;

	}

	@FXML
	private void handleCancel(ActionEvent event) {
		flextur.showMenuAdmin();
	}

	@FXML
	private void handleOpret(ActionEvent event) throws NoSuchAlgorithmException {
		DialogueBox alert = new DialogueBoxImpl(window);
		try{
			kunde.setFornavn(fornavn.getText());
			kunde.setEfternavn(efternavn.getText());
			kunde.setAdress(addresse.getText());
			kunde.setCprNummer(cprnummer.getText());
			kunde.setKommune(kommuneCombo.getValue());
			kunde.setTelefon(telefonnr.getText());
			kunde.setEmail(email.getText());
			bruger.setAndEncryptPassword(password.getText());
			kunde.setKodeord(bruger.getEncryptedKodeord());
			kunde.setPostnummer(Integer.parseInt(postnummer.getText()));

			fsController.opretKundeProfil(kunde);
		}catch(MissingOplysningExcpetion e){
			alert.visOplysningManglerAdvarselDialog();
		}
	}

	@FXML
	private void handleTjekCPR(ActionEvent event) {
		cprfejl.setVisible(false);
		String CPR = cprnummer.getText();
		Kunde KundeID = fsController.getKundeID(CPR);
		if (KundeID.getKundeID() > 0) {
			cprfejl.setVisible(true);

		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@Override
	public void update(Observable observable, Tilstand tilstand) {
		DialogueBox alert = new DialogueBoxImpl(window);
		if(tilstand.equals(Tilstand.KUNDE_OPRETTET)){
			alert.visProfilOperettet();
		}
	}

	@Override
	void postInitialize() {
		kommuneCombo.setItems(FXCollections.observableArrayList(fsController.getKommuneListe()));

	}

}
