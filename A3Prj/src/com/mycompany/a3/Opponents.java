package com.mycompany.a3;

import com.codename1.ui.geom.Point2D;
import java.util.Random;
//import java.util.Hashtable;
//import java.util.Random;

public abstract class Opponents extends GameObject implements Imove {

	// private int size;
	private int speed;
	private int direction;
	private int speedMultiplier;
	private GameWorld gw;
	private final boolean debug = false;
	private final boolean debugCollision = true;

	public Opponents(GameWorld gw) {
		this.gw = gw;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction % 360;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSpeedMultiplier() {
		return speedMultiplier;
	}

	public void setSpeedMultiplier(int speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}

	/*
	 * This method handles the move logic for all opponenets. Initially sets the
	 * direction they are going in, the timer they move at, distance (calculated
	 * by the speed * time they move at, & deltas for x & y. When this method is
	 * called, a new Point2D location is created with current location x,y +
	 * deltax,y As long as the location is within the screen & isn't a corner,
	 * they move there.
	 */
	public void move(int tickTime) {
		Random r = new Random();
		setDirection(getDirection() + r.nextInt(11) - 5);
		if(debug)System.out.println("GET THE DIRECTION OF THE MOVE: "+ getDirection());
		//133 - 250 - 360 - 246 - 152
		// System.out.println("MOVE IS CALLED");
		// System.out.println(getLocation());
		double time = tickTime/100;
		double distance = getSpeed() * time;
		int direction = getDirection();
		double radDirection = direction * (Math.PI / 180.0);
		double deltaX = Math.cos(radDirection) * distance;
		double deltaY = Math.sin(radDirection) * distance;
		if(debug){
			System.out.println("Direction: " + direction);
			System.out.println("Radians: " + radDirection);
			System.out.println("deltaX: " + deltaX);
			System.out.println("deltaY: " + deltaY);
			System.out.println("speed " + speed);
			System.out.println("speedMult " + speedMultiplier);
			System.out.println("effectiveSpeed " + getSpeed());
			System.out.println("time " + time);
			System.out.println("totalDist " + distance);
		}
		Point2D newLoc = new Point2D(getLocation().getX() + deltaX,
				getLocation().getY() + deltaY);

		if (newLoc.getX() < 0 || newLoc.getX() > getScreenWidth()
				|| newLoc.getY() < 0 || newLoc.getY() > getScreenHeight()) {
			setDirection(getDirection() + 15);
			if (deltaX > getScreenWidth()) {
				deltaX -= getScreenWidth();
				newLoc.setX(getScreenWidth());
			} else if (newLoc.getX() < 0) {
				deltaX *= -1;
				newLoc.setX(0);
			} else
				deltaX = 0;

			if (deltaY > getScreenHeight()) {
				deltaY -= getScreenHeight();
				newLoc.setY(getScreenHeight());
			} else if (deltaY < 0) {
				deltaY *= -1;
				newLoc.setY(0);
			} else
				deltaY = 0;

			deltaX = Math.cos(getDirection() * (Math.PI / 180.0)) * deltaX;
			deltaY = Math.sin(getDirection() * (Math.PI / 180.0)) * deltaY;

			newLoc = new Point2D(newLoc.getX() + deltaX, newLoc.getY() + deltaY);
		}
		// System.out.println(newLoc);
		setLocation(newLoc);
	}

	/*
	 * #XXX Radius check the two objects to check if they are colliding.
	 */
	public boolean collidesWith(ICollider obj) {
		boolean result = false;
		int xLoc = (int) getLocation().getX();
		int yLoc = (int) getLocation().getY();
		int otherLocX = (int) ((GameObject) obj).getLocation().getX();
		int otherLocY = (int) ((GameObject) obj).getLocation().getY();

		int dx = Math.abs(xLoc - otherLocX);
		int dy = Math.abs(yLoc - otherLocY);
		if(dx <= 20 && dy <= 20){
			result = true;
		}
		return result;
	}

	public String toString() {
		String direct = "" + direction;
		while (direct.length() < 3) {
			direct = "0" + direct;
		}
		return super.toString() + "\tspeed=\t" + speed + "\tdir=\t" + direct;
	}

	/*
	 * #TODO: Moving objects should consider consider the width and height of
	 * the mv in move(). TODO: When two objects collide, handle the
	 * handleCollision should only happen once.
	 */
	public void handleCollision(ICollider otherObject) {
		if(debugCollision){
			System.out.println("Collision is being handled");
			System.out.println("------------------");
		}
		
		if (otherObject instanceof Alien) {
			if(debugCollision)System.out.println("Alien collision occurs");
			 Alien ali = (Alien) otherObject;
			 gw.setParent(ali);
			 gw.bred();
		} else if (otherObject instanceof Astronaut) {
			if(debugCollision)System.out.println("Astronaut collision occurs");
			Astronaut astro = (Astronaut) otherObject;
			gw.fight(astro);
		}
		if(debugCollision){
			System.out.println("------------------");
			System.out.println("Collision was handled");
		}
	}
}