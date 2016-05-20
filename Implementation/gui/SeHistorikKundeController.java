/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import domain.Flextur;
import domain.HistorikSøgning;
import domain.HistorikSøgningImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.Observable;
import logic.Tilstand;

/**
 *
 * @author Jonas Mørch, Juyoung Choi
 *
 */
public class SeHistorikKundeController extends FSPane implements Initializable {

	private FlexturGUI flexturGUI;
	@FXML
	private TableView<Flextur> tableView;
	@FXML
	private TableColumn<Flextur, LocalDate> DatoColumn;
	@FXML
	private TableColumn<Flextur, String> fraKommuneColumn;
	@FXML
	private TableColumn<Flextur, String> tilKommuneColumn;
	@FXML
	private TableColumn<Flextur, Double> totalPrisColumn;
	@FXML
	private TableColumn<Flextur, Integer> antalPersonerColumn;
	@FXML
	private DatePicker fraDato;
	@FXML
	private DatePicker tilDato;
	private ObservableList<Flextur> resultListe = FXCollections.observableArrayList();

	@FXML
	private void handleToMenu(ActionEvent event) {
		flexturGUI.showMenuKunde();
	}

	public void setMainApp(FlexturGUI flexturGUI) {
		this.flexturGUI = flexturGUI;
	}

	// TODO input validation : empty text field
	@FXML
	private void hentHistorikListe(ActionEvent event) {
		resultListe.clear();
		HistorikSøgning hs = new HistorikSøgningImpl();
		hs.setFraDato(fraDato.getValue());
		hs.setTilDato(tilDato.getValue());
		hs.setCprNummer("000000000"); //TODO
		hs.setKommune("Herning");
		resultListe.addAll(fsController.angivSøgningOplysninger(hs));
		tableView.setItems(resultListe);
	}

	// TODO to exprot csv fil : in gui with fx dependency? or another class to
	// do so
	@FXML
	private void exporterCsvFil() {
		// f.eks.
		// fsController.exportCSVFil(resultListe);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		DatoColumn.setCellValueFactory(new PropertyValueFactory<Flextur, LocalDate>("dato"));
		fraKommuneColumn.setCellValueFactory(new PropertyValueFactory<Flextur, String>("Fra kommune"));
		tilKommuneColumn.setCellValueFactory(new PropertyValueFactory<Flextur, String>("Til kommune"));
		totalPrisColumn.setCellValueFactory(new PropertyValueFactory<Flextur, Double>("totalPris"));
		antalPersonerColumn.setCellValueFactory(new PropertyValueFactory<Flextur, Integer>("antalPersoner"));

		// //TODO
		// kommune.getSelectionModel().selectedItemProperty()
		// .addListener((observable, oldValue, newValue) -> (newValue));

	}

	@Override
	public void update(Observable observable, Object tilstand) {
		// TODO Auto-generated method stub

		if (observable.equals(Tilstand.HENT_HISTORIK)) {
			// resultListe.clear();
			tableView.setItems(resultListe);
		}

	}

}
