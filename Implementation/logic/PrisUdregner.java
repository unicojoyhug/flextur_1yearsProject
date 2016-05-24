package logic;

import domain.Flextur;

public class PrisUdregner {




	public double takstUdregner(Flextur flextur) {
		SatsFactory satsFactory = new SatsFactory();
		SatsAdapter rate = satsFactory.getSatsAdapter();
		double km = flextur.getKilometer();
		int personer = flextur.getAntalPersoner();
		double sats = rate.hentSatsRate(flextur);
		double pris = ((km*sats)*personer);
		
		return	pris;
		
	}

}
