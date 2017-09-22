package com.mycompany.a1;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class GameWorld {
	
	private int roamingAliens;
	private int roamingAstronauts;
	private int rescuedAstronauts;
	private int rescuedAliens;
	private int score;
	private GameCollection theGameCollection;
	private ArrayList<GameObject> gameObject;

	public GameWorld() {
		/* Aggrigators: DON'T MAKE THE THINGS YOU WANT TO CALL ABSTRACT.	
		 * GameObjects are set up to have: String, int screenHeight, int screenWidth, int color, and Point2D location*/
		gameObject.add(new Alien("First Alien", 300,400, 5, null));
		gameObject.add(new Alien("Second Alien", 400,500,5, null));
		gameObject.add(new Astronaut("Astronaut",400,400,5, null));
		gameObject.add(new Spaceship("Spaceship", 350, 250,5, null));
//		theGameCollection = new GameCollection();
//		theGameCollection.add(new GameObject("Alien"));
//		
//		roamingAliens = aliens.length;
//		roamingAstronauts = astronauts.length;
	}
	
	/*Set the initial state of the game*/
	public void init() {

	}
	
	public void bred() {
		if(Alien.class.isInstance(Alien.class)) {
			gameObject.add(new Alien("Born Alien", 400,500,5, null));
			roamingAliens ++;
		}
	}
	
	public void fight() {
		if(Alien.class.isInstance(Astronaut.class)){
//			Astronaut.class.
		}
//		if(alien instanceof astronaut){
//			this.astronaut.health --;
//		}
	}
	
	public void tick() {
		
	}
	
	/*Print the points of game state values:
	 * current score
	 * number of astronauts rescued
	 * number of aliens sneaked in to the spaceship
	 * number of astronauts left in the world
	 * Output should be appropriately labeled in easily readable format*/
	public void stats() {
		System.out.println("The score is: " + score 
				+ "\nNumber of Astronauts rescused: " + rescuedAstronauts 
				+ "\nNumber of Aliens rescued: " + rescuedAliens
				+ "\nNumber of Aliens roaming: " + roamingAliens);
	}
	
	/*Print a 'map' showing the current world state.*/
	public void map() {
		for(int i = 0; i<gameObject.size(); i++){
			//System.out.println(gameObject.all the gameobjects in the game. \n));
		}
	}
	
	public void score() {
		System.out.println("The score is: " + score);
	}
	
	public void expand() {
		
	}
	
	public void compress() {
		
	}
	
	public void spaceShipMove() {
		
	}
	
	public void teleportToAlien() {
		
	}
	
	public void teleportToAstronaut() {
		
	}
	
	public void exit() {
		
	}
	

}
