package test;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.HistorikSøgning;
import domain.HistorikSøgningImpl;
import exception.LoginException;
import exception.MissingOplysningExcpetion;
import logic.FSController;
import logic.FSControllerImpl;
/**
 * UC1 OC1.3 JUnitTest
 * @author Juyoung Choi
 *
 */
public class UC1_OC3 {

	@Test
	public void test1() {
		
		try {
			FSController fs = new FSControllerImpl();
			fs.angivLoginOplysninger("170182-3628", null);
			fail("Exception bliver ikke kastet.");

		} catch (LoginException  e) {
			// success
		}	
	}
	
	@Test
	public void test2() {
		
		try {
			FSController fs = new FSControllerImpl();
			fs.angivLoginOplysninger(null, null);
			fail("Exception bliver ikke kastet.");

		} catch (LoginException  e) {
			// success
		}	
	}
}
