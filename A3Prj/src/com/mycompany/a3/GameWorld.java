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
	private boolean debug = true;
	private GameCollection theGameCollection;
	private Vector<Observer> myObserverList;
	private Sound sound;
	private BGsound bgMusic = new BGsound("music.wav", this);
	private Sound alienSound = new Sound("alien.wav", this);
	private Sound astronautSound = new Sound("astro.wav", this);
	private Sound doorSound = new Sound("door.wav", this);

	public BGsound getBgMusic() {
		return bgMusic;
	}

	public void setBgMusic(BGsound bgMusic) {
		this.bgMusic = bgMusic;
	}

	public Sound getSound() {
		return sound;
	}

	public void setSound(Sound sound) {
		this.sound = sound;
	}

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
		System.out.println("Game Collection size: "
				+ getTheGameCollectionSize());
		initialSpawn();
		addSpaceship();
		bgMusic.play();
		updateGameWorld();
		System.out.println("Game Collection size: "
				+ getTheGameCollectionSize());
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
		this.debug();
	}

	public void bred() { // XXX fucking hell
		System.out.println("BREEDDDDD");
		Alien a = getRandomAlien();
		Alien b = getAlienChild();
		getBabyLocation(a, b);
		if (getIsSoundOn() && getIsPlaying()) {
			setSound(alienSound);
			getSound().play();
		}
		updateGameWorld();
	}

	public Alien getAlienChild() {
		Alien b = new Alien(ColorUtil.BLACK, screenHeight, screenWidth, speed,
				speedMulti);
		System.out.println(b);
		setRoamingAliens(getRoamingAliens() + 1);
		System.out.println("Roaming alien total: " + getRoamingAliens());
		updateGameWorld();
		return b;
	}

	public void getBabyLocation(Alien a, Alien b) {
		double x = a.getLocation().getX() + 40;
		double y = a.getLocation().getY() + 40;
		Point2D p = new Point2D(x, y);
		b.setLocation(p);
		getGameCollection().add(b);
		System.out.println("Two aliens bred.");
		updateGameWorld();
	}

	public void fight(Astronaut a) {
		a.damage();
		if ((getIsPlaying() == true) && (getIsSoundOn() == true)) {
			astronautSound.play();
			System.out
					.println("The astronaut fought the alien & the alien won.\nAstronaut takes 1 point of damage.");
		}
		updateGameWorld();
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
	 * other object are within a certain distance (oversimplied) of eachother
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
		Iiterator iter = theGameCollection.getIterator();
		System.out.println("TICKER TICKER TICKER TICKER");
		System.out.println("theGameCollection size "
				+ getTheGameCollectionSize());
		System.out.println("iter index: " + iter.getIndex());
		ArrayList<ICollider> collisionVectorObj1 = new ArrayList<ICollider>(); //
		ArrayList<ICollider> collisionVectorObj2 = new ArrayList<ICollider>(); //
		while (iter.hasNext()) {
			GameObject object = (GameObject) iter.getNext();
			if (object instanceof Opponents) {
				System.out.println("An Opponent moved");
				((Opponents) object).move(time);
				ICollider currentObject = (ICollider) iter.getNext();
				Iiterator iter2 = theGameCollection.getIterator();
				System.out.println("INSIDE TICK METHOD");
				System.out.println("theGameCollection size "
						+ getTheGameCollectionSize());
				System.out.println("iter index: " + iter2.getIndex());
				bred();
				// while (iter2.hasNext()) {
				// ICollider otherObject = (ICollider) iter2.getNext();
				// if (currentObject != otherObject) {
				// System.out.println("CurrentObject != OtherObject");
				// System.out.println("theGameCollection size "
				// + getTheGameCollectionSize());
				// if (currentObject.collidesWith(otherObject)) {
				// if (!collisionVectorObj1.contains(currentObject)
				// || !collisionVectorObj2
				// .contains(otherObject)) {
				// collisionVectorObj1.add(otherObject); //
				// collisionVectorObj2.add(currentObject);
				// collisionString(currentObject, otherObject);
				// System.out.println("theGameCollection size "
				// + getTheGameCollectionSize());
				// currentObject.handleCollision(otherObject);
				// System.out.println(collisionVectorObj1
				// .toArray());
				// System.out.println(collisionVectorObj2
				// .toArray());
				// }
				// } else {
				// collisionVectorObj1.remove(otherObject);
				// collisionVectorObj2.remove(currentObject);
				// System.out.println("Collision Vector1 has removed "
				// + otherObject);
				// System.out.println("Collision Vector2 has removed "
				// + currentObject);
				// System.out.println("theGameCollection size "
				// + getTheGameCollectionSize());
				// updateGameWorld();
				// }
				// } else {
				// System.out.println("CurrentObject == OtherObject");
				// }
				// }
				updateGameWorld();
			}
		}
		System.out.println("<<<<<<<<END OF TICKMETHOD>>>>>>>>>>");
		System.out.println("Game has advanced by " + tickTime + " ms = "
				+ tickTime / 1000 + " ticks.");
	}

	public void collisionString(ICollider currentObject, ICollider otherObject) {
		String curObj = "";
		String otherObj = "";
		System.out.println("CUR OBJ: " + currentObject.toString());
		System.out.println("OTHER OBJ: " + otherObject.toString());
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
		System.out.println("Collision Vector1 has added " + otherObject);
		System.out.println("Collision Vector2 has added " + currentObject);
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
			if (getRoamingAstronauts() <= 0 || roamingAliens <= 0) {
				System.out
						.println("Error: Need at least 1 astronaut & alien to fight.");
				return;
			}
		}
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
																	// works
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

	public Alien getRandomAlien() {
		Iiterator iter = getGameCollection().getIterator();
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
					System.out.println("Alien Positions "
							+ alienPositions.length);
					if (pos == alienPositions.length) {
						System.out.println("BREAK OUT");
						break;
					}
				}
				System.out.println("ESCAPED THE WHILE LOOP");
				System.out
						.println((Alien) (theGameCollection
								.get(alienPositions[random
										.nextInt(getRoamingAliens())])));
				return (Alien) (theGameCollection.get(alienPositions[random
						.nextInt(getRoamingAliens())]));
			}
		}
		return null;
	}

	private Spaceship getTheSpaceship() {
		Iiterator iter = theGameCollection.getIterator();
		System.out.println("Game Collection size: "
				+ getTheGameCollectionSize());
		while (iter.hasNext()) {
			GameObject object = (GameObject) iter.getNext();
			if (object instanceof Spaceship)
				return (Spaceship) object;
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
				return (Astronaut) (theGameCollection
						.get(astronautPositions[random
								.nextInt(getRoamingAstronauts())]));
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
