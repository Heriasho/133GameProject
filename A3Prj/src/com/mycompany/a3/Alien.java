package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
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
		//g.setColor(this.getColor());
		g.setColor(ColorUtil.BLUE);
		int xLoc = pCmpRelPrnt.getX() + (int) getLocation().getX();// shape location relative
		int yLoc = pCmpRelPrnt.getY() + (int) getLocation().getY();// to parent’s origin
		int r = 20;
		g.fillArc(xLoc-((2*r)/2), yLoc-((2*r)/2), 2*r, 2*r, 0, 360);
		//System.out.println("asteroid running");
		
	}
	/*#XXX Radius check the two objects to check if they are colliding.
	 * */
	@Override
	public boolean collidesWith(ICollider obj) {
		boolean result = false;
		int xLoc = (int) getLocation().getX() + (getScreenHeight()/2);
		int yLoc = (int) getLocation().getY() + (getScreenHeight()/2);
		int otherLocX = (int) ((GameObject) obj).getLocation().getX() + (getScreenHeight()/2);
		int otherLocY = (int) ((GameObject) obj).getLocation().getY() + (getScreenHeight()/2);
		
		int dx = xLoc - otherLocX;
		int dy = yLoc - otherLocY;
		int distanceBetweenSquared = (dx*dx + dy*dy);
		int thisRadius = (getScreenHeight()/2);
		int otherRadius = (getScreenHeight()/2);
		int radiusSquared = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		if(distanceBetweenSquared <= radiusSquared){
			result = true;
			System.out.println("Two objects collided");
		}
		return result ;
	}
	public void handleCollision(ICollider otherObject) {
		System.out.println("THE BOIZ ARE BACK IN TOWN!!");
	}
}
