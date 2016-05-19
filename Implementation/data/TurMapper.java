package data;

import java.util.List;

import domain.Flextur;
import domain.HistorikForBM;
import domain.HistorikSøgning;

public interface TurMapper {

	List<Flextur> getMatchendeHistorik(DataAccess dataAccess, HistorikSøgning historikSøgning);

	List<HistorikForBM> getMatchendeHistorikForBM(DataAccess dataAccess, HistorikSøgning historikSøgning);
	
	
}
