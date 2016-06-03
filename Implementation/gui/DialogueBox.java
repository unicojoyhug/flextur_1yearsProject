package gui;

import java.util.List;

import domain.Flextur;
import domain.HistorikForBM;
/**
 * 
 * @author Juyoung Choi
 *
 */
public interface DialogueBox {

	void visOplysningManglerAdvarselDialog();

	void visGemtDialogue(String filenavn);

	void visCSVFilExportingFejlDialog();

	void visCSVFilExportingAdvarselDialog(String filenavn, List<HistorikForBM> resultListe);
	
	void visCSVFilExportingAdvarselDialogForKunde(String filenavn, List<Flextur> resultListe);

	void visTomListeDialog();

	void visLoginFejllDialog();

	void visIngenTurValgt();

	void visFejl();

	void visGodkendt();

	void visPrisDelay();

	void visGemFlextur();

	void visDatoFejl();

	void visProfilOperettet();

	void visKontaktOs();

	void visProfilRettet();


}