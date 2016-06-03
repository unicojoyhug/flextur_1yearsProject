package domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import exception.MissingOplysningExcpetion;

/**
 * Domæneklasse for Brugers login (både bestillingsmodtagelse og kunde)
 * @author Juyoung Choi
 *
 */
public class BrugerImpl implements Bruger {
	
	private int id;
	private String loginId;
	private String encryptedKodeord;
	private boolean erLoggetInd = false;
	private boolean erKunde;
	private boolean erAktivt;
	
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String getLoginId() {
		return loginId;
	}
	
	@Override
	public void setLoginId(String loginId) {
		if (loginId.isEmpty()) {
			throw new MissingOplysningExcpetion ("LoginId Mangler");
		}
		this.loginId = loginId;
	}
	
	private static String encrypt(String kodeord) throws NoSuchAlgorithmException {
		if (kodeord.isEmpty()) {
			throw new MissingOplysningExcpetion ("LoginId Mangler");
		}
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] mdbytes = md.digest(kodeord.getBytes());
		return Base64.getEncoder().encodeToString(mdbytes);
	}
	
	
	@Override
	public void setAndEncryptPassword(String kodeord) throws NoSuchAlgorithmException {
		this.encryptedKodeord = encrypt(kodeord);
	}
	
	
	@Override
	public String getEncryptedKodeord() {
		return encryptedKodeord;
	}
	
	
	@Override
	public void setEncryptedKodeord(String encryptedKodeord) {
		
		if (encryptedKodeord.isEmpty()) {
			throw new MissingOplysningExcpetion ("LoginId Mangler");
		}
		this.encryptedKodeord = encryptedKodeord;
	}
	

	@Override
	public boolean erLoggetInd() {
		return erLoggetInd;
	}

	@Override
	public void setErLoggetInd(boolean erLoggetInd) {
		this.erLoggetInd = erLoggetInd;
	}

	@Override
	public boolean erKunde() {
		return erKunde;
	}
	
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
