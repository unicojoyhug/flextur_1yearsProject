package logic;

import java.util.ArrayList;
import java.util.List;

public interface Observable {
public List<Observer> observers = new ArrayList<>();
	
	default public void tilmedObserver(Observer observer) {
		observers.add(observer);
	}
	
	default public void notifyObservers(Observable observable, Object tilstand) {
		for(Observer observer : observers) {
			observer.update(observable, tilstand);
		}
	}
}
