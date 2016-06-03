package seHistorik;

import java.io.IOException;
import javax.xml.xpath.XPathExpressionException;

import logic.KilometerUdregningAdapter;
import logic.OkhttpKilometerUdregningAdapter;
/**
 * 
 * prototype klasse for at løse problemer for at forstå adapter og pris udregning algoritmer
 * @author Juyoung Choi
 *
 */
public class KilometerMain {
	
	private static String Origin = "Stadilvej 14, 6980";
	private static String Destination = "Munkgårds Alle 88, 7400";
//	private static String Destination = "Rome";

	private static KilometerUdregningAdapter KU = new OkhttpKilometerUdregningAdapter();
	
	

	public static void main(String[] args) throws XPathExpressionException, IOException {
		KU.getDistance(Origin, Destination);
		
	}

}
