package logic;

import domain.Flextur;

/**
 * interface for pris udregning returer pris som double værdi
 * 
 * 
 * @author Juyoung Choi
 *
 */
public interface PrisUdregnerMedTråd {

	void udregnPris(Flextur flextur);

}