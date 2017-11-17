package com.mycompany.a3;

import java.util.Vector;

public class GameCollection implements Icollection {         //Class to hold Game Objects, has same functionality as vector,
	 													     //but an Iterator can be implemented
	private Vector collection;
	/*DECLARE THE DAMN COLLECTION*/
	public GameCollection() {
		collection = new Vector();
	}
	/*A function that takes an object as a parameter and adds it to the collection.*/
	public void add(Object obj) {                        
		collection.addElement(obj);
	}
	/*An Iiterator function that returns a new Game Collection Iterator*/
	public Iiterator getIterator() {                       
		return new GameCollectionIterator();
	}
	/*A function that returns the int collection size.*/
	public int getSize() {
		return collection.size();
		
	}
	/*A function that takes in a paremter int n and returns a Game Object collection at position n*/
	public GameObject get(int n) {          
		return (GameObject) collection.get(n);
	}
	/*A function that takes in a paremeter int n and removes a collection object at position n*/
	public void remove(int n) {            // not being used
		collection.remove(n); 
	}
	
	private class GameCollectionIterator implements Iiterator {
		
		private int currIndex;	                     //The current place of the Iterator
		
		/*Default constructor that will simply set the index to -1*/
		public GameCollectionIterator() {                                               
			currIndex = -1;
		}

		/*A boolean function that checks to see if there is another element in the list.
		 * If the list is empty returns false, else return true*/
		public boolean hasNext() {                     
			
			if ( collection.size() <= 0 )                  
			{	
				return false;                               
			}
			if ( currIndex == (collection.size() - 1))   
			{	
				return false;
			}
			return true;
		}
		
		/*Increments current index by 1 & returns the next collection element */
		public Object getNext() {     	
			currIndex++;	                                  
			return collection.elementAt(currIndex);
		}
		/*Removes the collection object located at the current index.*/
		public void removeIt() { 
			collection.remove(currIndex);
			currIndex--;
		}
		/*A function that returns the current index.*/
		public int getIndex() {        
			return currIndex;
		}	
	}	
}