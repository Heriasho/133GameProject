package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;
import java.util.Random;

public class Astronaut extends Opponents {
	private int oSpeed;
	
	public Astronaut(int color, int screenHeight, int screenWidth, int speed, int speedMultiplier) {
		Random r = new Random();
		this.oSpeed = speed;
		setScreenHeight(screenHeight);
		setScreenWidth(screenWidth);
		setSpeedMultiplier(speedMultiplier);
		setColor(color);
		setDirection(r.nextInt(360));
		setLocation(new Point2D(r.nextDouble()*screenWidth, r.nextDouble()*screenHeight));
		super.setSize(r.nextInt(31)+20);
		setSpeed(speed);
	}
	/* (non-Javadoc)
	 * @see com.mycompany.a1.gameobject.GameObject#setColor(int)
	 * Method override due to astronaut not allowed to change color after initialization.
	 */
	@Override
	public void setColor(int size) {
		
	}	
	/* (non-Javadoc)
	 * @see com.mycompany.a1.gameobject.GameObject#setSize(int)
	 * Method override due to astronaut not allowed to change color after initialization.
	 */
	@Override
	public void setSize(int size) {
		
	}
	
	/**
	 * Damage from alien encounter, lowers speed by a point till it reaches 0.
	 * Color is updated to represent damage, by a factor of the original health.
	 * Since color is overridden for this class, super is invoked to update color.
	 */
	public void damage() {
		setSpeed(getSpeed() - 1);
		if(getSpeed() < 0)
			setSpeed(0);
		super.setColor(getRGB());
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a1.gameobject.opponent.Opponent#toString()
	 * toString adds the private variables of this class with parent.
	 */
	public String toString() {
		return super.toString() + "\thealth:\t" + getSpeed();
	}
	
	/**
	 * Helper function to get red, green, and blue integer's, 
	 * to represent damage as a shade of the original color.
	 *
	 * @return the rgb
	 */
	private int getRGB() {
		int blue = ColorUtil.blue(getColor());
		int green = ColorUtil.green(getColor());
		int red = ColorUtil.red(getColor());
	
		return ColorUtil.rgb(red * getSpeed() / oSpeed, green * getSpeed() / oSpeed, blue * getSpeed() / oSpeed);
	}
}
