package domain;

import java.time.LocalDate;
/**
 * 
 * @author Juyoung Choi
 *
 */
public class HistorikForBMImpl implements HistorikForBM {

	private String cprNummer;
	private LocalDate fraDato;
	private LocalDate tilDato;
	private String kommune;
	private double totalPris;
	private int antalPersoner;
	private int antalTur;
	private String efternavn;
	private String fornavn;
	
	
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
	public String getCprNummer() {
		return cprNummer;
	}
	
	@Override
	public void setCprNummer(String cprNummer) {
		this.cprNummer = cprNummer;
	}
	
	@Override
	public LocalDate getFraDato() {
		return fraDato;
	}
	
	@Override
	public void setFraDato(LocalDate fraDato) {
		this.fraDato = fraDato;
	}

	@Override
	public LocalDate getTilDato() {
		return tilDato;
	}

	@Override
	public void setTilDato(LocalDate tilDato) {
		this.tilDato = tilDato;
	}
	
	@Override
	public String getKommune() {
		return kommune;
	}

	@Override
	public void setKommune(String kommune) {
		this.kommune = kommune;
	}

	@Override
	public double getTotalPris() {
		return totalPris;
	}

	@Override
	public void setTotalPris(double totalPris) {
		this.totalPris = totalPris;
	}

	@Override
	public int getAntalPersoner() {
		return antalPersoner;
	}
	
	@Override
	public void setAntalPersoner(int antalPersoner) {
		this.antalPersoner = antalPersoner;
	}
	
	@Override
	public int getAntalTur() {
		return antalTur;
	}
	
	@Override
	public void setAntalTur(int antalTur) {
		this.antalTur = antalTur;
	}
	@Override
	public String toString() {
		return "HistorikForBMImpl [cprNummer=" + cprNummer + ", fraDato=" + fraDato + ", tilDato=" + tilDato
				+ ", kommune=" + kommune + ", totalPris=" + totalPris + ", antalPersoner=" + antalPersoner + ", antalTur="
				+ antalTur + ", efterNavn=" + efternavn + ", forNavn=" + fornavn + "]";
	}
	

}
