package logic;

import java.time.LocalDate;
import java.util.List;

import domain.Bruger;
import domain.Flextur;
import domain.HistorikForBM;
import domain.HistorikSøgning;
import domain.Kunde;

public interface FSController extends Observable {

	void søgHistorik();
	
	void angivSøgningOplysninger(HistorikSøgning historikSøgning);

	void angivSøgningOplysningerForBM(HistorikSøgning historikSøgning);

	String[] getKommuneListe();

	void exporterHistorikForBM(String filenavn, List<HistorikForBM> historikListe);

	void exporterHistorikForKunde(String filenavn, List<Flextur> historikListe);

	void angivLoginOplysninger(String loginId, String kodeord);
	
	List<Flextur> getHistorikResultForKunde();

	List<HistorikForBM> getHistorikResultForBM();

	Bruger getBruger();

	void angivFlexturOplysninger(Flextur flextur);

	void søgBestilteKørsler(LocalDate fraDato, LocalDate tilDato);

	List<Flextur> getBestilteKøsler();

	double udregnPris(Flextur flextur);
	
	String udregnKilometer(String origin, String destination);

	Kunde getKundeID(String cpr);
}
