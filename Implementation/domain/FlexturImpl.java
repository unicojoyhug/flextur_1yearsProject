package domain;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * 
 * @author Juyoung Choi & Jonas Mørch
 *
 */
public class FlexturImpl implements Flextur {
	private long flexturId;
	private int kundeId;
	private String efternavn;
	private String fornavn;
	private String telefon;
	private String cprNummer;
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
	private double kilometer;
	private String kommentar;
	private int barnevogne;
	private int autostole;
	private int koerestole;
	private int baggage;
	private String duration;
	private String distance;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#getFlexturId()
	 */
	@Override
	public long getFlexturId() {
		return flexturId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#setFlexturId(long)
	 */
	@Override
	public void setFlexturId(long flexturId) {
		this.flexturId = flexturId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#getKundeId()
	 */
	@Override
	public int getKundeId() {
		return kundeId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#setKundeId(int)
	 */
	@Override
	public void setKundeId(int kundeId) {
		this.kundeId = kundeId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#getDato()
	 */
	@Override
	public LocalDate getDato() {
		return dato;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#setDato(java.time.LocalDate)
	 */
	@Override
	public void setDato(LocalDate dato) {
		this.dato = dato;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#getTid()
	 */
	@Override
	public LocalTime getTid() {
		return tid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#setTid(java.time.LocalTime)
	 */
	@Override
	public void setTid(LocalTime tid) {
		this.tid = tid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#getFraAdress()
	 */
	@Override
	public String getFraAdress() {
		return fraAdress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#setFraAdress(java.lang.String)
	 */
	@Override
	public void setFraAdress(String fraAdress) {
		this.fraAdress = fraAdress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#getTilAdress()
	 */
	@Override
	public String getTilAdress() {
		return tilAdress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#setTilAdress(java.lang.String)
	 */
	@Override
	public void setTilAdress(String tilAdress) {
		this.tilAdress = tilAdress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#getPris()
	 */
	@Override
	public double getPris() {
		return pris;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#setPris(double)
	 */
	@Override
	public void setPris(double pris) {
		this.pris = pris;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#getAntalPersoner()
	 */
	@Override
	public int getAntalPersoner() {
		return antalPersoner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Flextur#setAntalPersoner(int)
	 */
	@Override
	public void setAntalPersoner(int antalPersoner) {
		this.antalPersoner = antalPersoner;
	}

	@Override
	public double getKilometer() {
		return kilometer;
	}

	@Override
	public void setKilometer(double kilometer) {
		this.kilometer = kilometer;

	}

	@Override
	public String getKommentar() {
		return kommentar;
	}

	@Override
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;

	}

	@Override
	public int getBarnevogne() {
		return barnevogne;
	}

	@Override
	public void setBarnevogne(int barnevogne) {
		this.barnevogne = barnevogne;

	}

	@Override
	public int getBaggage() {
		return baggage;
	}

	@Override
	public void setBaggage(int baggage) {
		this.baggage = baggage;

	}

	@Override
	public int getKoerestole() {
		return koerestole;
	}

	@Override
	public void setKoerestole(int koerestole) {
		this.koerestole = koerestole;

	}

	@Override
	public int getAutostole() {
		return autostole;
	}

	@Override
	public void setAutostole(int autostole) {
		this.autostole = autostole;

	}

	@Override
	public String getEfternavn() {
		return efternavn;
	}

	@Override
	public void setEfternavn(String efternavn) {
		this.efternavn = efternavn;
	}

	@Override
	public String getFornavn() {
		return fornavn;
	}

	@Override
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	@Override
	public String getTelefon() {
		return telefon;
	}

	@Override
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	@Override
	public String getCprNummer() {
		return cprNummer;
	}

	@Override
	public void setCprNummer(String cprNummer) {
		this.cprNummer = cprNummer;
	}

	@Override
	public String toString() {
		return "FlexturImpl [flexturId=" + flexturId + ", kundeId=" + kundeId + ", efternavn=" + efternavn
				+ ", fornavn=" + fornavn + ", telefon=" + telefon + ", cprNummer=" + cprNummer + ", dato=" + dato
				+ ", tid=" + tid + ", fraAdress=" + fraAdress + ", fraPostnummer=" + fraPostnummer + ", tilPostnummer="
				+ tilPostnummer + ", tilAdress=" + tilAdress + ", fraKommune=" + fraKommune + ", tilKommune="
				+ tilKommune + ", pris=" + pris + ", antalPersoner=" + antalPersoner + ", kilometer=" + kilometer
				+ ", kommentar=" + kommentar + ", barnevogne=" + barnevogne + ", autostole=" + autostole
				+ ", koerestole=" + koerestole + ", baggage=" + baggage + "]";
	}

	@Override
	public void setDistance(String distance) {
		this.distance = distance;

	}

	@Override
	public String getDistance() {
		return distance;
	}

	@Override
	public void setDuration(String duration) {
		this.duration = duration;

	}

	@Override
	public String getDuration() {
		return duration;
	}

}
