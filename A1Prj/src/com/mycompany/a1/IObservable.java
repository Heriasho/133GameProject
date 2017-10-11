package com.mycompany.a1;

public interface IObservable {
	public void addObserver (Observer obs);
	public void notifyObservers();
}
