package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

/*Probably want to make a singleton later on.*/
public class Spaceship extends Rescuers {

	private static Spaceship spaceship;
	private static int color;
	private static int screenHeight;
	private static int screenWidth;

	private Spaceship(int color, int screenHeight, int screenWidth) {
		Random r = new Random();
		super.setColor(color);
		setLocation(new Point2D(r.nextDouble() * screenWidth, r.nextDouble()
				* screenHeight));
		setSize(50);
		setScreenHeight(screenHeight);
		setScreenWidth(screenWidth);
	}

	public static Spaceship getSpaceship() {
		if (spaceship == null) {
			spaceship = new Spaceship(color, screenHeight, screenWidth);
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
		// g.setColor(this.getColor());
		g.setColor(ColorUtil.GREEN);
		// shape location relative
		// to parent’s origin
		int xLoc = pCmpRelPrnt.getX() + (int) getLocation().getX();
		int yLoc = pCmpRelPrnt.getY() + (int) getLocation().getY();
		g.fillRect(xLoc - (getSize() / 2), yLoc - (getSize() / 2), getSize(),
				getSize());
		// g.fillTriangle(xLoc-20, yLoc-40, xLoc+20, yLoc-40, xLoc, yLoc+40);
	}

	public boolean collidesWith(ICollider otherObject) {
		// TODO Auto-generated method stub
		return false;
	}

	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub

	}
}
