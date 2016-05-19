package domain;

import java.time.LocalDate;

public interface HistorikForBM {

	String getCprNummer();

	void setCprNummer(String cprNummer);

	LocalDate getFraDato();

	void setFraDato(LocalDate fraDato);

	LocalDate getTilDato();

	void setTilDato(LocalDate tilDato);

	String getKommune();

	void setKommune(String kommune);

	double getTotalPris();

	void setTotalPris(double totaPris);

	int getAntalPersoner();

	void setAntalPersoner(int antalPersoner);

	int getAntalTur();

	void setAntalTur(int antalTur);

	String getEfternavn();

	String getFornavn();

	void setEfternavn(String efternavn);

	void setFornavn(String fornavn);

}