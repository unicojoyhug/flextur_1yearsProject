package seHistorik;

import java.io.IOException;
import javax.xml.xpath.XPathExpressionException;

public class KilometerMain {
	
	private static String Origin = "Stadilvej 14, 6980";
	private static String Destination = "Munkgårds Alle 88, 7400";
//	private static String Destination = "Rome";

	private static KilometerUdregning KU = new KilometerUdregning();
	
	

	public static void main(String[] args) throws XPathExpressionException, IOException {
		KU.Distance(Origin, Destination);
		
	}

}
