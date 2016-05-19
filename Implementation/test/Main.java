package test;

import java.time.LocalDate;
import java.util.HashMap;

import domain.HistorikSøgning;
import domain.HistorikSøgningImpl;
import logic.FSController;
import logic.FSControllerImpl;

public class Main {

	public static void main(String[] args) {
		FSController fs = new FSControllerImpl();
		HistorikSøgning hs = new HistorikSøgningImpl();
//		hs.setCprNummer("170182-3628");
		hs.setKommune("Herning");
		hs.setFraDato(LocalDate.of(2016, 05, 01));
		hs.setTilDato(LocalDate.of(2016, 05, 31));
		fs.søgHistorik();
//		System.out.println(fs.angivSøgningOplysninger(hs));
		
		System.out.println(fs.angivSøgningOplysningerForBM(hs));
		
//		for(String s: fs.getKommuneListe()){
//			System.out.println(s);
//		}
		
//		fs.getKommuneListe();
		
//		System.out.println(fs.getKommuneListe());
	}

}
