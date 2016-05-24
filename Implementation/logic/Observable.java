package logic;

//import java.util.ArrayList;
import java.util.List;

public interface Observable {
	void tilmeldObserver(Observer observer); 	
	void notifyObservers(Observable observable, Tilstand tilstand); 
}

