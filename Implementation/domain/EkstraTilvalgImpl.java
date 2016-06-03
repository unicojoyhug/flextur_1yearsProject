package domain;
/**
 *  Domæneklasse for EkstraTilvalg for fremtiden.
 * @author Juyoung Choi
 *
 */
public class EkstraTilvalgImpl implements EkstraTilvalg {
	
	private int id;
	private String navn;
	private int antal;
	private double rate;
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String getNavn() {
		return navn;
	}
	
	@Override
	public void setNavn(String navn) {
		this.navn = navn;
	}
	
	@Override
	public int getAntal() {
		return antal;
	}

	@Override
	public void setAntal(int antal) {
		this.antal = antal;
	}
	
	@Override
	public double getRate() {
		return rate;
	}
	
	@Override
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	

}
