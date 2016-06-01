package data;

import java.time.LocalDate;
import java.util.List;

import domain.Flextur;
import domain.HistorikForBM;
import domain.HistorikSøgning;
import util.DataAccess;
/**
 * 
 * @author Juyoung Choi
 *
 */
public interface TurMapper {

	List<Flextur> getMatchendeHistorik(DataAccess dataAccess, HistorikSøgning historikSøgning);

	List<HistorikForBM> getMatchendeHistorikForBM(DataAccess dataAccess, HistorikSøgning historikSøgning);

	void gemFlextur(DataAccess dataAccess, Flextur tur); 

	List<Flextur> getBestilteKørsler(DataAccess dataAccess, LocalDate fraDato, LocalDate tilDato);

	void godkendKørsel(DataAccess dataAccess, long flexturId, String kommentar);

	List<Flextur> getAlleBestilteKørsler(DataAccess dataAccess);
	
	
}
