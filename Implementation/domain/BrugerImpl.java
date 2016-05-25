package domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Observable;

import util.LoginSingleton;

/**
 * 
 * @author Juyoung Choi
 *
 */
public class BrugerImpl implements Bruger {
	
	private int id;
	private String loginId;
	private String encryptedKodeord;
	private boolean erLoggetInd;
	private boolean erKunde;
	private boolean erAktivt;
	
	/* (non-Javadoc)
	 * @see domain.Bruger#getId()
	 */
	@Override
	public int getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see domain.Bruger#setId(long)
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see domain.Bruger#getLoginId()
	 */
	@Override
	public String getLoginId() {
		return loginId;
	}
	/* (non-Javadoc)
	 * @see domain.Bruger#setLoginId(java.lang.String)
	 */
	@Override
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	private static String encrypt(String kodeord) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] mdbytes = md.digest(kodeord.getBytes());
		return Base64.getEncoder().encodeToString(mdbytes);
	}
	
	
	/* (non-Javadoc)
	 * @see domain.Bruger#setAndEncryptPassword(java.lang.String)
	 */
	@Override
	public void setAndEncryptPassword(String kodeord) throws NoSuchAlgorithmException {
		this.encryptedKodeord = encrypt(kodeord);
	}
	
	/* (non-Javadoc)
	 * @see domain.Bruger#getEncryptedKodeord()
	 */
	@Override
	public String getEncryptedKodeord() {
		return encryptedKodeord;
	}
	
	/* (non-Javadoc)
	 * @see domain.Bruger#setEncryptedKodeord(java.lang.String)
	 */
	@Override
	public void setEncryptedKodeord(String encryptedKodeord) {
		this.encryptedKodeord = encryptedKodeord;
	}
	
	/* (non-Javadoc)
	 * @see domain.Bruger#erLoggetInd()
	 */
	@Override
	public boolean erLoggetInd() {
		return erLoggetInd;
	}
	/* (non-Javadoc)
	 * @see domain.Bruger#setErLoggetInd(boolean)
	 */
	@Override
	public void setErLoggetInd(boolean erLoggetInd) {
		this.erLoggetInd = erLoggetInd;
	}
	/* (non-Javadoc)
	 * @see domain.Bruger#erKunde()
	 */
	@Override
	public boolean erKunde() {
		return erKunde;
	}
	/* (non-Javadoc)
	 * @see domain.Bruger#setErKunde(boolean)
	 */
	@Override
	public void setErKunde(boolean erKunde) {
		this.erKunde = erKunde;
	}
	@Override
	public String toString() {
		return "BrugerImpl [id=" + id + ", loginId=" + loginId + ", encryptedKodeord=" + encryptedKodeord
				+ ", erLoggetInd=" + erLoggetInd + ", erKunde=" + erKunde + "]";
	}
	@Override
	public boolean erAktivt() {
		return erAktivt;
	}
	@Override
	public void setErAktivt(boolean erAktivt) {
		this.erAktivt = erAktivt;
	}
	
	

}
