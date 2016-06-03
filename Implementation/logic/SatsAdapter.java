package logic;

import domain.Flextur;
/**
 * 
 * Sats Adapter interface
 * @author Juyoung Choi
 *
 */
public interface SatsAdapter {
	double hentSats(Flextur flextur);
	String[] getKommuner();
}
