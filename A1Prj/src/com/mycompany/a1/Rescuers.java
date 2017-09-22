package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;

public abstract class Rescuers extends GameObject implements Iguided {
	
	public Rescuers(String name, int screenHeight, int screenWidth, int color, Point2D location) {
		super(name, screenHeight, screenWidth, color, location);
	}
	private void move(){
		//Mute since Rescurers do not use this.
	}
	public void moveLeft() {
		
	}
	public void moveRight(){
		
	}
	public void moveUp() {
		
	}
	public void moveDown() {
		
	}
	public void jumpToLocation() {
		
	}
	public void guided() {
		
	}
}
