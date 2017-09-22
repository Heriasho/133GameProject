package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;

public class Alien extends Opponents {
	
	private String name = "alien";
	private int size;
	private int color;
	private Point2D location;
	
	public Alien(String name, int screenHeight, int screenWidth, int color, Point2D location) {
		super(name, screenHeight, screenWidth, color, location);
	}

	private void move(){
		//Aliens move like dorks.
	}
	private void setColor(){
		//Aliens don't change color;
	}
}
