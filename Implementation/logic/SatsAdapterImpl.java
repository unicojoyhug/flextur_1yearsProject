package logic;

import domain.Flextur;
import sats.Sats;
import sats.UnknownKommuneException;

public class SatsAdapterImpl implements SatsAdapter {
	

	public double hentSatsRate(Flextur tur){
		int year = tur.getDato().getYear();
		int month = tur.getDato().getMonthValue();
		int day = tur.getDato().getDayOfMonth();
		double satsRate = Double.NaN;
		
		try {
			satsRate = (Sats.i().getSats(tur.getFraKommune(), tur.getTilKommune(), year, month, day));
		} catch (UnknownKommuneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return satsRate;
	}
}
