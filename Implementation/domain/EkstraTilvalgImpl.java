package domain;
/**
 * 
 * @author Juyoung Choi
 *
 */
public class EkstraTilvalgImpl implements EkstraTilvalg {
	
	private int id;
	private String navn;
	private int antal;
	private double rate;
	/* (non-Javadoc)
	 * @see domain.EkstraTilvalg#getId()
	 */
	@Override
	public int getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see domain.EkstraTilvalg#setId(int)
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see domain.EkstraTilvalg#getNavn()
	 */
	@Override
	public String getNavn() {
		return navn;
	}
	/* (non-Javadoc)
	 * @see domain.EkstraTilvalg#setNavn(java.lang.String)
	 */
	@Override
	public void setNavn(String navn) {
		this.navn = navn;
	}
	/* (non-Javadoc)
	 * @see domain.EkstraTilvalg#getAntal()
	 */
	@Override
	public int getAntal() {
		return antal;
	}
	/* (non-Javadoc)
	 * @see domain.EkstraTilvalg#setAntal(int)
	 */
	@Override
	public void setAntal(int antal) {
		this.antal = antal;
	}
	/* (non-Javadoc)
	 * @see domain.EkstraTilvalg#getRate()
	 */
	@Override
	public double getRate() {
		return rate;
	}
	/* (non-Javadoc)
	 * @see domain.EkstraTilvalg#setRate(double)
	 */
	@Override
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	

}
