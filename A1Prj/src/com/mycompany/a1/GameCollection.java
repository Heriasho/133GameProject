package com.mycompany.a1;

import java.util.Hashtable;

public class GameCollection {
	private Hashtable theCollection;
	
	public GameCollection(){
		theCollection = new Hashtable();
	}
	public void add(GameObject newObject){
		String hashKey = newObject.getName();
		theCollection.put(hashKey, newObject);
	}
	public Hashtable getObjects(){
		return theCollection;
	}
}
