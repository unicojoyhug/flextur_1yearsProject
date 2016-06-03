package logic;
/**
 * Sats Factory for at skabe FlemmingsSatsAdapter objekt
 * @author Juyoung Choi
 *
 */
public class SatsFactory {
	public SatsAdapter getSatsAdapter(){
		return new FlemmingSatsAdapter();
	}
}
