package domain;

public interface Kunde {

	int getCprNummer();

	void setCprNummer(int cprNummer);

	String getKommune();

	void setKommune(String kommune);

	String getTelefon();

	void setTelefon(String telefon);

	String getFornavn();

	void setFornavn(String fornavn);

	String getEfternavn();

	void setEfternavn(String efternavn);

	String getAdress();

	void setAdress(String adress);

	int getPostnummer();

	void setPostnummer(int postnummer);

	boolean erAktivt();

	void setErAktivt(boolean erAktivt);

}