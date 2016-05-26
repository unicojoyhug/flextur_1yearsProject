package logic;

import domain.Flextur;
import seHistorik.AntalPersonerException;

public class PrisUdregner {




	public Flextur takstUdregner(Flextur flextur) {
		SatsFactory satsFactory = new SatsFactory();
		SatsAdapter rate = satsFactory.getSatsAdapter();
		double km = flextur.getKilometer();
		double personer = antalPersoner(flextur);
		double sats = rate.hentSats(flextur);
		double tilvalg = antalTilvalg(flextur);
		flextur.setPris(((km*sats)*(personer+tilvalg)));
		
		return	flextur;
		
	}
	private double antalTilvalg(Flextur flextur){
		double temp = -1;
		double tilvalg = flextur.getAutostole()+flextur.getBaggage()+flextur.getBarnevogne()+flextur.getKoerestole();
		double tilvres = 0;
		if (tilvalg > 0){
			tilvres = (temp + tilvalg)*0.5;
		}
				
		return tilvres;
		
	}
	private double antalPersoner(Flextur flextur){
		double result = 0;
		double temp = -1;
		double personer = flextur.getAntalPersoner();
			if (personer==1){
				result = 1;
			}
			else if (6< personer || personer >0) {
				result = ((personer + temp) * 0.5) + 1;
			}
			else{
				throw new AntalPersonerException("Antal Personer exception");
			}

		return result;
	}
	
}
