package logic;

import domain.Flextur;

/**
 * Pris Udregner klasse for at hente sats ved SatsFactory og SatsAdapter
 * 
 * Den klasse med Flextur type parameter returer pris som double værdi
 * 
 * @author Juyoung Choi
 *
 */
public class PrisUdregnerMedTrådImpl implements PrisUdregnerMedTråd {
	
	@Override
	public void udregnPris(Flextur flextur) {
		SatsFactory satsFactory = new SatsFactory();
		SatsAdapter rate = satsFactory.getSatsAdapter();
		double km = flextur.getKilometer();
		double personer = antalPersoner(flextur);
		double tilvalg = antalTilvalg(flextur);
		double sats = rate.hentSats(flextur);
		flextur.setPris((km * sats) * (personer + tilvalg));
	}

	private double antalTilvalg(Flextur flextur) {
		double temp = -1;
		double tilvalg = flextur.getAutostole() + flextur.getBaggage() + flextur.getBarnevogne()
				+ flextur.getKoerestole();
		double tilvres = 0;
		if (tilvalg > 0) {
			tilvres = (temp + tilvalg) * 0.5;
		}

		return tilvres;

	}

	private double antalPersoner(Flextur flextur) {
		double result = 0;
		double temp = -1;
		double personer = flextur.getAntalPersoner();

		if (personer == 1) {
			result = 1;
		}  else if (0 < personer && personer < 6) {
			result = ((personer + temp) * 0.5) + 1;
		} 

		return result;
	}
	
}
