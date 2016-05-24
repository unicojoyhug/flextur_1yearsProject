package domain;

import java.time.LocalDate;

import exception.MissingOplysningExcpetion;
import logic.Observable;
import logic.Observer;
import logic.Tilstand;

/**
 * This is the domain class for HistorikSøgningsOplysninger for UC1-seHistorik
 * MissingOplysningException
 * @author Juyoung Choi
 *
 */

public class HistorikSøgningImpl implements HistorikSøgning  {


	private LocalDate fraDato = null;
	private LocalDate tilDato = null;
	private String kommune = null;
	private String cprNummer;




	@Override
	public LocalDate getFraDato() {
		return fraDato;
	}

	@Override
	public void setFraDato(LocalDate fraDato) {
		if (fraDato == null) {
			throw new MissingOplysningExcpetion ("Fra Dato Mangler");
		}

		this.fraDato = fraDato;
	}

	@Override
	public LocalDate getTilDato() {
		return tilDato;
	}

	@Override
	public void setTilDato(LocalDate tilDato) {
		if (tilDato == null) {
			throw new MissingOplysningExcpetion ("Til kommune Mangler");
		}
		this.tilDato = tilDato;
	}

	@Override
	public String getKommune() {
		return kommune;
	}

	@Override
	public void setKommune(String kommune) {
		if (kommune == null) {
			throw new MissingOplysningExcpetion ("Fra kommune Mangler");
		}
		this.kommune = kommune;
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
		return "HistorikSøgningImpl [fraDato=" + fraDato + ", tilDato=" + tilDato + ", kommune=" + kommune
				+ ", cprNummer=" + cprNummer + "]";
	}

	

}
