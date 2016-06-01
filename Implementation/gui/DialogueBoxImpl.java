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

/**
 * 
 * @author Juyoung Choi
 *
 */
public class DialogueBoxImpl implements DialogueBox {
	private Stage window;
	private Alert warning = new Alert(AlertType.WARNING);
	private Alert confirmation = new Alert(AlertType.CONFIRMATION);
	private Alert error = new Alert(AlertType.ERROR);
	private Alert info = new Alert(AlertType.INFORMATION);

	public DialogueBoxImpl(Stage window) {
		this.window = window;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.DialogBox#visOplysningManglerAdvarselDialog()
	 */
	@Override
	public void visOplysningManglerAdvarselDialog() {
		warning.initOwner(window);
		warning.setTitle("ADVARSEL");
		warning.setHeaderText("Mangler nogle oplysninger");
		warning.setContentText("Tjek venligst tekstfelterne");
		warning.showAndWait();
	}

	@Override
	public void visTomListeDialog() {
		info.initOwner(window);
		info.setTitle("ADVARSEL");
		info.setHeaderText("Matchende list er tom.");
		info.setContentText("Der er ingen list der matchende.");
		info.showAndWait();
	}

	@Override
	public void visPrisDelay() {
		info.initOwner(window);
		info.setTitle("ADVARSEL");
		info.setHeaderText("Pris udregning tager tid.");
		info.setContentText("Vent venligst.");
		info.showAndWait();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.DialogBox#visCSVFilExportingAdvarselDialog()
	 */
	@Override
	public void visCSVFilExportingAdvarselDialog(String filenavn, List<HistorikForBM> resultListe) {
		confirmation.initOwner(window);
		confirmation.setTitle("ADVARSEL");
		confirmation.setHeaderText("TOM LIST");
		confirmation.setContentText("Vil du gemme den som tom list?");

		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == ButtonType.OK) {

			FSController fsController = new FSControllerImpl();
			fsController.exporterHistorikForBM(filenavn, resultListe);
			visGemtDialogue(filenavn);

		}
	}

	@Override
	public void visCSVFilExportingFejlDialog() {
		error.initOwner(window);
		error.setTitle("FEJL");
		error.setHeaderText("CSV fil EXPORTERING FEJL");
		error.setContentText("Prøv igen.");
		error.showAndWait();

	}

	@Override
	public void visGemtDialogue(String filenavn) {
		confirmation.initOwner(window);
		confirmation.setTitle("GEM FIL");
		confirmation.setHeaderText("Vil du gerne gemme fil?");
		confirmation.setContentText("Tjek din mappe : " + filenavn);

		confirmation.showAndWait();

	}

	@Override
	public void visCSVFilExportingAdvarselDialogForKunde(String filenavn, List<Flextur> resultListe) {
		confirmation.initOwner(window);
		confirmation.setTitle("ADVARSEL");
		confirmation.setHeaderText("TOM LIST");
		confirmation.setContentText("Vil du gemme den som tom list?");

		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == ButtonType.OK) {

			FSController fsController = new FSControllerImpl();
			fsController.exporterHistorikForKunde(filenavn, resultListe);
			visGemtDialogue(filenavn);

		}

	}

	@Override
	public void visLoginFejllDialog() {
		warning.initOwner(window);
		warning.setTitle("ERROR");
		warning.setHeaderText("Fejl i login");
		warning.setContentText("Prøv igen");
		warning.showAndWait();
	}

	@Override
	public void visIngenTurValgt() {
		error.initOwner(window);
		error.setTitle("FEJL");
		error.setHeaderText("Ingen tur er valgt.");
		error.setContentText("Vælg en tur for godkendelse");
		error.showAndWait();
	}

	@Override
	public void visFejl() {
		error.initOwner(window);
		error.setTitle("FEJL");
		error.setHeaderText("Der er noget fejl.");
		error.setContentText("Prøv igen");
		error.showAndWait();
	}

	@Override
	public void visGodkendt() {
		info.initOwner(window);
		info.setTitle("GEMT");
		info.setHeaderText("Godkendelse gemt");
		info.setContentText("Godkendelse er gemt sikkert");
		info.showAndWait();
	}

}
