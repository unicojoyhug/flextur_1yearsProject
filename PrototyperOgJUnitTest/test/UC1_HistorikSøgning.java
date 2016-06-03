package test;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.HistorikSøgning;
import domain.HistorikSøgningImpl;
import exception.MissingOplysningExcpetion;
/**
 * JUnit Test for at teste exceptions til UC1: Se historik
 * @author Juyoung Choi
 *
 */
public class UC1_HistorikSøgning {

	@Test
	public void checkTilDatoMangler() {
		HistorikSøgning hs = new HistorikSøgningImpl();
		
		try {
			hs.setTilDato(null);
			fail("Exception bliver ikke kastet.");

		} catch (MissingOplysningExcpetion  e) {
			// success
		}
	}
	
	@Test
	public void checkFraDatoMangler() {
		HistorikSøgning hs = new HistorikSøgningImpl();
		
		try {
			hs.setFraDato(null);
			fail("Exception bliver ikke kastet.");

		} catch (MissingOplysningExcpetion  e) {
			// success
		}
	}

}
