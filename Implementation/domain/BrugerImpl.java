package domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import util.LoginSingleton;

public class BrugerImpl implements Bruger {
	
	private long id;
	private String loginId;
	private String encryptedKodeord;
	private boolean erLoggetInd = false;
	private boolean erKunde;
	
	/* (non-Javadoc)
	 * @see domain.Bruger#getId()
	 */
	@Override
	public long getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see domain.Bruger#setId(long)
	 */
	@Override
	public void setId(long id) {
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
	
	

}
