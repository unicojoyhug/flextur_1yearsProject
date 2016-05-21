/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import domain.HistorikForBM;
import domain.HistorikSøgning;
import domain.HistorikSøgningImpl;
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
import seHistorik.Kommune;
import logic.FSController;
import logic.FSControllerImpl;

/**
 *
 * @author Jonas Mørch, Juyoung Choi
 *
 */
public class SeHistorikAdminController extends FSPane implements Initializable {

	private FlexturGUI flexturGUI;
	private FSController FSC = new FSControllerImpl();
	

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

	// TODO input validation : empty text field (DONE with EXCEPTION ) 
	//TODO kommune combo back to empty choice : DONE with : setValue("")
	@FXML
	private void hentHistorikListe(ActionEvent event) {
		DialogBox alert = new DialogBox(window);

		resultListe.clear();

		HistorikSøgning hs = new HistorikSøgningImpl();
		try {
			hs.setFraDato(fraDato.getValue());
			hs.setTilDato(tilDato.getValue());
			hs.setKommune(getKommune(kommuneCombo));
			if (cprNummer.getText().isEmpty()) {
				hs.setCprNummer(null);
			} else {
				hs.setCprNummer(cprNummer.getText());
			}
			
			resultListe.addAll(fsController.angivSøgningOplysningerForBM(hs));		
			tableView.setItems(resultListe);
		} catch (MissingOplysningExcpetion e){
			alert.visOplysningManglerAdvarselDialog();
		} 

		kommuneCombo.setValue(""); 

	}

	// TODO to exprot csv fil : in gui with fx dependency? or another class to
	// do so
	@FXML
	private void exporterCsvFil() {
		if(!resultListe.isEmpty()){


			String filenavn = System.getProperty("user.home")+"\\"+fraDato.getValue().toString() 
					+ "_" + tilDato.getValue().toString() + "_" + getKommune(kommuneCombo)+ ".csv" ;

			fsController.exporterCSVForKommune(filenavn, resultListe);

		}else{

		}
	}

	private String getKommune(ComboBox<String> kommuneCombo2) {
		String k = kommuneCombo2.getValue();
		return k;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		kommuneCombo.setItems(FXCollections.observableArrayList(FSC.getKommuneListe()));
		personCPRColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, String>("cprNummer"));
		fraDatoColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, LocalDate>("fraDato"));
		tilDatoColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, LocalDate>("tilDato"));
		kommuneColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, String>("kommune"));
		totalPrisColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, Double>("totalPris"));
		antalPersonerColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, Integer>("antalPersoner"));
		antalTurColumn.setCellValueFactory(new PropertyValueFactory<HistorikForBM, Integer>("antalTur"));

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
