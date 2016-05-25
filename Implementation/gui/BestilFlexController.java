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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.FSController;
import logic.FSControllerImpl;
import logic.Observable;
import logic.PrisUdregner;
import logic.Tilstand;
import sats.Sats;
import util.KilometerUdregningAdapter;
import util.KilometerUdregningAdapterFactory;

/**
 *
 * @author Jonas Mørch
 */
public class BestilFlexController extends FSPane implements Initializable {

	@FXML
	private ChoiceBox<String> fraKommune, tilKommune;
	@FXML
	private ProgressBar prisBar;
	@FXML
	private TextArea kommentarer;
	@FXML
	private TextField fraAddresse, tilAddresse, prisfelt, PostnrO, PostnrD, kilometer, forventetTid, personer,
			barnevogne, koerestole, baggage, autostole, tidspunkt;
	@FXML
	private DatePicker dato;
	private FlexturGUI flexturGUI;
	private String seperator = " , ";
	private Flextur fti = new FlexturImpl();
	private PrisUdregner PU = new PrisUdregner();
	private FSController FSC = new FSControllerImpl();
	private Bruger bruger = new BrugerImpl();
	
	

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

		KilometerUdregningAdapterFactory KU = new KilometerUdregningAdapterFactory();
		KilometerUdregningAdapter KUadapter = KU.getKilometerUdregningAdapter();
		String KM = KUadapter.getDistance(Origin, Destination);
		kilometer.setText(KM);
		forventetTid.setText(KUadapter.getDuration());
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
				double result = PU.takstUdregner(fti);
				fti.setPris(result);
				prisfelt.setText(String.valueOf(result));
			} catch (IOException e) {
				System.out.println("Internet fejl");
			} catch (Throwable e) {
				System.out.println("Parameter fejl");
				e.printStackTrace();
			}
		else {
			double result = PU.takstUdregner(fti);
			fti.setPris(result);
			prisfelt.setText(String.valueOf(result));
		}
	}

	@FXML
	private void handleBestilFlextur(ActionEvent event) {
		if (fti.getPris() == 0.0) {
			handleBeregnPris(event);
		}
		fti.setKundeId(bruger.getId());
		fti.setFraAdress(fraAddresse.getText());
		fti.setTilAdress(tilAddresse.getText());
		fti.setTid(LocalTime.parse(tidspunkt.getText()));
		fti.setKommentar(kommentarer.toString());
		fti.setFraPostnummer(Integer.parseInt(PostnrO.getText()));
		fti.setTilPostnummer(Integer.parseInt(PostnrD.getText()));

		FSC.gemFlextur(fti);
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
		prisBar.setVisible(false);
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
