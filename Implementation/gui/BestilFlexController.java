/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sats.Sats;
import sats.UnknownKommuneException;
import util.KilometerUdregning;

/**
 *
 * @author Jonas Mørch
 */
public class BestilFlexController implements Initializable {

	@FXML
	private ChoiceBox<String> fraKommune, tilKommune;
	@FXML
	private ProgressBar prisBar;
	@FXML
	private TextArea kommentarer;
	@FXML
	private TextField fraAddresse, tilAddresse, prisfelt, PostnrO, PostnrD, kilometer, forventetTid, personer, barnevogne,
			koerestole, baggage, autostole, tidspunkt;
	@FXML
	private DatePicker dato;
	private FlexturGUI flextur;
	private String seperator = " , ";
	private KilometerUdregning KU = new KilometerUdregning();
	private int day, month, year;

	@FXML
	private void handleBeregnKM(ActionEvent event) throws Throwable, IOException {
		StringBuilder sbO = new StringBuilder();
		sbO.append(fraAddresse.getText());
		sbO.append(seperator);
		sbO.append(PostnrO.getText());
		String Origin = sbO.toString();

		StringBuilder sbD = new StringBuilder();
		sbD.append(tilAddresse.getText());
		sbD.append(seperator);
		sbD.append(PostnrD.getText());
		String Destination = sbD.toString();

		kilometer.setText(KU.Distance(Origin, Destination));
		forventetTid.setText(KU.duration);

	}

	@FXML
	private void handleBeregnPris(ActionEvent event) throws UnknownKommuneException {
		
		setDato();
		
	//	String distance = kilometer.getText();
		String OriginKommune = fraKommune.getValue();
		String DestinationKommune = tilKommune.getValue();
		double pris = Sats.i().getSats(OriginKommune, DestinationKommune, year, month, day);
		prisfelt.setText(String.valueOf(pris));
		
	}

	private void setDato() {
		LocalDate localDate = dato.getValue();
		year = localDate.getYear();
		month = localDate.getMonthValue();
		day = localDate.getDayOfMonth();

	}

	@FXML
	private void handleBestilFlextur(ActionEvent event) {
		System.out.println("Ikke impletemteret");

		String distance = kilometer.getText();
		String OriginKommune = fraKommune.getConverter().toString();
		String DestinationKommune = tilKommune.getConverter().toString();
	//	String Pris = pris.getText();
		// TODO Finish method

	}

	@FXML
	private void handleToMenu(ActionEvent event) {
		flextur.showMenuKunde();
	}

	public void setMainApp(FlexturGUI flextur) {
		this.flextur = flextur;

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		prisBar.setVisible(false);
		fraKommune.setItems(FXCollections.observableArrayList(Sats.i().getKommuner()));
		fraKommune.getSelectionModel().selectFirst();
		tilKommune.setItems(FXCollections.observableArrayList(Sats.i().getKommuner()));
		tilKommune.getSelectionModel().selectFirst();

	}

}
