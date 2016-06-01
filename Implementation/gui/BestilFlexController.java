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
import domain.Bruger;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Observable;
import logic.Tilstand;

/**
 *
 * @author Jonas Mørch
 */
public class BestilFlexController extends FSPane implements Initializable {

	@FXML
	private ProgressIndicator loading;
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
	private Bruger bruger;
	private Stage window;
	private Service<Void> backgroundThread;
	private DecimalFormat format = new DecimalFormat("#.##");

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
						if (kilometer.getText().isEmpty())
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
			fti.setKundeId(bruger.getId());
			fti.setTid(tidspunkt.getText().isEmpty() ? null :LocalTime.parse(tidspunkt.getText()));
			fti.setKommentar(kommentarer.getText());
			fsController.angivFlexturOplysninger(fti);
		}catch(MissingOplysningExcpetion e){
			alert.visOplysningManglerAdvarselDialog();
		}

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
		loading.setVisible(false);

	}

	@Override
	public void update(Observable observable, Tilstand tilstand) {
		DialogueBox alert = new DialogueBoxImpl(window);
		if(tilstand.equals(Tilstand.BESTIL_KØRSEL)){
			alert.visGemFlextur();
			flexturGUI.showHistorikKunde();
		}

	}

	@Override
	void postInitialize() {
		bruger = fsController.getBruger();
		fraKommune.setItems(FXCollections.observableArrayList(fsController.getKommuneListe()));
//		fraKommune.getSelectionModel().selectFirst();
		tilKommune.setItems(FXCollections.observableArrayList(fsController.getKommuneListe()));
//		tilKommune.getSelectionModel().selectFirst();
	}

}
