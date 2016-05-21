package domain;

import java.time.LocalDate;

import exception.MissingOplysningExcpetion;
import logic.Observable;
import logic.Tilstand;


public class HistorikSøgningImpl implements HistorikSøgning, Observable  {
	
	private LocalDate fraDato = null;
	private LocalDate tilDato = null;
	private String kommune = null;
	private String cprNummer;
	
	
	
	/* (non-Javadoc)
	 * @see domain.HistorikSøgning#getFraDato()
	 */
	@Override
	public LocalDate getFraDato() {
		return fraDato;
	}
	/* (non-Javadoc)
	 * @see domain.HistorikSøgning#setFraDato(java.time.LocalDate)
	 */
	@Override
	public void setFraDato(LocalDate fraDato) {
		if (fraDato == null) {
			throw new MissingOplysningExcpetion ("Fra Dato Mangler");
		}
		
		this.fraDato = fraDato;
	}
	/* (non-Javadoc)
	 * @see domain.HistorikSøgning#getTilDato()
	 */
	@Override
	public LocalDate getTilDato() {
		return tilDato;
	}
	/* (non-Javadoc)
	 * @see domain.HistorikSøgning#setTilDato(java.time.LocalDate)
	 */
	@Override
	public void setTilDato(LocalDate tilDato) {
		if (tilDato == null) {
			throw new MissingOplysningExcpetion ("Til Dato Mangler");
		}
		this.tilDato = tilDato;
	}
	/* (non-Javadoc)
	 * @see domain.HistorikSøgning#getKommune()
	 */
	@Override
	public String getKommune() {
		return kommune;
	}
	/* (non-Javadoc)
	 * @see domain.HistorikSøgning#setKommune(java.lang.String)
	 */
	@Override
	public void setKommune(String kommune) {
		if (kommune == null) {
			throw new MissingOplysningExcpetion ("Til Dato Mangler");
		}
		this.kommune = kommune;
	}
	/* (non-Javadoc)
	 * @see domain.HistorikSøgning#getCprNummer()
	 */
	@Override
	public String getCprNummer() {
		return cprNummer;
	}
	/* (non-Javadoc)
	 * @see domain.HistorikSøgning#setCprNummer(java.lang.String)
	 */
	@Override
	public void setCprNummer(String cprNummer) {
		this.cprNummer = cprNummer;
	}
	
	
	@Override
	public Tilstand getTilstand() {
		return Tilstand.SØG_HISTORIK;
	}
	
	@Override
	public String toString() {
		return "HistorikSøgningImpl [fraDato=" + fraDato + ", tilDato=" + tilDato + ", kommune=" + kommune
				+ ", cprNummer=" + cprNummer + "]";
	}

	
}
