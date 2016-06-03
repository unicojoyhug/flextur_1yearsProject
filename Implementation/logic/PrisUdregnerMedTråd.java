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

	double udregnPris(Flextur flextur);

}