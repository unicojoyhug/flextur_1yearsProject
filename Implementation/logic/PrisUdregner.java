package logic;

import domain.Flextur;

public class PrisUdregner {




	public double takstUdregner(Flextur flextur) {
		SatsFactory satsFactory = new SatsFactory();
		SatsAdapter rate = satsFactory.getSatsAdapter();
		double km = flextur.getKilometer();
		double personer = antalPersoner(flextur);
		double sats = rate.hentSats(flextur);
		double tilvalg = antalTilvalg(flextur);
		
		
		double pris = ((km*sats)*(personer+tilvalg));
		return	pris;
		
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
				System.out.println("enkelt person result er = "+result);
			}
			else {
				result = ((personer + temp) * 0.5) + 1;
				System.out.println("Multi Person er = " + result);
			}

		return result;
	}
	
}
