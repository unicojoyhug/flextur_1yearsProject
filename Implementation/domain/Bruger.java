package domain;

import java.security.NoSuchAlgorithmException;

public interface Bruger {

	long getId();

	void setId(long id);

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