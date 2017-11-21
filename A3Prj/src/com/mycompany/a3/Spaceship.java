package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

/*Probably want to make a singleton later on.*/
public class Spaceship extends Rescuers {

	private static Spaceship spaceship;
	private boolean debug = false;

	private Spaceship(int color, int mapViewHeight, int mapViewWidth) {
		Random r = new Random();
		super.setColor(color);
		setScreenHeight(mapViewHeight);
		setScreenWidth(mapViewWidth);
		setLocation(new Point2D(r.nextDouble() * getScreenWidth(),
				r.nextDouble() * getScreenHeight()));
		setSize(50);
		if (debug) {
			System.err.println("x: " + getLocation().getX());
			System.err.println("y: " + getLocation().getY());
			System.err.println("Ss screenHeight: " + getScreenHeight());
			System.err.println("Ss screenWidth: " + getScreenWidth());
		}
	}

	public static Spaceship getSpaceship(int color, int mapViewHeight,
			int mapViewWidth) {
		if (spaceship == null) {
			spaceship = new Spaceship(color, mapViewHeight, mapViewWidth);
		}
		return spaceship;
	}

	/* Spaceship color doesn't change. */
	public void setColor(int color) {

	}

	/* Contracts the door (spaceship) size by 10 */
	public void contractDoor() {
		setSize(getSize() - 10);
		System.out.println("You compressed the spaceship size to " + getSize());
	}

	/* Expands the door (spaceship) size by 10 */
	public void expandDoor() {
		setSize(getSize() + 10);
		System.out.println("You expanded the spaceship size to " + getSize());
	}

	/*
	 * Override the default size method to allow the spaceship to be much
	 * bigger.
	 */
	@Override
	public void setSize(int size) {
		if (size < 50)
			size = 50;
		else if (size > 1020)
			size = 1020;
		super.setSize(size);
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLoc = pCmpRelPrnt.getX() + (int) getLocation().getX();
		int yLoc = pCmpRelPrnt.getY() + (int) getLocation().getY();
		g.fillRect(xLoc - (getSize() / 2), yLoc - (getSize() / 2), getSize(),
				getSize());
	}

	public boolean collidesWith(ICollider otherObject) {
		// TODO Auto-generated method stub
		return false;
	}

	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub

	}
}
