package logic;

import domain.Flextur;
import sats.Sats;
import sats.UnknownKommuneException;
/**
 * sats : eksternt systemet for sats til Flextur
 * 
 * to metoder : hentSats og getKommuner
 * hentSats returer sats værdi som double (med Flextur type parameter)
 * 
 * @author Juyoung Choi & Jonas Mørch
 *
 */
public class FlemmingSatsAdapter implements SatsAdapter {
	

	public double hentSats(Flextur tur){
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

	@Override
	public String[] getKommuner() {
		return Sats.i().getKommuner();
	}
	
	
}
