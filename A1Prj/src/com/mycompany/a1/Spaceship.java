package com.mycompany.a1;

import java.util.Random;

import com.codename1.ui.geom.Point2D;

/*Probabaly want to make a singleton later on.*/
public class Spaceship extends Rescuers implements Iguided {
	
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
		System.out.println("You compressed the spaceship size by " + getSize());
	}

	/*Expands the door (spaceship) size by 10*/
	public void expandDoor() {
		setSize(getSize() + 10);
		System.out.println("You expanded the spaceship size by " + getSize());
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
