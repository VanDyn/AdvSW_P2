package main;
import java.util.Observer;

public interface CafeQueueInterface {
	public void registerObserver(Observer obs);
	public void removeObserver(Observer obs);
	public void notifyObservers();
}
