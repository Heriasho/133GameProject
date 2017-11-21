package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

import java.util.Random;

public class Astronaut extends Opponents implements ISelectable {
	private int originalSpeed;
	private boolean selected = false;

	public Astronaut(int color, int screenHeight, int screenWidth, int speed,
			int speedMultiplier, GameWorld gw) {
		super(gw);
		Random r = new Random();
		this.originalSpeed = speed;
		setName("Astronaut");
		setColor(color);
		setScreenHeight(screenHeight);
		setScreenWidth(screenWidth);
		setSpeedMultiplier(speedMultiplier);
		setDirection(r.nextInt(360));
		setLocation(new Point2D(r.nextDouble() * screenWidth, r.nextDouble()
				* screenHeight));
		super.setSize(r.nextInt(31) + 20);
		setSpeed(speed);
	}

	// @Override
	// public void setColor(int color) {
	// this.color = color;
	// }

	@Override
	public void setSize(int size) {

	}

	/*
	 * Whenever a fight() occurs, the astronaut takes damage, reducing their hp
	 * & speed by 1
	 */
	public void damage() {
		setSpeed(getSpeed() - 1);
		if (getSpeed() < 0)
			setSpeed(0);
		super.setColor(getRGB());
	}

	/*
	 * Returns a string with default info plus the astronaut's health, which is
	 * tracked by its speed.
	 */
	public String toString() {
		return super.toString() + "\thealth:\t" + getSpeed();
	}

	/*
	 * Additional function to track color integers to represent damage as a
	 * shade of the color.
	 */
	private int getRGB() {
		int blue = ColorUtil.blue(getColor());
		int green = ColorUtil.green(getColor());
		int red = ColorUtil.red(getColor());

		return ColorUtil
				.rgb(red * getSpeed() / originalSpeed, green * getSpeed()
						/ originalSpeed, blue * getSpeed() / originalSpeed);
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLoc = pCmpRelPrnt.getX() + (int) getLocation().getX();// shape
																	// location
																	// relative
		int yLoc = pCmpRelPrnt.getY() + (int) getLocation().getY();// to
																	// parent’s
																	// origin

		if (isSelected()) {
//			System.err
//					.println("I WAS BORN SHEEP STICKS, DISGUISED AS A TRIANGLE");
			g.drawLine(xLoc - 20, yLoc - 50, xLoc + 20, yLoc - 50);
			g.drawLine(xLoc + 20, yLoc - 50, xLoc, yLoc + 50);
			g.drawLine(xLoc, yLoc + 50, xLoc - 20, yLoc - 50);
		} else {
			//System.err.println("I WAS BORN A TRIANGLE");
			g.fillTriangle(xLoc - 20, yLoc - 50, xLoc + 20, yLoc - 50, xLoc,
					yLoc + 50);
		}
	}

	public void run() {

	}

	@Override
	public void setSelected(boolean yesNo) {
		this.selected = yesNo;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = pPtrRelPrnt.getX(); // pointer location relative to
		int py = pPtrRelPrnt.getY(); // parent’s origin
		int xLoc = pCmpRelPrnt.getX() + (int) getLocation().getX();// shape
																	// location
																	// relative
		int yLoc = pCmpRelPrnt.getY() + (int) getLocation().getY();// to
																	// parent’s
																	// origin
		if ((px >= xLoc-20) && (px <= xLoc + 20) && (py >= yLoc - 20)
				&& (py <= yLoc + 20))
			return true;
		else
			return false;
	}
}
