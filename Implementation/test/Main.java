package test;

import java.time.LocalDate;

import domain.HistorikSøgning;
import domain.HistorikSøgningImpl;
import logic.FSController;
import logic.FSControllerImpl;

public class Main {

	public static void main(String[] args) {
		FSController fs = new FSControllerImpl();
		
		fs.søgBestilteKørsler(LocalDate.of(2016, 05, 01), LocalDate.of(2016, 05, 31));
		
		System.out.println(fs.getBestilteKøsler());
//		HistorikSøgning hs = new HistorikSøgningImpl();
//		hs.setCprNummer("170182-3628");
//		hs.setKommune("Herning");
//		hs.setFraDato(null);
//		hs.setTilDato(LocalDate.of(2016, 05, 31));
//		fs.søgHistorik();
//		System.out.println(fs.angivSøgningOplysninger(hs));
//		System.out.println(hs);
		
//		System.out.println(fs.angivSøgningOplysningerForBM(hs));
		
		
//		for(String s: fs.getKommuneListe()){
//			System.out.println(s);
//		}
		
//		fs.getKommuneListe();
		
//		System.out.println(fs.getKommuneListe());
		
		
	}

}
