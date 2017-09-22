package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;

/*Probabaly want to make a singleton later on.*/
public class Spaceship extends Rescuers {
	private String name = "Spaceship";
	private int size;
	private int color;
	private Point2D location;
	
	public Spaceship(String name, int screenHeight, int screenWidth, int color, Point2D location) {
		super(name, screenHeight, screenWidth, color, location);
		// TODO Auto-generated constructor stub
	}
	
	private void size(){
		this.size = size;
	}
	private void setColor() {
		
	}
}
