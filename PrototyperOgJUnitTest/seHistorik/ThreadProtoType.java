package seHistorik;

import java.time.LocalDate;

import domain.Flextur;
import domain.FlexturImpl;
import logic.Observable;
import logic.Observer;
import logic.PrisUdregner;
import logic.Tilstand;
/**
 * 
 * prototype klasse for at løse tråd problem
 * @author Juyoung Choi
 *
 */

public class ThreadProtoType extends Thread {

	public static void main(String[] args) {
		Flextur flextur = new FlexturImpl();
		flextur.setAntalPersoner(3);
		flextur.setFraKommune("Herning");
		flextur.setTilKommune("Aarhus");
		flextur.setDato(LocalDate.of(2016, 05, 26));
		flextur.setKilometer(200);
		
		ThreadProtoType test = new ThreadProtoType();
		test.makePris(flextur);
		System.out.println(flextur);

	}
	
	private void makePris(Flextur flextur){
		ControllerPrototype c = new ControllerPrototype();
		
		c.udregnPris(flextur);
	}

	

}
