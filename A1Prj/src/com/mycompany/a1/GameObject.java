package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;


public abstract class GameObject extends GameWorld {
	
	private Point2D location;
	private int size;
	private int color;
	private String name;
	
	/*Going to store my gameobjects on a hashtable and identify them by their toString*/
	public GameObject() {

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
