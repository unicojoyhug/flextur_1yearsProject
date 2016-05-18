package logic;

import java.util.List;


import data.DataAccess;
import data.DataAccessImpl;
import data.TurMapper;
import data.TurMapperImpl;
import domain.Flextur;
import domain.HistorikSøgning;
import util.LogicTrans;

public class FSControllerImpl implements FSController {
	private TurMapper turMapper = new TurMapperImpl();
	private List<Observer> observers;
	private SøgHistorik søgHistorik;
	
	@Override
	public void tilmeldObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void notifyObservers(List<Tilstand> tilstande) {
		for (Observer o : observers) {
			o.update(tilstande);
		}
	}

	@Override
	public void søgHistorik() {
		// TODO tjek loggetInd bruger.....observer......
		

	}

	@Override
	public List<Flextur> angivSøgningOplysninger(HistorikSøgning historikSøgning) {
		DataAccess dataAccess = new DataAccessImpl();

		return new LogicTrans<List<Flextur>>(dataAccess).transaction(()->turMapper.getMatchendeHistorik(dataAccess, historikSøgning));
	}

}
