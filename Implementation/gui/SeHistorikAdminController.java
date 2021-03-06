/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import domain.HistorikForBM;
import domain.HistorikSøgning;
import domain.HistorikSøgningImpl;
import exception.CSVExportingFEJLException;
import exception.MissingOplysningExcpetion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.Observable;
import logic.Tilstand;

/**
 * GUI controller klasse for at se histsorik (bestillingsmodtagelse)
 * @author Jonas Mørch & Juyoung Choi
 *
 */
public class SeHistorikAdminController extends FSPane implements Initializable {

	private FlexturGUI flexturGUI;

	@FXML
	private Label label;

	@FXML
	private TableView<HistorikForBM> tableView;

	@FXML
	private TableColumn<HistorikForBM, String> personCPRColumn;

	@FXML
	private TableColumn<HistorikForBM, LocalDate> fraDatoColumn;

	@FXML
	private TableColumn<HistorikForBM, LocalDate> tilDatoColumn;

	@FXML
	private TableColumn<HistorikForBM, String> kommuneColumn;

	@FXML
	private TableColumn<HistorikForBM, Double> totalPrisColumn;

	@FXML
	private TableColumn<HistorikForBM, Integer> antalPersonerColumn;

	@FXML
	private TableColumn<HistorikForBM, Integer> antalTurColumn;

	@FXML
	private ComboBox<String> kommuneCombo;

	@FXML
	private DatePicker fraDato;

	@FXML
	private DatePicker tilDato;

	@FXML
	private TextField cprNummer;

	@FXML
	private Button csvFil;

	private ObservableList<HistorikForBM> resultListe = FXCollections.observableArrayList();

	private Stage window;

	@FXML
	private void handleToMenu(ActionEvent event) {

		flexturGUI.showMenuAdmin();

	}

	public void setMainApp(FlexturGUI flextur) {
		this.flexturGUI = flextur;

	}

	@FXML
	private void hentHistorikListe(ActionEvent event) {
		DialogueBox alert = new DialogueBoxImpl(window);

		resultListe.clear();

		HistorikSøgning hs = new HistorikSøgningImpl();
		try {
			hs.setFraDato(fraDato.getValue());
			hs.setTilDato(tilDato.getValue());
			hs.setKommune(kommuneCombo.getValue());
			if (cprNummer.getText().isEmpty()) {
				hs.setCprNummer(null);
			} else {
				hs.setCprNummer(cprNummer.getText());
			}

			fsController.angivSøgningOplysningerForBM(hs);

		} catch (MissingOplysningExcpetion e) {
			alert.visOplysningManglerAdvarselDialog();
		}

	}

	@FXML
	private void exporterCsvFil() {
		DialogueBox alert = new DialogueBoxImpl(window);
		String filenavn = System.getProperty("user.home") + "\\" + fraDato.getValue().toString() + "_"
				+ tilDato.getValue().toString() + "_" + kommuneCombo.getValue() + ".csv";

		try {

			if (resultListe.isEmpty()) {

				alert.visCSVFilExportingAdvarselDialog(filenavn, resultListe);

			} else {

				fsController.exporterHistorikForBM(filenavn, resultListe);
				alert.visGemtDialogue(filenavn);
			}

		} catch (CSVExportingFEJLException exe) {
			alert.visCSVFilExportingFejlDialog();

		}
		kommuneCombo.setValue("");

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		personCPRColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, String>("cprNummer"));
		fraDatoColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, LocalDate>("fraDato"));
		tilDatoColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, LocalDate>("tilDato"));
		kommuneColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, String>("kommune"));
		totalPrisColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, Double>("totalPris"));
		antalPersonerColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, Integer>("antalPersoner"));
		antalTurColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, Integer>("antalTur"));
		fraDato.setValue(LocalDate.now());
		tilDato.setValue(LocalDate.now());
	}

	@Override
	void postInitialize() {

		kommuneCombo.setItems(FXCollections.observableArrayList(fsController.getKommuneListe()));

	}

	@Override
	public void update(Observable observable, Tilstand tilstand) {
		if (tilstand.equals(Tilstand.SØG_HISTORIK_BM)) {
			resultListe.addAll(fsController.getHistorikResultForBM());
			tableView.setItems(resultListe);
		}

	}

}
