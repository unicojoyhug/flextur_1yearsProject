package gui;

import java.util.List;

import domain.Flextur;
import domain.HistorikForBM;

public interface DialogBox {

	void visOplysningManglerAdvarselDialog();

	void visGemtDialogue(String filenavn);

	void visCSVFilExportingFejlDialog();

	void visCSVFilExportingAdvarselDialog(String filenavn, List<HistorikForBM> resultListe);
	
	void visCSVFilExportingAdvarselDialogForKunde(String filenavn, List<Flextur> resultListe);

	void visTomListeDialog();

	void visLoginFejllDialog();

	void visIngenTurValgt();


}