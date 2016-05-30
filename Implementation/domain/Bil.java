package domain;

public interface Bil {

	int getId();

	void setId(int id);

	String getNavn();

	void setNavn(String navn);

	int getMaxAntalPersoner();

	void setMaxAntalPersoner(int maxAntalPersoner);

	boolean harTilvalgsMulighed();

	void setTilvalgsMulighed(boolean tilvalgsMulighed);

}