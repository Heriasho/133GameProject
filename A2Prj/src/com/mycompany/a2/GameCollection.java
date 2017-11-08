package com.mycompany.a2;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class GameCollection implements ICollection {
	private Hashtable<String, Object> theCollection;
	
	public GameCollection(){
		theCollection = new Hashtable<String, Object>();
	}
	public void add(Object newObject){
		String hashKey = ((GameObject) newObject).getName();
		theCollection.put(hashKey, newObject);
	}
	public Hashtable<String, Object> getObjects(){
		return theCollection;
	}

	public IIterator getIterator() {
		return new GameHashtableIterator();
	}
	
	private class GameHashtableIterator implements IIterator {
		private int currHashIndex;
		public GameHashtableIterator() {
			currHashIndex = -1;
		}

		public boolean hasNext() {
			if(theCollection.size() <= 0){
				return false;
			}
			if(currHashIndex == theCollection.size() -1){
				return false;
			}
			return true;
		}


		public Object getNext() {
			return theCollection.get(currHashIndex);
		}
		public void removeObject(){
			theCollection.remove(currHashIndex);
		}
		
	}
}
