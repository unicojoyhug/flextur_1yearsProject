package domain;

import java.security.NoSuchAlgorithmException;
/**
 * 
 * @author Juyoung Choi
 *
 */
public interface Bruger {

	int getId();

	void setId(int id);

	String getLoginId();

	void setLoginId(String loginId);

	void setAndEncryptPassword(String kodeord) throws NoSuchAlgorithmException;

	String getEncryptedKodeord();

	void setEncryptedKodeord(String encryptedKodeord);

	boolean erLoggetInd();

	void setErLoggetInd(boolean erLoggetInd);

	boolean erKunde();

	void setErKunde(boolean erKunde);

	boolean erAktivt();

	void setErAktivt(boolean erAktivt);

}