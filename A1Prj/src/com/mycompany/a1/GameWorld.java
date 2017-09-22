package com.mycompany.a1;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.charts.util.ColorUtil;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class GameWorld {
	
	private int roamingAliens = 3;
	private int roamingAstronauts = 3;
	private int rescuedAstronauts = 0;
	private int rescuedAliens = 0;
	private int score = 0;
	private int tickTime = 1000;
	private Random random = new Random();
	private int screenHeight = 768;
	private int screenWidth = 1024;
	private int screenRotation = 90;
	private int speed = 5;
	private int speedMulti = 1;
	private GameCollection theGameCollection;
	private ArrayList<GameObject> gameObject;

	public GameWorld() {

	}
	
	/*Set the initial state of the game*/
	public void init() {
		for(int i = 0; i < roamingAliens; ++i) {
			Alien alien = new Alien(ColorUtil.BLACK, screenHeight, screenWidth, speed, speedMultiplier);
			go.add((GameObject) alien);
		}
		for(int i = 0; i < roamingAstronauts; ++i) {
			Astronaut astronaut = new Astronaut(ColorUtil.GREEN, screenHeight, screenWidth, speed, speedMultiplier);
			gameObject.add((GameObject) astronaut);
		}
		Spaceship spaceship = new Spaceship(ColorUtil.GRAY, screenHeight, screenWidth);
		go.add((GameObject) spaceship);
	}
	
	public void bred() {
		if(roamingAliens < 2){
			System.out.println("Error: Requires two aliens!");
			return;
		}
		Alien a = getRandomAlien();
		//Alien b = new Alien(ColorUtil.BLACK, screenHeight, screenWidth, speed ))
		gameObject.add((GameObject) b);
		b.setLocation(a.getLocation());
		b.move((int) ((a.getSize() + 5.0) /5.0) * 1000);
		
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
		for(GameObject object : gameObject) {
			if(object instanceof Opponents) {
				((Opponents) object).move(tickTime);
			}
		}
		System.out.println("Game has advanced by " + tickTime + " ms = " + tickTime/1000 + " ticks.");
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
	
	/**
	 * Move space ship down.
	 */
	public void moveSpaceShipDown() {
		Spaceship sp = getSpaceShip();
		sp.moveDown();
	}
	/**
	 * Move space ship left.
	 */
	public void moveSpaceShipLeft() {
		Spaceship sp = getSpaceShip();
		sp.moveLeft();
	}
	/**
	 * Move space ship right.
	 */
	public void moveSpaceShipRight() {
		Spaceship sp = getSpaceShip();
		sp.moveRight();
	}
	/**
	 * Move space ship up.
	 */
	public void moveSpaceShipUp() {
		Spaceship sp = getSpaceShip();
		sp.moveUp();
	}
	
	public void teleportToAlien() {
		
	}
	
	public void teleportToAstronaut() {
		
	}
	
	public void exit() {
		
	}
	private Alien getRandomAlien() {
		while(roamingAliens >= 0){
			int i = random.nextInt(gameObject.size());
			if(gameObject.get(i)instanceof Alien)
				return (Alien) gameObject.get(i);
		}
		return null;
	}
	

}
