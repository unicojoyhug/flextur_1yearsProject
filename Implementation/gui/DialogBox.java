package gui;

import java.util.List;

import domain.HistorikForBM;

public interface DialogBox {

	void visOplysningManglerAdvarselDialog();

	void visGemtDialogue(String filenavn);

	void visCSVFilExportingFejlDialog();

	void visCSVFilExportingAdvarselDialog(String filenavn, List<HistorikForBM> resultListe);

	void visTomListeDialog();

}