package logic;
/**
 * 
 * @author Juyoung Choi
 *
 */
public class SatsFactory {
	public SatsAdapter getSatsAdapter(){
		return new FlemmingSatsAdapter();
	}
}
