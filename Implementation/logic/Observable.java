package logic;


public interface Observable {
	void tilmeldObserver(Observer observer); 	
	void notifyObservers(Observable observable, Tilstand tilstand); 
}

