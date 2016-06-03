package logic;

/**
 * interface for observable 
 * 
 * notifyObservers parameter observable bruges for flere observable i fremtiden
 * @author Juyoung Choi
 *
 */
public interface Observable {
	void tilmeldObserver(Observer observer); 	
	void notifyObservers(Observable observable, Tilstand tilstand); 
}

