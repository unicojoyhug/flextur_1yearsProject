package test;

import static org.junit.Assert.*;


import org.junit.Test;

import exception.LoginException;
import logic.FSController;
import logic.FSControllerImpl;
/**
 * 
 * UC3 login JUnitTest
 * @author Juyoung Choi
 *
 */
public class UC3_login {

	@Test
	public void angivLoginOplysninger1() {
		FSController fs = new FSControllerImpl();
		
		fs.angivLoginOplysninger("170182-3628", "123");
		
		assertEquals(3, fs.getBruger().getId());		
	}
	
	@Test
	public void angivLoginOplysinger2(){
		try {
			FSController fs = new FSControllerImpl();
			
			fs.angivLoginOplysninger(null, "123");
			
		} catch (LoginException  e) {
			// success
		}
	}
	
	
	
}
