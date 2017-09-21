package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;


public abstract class GameObject extends GameWorld {
	
	private String name;
	private int size;
	private int color;
	private Point2D location;
	
	/*Going to store my gameobjects on a hashtable and identify them by their toString*/
	public GameObject(String name, int size, int color, Point2D location) {
		this.name = name;
		this.size = size;
		this.color = color;
		this.location = location;
	}
	public String getName(){
		return name;
	}
	private Point2D getLocation(){
		return location;
	}
	private void setLocation(Point2D newLoc){
		location = newLoc;
	}
	private int getColor(){
		return color;
	}
	private void setColor(int color){
		color = ColorUtil.rgb(0,0,0);
	}
	private void size() {
		
	}
	private void random() {
		
	}
	public String toString() {
		return "GameObject " + name + " " + location.toString();
	}
	
}
