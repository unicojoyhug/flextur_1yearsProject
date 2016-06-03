package logic;
/**
 * 
 * KilometerUdregnningAdapterFactory for at skabe OkhttpKilometerUdregningAdapter objekt
 * 
 * @author Juyoung Choi
 *
 */
public class KilometerUdregningAdapterFactory {
	public KilometerUdregningAdapter getKilometerUdregningAdapter(){
		return new OkhttpKilometerUdregningAdapter();
	}
	
}
