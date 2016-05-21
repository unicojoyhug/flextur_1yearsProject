package util;

import domain.Flextur;
import domain.HistorikSøgning;

public interface OplysningChecker {

	void checkHistorikSøgOplysning(HistorikSøgning hisotrikSøgning);
	void checkBestillingsOplysning(Flextur flextur);
	

}
