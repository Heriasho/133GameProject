package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;


public abstract class GameObject extends GameWorld {
	
	public abstract double getX();
	public abstract double getY();
	public abstract void setLocation(double x, double y);
	private Point2D location;
	private Point2D distance;
	private int size;
	private int color;

	private void size() {
		
	}
	private Point2D getLocation(Point2D location){
		return location;
	}
	private void setLocation(Point2D location){
		//DO SOMETHING WITH THE LOCATION
	}
	public Point2D distance(Point2D pt) {
		return distance;
	}
	
	private int getColor(int color){
		return color;
	}
	private void setColor(int color){
		this.color = color;
	}
	private void random() {
		
	}
	
}
