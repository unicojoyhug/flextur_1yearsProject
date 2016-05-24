/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import logic.PrisUdregner;
import sats.Sats;
import util.KilometerUdregningAdapter;
import util.KilometerUdregningAdapterFactory;

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
	private TextField fraAddresse, tilAddresse, prisfelt, PostnrO, PostnrD, kilometer, forventetTid, personer,
			barnevogne, koerestole, baggage, autostole, tidspunkt;
	@FXML
	private DatePicker dato;
	private FlexturGUI flexturGUI;
	private String seperator = " , ";
	private Flextur fti = new FlexturImpl();
	private PrisUdregner PU = new PrisUdregner();

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
		String KM = KUadapter.Distance(Origin, Destination);
		kilometer.setText(KM);
		forventetTid.setText(KUadapter.Duration());
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
		fti.setEkstraTilvalg().setAntal(tilvalg());
		
		if (fti.getKilometer() == 0)
			try {
				handleBeregnKM(event);
				double result = PU.takstUdregner(fti);
				fti.setPris(result);
				prisfelt.setText(String.valueOf(result));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Internet fejl");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				System.out.println("Parameter fejl");
				e.printStackTrace();
			}
		else {
			double result = PU.takstUdregner(fti);
			fti.setPris(result);
			prisfelt.setText(String.valueOf(result));
		}
		// double result = PU.takstUdregner(fti);
		// fti.setPris(result);
		// prisfelt.setText(String.valueOf(result));

	}

	@FXML
	private void handleBestilFlextur(ActionEvent event) {
		System.out.println("Ikke impletemteret");

	}

	@FXML
	private void handleToMenu(ActionEvent event) {
		flexturGUI.showMenuKunde();
	}

	public void setMainApp(FlexturGUI flextur) {
		this.flexturGUI = flextur;

	}

	private int tilvalg(){
		int result = Integer.parseInt(barnevogne.getText()) + Integer.parseInt(koerestole.getText())+ Integer.parseInt(baggage.getText())+ Integer.parseInt(autostole.getText());
		return result;
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
