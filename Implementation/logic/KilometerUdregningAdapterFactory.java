package logic;

public class KilometerUdregningAdapterFactory {
	public KilometerUdregningAdapter getKilometerUdregningAdapter(){
		return new OkhttpKilometerUdregningAdapter();
	}
	
}
