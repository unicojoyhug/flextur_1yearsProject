package data;

import java.util.List;

import domain.Flextur;
import domain.HistorikSøgning;

public interface TurMapper {

	public List<Flextur> getMatchendeHistorik(DataAccess dataAccess, HistorikSøgning historikSøgning);
	
	
	
}
