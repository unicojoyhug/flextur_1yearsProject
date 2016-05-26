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
import domain.Bruger;
import domain.BrugerImpl;
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
import logic.Observable;
import logic.Tilstand;

/**
 *
 * @author Jonas Mørch
 */
public class BestilFlexController extends FSPane implements Initializable {

	@FXML
	private ChoiceBox<String> fraKommune, tilKommune;
	@FXML
	private TextArea kommentarer;
	@FXML
	private TextField fraAddresse, tilAddresse, prisfelt, PostnrO, PostnrD, kilometer, personer, barnevogne, koerestole,
			baggage, autostole, tidspunkt, forventetTid;
	@FXML
	private DatePicker dato;
	private FlexturGUI flexturGUI;
	private Flextur fti = new FlexturImpl();
	private FSController FSC = new FSControllerImpl();
	private Bruger bruger = new BrugerImpl();

	@FXML
	private void handleBeregnKM(ActionEvent event) throws Throwable, IOException {
		fti.setFraPostnummer(Integer.parseInt(PostnrO.getText()));
		fti.setTilPostnummer(Integer.parseInt(PostnrD.getText()));
		fti.setFraAdress(fraAddresse.getText());
		fti.setTilAdress(tilAddresse.getText());
		FSC.udregnKilometer(fti);

		kilometer.setText(fti.getDistance());
		forventetTid.setText(fti.getDuration());
		String[] parts = fti.getDistance().split(" ");
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
				FSC.udregnPris(fti);
				prisfelt.setText(String.valueOf(fti.getPris()));
			} catch (IOException e) {
				System.out.println("Internet fejl");
			} catch (Throwable e) {
				System.out.println("Parameter fejl");
				e.printStackTrace();
			}
		else {
			FSC.udregnPris(fti);
			prisfelt.setText(String.valueOf(fti.getPris()));
		}
	}

	@FXML
	private void handleBestilFlextur(ActionEvent event) {
		if (fti.getPris() == 0.0) {
			handleBeregnPris(event);
		}
		fti.setKundeId(bruger.getId());
		fti.setTid(LocalTime.parse(tidspunkt.getText()));
		fti.setKommentar(kommentarer.toString());
		fsController.angivFlexturOplysninger(fti);
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

	}

	@Override
	public void update(Observable observable, Tilstand tilstand) {
		// TODO Auto-generated method stub

	}

	@Override
	void postInitialize() {
		fraKommune.setItems(FXCollections.observableArrayList(fsController.getKommuneListe()));
		fraKommune.getSelectionModel().selectFirst();
		tilKommune.setItems(FXCollections.observableArrayList(fsController.getKommuneListe()));
		tilKommune.getSelectionModel().selectFirst();
	}

}
