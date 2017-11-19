package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class Alien extends Opponents {
	private GameWorld gw = new GameWorld();
	public Alien(int color, int screenHeight, int screenWidth, int speed,
			int speedMultiplier) {
		
		Random r = new Random();
		setColor(color);
		setScreenHeight(screenHeight);
		setScreenWidth(screenWidth);
		setSpeedMultiplier(speedMultiplier);
		setDirection(r.nextInt(360));
		setLocation(new Point2D(r.nextDouble() * screenWidth, r.nextDouble()
				* screenHeight));
		super.setSize(r.nextInt(31) + 20);
		setSpeed(speed);
	}

	@Override
	public void setColor(int color) {
		// Aliens don't change color;
	}

	@Override
	public void setSize(int size) {

	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		// g.setColor(this.getColor());
		g.setColor(ColorUtil.BLUE);
		int xLoc = pCmpRelPrnt.getX() + (int) getLocation().getX();
		int yLoc = pCmpRelPrnt.getY() + (int) getLocation().getY();
		int r = 20;
		g.fillArc(xLoc - ((2 * r) / 2), yLoc - ((2 * r) / 2), 2 * r, 2 * r, 0,
				360);

	}

	/*
	 * #TODO: Moving objects should consider consider the width and height of
	 * the mv in move(). TODO: When two objects collide, handle the
	 * handleCollision should only happen once.
	 */
	public void handleCollision(ICollider otherObject) {
		//gw = new GameWorld();
		System.out.println("------------------");
		System.out.println("Collision detected with: " + otherObject);
		
		if (otherObject instanceof Alien) {
			System.out.println("Alien collision!@!!!!!");
			//Alien ali = (Alien) otherObject;
			gw.bred();
		}
		else if(otherObject instanceof Astronaut){
			System.out.println("Astronaut collision!!!!");
			Astronaut astro = (Astronaut) otherObject;
			//gw.fight();
		}
		System.out.println("------------------");
		System.out.println("THE BOIZ ARE BACK IN TOWN!!");
	}
	public void run(){
		
	}
}
