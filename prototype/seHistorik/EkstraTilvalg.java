package seHistorik;

public class EkstraTilvalg {
	
	int id;
	String navn;
	int antal;
	double rate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNavn() {
		return navn;
	}
	public void setNavn(String navn) {
		this.navn = navn;
	}
	public int getAntal() {
		return antal;
	}
	public void setAntal(int antal) {
		this.antal = antal;
	}
	
	
	public double getRate() {
		return rate;
	}
	
	
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
	@Override
	public String toString() {
		return "EkstraTilvalg [id=" + id + ", navn=" + navn + ", antal=" + antal + ", rate=" + rate + "]";
	}
	

}
