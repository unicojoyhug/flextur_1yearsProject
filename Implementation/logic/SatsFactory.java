package logic;

public class SatsFactory {
	public SatsAdapter getSatsAdapter(){
		return new SatsAdapterImpl();
	}
}
