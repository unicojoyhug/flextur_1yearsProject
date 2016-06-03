package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import domain.Flextur;
import domain.FlexturImpl;
import logic.FSController;
import logic.FSControllerImpl;
/**
 * 
 * @author Juyoung Choi
 *
 */
public class UC4RegisterKørsel {

	@Test
	public void udregnPris1() {
		FSController fs = new FSControllerImpl();
		
		Flextur flextur = new FlexturImpl();
		flextur.setAntalPersoner(3);
		flextur.setFraKommune("Herning");
		flextur.setTilKommune("Aarhus");
		flextur.setDato(LocalDate.of(2016, 05, 26));
		flextur.setKilometer(200);
		
		fs.udregnPris(flextur);
		
		assertEquals(5600.0, flextur.getPris(), 0);	
	}
	
	
	@Test
	public void udrengPris2() {
		
		try {
			FSController fs = new FSControllerImpl();
			
			Flextur flextur = new FlexturImpl();
			flextur.setKundeId(3);
			flextur.setAntalPersoner(3);
			flextur.setFraKommune("Herning");
			flextur.setTilKommune("Aarhus");
			flextur.setDato(LocalDate.of(2016, 05, 26));
			flextur.setKilometer(200);
			flextur.setPris(5600.0);
			fs.angivFlexturOplysninger(flextur);		
			
			fail("Exception bliver ikke kastet.");

		} catch (RuntimeException  e) {
			// success
		}	
		
	}


}
