package data;

import java.time.LocalDate;
import java.util.List;

import domain.Flextur;
import domain.HistorikForBM;
import domain.HistorikSøgning;

public interface TurMapper {

	List<Flextur> getMatchendeHistorik(DataAccess dataAccess, HistorikSøgning historikSøgning);

	List<HistorikForBM> getMatchendeHistorikForBM(DataAccess dataAccess, HistorikSøgning historikSøgning);

	Object gemFlextur(DataAccess dataAccess, Flextur tur); //? is it because we want something return?

	List<Flextur> getBestilteKørsler(DataAccess dataAccess, LocalDate fraDato, LocalDate tilDato);
	
	
}
