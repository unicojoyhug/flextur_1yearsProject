package logic;

import java.util.List;

import domain.Flextur;
import domain.HistorikSøgning;

public interface FSController {

	void søgHistorik();
	
	List<Flextur> angivSøgningOplysninger(HistorikSøgning historikSøgning);

	void tilmeldObserver(Observer observer);

	void notifyObservers(List<Tilstand> tilstande);
	
}
