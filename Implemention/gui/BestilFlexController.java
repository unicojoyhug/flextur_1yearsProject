/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import util.KilometerUdregning;

/**
 *
 * @author Jonas Mørch
 */
public class BestilFlexController implements Initializable {

	@FXML
	private ChoiceBox<?> fraKommune, tilKommune;
	@FXML
	private Spinner<?> personer, barnevogne, koerestole, baggage, autostole;
	@FXML
	private Spinner<?> tidspunkt;
	@FXML
	private ProgressBar prisBar;
	@FXML
	private TextArea kommentarer;
	@FXML
	private TextField fraAddresse, tilAddresse, pris, PostnrO, PostnrD, kilometer, forventetTid;
	@FXML
	private DatePicker dato;
	private FlexturGUI flextur;
	private String seperator = " , ";
	private KilometerUdregning KU = new KilometerUdregning();

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
	private void handleBeregnPris(ActionEvent event) {
		prisBar.setVisible(true);
		String distance = kilometer.getText();
		String OriginKommune = fraKommune.getConverter().toString();
		String DestinationKommune = tilKommune.getConverter().toString();
		//TODO String pris = someone.udregn(distance, OriginKommune, DestinationKommune);
		//TODO pris.setText(pris);
		prisBar.setVisible(false);
	}

	@FXML
	private void handleBestilFlextur(ActionEvent event) {
		System.out.println("Ikke impletemteret");
		
		String distance = kilometer.getText();
		String OriginKommune = fraKommune.getConverter().toString();
		String DestinationKommune = tilKommune.getConverter().toString();
		String Pris = pris.getText();
		//TODO Finish method
		
	}
	public void setMainApp(FlexturGUI flextur) {
		this.flextur = flextur;

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		prisBar.setVisible(false);
	}

}
