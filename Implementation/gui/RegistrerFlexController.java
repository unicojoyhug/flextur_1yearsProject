/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import domain.Flextur;
import domain.FlexturImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.FSController;
import logic.FSControllerImpl;
import sats.Sats;

/**
 *
 * @author Jonas Mørch
 */
public class RegistrerFlexController implements Initializable {

	@FXML
	private ChoiceBox<String> fraKommune, tilKommune;
	@FXML
	private TextArea kommentarer;
	@FXML
	private TextField fraAddresse, tilAddresse, prisfelt, PostnrO, PostnrD, kilometer, personer, barnevogne, koerestole,
			baggage, autostole, tidspunkt, cprNummer, kundeID;
	@FXML
	private DatePicker dato;
	private FlexturGUI flexturGUI;
	private String seperator = " , ";
	private Flextur fti = new FlexturImpl();
	private FSController FSC = new FSControllerImpl();

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

		String KM = FSC.udregnKilometer(Origin, Destination);
		kilometer.setText(KM);
		String[] parts = KM.split(" ");
		String part1 = parts[0];
		fti.setKilometer(Double.parseDouble(part1.replace(',', '.')));
	}

	@FXML
	private void handleBeregnPris(ActionEvent event) {
		fti.setFraKommune(fraKommune.getValue());
		fti.setDato(dato.getValue());
		fti.setTilKommune(tilKommune.getValue());
		fti.setAntalPersoner(Integer.parseInt(personer.getText()));
		fti.setAutostole(Integer.parseInt(autostole.getText()));
		fti.setBaggage(Integer.parseInt(baggage.getText()));
		fti.setBarnevogne(Integer.parseInt(barnevogne.getText()));
		fti.setKoerestole(Integer.parseInt(koerestole.getText()));

		if (fti.getKilometer() == 0)
			try {
				handleBeregnKM(event);
				double result = FSC.udregnPris(fti);
				fti.setPris(result);
				prisfelt.setText(String.valueOf(result));
			} catch (IOException e) {
				System.out.println("Internet fejl");
			} catch (Throwable e) {
				System.out.println("Parameter fejl");
				e.printStackTrace();
			}
		else {
			double result = FSC.udregnPris(fti);
			fti.setPris(result);
			prisfelt.setText(String.valueOf(result));
		}
	}

	@FXML
	private void handleBestilFlextur(ActionEvent event) {
		if (fti.getPris() == 0.0) {
			handleBeregnPris(event);
		}
		if (kundeID.getText().isEmpty())
			handleGetKundeID(event);

		fti.setKundeId(Integer.parseInt(kundeID.getText()));
		fti.setFraAdress(fraAddresse.getText());
		fti.setTilAdress(tilAddresse.getText());
		fti.setTid(LocalTime.parse(tidspunkt.getText()));
		fti.setKommentar(kommentarer.toString());
		fti.setFraPostnummer(Integer.parseInt(PostnrO.getText()));
		fti.setTilPostnummer(Integer.parseInt(PostnrD.getText()));

		FSC.angivFlexturOplysninger(fti);
	}

	@FXML
	private void handleGetKundeID(ActionEvent event) {
		String CPR = cprNummer.getText();
		int ID = FSC.getKundeID(CPR).getKundeID();
		fti.setKundeId(ID);
		kundeID.setText(String.valueOf(ID));
	}

	@FXML
	private void handleToMenu(ActionEvent event) {
		flexturGUI.showMenuKunde();
	}

	public void setMainApp(FlexturGUI flextur) {
		this.flexturGUI = flextur;

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		dato.setValue(LocalDate.now());
		fraKommune.setItems(FXCollections.observableArrayList(Sats.i().getKommuner()));
		fraKommune.getSelectionModel().selectFirst();
		tilKommune.setItems(FXCollections.observableArrayList(Sats.i().getKommuner()));
		tilKommune.getSelectionModel().selectFirst();

	}

}
