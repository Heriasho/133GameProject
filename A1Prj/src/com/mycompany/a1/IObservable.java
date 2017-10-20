package com.mycompany.a1;

import java.util.Observer;

public interface IObservable {
	public void addObserver (Observer obs);
	public void notifyObservers();
	public void setChange();
}
