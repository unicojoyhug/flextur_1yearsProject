package logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import data.BrugerMapperCRUDImpl;
import data.CRUD;
import data.DataAccess;
import data.DataAccessImpl;
import data.TurMapper;
import data.TurMapperImpl;
import domain.Bruger;
import domain.Flextur;
import domain.HistorikForBM;
import domain.HistorikSøgning;
import exception.LoginException;
import util.LogicTrans;
import sats.Sats;

/**
 * FSControllerImpl : facade controller :
 * 
 * @author Juyoung Choi
 *
 */
public class FSControllerImpl implements FSController {
	private TurMapper turMapper = new TurMapperImpl();
	private CRUD<Bruger, String> brugerMapper = new BrugerMapperCRUDImpl();
	private List<Observer> observers = new ArrayList<>();
	private List<Flextur> flexturListResult = new ArrayList<>();
	private List<HistorikForBM> flexturListResult_BM = new ArrayList<>();
	private Bruger bruger;
	// private HistorikSøgning historikSøgning;

	// larsnielsenlind@gmail.com

	
	
	
	@Override
	public void tilmeldObserver(Observer observer) {
		observers.add(observer);

	}

	@Override
	public void notifyObservers(Observable observable, Tilstand tilstand) {
		for (Observer observer : observers) {
			observer.update(observable, tilstand);
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
	public void angivLoginOplysninger(String loginId, String kodeord) {
		DataAccess dataAccess = new DataAccessImpl();
		try {
			this.bruger = new LogicTrans<Bruger>(dataAccess).transaction(() -> brugerMapper.read(dataAccess, loginId));

			if (bruger.getEncryptedKodeord().contentEquals(kodeord)) {
				bruger.setErLoggetInd(true);
				
				if(bruger.erKunde()){
					notifyObservers (this, Tilstand.LOGIN_KUNDE);

				}else{
					notifyObservers(this, Tilstand.LOGIN_BM);
				}
				
			} else {
				notifyObservers(this, Tilstand.LOGIN_FEJL);

				throw new LoginException("Login fejl");
			}
		} catch (NullPointerException e) {
			throw new LoginException("Login fejl");

		}
	
	}
	
	
	@Override
	public Bruger getBruger (){
		return bruger;
	}
	

	@Override
	public void angivFlexturOplysninger(Flextur tur) {
		DataAccess dataAccess = new DataAccessImpl();
		new LogicTrans<>(dataAccess).transaction(() -> turMapper.gemFlextur(dataAccess, tur));
		notifyObservers(this, Tilstand.BESTIL_KØRSEL);
	}

	
	@Override
	public void søgBestilteKørsler(LocalDate fraDato, LocalDate tilDato){
		DataAccess dataAccess = new DataAccessImpl();
		this.flexturListResult = new LogicTrans<List<Flextur>>(dataAccess).transaction(()-> turMapper.getBestilteKørsler(dataAccess, fraDato, tilDato));
		notifyObservers(this, Tilstand.SØG_BESTILE_KØRSLER);
		System.out.println(flexturListResult);

	}
	
	@Override
	public List<Flextur> getBestilteKøsler(){
		return flexturListResult;
	}
	
	///// udregn pris til PrisUdregner - factory + adapter 
	// one void method and return double pris method (datakerne: private double pris)
	
}
