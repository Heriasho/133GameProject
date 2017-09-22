package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;
import java.util.Hashtable;
import java.util.Random;

public abstract class Opponents extends GameObject implements Imove {
	
	private int size;
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
		this.speed = speed & speedMultiplier;
	}
	
	
	public int getSpeedMultiplier() {
		return speedMultiplier;
	}
	
	public void move(int elapsedTime) {
		setDirection(getDirection() + 5);		
		double time = elapsedTime/1000;
		double distance = getSpeed() * time;
		double deltaX = Math.cos(getDirection() * Math.PI / 180.0) * distance;
		double deltaY = Math.sin(getDirection() * Math.PI / 180.0) * distance;
		Point2D nLoc = new Point2D(getLocation().getX() + deltaX, getLocation().getY() + deltaY);
		
		while(nLoc.getX() < 0 || nLoc.getX() > getScreenWidth() || nLoc.getY() < 0 || nLoc.getY() > getScreenHeight()) {						
			setDirection(getDirection() + 15);
			
			if(deltaX > getScreenWidth()) {
				deltaX -= getScreenWidth();
				nLoc.setX(getScreenWidth());
			} else if (nLoc.getX() < 0) {
				deltaX *= -1;
				nLoc.setX(0);
			} else
				deltaX = 0;
			
			if(deltaY > getScreenHeight()) { 
				deltaY -= getScreenHeight();
				nLoc.setY(getScreenHeight());
			} else if (deltaY < 0) {
				deltaY *= -1;
				nLoc.setY(0);
			} else
				deltaY = 0;
			
			deltaX = Math.cos(getDirection() * Math.PI / 180.0) * deltaX;
			deltaY = Math.sin(getDirection() * Math.PI / 180.0) * deltaY;
			nLoc = new Point2D(nLoc.getX() + deltaX, nLoc.getY() + deltaY);
		}	
		setLocation(nLoc);
	}
}