package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

import java.util.ArrayList;
import java.util.Random;
//import java.util.Hashtable;

public class GameWorld implements IObservable {
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
		gameObject.add((Spaceship.getSpaceship()));
	}
	/*A method called when an alien is instanceof another alien. If so, they bred a new gameObject alien that spawns close to one of them.*/
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
		System.out.println("Two aliens bred.");
	}
	/*A method called when an alien is instanceof an astronaut. If so, the astronaut takes damage, changes color shade, & reduces in speed.*/
	public void fight() {
		if(roamingAstronauts <= 0 || roamingAliens <= 0){
			System.out.println("Error: Need at least 1 astronaut & alien to fight.");
			return;
		}
		Astronaut a = getRandomAstronaut();
		a.damage();
		System.out.println("The astronaut fought the alien & the alien won.\nAstronaut takes 1 point of damage.");
	}
	/*A method that moves the opponents game objects at a set pace.*/
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
	/*Returns the score.*/
	public void score() {
		System.out.println("The score is: " + score);
	}
	/*Gets a spaceship & increases it's size through its expandDoor().*/
	public void expand() {
		Spaceship sp  = getTheSpaceship();
		sp.expandDoor();
	}
	/*Gets a spaceship & decreases it's size through its contractDoor().*/
	public void compress() {
		Spaceship sp = getTheSpaceship();
		sp.contractDoor();
	}
	/*Gets a spaceship & checks to see if Opponents are instanceof it.
	 * If so, the opponents are 'rescued' & removed from the gameworld.*/
	public void openDoor() {
		System.out.println("The spaceship door has opened.");
		Spaceship sp = getTheSpaceship();
		ArrayList<GameObject> remove = new ArrayList<GameObject>();
		for(GameObject object : gameObject) {
			if(object instanceof Opponents) {
				Opponents o = (Opponents) object;
				double x = Math.abs(sp.getLocation().getX() - o.getLocation().getX());
				double y = Math.abs(sp.getLocation().getY() - o.getLocation().getY());
				if(x >= 0 && x <= sp.getSize() && y >= 0 && y <= sp.getSize()) {
					rescueAnObject(object);
					remove.add(object);
					System.out.println("You've rescued a " + object);
				}
			}
	}
		/* Concurrency issue with for each loop, created delete ArrayList<GameObject>.
		 * and remove after.
		 */
		for(GameObject object : remove)
			gameObject.remove(object);
	}
	/*Checks to see if a given gameobject is instanceof an alien or an astroanut.
	 * Depending on which one the object is instanceof, score is modified & so is corresponding
	 * trackers of aliens/astronauts in the gameworld.*/
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
	/*Moves the spaceship down*/
	public void moveSpaceShipDown() {
		Spaceship sp = getTheSpaceship();
		sp.moveDown();
	}
	/*Moves the spaceship left*/
	public void moveSpaceShipLeft() {
		Spaceship sp = getTheSpaceship();
		sp.moveLeft();
	}
	/*Moves the spaceship right*/
	public void moveSpaceShipRight() {
		Spaceship sp = getTheSpaceship();
		sp.moveRight();
	}
	/*Moves the spaceship up*/
	public void moveSpaceShipUp() {
		Spaceship sp = getTheSpaceship();
		sp.moveUp();
	}
	/*Teleports the spaceship to a random alien*/
	public void teleportToAlien() {
		Alien a;
		Spaceship sp;
		if(roamingAliens > 0){
			sp = getTheSpaceship();
			a = getRandomAlien();
			sp.setLocation(a.getLocation());
			System.out.println("You've teleported to an alien");
		}
		else
			System.out.println("Error: There were no aliens to jump to.");
	}
	/*Teleports the spaceship to a random astronaut*/
	public void teleportToAstronaut() {
		Astronaut a;
		Spaceship sp;
		if(roamingAstronauts > 0) {
			sp = getTheSpaceship();
			a = getRandomAstronaut();
			sp.setLocation(a.getLocation());
			System.out.println("You've teleported to an astronaut. \n Hope you didn't hit them.");
		}
		else 
			System.out.println("Error: Ther were no astronauts to jump to.");
	}
	/*While there is at least 1 alien remaining, returns a random alien.*/
	private Alien getRandomAlien() {
		while(roamingAliens >= 0){
			int i = random.nextInt(gameObject.size());
			if(gameObject.get(i)instanceof Alien)
				return (Alien) gameObject.get(i);
		}
		return null;
	}
	/*While there is a spaceship in the gameworld, returns the spaceship.*/
	private Spaceship getTheSpaceship() {
		for(GameObject object : gameObject)
			if(object instanceof Spaceship)
				return (Spaceship) object;
		return null;
	}
	/*While there is at least 1 astronaut remaining, returns a random astronaut.*/
	private Astronaut getRandomAstronaut() {
		while(roamingAstronauts > 0) {
			int i = random.nextInt(gameObject.size());
			if (gameObject.get(i) instanceof Astronaut)
				return (Astronaut) gameObject.get(i);
		}
		return null;
	}
	public void addObserver(Observer obs) {
		// TODO Auto-generated method stub
		
	}
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}
}
