package com.mycompany.a3;

import com.codename1.ui.geom.Point2D;
//import java.util.Hashtable;
//import java.util.Random;

public abstract class Opponents extends GameObject implements Imove {

	// private int size;
	private int speed;
	private int direction;
	private int speedMultiplier;

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
		setDirection(getDirection() + 5);
		System.out.println("MOVE IS CALLED");
		System.out.println(getLocation());
		double time = tickTime;
		double distance = getSpeed() * time;
		int direction = getDirection();
		double radDirection = direction * (Math.PI / 180.0);
		double deltaX = Math.cos(radDirection) * distance;
		double deltaY = Math.sin(radDirection) * distance;
		System.out.println("Direction: " + direction);
		System.out.println("Radians: " + radDirection);
		System.out.println("deltaX: " + deltaX);
		System.out.println("deltaY: " + deltaY);
		System.out.println("speed " + speed);
		System.out.println("speedMult " + speedMultiplier);
		System.out.println("effectiveSpeed " + getSpeed());
		System.out.println("time " + time);
		System.out.println("totalDist " + distance);

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
		System.out.println(newLoc);
		setLocation(newLoc);
	}

	public String toString() {
		String direct = "" + direction;
		while (direct.length() < 3) {
			direct = "0" + direct;
		}
		return super.toString() + "\tspeed=\t" + speed + "\tdir=\t" + direct;
	}
}