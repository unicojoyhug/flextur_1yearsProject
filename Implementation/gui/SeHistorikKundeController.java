/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import domain.Bruger;
import domain.Flextur;
import domain.HistorikSøgning;
import domain.HistorikSøgningImpl;
import exception.CSVExportingFEJLException;
import exception.MissingOplysningExcpetion;
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
 *
 * @author Jonas Mørch, Juyoung Choi
 *
 */
public class SeHistorikKundeController extends FSPane implements Initializable {

	private FlexturGUI flexturGUI;
	@FXML
	private TableView<Flextur> tableView;
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
	
	private ObservableList<Flextur> resultListe = FXCollections.observableArrayList();
	private Stage window;
	private Bruger bruger;
	
	
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
		DialogueBox alert = new DialogueBoxImpl(window);
		
		try{
		resultListe.clear();
		HistorikSøgning hs = new HistorikSøgningImpl();
		hs.setFraDato(fraDato.getValue());
		hs.setTilDato(tilDato.getValue());
		hs.setCprNummer(bruger.getLoginId());
		
		fsController.angivSøgningOplysninger(hs);
		
		
	} catch (MissingOplysningExcpetion e){
		alert.visOplysningManglerAdvarselDialog();
	} 

	}

	// TODO to exprot csv fil : in gui with fx dependency? or another class to
	// do so
	@FXML
	private void exporterCsvFil() {
		DialogueBox alert = new DialogueBoxImpl(window);
		String filenavn = System.getProperty("user.home")+"\\"+fraDato.getValue().toString() 
				+ "_" + tilDato.getValue().toString() + ".csv" ;
		
		try {

			if(resultListe.isEmpty()){
				
				alert.visCSVFilExportingAdvarselDialogForKunde(filenavn, resultListe);

			}else{

				fsController.exporterHistorikForKunde(filenavn, resultListe);
				alert.visGemtDialogue(filenavn);
			}

		} catch (CSVExportingFEJLException exe) {
			alert.visCSVFilExportingFejlDialog();

		}
		
	

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
		datoColumn.setCellValueFactory(new PropertyValueFactory<Flextur, LocalDate>("dato"));
		fraAdressColumn.setCellValueFactory(new PropertyValueFactory<Flextur, String>("fraAdress"));
		tilAdressColumn.setCellValueFactory(new PropertyValueFactory<Flextur, String>("tilAdress"));
		totalPrisColumn.setCellValueFactory(new PropertyValueFactory<Flextur, Double>("pris"));
		antalPersonerColumn.setCellValueFactory(new PropertyValueFactory<Flextur, Integer>("antalPersoner"));

		
	}

	

	@Override
	public void update(Observable observable, Tilstand tilstand) {
		


		if(tilstand.equals(Tilstand.SØG_HISTORIK_KUNDE)){
			resultListe.clear();
			resultListe.addAll(fsController.getHistorikResultForKunde());
			tableView.setItems(resultListe);
			
		}
	}

	@Override
	void postInitialize() {
		bruger = fsController.getBruger();
	}

}
