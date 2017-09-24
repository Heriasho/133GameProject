package com.mycompany.a1;

import java.util.Random;
import com.codename1.ui.geom.Point2D;

/*Probabaly want to make a singleton later on.*/
public class Spaceship extends Rescuers implements Iguided {
	private String name = "Spaceship";
//	private int size;
//	private int color;
//	private Point2D location;
	
	public Spaceship(int color, int screenHeight, int screenWidth) {
		Random r = new Random();
		super.setColor(color);
		setLocation(new Point2D(r.nextDouble() * screenWidth, r.nextDouble() * screenHeight));
		setSize(100);
		setScreenHeight(screenHeight);
		setScreenWidth(screenWidth);
	}
	
	public void setColor(int color) {
		
	}
	public void contractDoor() {
		setSize(getSize() - 10);
	}

	/**
	 * Expand door.
	 */
	public void expandDoor() {
		setSize(getSize() + 10);
	}
	@Override
	public void setSize(int size) {
		if(getSize() < 50)
			size = 50;
		else if(size > 1024)
			size =1024;
		super.setSize(size);
	}
}
