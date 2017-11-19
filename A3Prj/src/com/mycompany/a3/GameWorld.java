package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Random;
import java.util.Observable;
import java.util.Vector;
import java.util.List;
import java.lang.Integer;

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
	private int screenHeight;
	private int screenWidth;
	private int startWidth;
	private int startHeight;
	private int endWidth;
	private int endHeight;
	private Random random;
	private boolean isPlaying = true;
	private boolean isSoundOn = true;
	private GameCollection theGameCollection;
	private Vector<Observer> myObserverList;
	BGsound bgMusic = new BGsound("music.wav", this);
	Sound alienSound = new Sound("alien.wav", this);
	Sound astronautSound = new Sound("astro.wav", this);
	Sound doorSound = new Sound("door.wav", this);

	public GameWorld() {
		random = new Random();
		theGameCollection = new GameCollection();
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
	}

	/* Set the initial state of the game */
	public void init() {
		initialSpawn();
		addSpaceship();
		bgMusic.play();
		updateGameWorld();
	}

	public void initialSpawn() {
		for (int i = 0; i < roamingAliens; ++i) {
			addAlien();
		}
		for (int i = 0; i < roamingAstronauts; ++i) {
			addAstro();
		}
	}

	public void addAlien() {
		theGameCollection.add((GameObject) new Alien(ColorUtil.MAGENTA,
				screenHeight, screenWidth, speed, speedMulti));
		System.out.println("Game Collection Size: "
				+ theGameCollection.getSize());
		updateGameWorld();
	}

	public void addAstro() {
		theGameCollection.add((GameObject) new Astronaut(ColorUtil.GREEN,
				screenHeight, screenWidth, speed, speedMulti));
		System.out.println("Game Collection Size: "
				+ theGameCollection.getSize());
		updateGameWorld();
	}

	public void addSpaceship() {
		theGameCollection.add((Spaceship.getSpaceship()));
		updateGameWorld();
	}

	public void updateGameWorld() {
		this.setChanged();
		this.notifyObservers();
		this.clearChanged();
	}

    public void bred() { // XXX fucking hell
        if (roamingAliens < 2) {
            System.out.println("Error: Requires two aliens!");
            return;
        }
        //#XXX ALIEN THRUST
//        Alien a = getRandomAlien();
//        Alien b = new Alien(ColorUtil.BLACK, screenHeight, screenWidth, speed,speedMulti);
//        theGameCollection.add((GameObject) b);
//        roamingAliens++;
//        double x = a.getLocation().getX() + 40;
//        double y = a.getLocation().getY() + 40;
//        Point2D p = new Point2D(x, y);
//        b.setLocation(p);
        System.out.println("Two aliens bred.");
        if(getIsSoundOn() && getIsPlaying()) alienSound.play();
        updateGameWorld();
    }

	public void fight() {
		if (roamingAstronauts <= 0 || roamingAliens <= 0) {
			System.out
					.println("Error: Need at least 1 astronaut & alien to fight.");
			return;
		}
		Astronaut a = getRandomAstronaut();
		a.damage();
		if ((getIsPlaying() == true) && (getIsSoundOn() == true))
			astronautSound.play();
		updateGameWorld();
		System.out
				.println("The astronaut fought the alien & the alien won.\nAstronaut takes 1 point of damage.");

	}

	/*A method that animates the gameObjects based on time passed in a timer.
	 * This calls the move method within the Opponents class and causes it's children to move.
	 * If a child collides with another gameObject, it will react based on what kind of child it is.
	 * Collisions only happen once.
	 * To ensure this, two nested loops of collision will take place.*/
	public void tick(int time) {
		Iiterator iter = theGameCollection.getIterator();
		ArrayList<ICollider> collisionVector = new ArrayList<ICollider>(); //
		while (iter.hasNext()) {
			GameObject object = (GameObject) iter.getNext();
			if (object instanceof Opponents) {
				((Opponents) object).move(time);
				System.out.println("An Opponent moved");
				/*ICollider curObj gets the next current ICOllider Object in iter1
				 * Iiterator iter2 gets the next gameCollection object.
				 * While iter2 still has objects, another ICollider object (otherObject) in iter2.
				 * The iterators are seperated to not overlap.
				 * If the current object isn't equal to the other object then we check for collision.
				 * If current object and other object are within a certain distance (oversimplied) of eachother
				 * then they have collided and should handle collision.
				 * Each object should have a list of objects they have already colliding with & Removed when not.
				 * TO do this, there will also be a Vector list of each collidable object [known as the Collision Vector].
				 * When collision occurs, obj2 is added to Collision Vector of obj1 and vise versa.
				 * At each collision detection, if both are no longer colliding, remove them, else, do not add them.
				 * Java's contains() can check if obj is already in collision vector or not, thus, can be used to determine 
				 * if collision handling happens or not.
				 * 
				 * */
				ICollider currentObject = (ICollider) iter.getNext();
				Iiterator iter2 = theGameCollection.getIterator();
				while (iter2.hasNext()) {
					ICollider otherObject = (ICollider) iter2.getNext();
					if (currentObject != otherObject) {
						if (currentObject.collidesWith(otherObject)) {
							if(!collisionVector.contains(otherObject)){
								collisionVector.add(otherObject); //
								currentObject.handleCollision(otherObject);
							}
							System.out.println(currentObject
									+ " has collided with " + otherObject);
						}
						else{
							collisionVector.remove(otherObject);
						}
					}
				}

				updateGameWorld();
			}
		}
		System.out.println("Game has advanced by " + tickTime + " ms = "
				+ tickTime / 1000 + " ticks.");
	}

	public void stats() {
		System.out.println("The score is: " + score
				+ "\nNumber of Astronauts rescused: " + rescuedAstronauts
				+ "\nNumber of Astronauts roaming: " + roamingAstronauts
				+ "\nNumber of Aliens rescued: " + rescuedAliens
				+ "\nNumber of Aliens roaming: " + roamingAliens);
	}

	public void map() {
		Iiterator theIterator = theGameCollection.getIterator();
		while (theIterator.hasNext()) {
			GameObject obj = (GameObject) theIterator.getNext();
			System.out.println(obj);
		}
	}

	public void score() {
		System.out.println("The score is: " + score);
	}

	public void expand() {
		Spaceship sp = getTheSpaceship();
		sp.expandDoor(); // Null pointer exception
		updateGameWorld();
	}

	public void compress() {
		Spaceship sp = getTheSpaceship();
		sp.contractDoor(); // Null pointer exception
		updateGameWorld();
	}

	public void openDoor() {
		System.out.println("The spaceship door has opened.");
		Spaceship sp = getTheSpaceship();
		ArrayList<Integer> remove = new ArrayList<Integer>();
		Iiterator iter = theGameCollection.getIterator();
		while (iter.hasNext()) {
			GameObject object = (GameObject) iter.getNext();
			if (object instanceof Opponents) {
				Opponents o = (Opponents) object;
				double x = Math.abs(sp.getLocation().getX()
						- o.getLocation().getX());
				double y = Math.abs(sp.getLocation().getY()
						- o.getLocation().getY());
				if (x >= 0 && x <= sp.getSize() && y >= 0 && y <= sp.getSize()) {
					rescueAnObject(object);
					remove.add(iter.getIndex());
					System.out.println("You've rescued a " + object);
				}
			}

		}
		fuckingSort(remove);
		for (int i = 0; i < remove.size(); i++) {
			theGameCollection.remove(remove.get(i) - i);
		}
		if (this.isSoundOn)
			if (!doorSound.play())
				System.err.print("Door cannot be played!");
	}

	private void rescueAnObject(GameObject object) {
		// XXX:Gotta confirm this works later.
		if (object instanceof Alien) {
			score -= 10;
			roamingAliens -= 1;
			rescuedAliens += 1;
		} else if (object instanceof Astronaut) {
			score += (5 + ((Astronaut) object).getSpeed());
			roamingAstronauts -= 1;
			rescuedAstronauts += 1;
		}
	}

	private ArrayList<Integer> fuckingSort(ArrayList<Integer> ary) {// XXX:Probably
																	// works but
																	// not 100%
																	// sure
		if (ary.size() <= 1)
			return ary;
		List<Integer> a1 = ary.subList(0, ary.size() / 2);
		List<Integer> a2 = ary.subList(ary.size() / 2, ary.size());
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		for (int i = 0; i < ary.size(); i++) {
			if (a1.size() == 0) {
				sorted.add(a1.get(0));
				a1.remove(0);
			} else if (a2.size() == 0) {
				sorted.add(a2.get(0));
				a2.remove(0);
			} else {
				if (a1.get(0) < a2.get(0)) {
					sorted.add(a1.get(0));
					a1.remove(0);
				} else {
					sorted.add(a2.get(0));
					a2.remove(0);
				}
			}
		}
		return sorted;
	}

	/* Moves the spaceship left */
	public void moveSpaceShipLeft() {
		Spaceship sp = getTheSpaceship();
		sp.moveLeft();
	}

	/* Moves the spaceship right */
	public void moveSpaceShipRight() {
		Spaceship sp = getTheSpaceship();
		sp.moveRight();
	}

	/* Moves the spaceship up */
	public void moveSpaceShipUp() {
		Spaceship sp = getTheSpaceship();
		sp.moveUp();
	}

	/* Moves the spaceship down */
	public void moveSpaceShipDown() {
		Spaceship sp = getTheSpaceship();
		sp.moveDown();
	}

	/* Teleports the spaceship to a random alien */
	public void teleportToAlien() {
		Alien a;
		Spaceship sp;
		if (roamingAliens > 0) {
			sp = getTheSpaceship();
			a = getRandomAlien();
			sp.setLocation(a.getLocation());
			System.out.println("You've teleported to an alien");
		} else
			System.out.println("Error: There were no aliens to jump to.");
	}

	/* Teleports the spaceship to a random astronaut */
	public void teleportToAstronaut() {
		Astronaut a;
		Spaceship sp;
		if (roamingAstronauts > 0) {
			sp = getTheSpaceship();
			a = getRandomAstronaut();
			sp.setLocation(a.getLocation());
			System.out
					.println("You've teleported to an astronaut. \n Hope you didn't hit them.");
		} else
			System.out.println("Error: Ther were no astronauts to jump to.");
	}

	private Alien getRandomAlien() {
		if (roamingAliens > 0) {
			while (true) {
				int[] alienPositions = new int[roamingAliens];
				int pos = 0;
				Iiterator iter = theGameCollection.getIterator();
				while (iter.hasNext()) {
					GameObject gObject = (GameObject) iter.getNext();
					if (gObject instanceof Alien) {
						alienPositions[pos] = iter.getIndex();
						pos++;
					}
				}
				return (Alien) (theGameCollection.get(alienPositions[random
						.nextInt(roamingAliens)]));
			}
		}
		return null;
	}

	private Spaceship getTheSpaceship() {
		Iiterator iter = theGameCollection.getIterator();
		while (iter.hasNext()) {
			GameObject object = (GameObject) iter.getNext();
			if (object instanceof Spaceship)
				return (Spaceship) object;
		}
		return null;
	}

	private Astronaut getRandomAstronaut() {
		if (roamingAstronauts > 0) {
			while (true) {
				int[] astronautPositions = new int[roamingAstronauts];
				int pos = 0;
				Iiterator iter = theGameCollection.getIterator();
				while (iter.hasNext()) {
					GameObject gObject = (GameObject) iter.getNext();
					if (gObject instanceof Astronaut) {
						astronautPositions[pos] = iter.getIndex();
						pos++;
					}
				}
				return (Astronaut) (theGameCollection
						.get(astronautPositions[random
								.nextInt(roamingAstronauts)]));
			}
		}
		return null;
	}

	public int getTheGameCollectionSize() {
		return theGameCollection.getSize();
	}

	public GameCollection getGameCollection() {
		return theGameCollection;
	}

	public int getRoamingAliens() {
		return roamingAliens;
	}

	public void setRoamingAliens(int value) {
		roamingAliens = value;
	}

	public int getRoamingAstronauts() {
		return roamingAstronauts;
	}

	public void setRoamingAstronauts(int value) {
		roamingAstronauts = value;
	}

	public int getStartHeight() {
		return startHeight;
	}

	public int getStartWidth() {
		return endWidth;
	}

	public int getEndWidth() {
		return endWidth;
	}

	public int getEndHeight() {
		return endHeight;
	}

	public int getRescuedAstronauts() {
		return rescuedAstronauts;
	}

	public void setRescuedAstronauts(int value) {
		rescuedAstronauts = value;
	}

	public int getRescuedAliens() {
		return rescuedAliens;
	}

	public void setRescuedAliens(int value) {
		rescuedAliens = value;
	}

	public int getScore() {
		return score;
	}

	public void setStartWidth(int width) {
		this.startWidth = width;
	}

	public void setStartHeight(int height) {
		this.startHeight = height;
	}

	public void setEndWidth(int width) {
		this.endWidth = width;
	}

	public void setEndHeight(int height) {
		this.endHeight = height;
	}

	public void setScore(int value) {
		score = value;
	}

	public boolean getIsSoundOn() {
		return isSoundOn;
	}

	public void setIsSoundOn() {
		isSoundOn = !isSoundOn;
	}

	public boolean getIsPlaying() {
		return isPlaying;
	}

	public void setIsPlaying() {
		isPlaying = !isPlaying;
	}

	public void pauseSound() {
		if (getIsPlaying() == false) {
			bgMusic.pause();
			alienSound.pause();
			astronautSound.pause();
			doorSound.pause();
		} else {
			bgMusic.resume();
			alienSound.resume();
			astronautSound.resume();
			doorSound.resume();
		}
	}

	public void muteSound() {
		if (getIsSoundOn() == false) {
			bgMusic.pause();
			alienSound.pause();
			astronautSound.pause();
			doorSound.pause();
		} else {
			bgMusic.play();
		}
	}

	public void addObserver(Observer o) {
		myObserverList.add(o);
	}

}
