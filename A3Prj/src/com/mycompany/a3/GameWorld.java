package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;

import java.util.ArrayList;
import java.util.Arrays;
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
	private int mapViewWidth;
	private int mapViewHeight;
	private Random random;
	private String statsString = "";
	private boolean isPlaying = true;
	private boolean isSoundOn = true;
	private final boolean debug = false;
	private final boolean debugErr = false;
	private GameCollection theGameCollection;
	private Vector<Observer> myObserverList;
	private Alien parent = null;
	private Sound sound;
	private Game g;
	private MapView mv;
	private BackgroundSound bgMusic = new BackgroundSound("music.wav", this);
	private Sound astronautSound = new Sound("astro.wav", this);
	private Sound alienSound = new Sound("alien.wav", this);
	private Sound doorSound = new Sound("door.wav", this);
	private ArrayList<ICollider> collisionVectorObj1 = new ArrayList<ICollider>();
	private ArrayList<ICollider> collisionVectorObj2 = new ArrayList<ICollider>();

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
		mapViewWidth = 814; // XXX HardCoded
		mapViewHeight = 501;// XXX HardCoded
	}

	public void init(Game g) {
		this.g = g;
		initialSpawn();
		addSpaceship();
		bgMusic.play();
		updateGameWorld();
	}

	public void debug() {
		if (debug) {
			if (getTheGameCollectionSize() == 0) {
				System.out
						.println("WOAH, what happened to your collection buddy?");
			}
			if (getRoamingAliens() < 2) {
				System.out.println("Error: Requires two aliens!");
				return;
			}
			if (getRoamingAstronauts() <= 0 || getRoamingAliens() <= 0) {
				System.out
						.println("Error: Need at least 1 astronaut & alien to fight.");
				return;
			}
		}
	}

	public void spawnString() {
		if (debug)
			System.out.println("Game Collection Size: "
					+ theGameCollection.getSize());
	}

	/*
	 * XXX This is where everything begins. Set the initial state of the game
	 */

	public void initialSpawn() {
		for (int i = 0; i < getRoamingAliens(); ++i) {
			addAlien();
		}
		for (int i = 0; i < getRoamingAstronauts(); ++i) {
			addAstro();
		}
	}

	public void addAlien() {
		theGameCollection.add((GameObject) new Alien(ColorUtil.MAGENTA,
				getMapViewHeight(), getMapViewWidth(), speed, speedMulti, this,
				false));
		spawnString();
		updateGameWorld();
	}

	public void addAstro() {
		theGameCollection
				.add((GameObject) new Astronaut(ColorUtil.GREEN,
						getMapViewHeight(), getMapViewWidth(), speed,
						speedMulti, this));
		spawnString();
		updateGameWorld();
	}

	public void addSpaceship() {
		theGameCollection.add((Spaceship.getSpaceship(ColorUtil.LTGRAY,
				getMapViewHeight(), getMapViewWidth())));
		updateGameWorld();
	}

	private Spaceship getTheSpaceship() {
		Iiterator iter = theGameCollection.getIterator();
		spawnString();
		while (iter.hasNext()) {
			GameObject object = (GameObject) iter.getNext();
			if (object instanceof Spaceship)
				return (Spaceship) object;
		}
		return null;
	}

	public void updateGameWorld() {
		this.setChanged();
		this.notifyObservers();
		this.clearChanged();
		this.debug();
	}

	/*
	 * #XXX: Insert a way to end the game here.
	 */
	public void gameOverCheck() {
		if (getRoamingAstronauts() <= 0) {
			g.gameOver();
		}
	}

	/*
	 * A method that animates the gameObjects based on time passed in a timer.
	 * This calls the move method within the Opponents class and causes it's
	 * children to move. If a child collides with another gameObject, it will
	 * react based on what kind of child it is. Collisions only happen once. To
	 * ensure this, two nested loops of collision will take place.
	 */

	/*
	 * ICollider curObj gets the next current ICOllider Object in iter1
	 * Iiterator iter2 gets the next gameCollection object. While iter2 still
	 * has objects, another ICollider object (otherObject) in iter2. The
	 * iterators are seperated to not overlap. If the current object isn't equal
	 * to the other object then we check for collision. If current object and
	 * other object are within a certain distance (oversimplied) of each other
	 * then they have collided and should handle collision. Each object should
	 * have a list of objects they have already colliding with & Removed when
	 * not. TO do this, there will also be a Vector list of each collidable
	 * object [known as the Collision Vector]. When collision occurs, obj2 is
	 * added to Collision Vector of obj1 and vise versa. At each collision
	 * detection, if both are no longer colliding, remove them, else, do not add
	 * them. Java's contains() can check if obj is already in collision vector
	 * or not, thus, can be used to determine if collision handling happens or
	 * not.
	 */
	public void tick(int time) {
		Iiterator collectionIterator = theGameCollection.getIterator();
		if (debug)
			System.out.println("Ticker is called");
		spawnString();
		collisionVectorObj1 = new ArrayList<ICollider>(); //
		collisionVectorObj2 = new ArrayList<ICollider>();
		gameOverCheck();
		if (debug)
			System.out.println("iter index: " + collectionIterator.getIndex());
		while (collectionIterator.hasNext()) {
			GameObject object = (GameObject) collectionIterator.getNext();

			if (debug)
				System.out.println(object.toString());
			if (object instanceof Opponents) {
				if (debug) {
					System.out.println("An Opponent moved");
				}
				// System.out.println("Again i repeat");
				// System.out.println(object.toString());
				((Opponents) object).move(time);
				updateGameWorld();
			}
		}
		ArrayList<ICollider> a1 = new ArrayList<ICollider>();
		ArrayList<ICollider> a2 = new ArrayList<ICollider>();
		Iiterator firstIt = getGameCollection().getIterator();
		Iiterator secondIt = getGameCollection().getIterator();
		while (firstIt.hasNext()) {
			a1.add((ICollider) firstIt.getNext());
		}
		while (secondIt.hasNext()) {
			a2.add((ICollider) secondIt.getNext());
		}
		for (ICollider ic1 : a1) {
			for (ICollider ic2 : a2) {
				collisionManager(ic1, ic2);
			}
		}

		if (debug)
			System.out.println("Ticker method ends");
		if (debug)
			System.out.println("Game has advanced by " + tickTime + " ms = "
					+ tickTime / 1000 + " ticks.");
	}

	/* A Tick helper method that handles all the collision between two objects. */
	/*
	 * TODO rerite This if statement checks to see if either alien was recently
	 * spawned & preventing collision if so. Comment out this if/else statement
	 * to see animation
	 */
	public void collisionManager(ICollider currentObject, ICollider otherObject) {
		if (currentObject != otherObject) {
			if (debug)
				System.out.println("CurrentObject != OtherObject");
			if (currentObject.collidesWith(otherObject)) {
				if (!collisionVectorObj1.contains(currentObject)
						&& !collisionVectorObj2.contains(otherObject)) {
					collisionVectorObj1.add(otherObject);
					collisionVectorObj2.add(currentObject);
					collisionString(currentObject, otherObject, true);
					currentObject.handleCollision(otherObject);
					if (debug)
						System.out.println(Arrays.toString(collisionVectorObj1
								.toArray()));
					if (debug)
						System.out.println(Arrays.toString(collisionVectorObj2
								.toArray()));
					updateGameWorld();
				}
			} else {
				collisionVectorObj1.remove(otherObject);
				collisionVectorObj2.remove(currentObject);
				collisionString(currentObject, otherObject, false);
				updateGameWorld();
			}
		} else {
			if (debug) {
				System.out.println("CurrentObject == OtherObject");
			}
		}
	}

	/*
	 * This method provides valuable information about the results of collisions
	 * in the Tick method while debug mode is on.
	 */
	public void collisionString(ICollider currentObject, ICollider otherObject,
			boolean add) {
		if (debug) {
			String curObj = "";
			String otherObj = "";
			System.out.println("CUR OBJ: " + currentObject.toString());
			System.out.println("OTHER OBJ: " + otherObject.toString());
			if (add == true) {
				if (currentObject instanceof Alien) {
					curObj = "Alien";
				} else if (currentObject instanceof Astronaut) {
					curObj = "Astronaut";
				} else {
					curObj = "Spaceship";
				}
				if (otherObject instanceof Alien) {
					otherObj = "Alien";
				} else if (otherObject instanceof Astronaut) {
					otherObj = "Astronaut";
				} else {
					otherObj = "Spaceship";
				}
				System.out.println("<<CollisionString>>");
				System.out.println(curObj + " has collided with " + otherObj);
				System.out
						.println("Collision Vector1 has added " + otherObject);
				System.out.println("Collision Vector2 has added "
						+ currentObject);
			} else if (add == false) {
				System.out.println("Collision Vector1 has removed "
						+ otherObject);
				System.out.println("Collision Vector2 has removed "
						+ currentObject);
				System.out.println("theGameCollection size "
						+ getTheGameCollectionSize());
			}
		}
		updateGameWorld();
	}

	/*
	 * This method selects an alien and then spawns a new alien next to its
	 * position. #XXX This bred method has been the source of most of the issues
	 * in this game. Most collision stuff seems to over do the bred method.
	 * 
	 * The bred method checks if a spawned alien has a parent, (a colliding
	 * alien) or should spawn by a random 'guardian' alien.
	 */
	public void bred() {
		if (debug) {
			System.out.println("Bred method is called");
		}
		if (getRoamingAliens() < 30) {
			if (getParent() != null) {
				Alien a = getParent();
				if (debug)
					System.out.println("The parent is found");
				Alien b = spawnAlienChild();
				if (debug)
					System.out.println("The child is spawned");
				setBabyLocation(a, b);
			} else {
				Alien a = getRandomAlien();
				if (debug)
					System.out
							.println("Guardian has been provided for the orphaned child");
				Alien b = spawnAlienChild();
				if (debug)
					System.out.println("The child is spawned");
				setBabyLocation(a, b);
			}
			if (debug)
				System.out.println("Spawn process is done");
			if (getIsSoundOn() && getIsPlaying()) {
				setSound(alienSound);
				getSound().play();
			}
		}
		updateGameWorld();
	}

	/*
	 * An alien helper method that spawns a new Alien and updates the amount of
	 * roaming aliens
	 */
	public Alien spawnAlienChild() {
		theGameCollection.add((GameObject) new Alien(ColorUtil.BLACK,
				getMapViewHeight(), getMapViewWidth(), speed, speedMulti, this,
				true));
		Alien b = (Alien) theGameCollection.get(getTheGameCollectionSize() - 1);
		System.out.println(b);
		setRoamingAliens(getRoamingAliens() + 1);
		System.out.println("Roaming alien total: " + getRoamingAliens());
		updateGameWorld();
		return b;
	}

	/*
	 * An alien helper method that takes the newly spawned alien and gives it a
	 * location near its parent
	 */
	public void setBabyLocation(Alien a, Alien b) {
		double x = a.getLocation().getX() + 40;
		double y = a.getLocation().getY() + 40;
		Point2D p = new Point2D(x, y);
		b.setLocation(p);
		getGameCollection().add(b);
		if (debug)
			System.out.println("Two aliens bred.");
		updateGameWorld();
	}

	/*
	 * This method reacts from colliding aliens with astronauts, decreasing the
	 * astronaut's health and speed.
	 */
	public void fight(Astronaut a) {
		if (debug)
			System.out.println("Alien speed before hand " + a.getSpeed());
		a.damage();
		if (debug)
			System.out.println("Astronaut takes 1 point of damage.");
		if (debug)
			System.out.println("Astronaut health: " + a.getSpeed());
		if ((getIsPlaying() == true) && (getIsSoundOn() == true)) {
			astronautSound.play();
		}
		updateGameWorld();
	}

	public void healAstro(Astronaut a) {
		a.heal();
	}

	public void stats() {
		System.out.println("The score is: " + getScore()
				+ "\nNumber of Astronauts rescused: " + getRescuedAstronauts()
				+ "\nNumber of Astronauts roaming: " + getRoamingAstronauts()
				+ "\nNumber of Aliens rescued: " + getRescuedAliens()
				+ "\nNumber of Aliens roaming: " + getRoamingAliens());
		statsString = "The score is: " + getScore()
				+ "\nNumber of Astronauts rescused: " + getRescuedAstronauts()
				+ "\nNumber of Astronauts roaming: " + getRoamingAstronauts()
				+ "\nNumber of Aliens rescued: " + getRescuedAliens()
				+ "\nNumber of Aliens roaming: " + getRoamingAliens();
		setStats(statsString);
	}

	public String getStats() {
		return statsString;
	}

	public void setStats(String statsString) {
		this.statsString = statsString;
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
		if (debug)
			System.out.println("The spaceship door has opened.");
		Spaceship sp = getTheSpaceship();
		ArrayList<Integer> remove = new ArrayList<Integer>();
		Iiterator iter = theGameCollection.getIterator();
		while (iter.hasNext()) {
			GameObject object = (GameObject) iter.getNext();
			if (object instanceof Opponents) {
				Opponents o = (Opponents) object;
				double spaceXPosition = Math.abs(sp.getLocation().getX()
						- o.getLocation().getX());
				double spaceYPosition = Math.abs(sp.getLocation().getY()
						- o.getLocation().getY());
				if (spaceXPosition >= 0 && spaceXPosition <= sp.getSize()
						&& spaceYPosition >= 0
						&& spaceYPosition <= sp.getSize()) {
					rescueAnObject(object);
					remove.add(iter.getIndex());
					System.out.println("You've rescued a " + object);
				}
			}

		}
		simpleMergeSort(remove);
		for (int i = 0; i < remove.size(); i++) {
			theGameCollection.remove(remove.get(i) - i);
		}
		if (this.isSoundOn)
			if (!doorSound.play())
				if (debugErr)
					System.err.print("Door cannot be played!");
	}

	private void rescueAnObject(GameObject object) {
		if (object instanceof Alien) {
			setScore(getScore() - 10);
			setRoamingAliens(getRoamingAliens() - 1);
			setRescuedAliens(getRescuedAliens() + 1);
		} else if (object instanceof Astronaut) {
			setScore(getScore() + (5 + ((Astronaut) object).getSpeed()));
			setRoamingAstronauts(getRoamingAstronauts() - 1);
			setRescuedAstronauts(getRescuedAstronauts() + 1);
		}
		updateGameWorld();
		System.out.println("SOMETHING WAS DECREASED");
	}

	/*
	 * Since Codename One overrides Collections.sort, I implemented my own
	 * version of the Merge Sort to conduct sorting for the openDoor method.
	 */
	private ArrayList<Integer> simpleMergeSort(ArrayList<Integer> ary) {
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
		if (getRoamingAliens() > 0) {
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
		if (getRoamingAstronauts() > 0) {
			sp = getTheSpaceship();
			a = getRandomAstronaut();
			sp.setLocation(a.getLocation());
			System.out
					.println("You've teleported to an astronaut. \n Hope you didn't hit them.");
		} else
			System.out.println("Error: Ther were no astronauts to jump to.");
	}

	/*
	 * An alien helper method that returns a random alien from the game
	 * collection
	 */
	public Alien getRandomAlien() {
		Iiterator iter = getGameCollection().getIterator();
		spawnString();
		if (debug)
			System.out.println(getRoamingAliens());
		if (getRoamingAliens() > 0) {
			while (true) {
				int[] alienPositions = new int[getRoamingAliens()];
				int pos = 0;
				System.out.println("Current Index is : " + pos);
				System.out
						.println("Alien Positions : " + alienPositions.length);
				while (iter.hasNext()) {
					GameObject gObject = (GameObject) iter.getNext();
					if (gObject instanceof Alien) {
						alienPositions[pos] = iter.getIndex();
						pos++;
						System.out.println("Current Index is : " + pos);
					}
					if (pos == alienPositions.length - 1) {
						System.out.println("BREAK OUT");
						break;
					}
				}
				spawnString();
				return (Alien) (theGameCollection.get(alienPositions[random
						.nextInt(getRoamingAliens())]));
			}
		}
		return null;
	}

	private Astronaut getRandomAstronaut() {
		if (getRoamingAstronauts() > 0) {
			while (true) {
				int[] astronautPositions = new int[getRoamingAstronauts()];
				int pos = 0;
				Iiterator iter = theGameCollection.getIterator();
				while (iter.hasNext()) {
					GameObject gObject = (GameObject) iter.getNext();
					if (gObject instanceof Astronaut) {
						astronautPositions[pos] = iter.getIndex();
						pos++;
					}
				}
				spawnString();
				return (Astronaut) (theGameCollection
						.get(astronautPositions[random
								.nextInt(getRoamingAstronauts())]));
			}
		}
		return null;
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

	/* A long list of getters and setters */
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
		updateGameWorld();
	}

	public int getRoamingAstronauts() {
		return roamingAstronauts;
	}

	public void setRoamingAstronauts(int value) {
		roamingAstronauts = value;
		updateGameWorld();
	}

	public int getRescuedAstronauts() {
		return rescuedAstronauts;
	}

	public void setRescuedAstronauts(int value) {
		rescuedAstronauts = value;
		updateGameWorld();
	}

	public int getRescuedAliens() {
		return rescuedAliens;
	}

	public void setRescuedAliens(int value) {
		rescuedAliens = value;
		updateGameWorld();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int value) {
		score = value;
		updateGameWorld();
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

	public BackgroundSound getBgMusic() {
		return bgMusic;
	}

	public void setBgMusic(BackgroundSound bgMusic) {
		this.bgMusic = bgMusic;
	}

	public Sound getSound() {
		return sound;
	}

	public void setSound(Sound sound) {
		this.sound = sound;
	}

	public Alien getParent() {
		return parent;
	}

	public void setParent(Alien parent) {
		this.parent = parent;
	}

	/*-----------------------------------*/

	public int getMapViewWidth() {
		return mapViewWidth;
	}

	public void setMapViewWidth(int mapViewWidth) {
		this.mapViewWidth = mapViewWidth;
	}

	public int getMapViewHeight() {
		return mapViewHeight;
	}

	public void setMapViewHeight(int mapViewHeight) {
		this.mapViewHeight = mapViewHeight;
	}
}
