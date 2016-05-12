package seHistorik;

import sats.Sats;
import sats.UnknownKommuneException;
public class Pris {

	EkstraTilvalg ekstraTilvalg;
	double satsRate;

	public EkstraTilvalg getEkstraTilvalg() {
		return ekstraTilvalg;
	}

	public void setEkstraTilvalg(EkstraTilvalg ekstraTilvalg) {
		this.ekstraTilvalg = ekstraTilvalg;
	}

	public double getTotalPris(Tur tur) {
	
		return calculateTotalPris(tur);
	}


	private double calculateTotalPris(Tur tur){
		int year = tur.getDato().getYear();
		int month = tur.getDato().getMonthValue();
		int day = tur.getDato().getDayOfMonth();
		try {
			satsRate = (Sats.i().getSats(tur.getFraKommune().name(), tur.getTilKommune().name(), year, month, day));
		} catch (UnknownKommuneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(tur.getAntalPersoner()<0){

			throw new AntalPersonerException("Antal Personer exception");

		}
		return tur.getAfstand() * (satsRate) * ((tur.getAntalPersoner() * 0.5) + 0.5);

	}


}
