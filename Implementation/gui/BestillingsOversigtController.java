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
import exception.DatoFEJLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.Observable;
import logic.Tilstand;

/**
 *GUI controller for at se oversigt over bestilte kørsler (bestillingsmodtagelse)
 * @author Jonas Mørch & Juyoung Choi
 */
public class BestillingsOversigtController extends FSPane implements Initializable {

	@FXML
	private TableView<Flextur> tableView;
	@FXML
	private TableColumn<Flextur, String> personCPRColumn;
	@FXML
	private TableColumn<Flextur, LocalDate> datoColumn;
	@FXML
	private TableColumn<Flextur, String> fraAdressColumn;
	@FXML
	private TableColumn<Flextur, String> tilAdressColumn;
	@FXML
	private TableColumn<Flextur, Double> totalPrisColumn;
	@FXML
	private TableColumn<Flextur, Integer> antalPersonerColumn;
	@FXML
	private DatePicker fraDato;
	@FXML
	private DatePicker tilDato;

	private FlexturGUI flexturGUI;
	private ObservableList<Flextur> resultListe = FXCollections.observableArrayList();
	private Stage window;

	@FXML
	private void handleNyBestilling(ActionEvent event) {
		flexturGUI.showBestilFlex();

	}

	@FXML
	private void hentBestilteKørsler(ActionEvent event) {
		DialogueBox alert = new DialogueBoxImpl(window);
		resultListe.clear();
		try{

		fsController.søgBestilteKørsler(fraDato.getValue(), tilDato.getValue());
		} catch (DatoFEJLException e){
			alert.visDatoFejl();
		}

	}

	@FXML
	private void handleSeHistorik(ActionEvent event) {
		flexturGUI.showHistorikAdmin();

	}

	@FXML
	private void handleTildelBil(ActionEvent event) {
		DialogueBox alert = new DialogueBoxImpl(window);
		Flextur flexturForTildel = tableView.getSelectionModel().getSelectedItem();

		if (flexturForTildel != null) {
			fsController.hentBilListe(flexturForTildel);
			flexturGUI.showTildelBil();

		} else {
			alert.visIngenTurValgt();
		}
	}

	public void setMainApp(FlexturGUI flextur) {
		this.flexturGUI = flextur;

	}

	@FXML
	private void handleToMenu(ActionEvent event) {
		flexturGUI.showMenuAdmin();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		personCPRColumn.setCellValueFactory(new PropertyValueFactory<Flextur, String>("cprNummer"));
		datoColumn.setCellValueFactory(new PropertyValueFactory<Flextur, LocalDate>("dato"));
		fraAdressColumn.setCellValueFactory(new PropertyValueFactory<Flextur, String>("fraAdress"));
		tilAdressColumn.setCellValueFactory(new PropertyValueFactory<Flextur, String>("tilAdress"));
		totalPrisColumn.setCellValueFactory(new PropertyValueFactory<Flextur, Double>("pris"));
		antalPersonerColumn.setCellValueFactory(new PropertyValueFactory<Flextur, Integer>("antalPersoner"));
	

	}

	@Override
	public void update(Observable observable, Tilstand tilstand) {
		if (tilstand.equals(Tilstand.SØG_BESTILTE_KØRSLER)) {
			resultListe.clear();
			resultListe.addAll(fsController.getBestilteKøsler());
			tableView.setItems(resultListe);

		}

	}

	@Override
	void postInitialize() {
		fsController.søgAlleBestilteKørsler();
		resultListe.addAll(fsController.getBestilteKøsler());
		tableView.setItems(resultListe);

	}

}
