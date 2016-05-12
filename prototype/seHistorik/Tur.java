package seHistorik;

import java.time.LocalDate;
import java.util.List;

public class Tur {
	
	long id;
	long kunde;
	LocalDate dato;
	Kommune fraKommune;
	Kommune tilKommune;
	String fraAdress;
	String tilAdress;
	double afstand;
	int antalPersoner;
	boolean erGodkendt;
	List<EkstraTilvalg> ekstraTilvalg;
	
	double totalPris;

	public boolean isErGodkendt() {
		return erGodkendt;
	}

	public void setErGodkendt(boolean erGodkendt) {
		this.erGodkendt = erGodkendt;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getKunde() {
		return kunde;
	}

	public void setKunde(long kunde) {
		this.kunde = kunde;
	}

	public LocalDate getDato() {
		return dato;
	}

	public void setDato(LocalDate dato) {
		this.dato = dato;
	}

	public Kommune getFraKommune() {
		return fraKommune;
	}

	public void setFraKommune(Kommune fraKommune) {
		this.fraKommune = fraKommune;
	}

	public Kommune getTilKommune() {
		return tilKommune;
	}

	public void setTilKommune(Kommune tilKommune) {
		this.tilKommune = tilKommune;
	}

	public String getFraAdress() {
		return fraAdress;
	}
	
	public void setFraAdress(String fraAdress) {
		this.fraAdress = fraAdress;
	}

	public String getTilAdress() {
		return tilAdress;
	}

	public void setTilAdress(String tilAdress) {
		this.tilAdress = tilAdress;
	}

	public double getAfstand() {
		return afstand;
	}

	public void setAfstand(double afstand) {
		this.afstand = afstand;
	}

	public int getAntalPersoner() {
		return antalPersoner;
	}

	public void setAntalPersoner(int antalPersoner) {
		this.antalPersoner = antalPersoner;
	}

	public List<EkstraTilvalg> getEkstraTilvalg() {
		return ekstraTilvalg;
	}

	public void setEkstraTilvalg(List<EkstraTilvalg> ekstraTilvalg) {
		this.ekstraTilvalg = ekstraTilvalg;
	}

	public double getTotalPris() {
		
		return totalPris ;
	}

	public void setTotalPris(Tur tur) {
		this.totalPris = new Pris().getTotalPris(tur);
	}


	
	@Override
	public String toString() {
		return "Tur [id=" + id + ", kunde=" + kunde + ", dato=" + dato + ", fraKommune=" + fraKommune + ", tilKommune="
				+ tilKommune + ", FraAdress=" + fraAdress + ", TilAdress=" + tilAdress + ", afstand=" + afstand
				+ ", antalPersoner=" + antalPersoner + ", erGodkendt=" + erGodkendt + ", ekstraTilvalg=" + ekstraTilvalg
				+ ", TotalPris=" + totalPris + "]";
	}


	
	

}
