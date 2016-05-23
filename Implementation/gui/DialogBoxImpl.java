package gui;

import java.util.List;
import java.util.Optional;

import domain.Flextur;
import domain.HistorikForBM;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import logic.FSController;
import logic.FSControllerImpl;

public class DialogBoxImpl implements DialogBox {
	private Stage window;

	public DialogBoxImpl(Stage window){
		this.window = window;
	}
	
	/* (non-Javadoc)
	 * @see gui.DialogBox#visOplysningManglerAdvarselDialog()
	 */
	@Override
	public void visOplysningManglerAdvarselDialog(){
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(window);
		alert.setTitle("ADVARSEL");
		alert.setHeaderText("Mangler nogle oplysninger");
		alert.setContentText("Tjek venligst tekstfelterne");
		alert.showAndWait();
	}
	
	@Override
	public void visTomListeDialog(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(window);
		alert.setTitle("ADVARSEL");
		alert.setHeaderText("Matchende list er tom.");
		alert.setContentText("Der er ingen list der matchende.");
		alert.showAndWait();
	}
	
	/* (non-Javadoc)
	 * @see gui.DialogBox#visCSVFilExportingAdvarselDialog()
	 */
	@Override
	public void visCSVFilExportingAdvarselDialog(String filenavn, List<HistorikForBM> resultListe){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(window);
		alert.setTitle("ADVARSEL");
		alert.setHeaderText("TOM LIST");
		alert.setContentText("Vil du gemme den som tom list?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
						
			FSController fsController = new FSControllerImpl();
			fsController.exporterHistorikForBM(filenavn, resultListe);
			visGemtDialogue(filenavn);

		} 
	}
	
	@Override
	public void visCSVFilExportingFejlDialog(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(window);
		alert.setTitle("FEJL");
		alert.setHeaderText("CSV fil EXPORTERING FEJL");
		alert.setContentText("Prøv igen.");
		alert.showAndWait();

	}

	@Override
	public void visGemtDialogue(String filenavn){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(window);
		alert.setTitle("GEM FIL");
		alert.setHeaderText("Vil du gerne gemme fil?");
		alert.setContentText("Tjek din mappe : " + filenavn );

		alert.showAndWait();
		
	}

	@Override
	public void visCSVFilExportingAdvarselDialogForKunde(String filenavn, List<Flextur> resultListe) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(window);
		alert.setTitle("ADVARSEL");
		alert.setHeaderText("TOM LIST");
		alert.setContentText("Vil du gemme den som tom list?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
						
			FSController fsController = new FSControllerImpl();
			fsController.exporterHistorikForKunde(filenavn, resultListe);
			visGemtDialogue(filenavn);

		} 
		
	}

	
	@Override
	public void visLoginFejllDialog(){
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(window);
		alert.setTitle("ERROR");
		alert.setHeaderText("Fejl i login");
		alert.setContentText("Prøv igen");
		alert.showAndWait();
	}
	
}
