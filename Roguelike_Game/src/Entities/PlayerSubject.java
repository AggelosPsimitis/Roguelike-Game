package Entities;

import Main.MyObserver;

public interface PlayerSubject {

	void addObserver(MyObserver o);
	void removeObserver(MyObserver o);
	void notifyObservers();

}


