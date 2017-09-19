package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;

import java.util.Hashtable;
import java.util.Random;

public abstract class Opponents extends GameObject implements Imove {
	private Point2D size;
	private int speed;
	private int direction;
	
	public Opponents() {
	}
	public void move(int direction) {
		//moves the aliens and the astronauts
		double deltax = Math.cos(90-direction)* speed;
		double deltay = Math.sin(90-direction)* speed;
		Point2D oldLocation = new Point2D(deltax, deltay);
		double directionX = oldLocation.getX() + deltax;
		double directionY = oldLocation.getY() + deltay;
		Point2D newLocation = new Point2D(directionX,directionY);
	}
}