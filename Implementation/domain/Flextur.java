package domain;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Flextur {

	long getFlexturId();

	void setFlexturId(long flexturId);

	int getKundeId();

	void setKundeId(int kundeId);

	LocalDate getDato();

	void setDato(LocalDate dato);

	LocalTime getTid();

	void setTid(LocalTime tid);

	String getFraAdress();

	void setFraAdress(String fraAdress);

	String getTilAdress();

	void setTilAdress(String tilAdress);

	double getPris();

	void setPris(double pris);

	int getAntalPersoner();

	void setAntalPersoner(int antalPersoner);
	
	int getFraPostnummer();

	void setTilPostnummer(int tilPostnummer);

	int getTilPostnummer();

	void setFraPostnummer(int fraPostnummer);

	String getFraKommune();

	void setFraKommune(String fraKommune);

	String getTilKommune();

	void setTilKommune(String tilKommune);

	double getKilometer();

	void setKilometer(double kilometer);

	String getKommentar();

	void setKommentar(String kommentar);

	int getBarnevogne();

	void setBarnevogne(int barnevogne);

	int getBaggage();

	void setBaggage(int baggage);

	int getKoerestole();

	void setKoerestole(int koerestole);

	int getAutostole();

	void setAutostole(int autostole);

	void setEfternavn(String efternavn);

	String getEfternavn();

	String getFornavn();

	void setFornavn(String fornavn);

	String getTelefon();

	void setTelefon(String telefon);

	String getCprNummer();

	void setCprNummer(String cprNummer);

}