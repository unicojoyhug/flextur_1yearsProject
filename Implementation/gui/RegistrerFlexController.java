/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import domain.Flextur;
import domain.FlexturImpl;
import exception.MissingOplysningExcpetion;
import javafx.collections.FXCollections;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Observable;
import logic.Tilstand;

/**
 *
 * @author Jonas Mørch & Juyoung Choi
 */
public class RegistrerFlexController extends FSPane implements Initializable {
	@FXML
	private Label ventText;

	@FXML
	private ProgressIndicator loading;

	@FXML
	private ComboBox<String> fraKommune;
	@FXML
	private ComboBox<String> tilKommune;
	@FXML
	private TextArea kommentarer;
	@FXML
	private TextField fraAddresse, tilAddresse, prisfelt, PostnrO, PostnrD, kilometer, personer, barnevogne, koerestole,
	baggage, autostole, tidspunkt, cprNummer, kundeID, forventetTid;


	@FXML
	private DatePicker dato;
	private FlexturGUI flexturGUI;
	private Flextur fti = new FlexturImpl();
	private Service<Void> backgroundThread;
	private DecimalFormat format = new DecimalFormat("#.##");
	private Stage window;

	@FXML
	private void handleBeregnKM(ActionEvent event) throws Throwable, IOException {
		DialogueBox alert = new DialogueBoxImpl(window);

		try{
			fti.setFraPostnummer(PostnrO.getText().isEmpty() ? 0 :Integer.parseInt(PostnrO.getText()));
			fti.setTilPostnummer(PostnrD.getText().isEmpty() ? 0 :Integer.parseInt(PostnrD.getText()));
			fti.setFraAdress(fraAddresse.getText());
			fti.setTilAdress(tilAddresse.getText());
			fsController.udregnKilometer(fti);
			kilometer.setText(fti.getDistance());
			forventetTid.setText(fti.getDuration());
			String[] parts = fti.getDistance().split(" ");
			String part1 = parts[0];
			fti.setKilometer(Double.parseDouble(part1.replace(',', '.')));
		}
		catch(MissingOplysningExcpetion e){
			alert.visOplysningManglerAdvarselDialog();
		}
	}

	@FXML
	private void handleBeregnPris(ActionEvent event) {
		loading.setVisible(true);

		backgroundThread = new Service<Void>() {

			@Override
			protected Task<Void> createTask() {

				return new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						if (fti.getKilometer() == 0)
							try {
								handleBeregnKM(event);
							} catch (Throwable e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						fti.setFraKommune(fraKommune.getValue());
						fti.setDato(dato.getValue());
						fti.setTilKommune(tilKommune.getValue());
						fti.setAntalPersoner(Integer.parseInt(personer.getText()));
						fti.setAutostole(Integer.parseInt(autostole.getText()));
						fti.setBaggage(Integer.parseInt(baggage.getText()));
						fti.setBarnevogne(Integer.parseInt(barnevogne.getText()));
						fti.setKoerestole(Integer.parseInt(koerestole.getText()));
						fsController.udregnPrisMedTråd(fti);
						updateMessage(String.valueOf(format.format(fti.getPris())).replace('.', ','));
						return null;
					}

				};
			}

		};

		backgroundThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent event) {
				loading.setVisible(false);

				prisfelt.textProperty().unbind();
			}

		});

		prisfelt.textProperty().bind(backgroundThread.messageProperty());
		backgroundThread.restart();
	}

	@FXML
	private void handleBestilFlextur(ActionEvent event) {
		DialogueBox alert = new DialogueBoxImpl(window);

		try{
			if (fti.getPris() == 0.0) {
				handleBeregnPris(event);
			}
			if (kundeID.getText().isEmpty())
				handleGetKundeID(event);

			fti.setKundeId(kundeID.getText().isEmpty() ? 0 : Integer.parseInt(kundeID.getText()));
			fti.setTid(tidspunkt.getText().isEmpty() ? null :LocalTime.parse(tidspunkt.getText()));
			fti.setKommentar(kommentarer.getText());
			fsController.angivFlexturOplysninger(fti);
		}catch(MissingOplysningExcpetion e){
				kundeID.setText(null);
			alert.visOplysningManglerAdvarselDialog();
		}
	}

	@FXML
	private void handleGetKundeID(ActionEvent event) {

		String CPR = cprNummer.getText();
		int ID = fsController.getKundeID(CPR).getKundeID();
		fti.setKundeId(ID);
		kundeID.setText(String.valueOf(ID));
		
	}

	@FXML
	private void handleToMenu(ActionEvent event) {
		flexturGUI.showMenuAdmin();
	}

	public void setMainApp(FlexturGUI flextur) {
		this.flexturGUI = flextur;

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		dato.setValue(LocalDate.now());
		
		loading.setVisible(false);

	}

	@Override
	public void update(Observable observable, Tilstand tilstand) {
		DialogueBox alert = new DialogueBoxImpl(window);

		if(tilstand.equals(Tilstand.BESTIL_KØRSEL)){
			alert.visGemFlextur();
			flexturGUI.showBestillingsOversigt();

		}


	}

	@Override
	void postInitialize() {
		fraKommune.setItems(FXCollections.observableArrayList(fsController.getKommuneListe()));
		tilKommune.setItems(FXCollections.observableArrayList(fsController.getKommuneListe()));

	}

}
