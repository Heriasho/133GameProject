package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Random;
import java.util.Observable;
import java.util.Vector;
//import java.util.Hashtable;

public class GameWorld extends Observable {
	private int roamingAliens;
	private int roamingAstronauts;
	private int rescuedAstronauts;
	private int rescuedAliens;
	private int score;
	private int tickTime;
	private int speed;
	private int speedMulti;
	private boolean sound;
	private int screenHeight;
	private int screenWidth;
	private Random random;
	private GameCollection theGameCollection;
	private ArrayList<GameObject> gameObject;
	private Vector<Observer> myObserverList;
	
	public GameWorld(){
		//random = new Random();
		theGameCollection = new GameCollection(); //Collection of all objects
		gameObject = new ArrayList<GameObject>(); //Array list of GameObjects
		myObserverList = new Vector<Observer>();
		roamingAliens = 3;
		rescuedAliens = 0;
		roamingAstronauts = 3;
		rescuedAstronauts = 0;
		score = 0;
		tickTime = 1000;
		speed = 5;
		speedMulti = 1;
		screenHeight = 768;
		screenWidth = 1024;
		sound = true;
		//this.logicBoy();
		init();
	}
/*	public void logicBoy(){
//		Iiterator theIterator = theGameCollection.getIterator();
//		theGameCollection.add("Hello");
//		theGameCollection.add("There");
//		System.out.println(theIterator.getNext());
//		System.out.println(theIterator.toString());
//		//Astronaut astroboy = new Astronaut(ColorUtil.MAGENTA, 20 , 15, speed, speedMulti);
//		//gameObject.add(astroboy);
//		while(theIterator.hasNext())
//		{
//			System.out.print("THERE WAS SOMETHING HERE:   ");
//			//GameObject obj = (GameObject) theIterator.getNext();
//			//System.out.println(obj);
//		}
	} */
	/*Set the initial state of the game*/
	public void init() {
		initialSpawn();
		addSpaceship();
		updateGameWorld();
	}
	public void initialSpawn() {
		//System.out.print(getTheGameCollectionSize());
		for(int i = 0; i < roamingAliens; ++i) {
			//Alien alien = new Alien(ColorUtil.MAGENTA, screenHeight, screenWidth, speed, speedMulti);
			//theGameCollection.add((GameObject) alien); 
			addAlien();
		}
		for(int i = 0; i < roamingAstronauts; ++i) {
			//Astronaut astronaut = new Astronaut(ColorUtil.GREEN, screenHeight, screenWidth, speed, speedMulti);
			//theGameCollection.add((GameObject) astronaut);
			addAstro();
		}
	}
	public void addAlien(){
		theGameCollection.add((GameObject)new Alien(ColorUtil.MAGENTA, screenHeight, screenWidth, speed, speedMulti));
		System.out.println("Game Collection Size: " + theGameCollection.getSize());
		updateGameWorld();
	}
	public void addAstro(){
		theGameCollection.add((GameObject)new Astronaut(ColorUtil.GREEN, screenHeight, screenWidth, speed, speedMulti));
		System.out.println("Game Collection Size: " + theGameCollection.getSize());
		updateGameWorld();
	}
	public void addSpaceship(){
		theGameCollection.add((Spaceship.getSpaceship()));
		updateGameWorld();
	}
	public void updateGameWorld() {
		this.setChanged();
		this.notifyObservers();
		this.clearChanged();
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
				+ "\nNumber of Astronauts roaming: " + roamingAstronauts
				+ "\nNumber of Aliens rescued: " + rescuedAliens
				+ "\nNumber of Aliens roaming: " + roamingAliens);
	}
	
	/*Print a 'map' showing the current world state.*/
	public void map() {
//		for(GameObject object : gameObject)
//			System.out.println(object);
		Iiterator theIterator = theGameCollection.getIterator();
		while(theIterator.hasNext())
		{
			GameObject obj = (GameObject) theIterator.getNext();
			System.out.println(obj);
		}
	}
	/*Returns the score.*/
	public void score() {
		System.out.println("The score is: " + score);
	}
	/*Gets a spaceship & increases it's size through its expandDoor().*/
	public void expand() {
		Spaceship sp  = getTheSpaceship();
		sp.expandDoor();
		updateGameWorld();
	}
	/*Gets a spaceship & decreases it's size through its contractDoor().*/
	public void compress() {
		Spaceship sp = getTheSpaceship();
		sp.contractDoor();
		updateGameWorld();
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
	public int getTheGameCollectionSize() {
		return theGameCollection.getSize();
	}
	public GameCollection getGameCollection(){
		return theGameCollection;
	}
	public int getRoamingAliens(){
		return roamingAliens;
	}
	public void setRoamingAliens(int value){
		roamingAliens = value;
	}
	public int getRoamingAstronauts(){
		return roamingAstronauts;
	}
	public void setRoamingAstronauts(int value){
		roamingAstronauts = value;
	}
	public int getRescuedAstronauts(){
		return rescuedAstronauts;
	}
	public void setRescuedAstronauts(int value){
		rescuedAstronauts = value;
	}
	public int getRescuedAliens(){
		return rescuedAliens;
	}
	public void setRescuedAliens(int value){
		rescuedAliens = value;
	}
	public int getScore(){
		return score;
	}
	public void setScore(int value){
		score = value;
	}
	public boolean getSound() {
		return sound;
	}
	public void setSound(boolean b) {
		this.sound = b;
		updateGameWorld();
	}
	public void addObserver(Observer o) {          //add observers to observer list
		myObserverList.add(o);
	}
}
