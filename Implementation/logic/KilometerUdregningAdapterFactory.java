package logic;
/**
 * 
 * @author Juyoung Choi
 *
 */
public class KilometerUdregningAdapterFactory {
	public KilometerUdregningAdapter getKilometerUdregningAdapter(){
		return new OkhttpKilometerUdregningAdapter();
	}
	
}
