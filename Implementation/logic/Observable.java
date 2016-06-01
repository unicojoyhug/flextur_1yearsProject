package logic;

/**
 * 
 * @author Juyoung Choi
 *
 */
public interface Observable {
	void tilmeldObserver(Observer observer); 	
	void notifyObservers(Observable observable, Tilstand tilstand); 
}

