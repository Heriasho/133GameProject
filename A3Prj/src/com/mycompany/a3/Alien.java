package com.mycompany.a3;

import java.util.Random;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class Alien extends Opponents {
	
	public Alien(int color, int screenHeight, int screenWidth, int speed, int speedMultiplier) {
		Random r = new Random();
		setColor(color);
		setScreenHeight(screenHeight);
		setScreenWidth(screenWidth);
		setSpeedMultiplier(speedMultiplier);
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
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getColor());
		int xLoc = pCmpRelPrnt.getX() + (int) getLocation().getX();// shape location relative
		int yLoc = pCmpRelPrnt.getY() + (int) getLocation().getY();// to parent’s origin
		
		g.fillRect(xLoc, yLoc, 30, 50);
		//System.out.println("asteroid running");
		
	}
}
