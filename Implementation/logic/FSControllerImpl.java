package logic;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xpath.XPathExpressionException;
import data.BilMapper;
import data.BilMapperCRUDImpl;
import data.BrugerMapperCRUDImpl;
import data.CRUD;
import data.DataAccessImpl;
import data.KundeMapper;
import data.KundeMapperCRUDImpl;
import data.TurMapper;
import data.TurMapperImpl;
import domain.Bil;
import domain.Bruger;
import domain.BrugerImpl;
import domain.Flextur;
import domain.HistorikForBM;
import domain.HistorikSøgning;
import domain.Kunde;
import exception.DatoFEJLException;
import exception.LoginException;
import exception.TildelogGodkendBilFejException;
import util.DataAccess;
import util.LogicTrans;

/**
 * FSControllerImpl : facade controller
 * Den klasse er subjekt(observable) og observer vill være GUI controller
 * 
 * Abstraktklasse (FSPane) tilmeldObserver
 * 
 * @author Juyoung Choi & Jonas Mørch
 *
 */
public class FSControllerImpl implements FSController {
	private TurMapper turMapper = new TurMapperImpl();
	private KundeMapper kundeMapper = new KundeMapperCRUDImpl();
	private BilMapper bilMapper = new BilMapperCRUDImpl();
	private CRUD<Bruger, String> brugerMapper = new BrugerMapperCRUDImpl();
	private List<Observer> observers = new ArrayList<>();
	private List<Flextur> flexturListResult = new ArrayList<>();
	private List<HistorikForBM> flexturListResult_BM = new ArrayList<>();
	private List<Bil> bilListe = new ArrayList<>();
	private Bruger bruger;
	private Kunde kunde;
	private Flextur flextur;
	private PrisUdregner PU = new PrisUdregner();

	@Override
	public void tilmeldObserver(Observer observer) {
		observers.add(observer);

	}

	@Override
	public void notifyObservers(Observable observable, Tilstand tilstand) {

		int size = observers.size();

		for (int i = 0; i < size; i++) {
			observers.get(i).update(observable, tilstand);
		}
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
		if(fraDato.isAfter(LocalDate.now()) && tilDato.isAfter(LocalDate.now())){
		DataAccess dataAccess = new DataAccessImpl();
		this.flexturListResult = new LogicTrans<List<Flextur>>(dataAccess)
				.transaction(() -> turMapper.getBestilteKørsler(dataAccess, fraDato, tilDato));
		notifyObservers(this, Tilstand.SØG_BESTILTE_KØRSLER);
		}else{
			throw new DatoFEJLException("Dato fejl");
		}
		
	}

	@Override
	public List<Flextur> getBestilteKøsler() {
		return flexturListResult;
	}

	@Override
	public Flextur udregnPris(Flextur flextur) {
		return PU.takstUdregner(flextur);
	}

	@Override
	public void udregnPrisMedTråd(Flextur flextur) {
		PrisUdregnerMedTråd pris = new PrisUdregnerMedTrådImpl();
		pris.udregnPris(flextur);
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
		}
		;
		return flextur;
	}

	@Override
	public Kunde getKundeID(String cpr) {
		DataAccess dataAccess = new DataAccessImpl();
		kunde = new LogicTrans<Kunde>(dataAccess).transaction(() -> kundeMapper.read(dataAccess, cpr));
		return kunde;
	}



	@Override
	public void hentBilListe(Flextur flextur) {
		DataAccess dataAccess = new DataAccessImpl();
		int antalPersoner = flextur.getAntalPersoner();
		boolean tilvalgMulighed;
		if (flextur.getKoerestole() > 0) {
			tilvalgMulighed = true;
		} else {
			tilvalgMulighed = false;
		}

		this.bilListe = new LogicTrans<List<Bil>>(dataAccess)
				.transaction(() -> bilMapper.hentBilListe(dataAccess, antalPersoner, tilvalgMulighed));
		this.flextur = flextur;
		notifyObservers(this, Tilstand.HENT_BIL_LISTE);
	}

	@Override
	public Flextur getFlextur() {
		return flextur;
	}

	@Override
	public List<Bil> getBilListe() {
		return bilListe;
	}

	@Override
	public void tildelBil(long flexturId, int bilId) {
		DataAccess dataAccess = new DataAccessImpl();
		try {
			new LogicTrans<>(dataAccess).transaction(() -> bilMapper.tildelBil(dataAccess, bilId, flexturId));
		} catch (RuntimeException e) {
			throw new TildelogGodkendBilFejException("Tildel Fejl");
		}

		notifyObservers(this, Tilstand.TILDEL_BIL);
	}

	@Override
	public void godkendKørsel(long flexturId, String kommentar) {
		DataAccess dataAccess = new DataAccessImpl();
		try {
			new LogicTrans<>(dataAccess).transaction(() -> turMapper.godkendKørsel(dataAccess, flexturId, kommentar));
		} catch (RuntimeException e) {
			throw new TildelogGodkendBilFejException("Tildel Fejl");
		}

		notifyObservers(this, Tilstand.GODKEND_KØRSEL);
	}

	@Override
	public void søgAlleBestilteKørsler() {
		DataAccess dataAccess = new DataAccessImpl();
		this.flexturListResult = new LogicTrans<List<Flextur>>(dataAccess)
				.transaction(() -> turMapper.getAlleBestilteKørsler(dataAccess));
		notifyObservers(this, Tilstand.SØG_ALLE_BESTILTE_KØRSLER);

	}
	
	
	@Override
	public void opretKundeProfil(Kunde kunde){
		DataAccess dataAccess = new DataAccessImpl();
		new LogicTrans<>(dataAccess).transaction(()->kundeMapper.createCPRogKunde(dataAccess, kunde));
		notifyObservers(this, Tilstand.KUNDE_OPRETTET);

	}
	
	@Override
	public Kunde readKundeProfil(String kundeId){
		DataAccess dataAccess = new DataAccessImpl();
		return new LogicTrans<Kunde>(dataAccess).transaction(()->kundeMapper.read(dataAccess, kundeId));
		
	}
	
	@Override
	public void retKundeProfil(Kunde kunde){
		DataAccess dataAccess = new DataAccessImpl();
		new LogicTrans<>(dataAccess).transaction(()->kundeMapper.update(dataAccess, kunde));
		notifyObservers(this, Tilstand.KUNDE_RETTET);

	}

}
