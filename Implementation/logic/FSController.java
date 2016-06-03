package logic;

import java.time.LocalDate;
import java.util.List;
import domain.Bil;
import domain.Bruger;
import domain.Flextur;
import domain.HistorikForBM;
import domain.HistorikSøgning;
import domain.Kunde;
/**
 * Interface til FSController
 * 
 * @author Juyoung Choi & Jonas Mørch
 *
 */
public interface FSController extends Observable {

	
	void angivSøgningOplysninger(HistorikSøgning historikSøgning);

	void angivSøgningOplysningerForBM(HistorikSøgning historikSøgning);

	String[] getKommuneListe();

	void exporterHistorikForBM(String filenavn, List<HistorikForBM> historikListe);

	void exporterHistorikForKunde(String filenavn, List<Flextur> historikListe);

	void angivLoginOplysninger(String loginIdS, String kodeordS);
	
	List<Flextur> getHistorikResultForKunde();

	List<HistorikForBM> getHistorikResultForBM();

	Bruger getBruger();

	void angivFlexturOplysninger(Flextur flextur);

	void søgBestilteKørsler(LocalDate fraDato, LocalDate tilDato);

	List<Flextur> getBestilteKøsler();

	Flextur udregnPris(Flextur flextur);
	
	Kunde getKundeID(String cpr);

	Flextur udregnKilometer(Flextur flextur);

	double udregnPrisMedTråd(Flextur flextur);

//	void opretKunde(Kunde kunde);

	void hentBilListe(Flextur flextur);

	List<Bil> getBilListe();

	Flextur getFlextur();

	void tildelBil(long flexturId, int bilId);

	void godkendKørsel(long flexturId, String kommentar);

	void søgAlleBestilteKørsler();

	void opretKundeProfil(Kunde kunde);

	Kunde readKundeProfil(String kundeId);

	void retKundeProfil(Kunde kunde);

}
