package domain;

import java.time.LocalDate;
import java.time.LocalTime;

import logic.Observable;

public class FlexturImpl implements Flextur , Observable{
	private long flexturId;
	private int kundeId;
	private LocalDate dato;
	private LocalTime tid;
	private String fraAdress;
	private int fraPostnummer;
	private int tilPostnummer;
	private String tilAdress;
	private String fraKommune;
	private String tilKommune;
	private double pris;
	private int antalPersoner;
	private EkstraTilvalg ekstraTilvalg;
	
	
	@Override
	public String getFraKommune() {
		return fraKommune;
	}
	@Override
	public void setFraKommune(String fraKommune) {
		this.fraKommune = fraKommune;
	}
	@Override
	public String getTilKommune() {
		return tilKommune;
	}
	@Override
	public void setTilKommune(String tilKommune) {
		this.tilKommune = tilKommune;
	}
	@Override
	public int getFraPostnummer() {
		return fraPostnummer;
	}
	@Override
	public void setFraPostnummer(int fraPostnummer) {
		this.fraPostnummer = fraPostnummer;
	}
	@Override
	public int getTilPostnummer() {
		return tilPostnummer;
	}
	@Override
	public void setTilPostnummer(int tilPostnummer) {
		this.tilPostnummer = tilPostnummer;
	}
	
	/* (non-Javadoc)
	 * @see domain.Flextur#getFlexturId()
	 */
	@Override
	public long getFlexturId() {
		return flexturId;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#setFlexturId(long)
	 */
	@Override
	public void setFlexturId(long flexturId) {
		this.flexturId = flexturId;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#getKundeId()
	 */
	@Override
	public int getKundeId() {
		return kundeId;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#setKundeId(int)
	 */
	@Override
	public void setKundeId(int kundeId) {
		this.kundeId = kundeId;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#getDato()
	 */
	@Override
	public LocalDate getDato() {
		return dato;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#setDato(java.time.LocalDate)
	 */
	@Override
	public void setDato(LocalDate dato) {
		this.dato = dato;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#getTid()
	 */
	@Override
	public LocalTime getTid() {
		return tid;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#setTid(java.time.LocalTime)
	 */
	@Override
	public void setTid(LocalTime tid) {
		this.tid = tid;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#getFraAdress()
	 */
	@Override
	public String getFraAdress() {
		return fraAdress;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#setFraAdress(java.lang.String)
	 */
	@Override
	public void setFraAdress(String fraAdress) {
		this.fraAdress = fraAdress;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#getTilAdress()
	 */
	@Override
	public String getTilAdress() {
		return tilAdress;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#setTilAdress(java.lang.String)
	 */
	@Override
	public void setTilAdress(String tilAdress) {
		this.tilAdress = tilAdress;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#getPris()
	 */
	@Override
	public double getPris() {
		return pris;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#setPris(double)
	 */
	@Override
	public void setPris(double pris) {
		this.pris = pris;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#getAntalPersoner()
	 */
	@Override
	public int getAntalPersoner() {
		return antalPersoner;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#setAntalPersoner(int)
	 */
	@Override
	public void setAntalPersoner(int antalPersoner) {
		this.antalPersoner = antalPersoner;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#getEkstraTilvalg()
	 */
	@Override
	public EkstraTilvalg getEkstraTilvalg() {
		return ekstraTilvalg;
	}
	/* (non-Javadoc)
	 * @see domain.Flextur#setEkstraTilvalg(domain.EkstraTilvalg)
	 */
	@Override
	public void setEkstraTilvalg(EkstraTilvalg ekstraTilvalg) {
		this.ekstraTilvalg = ekstraTilvalg;
	}
	@Override
	public String toString() {
		return "FlexturImpl [flexturId=" + flexturId + ", kundeId=" + kundeId + ", dato=" + dato + ", tid=" + tid
				+ ", fraAdress=" + fraAdress + ", tilAdress=" + tilAdress + ", pris=" + pris + ", antalPersoner="
				+ antalPersoner + ", ekstraTilvalg=" + ekstraTilvalg + "]";
	}
	
	
	
}
