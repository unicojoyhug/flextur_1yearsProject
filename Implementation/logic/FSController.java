package logic;

import java.util.List;


import domain.Flextur;
import domain.HistorikForBM;
import domain.HistorikSøgning;

public interface FSController extends Observable {

	void søgHistorik();
	
	List<Flextur> angivSøgningOplysninger(HistorikSøgning historikSøgning);

	List<HistorikForBM> angivSøgningOplysningerForBM(HistorikSøgning historikSøgning);

	String[] getKommuneListe();

	void exporterHistorikForBM(String filenavn, List<HistorikForBM> historikListe);

	void exporterHistorikForKunde(String filenavn, List<Flextur> historikListe);

	
}
