/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import domain.HistorikForBM;
import domain.HistorikSøgning;
import domain.HistorikSøgningImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.Observable;
import logic.Tilstand;

/**
 *
 * @author Jonas Mørch, Juyoung Choi
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
	private ChoiceBox<String> kommune;
	
	@FXML
	private ComboBox<String> kommuneCombo;
	
	@FXML
	private DatePicker fraDato;
	
	@FXML
	private DatePicker tilDato;
	
	@FXML
	private TextField cprNummer;
	
	
	@FXML
	private ComboBox<?> cprsoeg;
	
	@FXML
	private Button csvFil;
	
	private ObservableList<HistorikForBM> resultListe = FXCollections.observableArrayList();


	@FXML
	private void handleToMenu(ActionEvent event) {
		flexturGUI.showMenuAdmin();
	}

	public void setMainApp(FlexturGUI flexturGUI) {
		this.flexturGUI = flexturGUI;
	}
	
	//TODO input validation : empty text field
	@FXML
	private void hentHistorikListe(ActionEvent event) {
		
		resultListe.clear();
		
		HistorikSøgning hs = new HistorikSøgningImpl();
		hs.setFraDato(fraDato.getValue());
		hs.setTilDato(tilDato.getValue());
		hs.setKommune(getKommune(kommune));
		if(cprNummer.getText().isEmpty()){
			hs.setCprNummer(null);
		}else{
			hs.setCprNummer(cprNummer.getText());
		}
		System.out.println(hs);
		
		resultListe.addAll(fsController.angivSøgningOplysningerForBM(hs));	
//		resultListe.addAll(fsController.angivSøgningOplysningerForBM(hs));
//		
		tableView.setItems(resultListe);
	}

	
	//TODO to exprot csv fil : in gui with fx dependency? or another class to do so
	@FXML
	private void exporterCsvFil(){
		//f.eks.
		//fsController.exportCSVFil(resultListe); 
	}
	
	private void dropDownMenu() {
//		for(String s: fsController.getKommuneListe()){
//			System.out.println(s);
//		}
		
	
		kommune.getItems().addAll("Herning", "Aarhus" );
	}
	
	private String getKommune(ChoiceBox<String> kommune) {
		String k = kommune.getValue();
		return k;
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	
		
		
		dropDownMenu();
		
//		for(String s : fsController.getKommuneListe()){
//			kommune.getItems().add(s);
//		}
//		kommune.setItems(FXCollections.observableArrayList(fsController.getKommuneListe()));
		
//		kommuneCombo.setItems(FXCollections.observableArrayList(fsController.getKommuneListe()));

		
		personCPRColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, String>("cprNummer"));
		fraDatoColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, LocalDate>("fraDato"));
		tilDatoColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, LocalDate>("tilDato"));
		kommuneColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, String>("kommune"));
		totalPrisColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, Double>("totalPris"));
		antalPersonerColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, Integer>("antalPersoner"));
		antalTurColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, Integer>("antalTur"));

		
		
		
		
//		//TODO
//		kommune.getSelectionModel().selectedItemProperty()
//		.addListener((observable, oldValue, newValue) -> (newValue));

	}

	@Override
	public void update(Observable observable, Object tilstand) {
			// TODO Auto-generated method stub
		
		if(observable.equals(Tilstand.HENT_HISTORIK)){	
//			resultListe.clear();
			tableView.setItems(resultListe);
		}
		
	}



}
