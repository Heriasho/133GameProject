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
	private int speed = 5;
	private int speedMulti = 1;
	//private GameCollection theGameCollection;
	private ArrayList<GameObject> gameObject = new ArrayList<GameObject>();
	

	public GameWorld() {

	}
	
	/*Set the initial state of the game*/
	public void init() {
		for(int i = 0; i < roamingAliens; ++i) {
			Alien alien = new Alien(ColorUtil.MAGENTA, screenHeight, screenWidth, speed, speedMulti);
			gameObject.add((GameObject) alien); 
		}
		for(int i = 0; i < roamingAstronauts; ++i) {
			Astronaut astronaut = new Astronaut(ColorUtil.GREEN, screenHeight, screenWidth, speed, speedMulti);
			gameObject.add((GameObject) astronaut);
		}
		Spaceship spaceship = new Spaceship(ColorUtil.GRAY, screenHeight, screenWidth);
		gameObject.add((GameObject) spaceship);
	}
	
	public void bred() {
		if(roamingAliens < 2){
			System.out.println("Error: Requires two aliens!");
			return;
		}
		Alien a = getRandomAlien();
		Alien b = new Alien(ColorUtil.BLACK, screenHeight, screenWidth, speed, speedMulti );
		gameObject.add((GameObject) b);
		b.setLocation(a.getLocation());
		b.move((int) ((a.getSize() + 5.0) /5.0) * 1000);
		
	}
	
	public void fight() {
		if(roamingAstronauts <= 0 || roamingAliens <= 0){
			System.out.println("Error: Need at least 1 astronaut & alien to fight.");
			return;
		}
		Astronaut a = getRandomAstronaut();
		a.damage();
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
		for(GameObject object : gameObject)
			System.out.println(object);
	}
	
	public void score() {
		System.out.println("The score is: " + score);
	}
	
	public void expand() {
		Spaceship sp  = getSpaceship();
		sp.expandDoor();
	}
	
	public void compress() {
		Spaceship sp = getSpaceship();
		sp.contractDoor();
	}
	public void openDoor() {
		Spaceship sp = getSpaceship();
		ArrayList<GameObject> remove = new ArrayList<GameObject>();
		for(GameObject object : gameObject) {
			if(object instanceof Opponents) {
				Opponents o = (Opponents) object;
				double x = Math.abs(sp.getLocation().getX() - o.getLocation().getX());
				double y = Math.abs(sp.getLocation().getY() - o.getLocation().getY());
				if(x >= 0 && x <= sp.getSize() && y >= 0 && y <= sp.getSize()) {
					rescueAnObject(object);
					remove.add(object);
				}
			}
	}
		/* Concurrency issue with for each loop, created delete ArrayList<GameObject>
		 * and remove after.
		 */
		for(GameObject object : remove)
			gameObject.remove(object);
	}
	private void rescueAnObject(GameObject object) {
		if(object instanceof Alien) {
			score -= 10;
			roamingAliens -= 1;
			rescuedAliens += 1;
		} else if (object instanceof Astronaut) {
			score += (5 + ((Astronaut) object).getSpeed());
			roamingAstronauts -= 1;
			rescuedAstronauts += 1;
		}
	}
	
	
	/**
	 * Move space ship down.
	 */
	public void moveSpaceShipDown() {
		Spaceship sp = getSpaceship();
		sp.moveDown();
	}
	/**
	 * Move space ship left.
	 */
	public void moveSpaceShipLeft() {
		Spaceship sp = getSpaceship();
		sp.moveLeft();
	}
	/**
	 * Move space ship right.
	 */
	public void moveSpaceShipRight() {
		Spaceship sp = getSpaceship();
		sp.moveRight();
	}
	/**
	 * Move space ship up.
	 */
	public void moveSpaceShipUp() {
		Spaceship sp = getSpaceship();
		sp.moveUp();
	}
	
	public void teleportToAlien() {
		Alien a;
		Spaceship sp;
		if(roamingAliens > 0){
			sp = getSpaceship();
			a = getRandomAlien();
			sp.setLocation(a.getLocation());
			System.out.println("You've teleported to an alien");
		}
		else
			System.out.println("Error: There were no aliens to jump to.");
	}
	
	public void teleportToAstronaut() {
		Astronaut a;
		Spaceship sp;
		if(roamingAstronauts > 0) {
			sp = getSpaceship();
			a = getRandomAstronaut();
			sp.setLocation(a.getLocation());
			System.out.println("You've teleported to an astronaut. \n Hope you didn't hit them.");
		}
		else 
			System.out.println("Error: Ther were no astronauts to jump to.");
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
	private Spaceship getSpaceship() {
		for(GameObject object : gameObject)
			if(object instanceof Spaceship)
				return (Spaceship) object;
		return null;
	}
	
	private Astronaut getRandomAstronaut() {
		while(roamingAstronauts > 0) {
			int i = random.nextInt(gameObject.size());
			if (gameObject.get(i) instanceof Astronaut)
				return (Astronaut) gameObject.get(i);
		}
		return null;
	}
	

}
