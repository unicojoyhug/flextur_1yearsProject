package domain;

/**
 * 
 * @author Juyoung Choi
 *
 */

public class BilImpl implements Bil {
	int id;
	String navn;
	int maxAntalPersoner;
	boolean tilvalgsMulighed;

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Bil#getId()
	 */
	@Override
	public int getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Bil#setId(int)
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Bil#getNavn()
	 */
	@Override
	public String getNavn() {
		return navn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Bil#setNavn(java.lang.String)
	 */
	@Override
	public void setNavn(String navn) {
		this.navn = navn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Bil#getMaxAntalPersoner()
	 */
	@Override
	public int getMaxAntalPersoner() {
		return maxAntalPersoner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Bil#setMaxAntalPersoner(int)
	 */
	@Override
	public void setMaxAntalPersoner(int maxAntalPersoner) {
		this.maxAntalPersoner = maxAntalPersoner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Bil#harTilvalgsMulighed()
	 */
	@Override
	public boolean harTilvalgsMulighed() {
		return tilvalgsMulighed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see domain.Bil#setTilvalgsMulighed(boolean)
	 */
	@Override
	public void setTilvalgsMulighed(boolean tilvalgsMulighed) {
		this.tilvalgsMulighed = tilvalgsMulighed;
	}

	@Override
	public String toString() {
		return navn;
	}

}
