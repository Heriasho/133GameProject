package com.mycompany.a3;

import java.util.Vector;

public class GameCollection implements Icollection {         //Class to hold Game Objects, has same functionality as vector,
	 													     //but an Iterator can be implemented
	private Vector collection;
	

	public GameCollection() {
		
		collection = new Vector();
	}
	
	public void add(Object obj) {                        
		
		collection.addElement(obj);
	}
	
	public Iiterator getIterator() {                          // the iterator
		
		return new GameCollectionIterator();
	}
	
	public int getSize() {
		
		return collection.size();
		
	}

	public GameObject get(int n) {           // not being used
		
		return (GameObject) collection.get(n);
	}

	public void remove(int n) {            // not being used
		
		collection.remove(n); 
	}

	
	private class GameCollectionIterator implements Iiterator {
		
		private int currIndex;	                     //The current place of the Iterator
		
		
		public GameCollectionIterator() {               // This is the default constructor. This will simply set the Index to -1 so
			                                            // that the entire algorithm works.
			currIndex = -1;
		}

		public boolean hasNext() {                     //Checks if there is another element in the list
			
			if ( collection.size() <= 0 )                   //Check for 'empty list'
			{	
				return false;                               
			}
			if ( currIndex == (collection.size() - 1))   //Check for 'end-of-list'
			{	
				return false;
			}
			return true;	//Otherwise there is another element
		}
		
		
		public Object getNext() {                                //Gets the next element. Should be used in conjunction with hasNext().
			
			currIndex++;	                                  //Increment to next element true element location
			return collection.elementAt(currIndex);
		}
		
		public void removeIt() {                     //remove current object
			
			collection.remove(currIndex);
			currIndex--;
		}
		
		public int getIndex() {              // not being used
			
			return currIndex;
		}
		
		
	}	//end of private GameCollectionIterator
}