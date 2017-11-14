package com.mycompany.a3;

import java.util.Random;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

/*Probably want to make a singleton later on.*/
public class Spaceship extends Rescuers {
	
	private static Spaceship spaceship;
	private static int color;
	private static int screenHeight;
	private static int screenWidth;
	
	private Spaceship(int color, int screenHeight, int screenWidth) {
		Random r = new Random();
		super.setColor(color);
		setLocation(new Point2D(r.nextDouble() * screenWidth, r.nextDouble() * screenHeight));
		setSize(100);	
		setScreenHeight(screenHeight);
		setScreenWidth(screenWidth);
	}
	public static Spaceship getSpaceship(){
		if(spaceship == null){
			spaceship = new Spaceship(color, screenHeight, screenWidth);
		}
		return spaceship;
	}
	/*Spaceship color doesn't change.*/
	public void setColor(int color) {
		
	}
	/*Contracts the door (spaceship) size by 10*/
	public void contractDoor() {
		setSize(getSize() - 10);
		System.out.println("You compressed the spaceship size by " + getSize());
	}

	/*Expands the door (spaceship) size by 10*/
	public void expandDoor() {
		setSize(getSize() + 10);
		System.out.println("You expanded the spaceship size by " + getSize());
	}
	/*Override the default size method to allow the spaceship to be much bigger.*/
	@Override
	public void setSize(int size) {
		if(getSize() < 50)
			size = 50;
		else if(size > 1024)
			size =1024;
		super.setSize(size);
	}
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLoc = pCmpRelPrnt.getX() + (int) getLocation().getX();// shape location relative
		int yLoc = pCmpRelPrnt.getY() + (int) getLocation().getY();// to parent’s origin
		
		g.fillTriangle(xLoc-20, yLoc-40, xLoc+20, yLoc-40, xLoc, yLoc+40);
	}
}
