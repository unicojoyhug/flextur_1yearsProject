package domain;

import java.time.LocalDate;

public interface HistorikSøgning {

	LocalDate getFraDato();

	void setFraDato(LocalDate fraDato);

	LocalDate getTilDato();

	void setTilDato(LocalDate tilDato);

	String getKommune();

	void setKommune(String kommune);

	String getCprNummer();

	void setCprNummer(String cprNummer);

}