package logic;

import java.util.List;

import domain.Bruger;
import domain.Flextur;
import domain.HistorikForBM;
import domain.HistorikSøgning;

public interface FSController extends Observable {

	void søgHistorik();
	
	void angivSøgningOplysninger(HistorikSøgning historikSøgning);

	void angivSøgningOplysningerForBM(HistorikSøgning historikSøgning);

	String[] getKommuneListe();

	void exporterHistorikForBM(String filenavn, List<HistorikForBM> historikListe);

	void exporterHistorikForKunde(String filenavn, List<Flextur> historikListe);

	Bruger checkLogin(String loginId, String kodeord);

	List<Flextur> getHistorikResultForKunde();

	List<HistorikForBM> getHistorikResultForBM();

	void gemFlextur(Flextur flextur);

	
}
