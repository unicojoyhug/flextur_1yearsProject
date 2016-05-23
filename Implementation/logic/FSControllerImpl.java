package logic;

import java.util.List;

import data.BrugerMapperImpl;
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
import util.CSVExporter;
import util.CSVExporterImpl;
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
	private CRUD<Bruger, String> brugerMapper = new BrugerMapperImpl();
	private HistorikSøgning historikSøgning;

	// private HistorikSøgning historikSøgning;
	
//	larsnielsenlind@gmail.com

	@Override
	public void søgHistorik() {
		// TODO tjek loggetInd bruger.....observer......
		notifyObservers(this, Tilstand.SØG_HISTORIK);

	}

	@Override
	public List<Flextur> angivSøgningOplysninger(HistorikSøgning historikSøgning) {
		DataAccess dataAccess = new DataAccessImpl();
		// TODO 
		this.historikSøgning = historikSøgning;
		notifyObservers(this, Tilstand.HENT_HISTORIK);
		
		return new LogicTrans<List<Flextur>>(dataAccess)
				.transaction(() -> turMapper.getMatchendeHistorik(dataAccess, historikSøgning));

	}

	@Override
	public List<HistorikForBM> angivSøgningOplysningerForBM(HistorikSøgning historikSøgning) {

		DataAccess dataAccess = new DataAccessImpl();

		return new LogicTrans<List<HistorikForBM>>(dataAccess)
				.transaction(() -> turMapper.getMatchendeHistorikForBM(dataAccess, historikSøgning));

	}

	@Override
	public String[] getKommuneListe() {

		return Sats.i().getKommuner();

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
	public Bruger checkLogin(String loginId, String kodeord) {
		DataAccess dataAccess = new DataAccessImpl();
		Bruger bruger = null;
		try {
			bruger = new LogicTrans<Bruger>(dataAccess).transaction(() -> brugerMapper.read(dataAccess, loginId));

			if (bruger.getEncryptedKodeord().contentEquals(kodeord)) {

				bruger.setErLoggetInd(true);

			} else {
				throw new LoginException("Login fejl");
			}
		} catch (NullPointerException e) {
			throw new LoginException("Login fejl");

		}
		return bruger;
	}

//	public void gemFlextur(Flextur tur) {
//		DataAccess dataAccess = new DataAccessImpl();
//
//		new LogicTrans<>(dataAccess).transaction(() -> brugerMapper.gemFlextur(dataAccess, tur));
//	}

}
