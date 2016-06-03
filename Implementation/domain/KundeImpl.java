package domain;

import exception.MissingOplysningExcpetion;

/**
 * Domæneklasse for kundeprofil
 *  
 * @author Juyoung Choi & Jonas Mørch
 *
 */
public class KundeImpl implements Kunde {
	private String cprNummer = null;
	private int cprId;
	private String kommune;
	private String telefon;
	private String fornavn;
	private String efternavn;
	private String adress;
	private String kodeord;
	private String email;
	private int postnummer;
	private int kundeID = 0;
	private boolean erAktivt;
	
	@Override
	public String getCprNummer() {
		return cprNummer;
	}
	
	@Override
	public void setCprNummer(String cprNummer) {
		if (cprNummer == null) {
			throw new MissingOplysningExcpetion ("CPR Mangler");
		}
		this.cprNummer = cprNummer;
	}
	
	@Override
	public String getKommune() {
		return kommune;
	}
	
	@Override
	public void setKommune(String kommune) {
		if (kommune == null) {
			throw new MissingOplysningExcpetion ("kommune Mangler");
		}
		this.kommune = kommune;
	}
	
	@Override
	public String getTelefon() {
		return telefon;
	}

	@Override
	public void setTelefon(String telefon) {
		if (telefon == null) {
			throw new MissingOplysningExcpetion ("telefon Mangler");
		}
		this.telefon = telefon;
	}
	
	@Override
	public String getFornavn() {
		return fornavn;
	}
	
	@Override
	public void setFornavn(String fornavn) {
		if (fornavn == null) {
			throw new MissingOplysningExcpetion ("fornavn Mangler");
		}
		this.fornavn = fornavn;
	}
	
	@Override
	public String getEfternavn() {
		return efternavn;
	}
	
	@Override
	public void setEfternavn(String efternavn) {
		if (efternavn == null) {
			throw new MissingOplysningExcpetion ("efternavn Mangler");
		}
		this.efternavn = efternavn;
	}

	@Override
	public String getAdress() {
		return adress;
	}
	
	@Override
	public void setAdress(String adress) {
		if (adress == null) {
			throw new MissingOplysningExcpetion ("adress Mangler");
		}
		this.adress = adress;
	}
	
	@Override
	public int getPostnummer() {
		return postnummer;
	}
	
	@Override
	public void setPostnummer(int postnummer) {
		if (postnummer == 0) {
			throw new MissingOplysningExcpetion ("postnummer Mangler");
		}
		this.postnummer = postnummer;
	}
	
	@Override
	public boolean erAktivt() {
		return erAktivt;
	}
	
	@Override
	public void setErAktivt(boolean erAktivt) {
		this.erAktivt = erAktivt;
	}
	@Override
	public int getKundeID() {
		return kundeID;
	}
	@Override
	public void setKundeID(int kundeID) {
		if (kundeID == 0 ) {
			throw new MissingOplysningExcpetion ("kundeID Mangler");
		}
		this.kundeID = kundeID;
		
	}
	@Override
	public void setKodeord(String kodeord) {
		if (kodeord == null) {
			throw new MissingOplysningExcpetion ("kodeord Mangler");
		}
		this.kodeord = kodeord;
		
	}
	@Override
	public void setEmail(String email) {
		if (email == null ) {
			throw new MissingOplysningExcpetion ("kodeord Mangler");
		}
		this.email = email;
		
	}
	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public String getKodeord() {
		return kodeord;
	}
	@Override
	public int getCprId() {
		return cprId;
	}
	@Override
	public void setCprId(int cprId) {
		if (cprId == 0 ) {
			throw new MissingOplysningExcpetion ("cprId Mangler");
		}
		this.cprId = cprId;
	}

	@Override
	public String toString() {
		return "KundeImpl [cprNummer=" + cprNummer + ", cprId=" + cprId + ", kommune=" + kommune + ", telefon="
				+ telefon + ", fornavn=" + fornavn + ", efternavn=" + efternavn + ", adress=" + adress + ", kodeord="
				+ kodeord + ", email=" + email + ", postnummer=" + postnummer + ", kundeID=" + kundeID + ", erAktivt="
				+ erAktivt + "]";
	}
	
	
	

}
