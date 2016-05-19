package logic;

import java.util.ArrayList;
import java.util.List;


import data.DataAccess;
import data.DataAccessImpl;
import data.TurMapper;
import data.TurMapperImpl;
import domain.Flextur;
import domain.HistorikForBM;
import domain.HistorikSøgning;
import util.LogicTrans;
import sats.Sats;
/**
 * FSControllerImpl : facade controller : 
 * @author Juyoung Choi
 *
 */
public class FSControllerImpl implements FSController {
	private TurMapper turMapper = new TurMapperImpl();

//	private HistorikSøgning historikSøgning;
	
	

	

	@Override
	public void søgHistorik() {
		// TODO tjek loggetInd bruger.....observer......
		notifyObservers(this, Tilstand.SØG_HISTORIK);

	}

	@Override
	public List<Flextur> angivSøgningOplysninger(HistorikSøgning historikSøgning) {
		DataAccess dataAccess = new DataAccessImpl();
		
		notifyObservers(this, Tilstand.HENT_HISTORIK);

		return new LogicTrans<List<Flextur>>(dataAccess).transaction
				(()->turMapper.getMatchendeHistorik(dataAccess, historikSøgning));
	
	
	}
	


	@Override
	public List<HistorikForBM> angivSøgningOplysningerForBM(HistorikSøgning historikSøgning) {
	
		DataAccess dataAccess = new DataAccessImpl();
		
		return new LogicTrans<List<HistorikForBM>>(dataAccess).transaction
				(()->turMapper.getMatchendeHistorikForBM(dataAccess, historikSøgning));
		
	}

	@Override
	public String[] getKommuneListe(){
		return Sats.i().getKommuner();
		
	}
	
}
