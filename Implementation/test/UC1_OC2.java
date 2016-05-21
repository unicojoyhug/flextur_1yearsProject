package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import domain.HistorikSøgning;
import domain.HistorikSøgningImpl;
import logic.FSController;
import logic.FSControllerImpl;

public class UC1_OC2 {

	@Test
	public void angivSøgOplysningerForBM1() {
		FSController fs = new FSControllerImpl();
		
		HistorikSøgning hs = new HistorikSøgningImpl();
		
		hs.setFraDato(LocalDate.of(2016, 05, 01));
		hs.setTilDato(LocalDate.of(2016, 05, 31));
		hs.setKommune("Herning");
		
		assertEquals(1, fs.angivSøgningOplysningerForBM(hs).size());		
	}
	
	@Test
	public void angivSøgOplysningerForBM2() {
		FSController fs = new FSControllerImpl();
		
		HistorikSøgning hs = new HistorikSøgningImpl();
		hs.setCprNummer("170182-3628");
		hs.setFraDato(LocalDate.of(2016, 05, 01));
		hs.setTilDato(LocalDate.of(2016, 05, 31));
		hs.setKommune("Herning");
		
		assertEquals(1, fs.angivSøgningOplysningerForBM(hs).size());		
	}
	
	
	@Test
	public void angivSøgOplysningerForBM3() {
		FSController fs = new FSControllerImpl();
		
		HistorikSøgning hs = new HistorikSøgningImpl();
		hs.setCprNummer("170182-3008");
		hs.setFraDato(LocalDate.of(2016, 05, 01));
		hs.setTilDato(LocalDate.of(2016, 05, 31));
		
		assertEquals(0, fs.angivSøgningOplysningerForBM(hs).size());		

	}
	
	@Test
	public void angivSøgOplysninger1() {
		FSController fs = new FSControllerImpl();
		
		HistorikSøgning hs = new HistorikSøgningImpl();
		hs.setCprNummer("170182-3628");

		hs.setFraDato(LocalDate.of(2016, 05, 01));
		hs.setTilDato(LocalDate.of(2016, 05, 31));
		
		assertEquals(2, fs.angivSøgningOplysninger(hs).size());		
	}
	
	@Test
	public void angivSøgOplysninger2() {
		FSController fs = new FSControllerImpl();
		
		HistorikSøgning hs = new HistorikSøgningImpl();
		hs.setCprNummer("170182-3628");

		hs.setFraDato(LocalDate.of(2016, 05, 22));
		hs.setTilDato(LocalDate.of(2016, 05, 31));
		
		assertEquals(1, fs.angivSøgningOplysninger(hs).size());		
	}
	

}
