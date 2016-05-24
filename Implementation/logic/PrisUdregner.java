package logic;

import domain.Flextur;

public class PrisUdregner {




	public double takstUdregner(Flextur flextur) {
		SatsFactory satsFactory = new SatsFactory();
		SatsAdapter rate = satsFactory.getSatsAdapter();
		double km = flextur.getKilometer();
		double personer = antalPersoner(flextur);
		double sats = rate.hentSatsRate(flextur);
		double tilvalg = antalTilvalg(flextur);
		
		
		double pris = ((km*sats)*(personer+tilvalg));
		return	pris;
		
	}
	private double antalTilvalg(Flextur flextur){
		double temp = -1;
		double tilvalg = flextur.getEkstraTilvalg().getAntal();
		double tilvres = (temp + tilvalg)*0.5;
				
		return tilvres;
		
	}
	private double antalPersoner(Flextur flextur){
		double result = 0;
		double temp = -1;
		double personer = flextur.getAntalPersoner();
			if (personer==1){
				result = 1;
			}
			else {
				result = ((personer - temp) * 0.5) + 1;
				System.out.println("Multi Person er = " + result);
			}

		return result;
	}
	
}
