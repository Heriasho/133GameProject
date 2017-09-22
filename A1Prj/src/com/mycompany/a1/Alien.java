package com.mycompany.a1;

import java.util.Random;

import com.codename1.ui.geom.Point2D;

public class Alien extends Opponents {
	

	
	public Alien(int color, int screenHeight, int screenWidth, int speed, int speedMultiplier) {
		Random r = new Random();
		setScreenHeight(screenHeight);
		setScreenWidth(screenWidth);
		setSpeedMultiplier(speedMultiplier);
		setColor(color);
		setDirection(r.nextInt(360));
		setLocation(new Point2D(r.nextDouble()*screenWidth, r.nextDouble()*screenHeight));
		super.setSize(r.nextInt(31)+20);
		setSpeed(speed);
	}

	@Override
	public void setColor(int color){
		//Aliens don't change color;
	}
	@Override
	public void setSize(int size) {
		
	}
}
