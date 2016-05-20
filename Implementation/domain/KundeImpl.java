package domain;

public class KundeImpl implements Kunde {
	private int cprNummer;
	private String kommune;
	private String telefon;
	private String fornavn;
	private String efternavn;
	private String adress;
	private int postnummer;
	private boolean erAktivt;
	/* (non-Javadoc)
	 * @see domain.Kunde#getCprNummer()
	 */
	@Override
	public int getCprNummer() {
		return cprNummer;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#setCprNummer(int)
	 */
	@Override
	public void setCprNummer(int cprNummer) {
		this.cprNummer = cprNummer;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#getKommune()
	 */
	@Override
	public String getKommune() {
		return kommune;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#setKommune(java.lang.String)
	 */
	@Override
	public void setKommune(String kommune) {
		this.kommune = kommune;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#getTelefon()
	 */
	@Override
	public String getTelefon() {
		return telefon;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#setTelefon(java.lang.String)
	 */
	@Override
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#getFornavn()
	 */
	@Override
	public String getFornavn() {
		return fornavn;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#setFornavn(java.lang.String)
	 */
	@Override
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#getEfternavn()
	 */
	@Override
	public String getEfternavn() {
		return efternavn;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#setEfternavn(java.lang.String)
	 */
	@Override
	public void setEfternavn(String efternavn) {
		this.efternavn = efternavn;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#getAdress()
	 */
	@Override
	public String getAdress() {
		return adress;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#setAdress(java.lang.String)
	 */
	@Override
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#getPostnummer()
	 */
	@Override
	public int getPostnummer() {
		return postnummer;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#setPostnummer(int)
	 */
	@Override
	public void setPostnummer(int postnummer) {
		this.postnummer = postnummer;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#erAktivt()
	 */
	@Override
	public boolean erAktivt() {
		return erAktivt;
	}
	/* (non-Javadoc)
	 * @see domain.Kunde#setErAktivt(boolean)
	 */
	@Override
	public void setErAktivt(boolean erAktivt) {
		this.erAktivt = erAktivt;
	}
	
	
	

}