package logic;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xpath.XPathExpressionException;
import data.BrugerMapperCRUDImpl;
import data.CRUD;
import data.DataAccess;
import data.DataAccessImpl;
import data.KundeMapperCRUDImpl;
import data.TurMapper;
import data.TurMapperImpl;
import domain.Bruger;
import domain.BrugerImpl;
import domain.Flextur;
import domain.HistorikForBM;
import domain.HistorikSøgning;
import domain.Kunde;
import exception.LoginException;
import util.LogicTrans;

/**
 * FSControllerImpl : facade controller :
 * 
 * @author Juyoung Choi
 *
 */
public class FSControllerImpl implements FSController {
	private TurMapper turMapper = new TurMapperImpl();
	private CRUD<Kunde, String> kundeMapper = new KundeMapperCRUDImpl();

	private CRUD<Bruger, String> brugerMapper = new BrugerMapperCRUDImpl();
	private List<Observer> observers = new ArrayList<>();
	private List<Flextur> flexturListResult = new ArrayList<>();
	private List<HistorikForBM> flexturListResult_BM = new ArrayList<>();
	private Bruger bruger;
	private Kunde kunde;
	private PrisUdregner PU = new PrisUdregner();

	@Override
	public void tilmeldObserver(Observer observer) {
		observers.add(observer);

	}

	@Override
	public void notifyObservers(Observable observable, Tilstand tilstand) {
//		for (Observer observer : observers) {
//			observer.update(observable, tilstand);
//		}
		
		int size = observers.size();
		
		for (int i=0;i<size;i++) {
			observers.get(i).update(observable, tilstand);
		}
	}

	@Override
	public void søgHistorik() {
		// TODO tjek loggetInd bruger.....observer......

	}

	@Override
	public void angivSøgningOplysninger(HistorikSøgning historikSøgning) {
		DataAccess dataAccess = new DataAccessImpl();

		this.flexturListResult = new LogicTrans<List<Flextur>>(dataAccess)
				.transaction(() -> turMapper.getMatchendeHistorik(dataAccess, historikSøgning));

		notifyObservers(this, Tilstand.SØG_HISTORIK_KUNDE);

	}

	@Override
	public List<Flextur> getHistorikResultForKunde() {
		return flexturListResult;
	}

	@Override
	public void angivSøgningOplysningerForBM(HistorikSøgning historikSøgning) {

		DataAccess dataAccess = new DataAccessImpl();

		this.flexturListResult_BM = new LogicTrans<List<HistorikForBM>>(dataAccess)
				.transaction(() -> turMapper.getMatchendeHistorikForBM(dataAccess, historikSøgning));
		notifyObservers(this, Tilstand.SØG_HISTORIK_BM);

	}

	@Override
	public List<HistorikForBM> getHistorikResultForBM() {
		return flexturListResult_BM;
	}

	@Override
	public String[] getKommuneListe() {
		SatsFactory satsFactory = new SatsFactory();
		SatsAdapter kommunerFraSats = satsFactory.getSatsAdapter();

		return kommunerFraSats.getKommuner();

	}

	@Override
	public void exporterHistorikForBM(String filenavn, List<HistorikForBM> historikListe) {
		CSVExporter csvExporter = new CSVExporterImpl();

		csvExporter.generateCsvFileFlexturForBM(filenavn, historikListe);

	}

	@Override
	public void exporterHistorikForKunde(String filenavn, List<Flextur> historikListe) {
		CSVExporter csvExporter = new CSVExporterImpl();

		csvExporter.generateCsvFileFlexturForKunde(filenavn, historikListe);

	}

	@Override
	public void angivLoginOplysninger(String loginIdS, String kodeordS) {
		DataAccess dataAccess = new DataAccessImpl();
		try {
			Bruger bruger1 = new BrugerImpl();
			bruger1.setAndEncryptPassword(kodeordS);
			String kodeord = bruger1.getEncryptedKodeord();
			
			this.bruger = new LogicTrans<Bruger>(dataAccess).transaction(() -> brugerMapper.read(dataAccess, loginIdS));

			if (bruger.getEncryptedKodeord().contentEquals(kodeord)) {
				bruger.setErLoggetInd(true);

				if (bruger.erKunde()) {
					notifyObservers(this, Tilstand.LOGIN_KUNDE);

				} else {
					notifyObservers(this, Tilstand.LOGIN_BM);
				}

			} else {
				notifyObservers(this, Tilstand.LOGIN_FEJL);

				throw new LoginException("Login fejl");
			}
		} catch (NullPointerException | NoSuchAlgorithmException e) {
			throw new LoginException("Login fejl");

		}

	}

	@Override
	public Bruger getBruger() {
		return bruger;
	}

	@Override
	public void angivFlexturOplysninger(Flextur tur) {
		DataAccess dataAccess = new DataAccessImpl();
		new LogicTrans<>(dataAccess).transaction(() -> turMapper.gemFlextur(dataAccess, tur));
		notifyObservers(this, Tilstand.BESTIL_KØRSEL);
	}

	@Override
	public void søgBestilteKørsler(LocalDate fraDato, LocalDate tilDato) {
		DataAccess dataAccess = new DataAccessImpl();
		this.flexturListResult = new LogicTrans<List<Flextur>>(dataAccess)
				.transaction(() -> turMapper.getBestilteKørsler(dataAccess, fraDato, tilDato));
		notifyObservers(this, Tilstand.SØG_BESTILTE_KØRSLER);

	}

	@Override
	public List<Flextur> getBestilteKøsler() {
		return flexturListResult;
	}

	@Override
	public Flextur udregnPris(Flextur flextur){
		return PU.takstUdregner(flextur);
	}
	
	@Override
	public void udrengPrisMedTråd(Flextur flextur){
		PrisUdregnerMedTråd prisUdregnerMedTråd = new PrisUdregnerMedTråd(flextur);
		prisUdregnerMedTråd.run();
		System.out.println(flextur);
	}

	@Override
	public Flextur udregnKilometer(Flextur flextur) {
		KilometerUdregningAdapterFactory KU = new KilometerUdregningAdapterFactory();
		KilometerUdregningAdapter KUadapter = KU.getKilometerUdregningAdapter();
		
		try {
			return KUadapter.getDistance(flextur);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return flextur;
	}

	@Override
	public Kunde getKundeID(String cpr) {
		DataAccess dataAccess = new DataAccessImpl();
		kunde = new LogicTrans<Kunde>(dataAccess).transaction(() -> kundeMapper.read(dataAccess, cpr));
		return kunde;
	}

	
	
	///// udregn pris til PrisUdregner - factory + adapter
	// one void method and return double pris method (datakerne: private double
	///// pris)

}
