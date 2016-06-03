package logic;

/**
 * 
 * Observer interface : update metoder bruges for flere observable i fremtiden
 * 
 * @author Juyoung Choi
 *
 */
public interface Observer {
	public void update(Observable observable, Tilstand tilstand);
	
}
