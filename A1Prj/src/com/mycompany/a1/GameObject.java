package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;


public abstract class GameObject extends GameWorld {
	
	private String name;
	private int screenHeight;
	private int screenWidth;
	private int color;
	private Point2D location;
	private int size;
	

	public String getName(){
		return name;
	}
	
	
	private int getScreenHeight() {
		return screenHeight;
	}
	private void setScreenHeight(int screenHeight){
		this.screenHeight = screenHeight;
	}
	
	
	private int getScreenWidth() {
		return screenWidth;
	}
	private void setScreenWidth(int screenWidth){
		this.screenWidth = screenWidth;
	}
	
	
	private Point2D getLocation(){
		return location;
	}
	private void setLocation(Point2D location){
		this.location = location;
	}
	
	
	private int getColor(){
		return color;
	}
	private void setColor(int color){
		this.color = color;
	}
	
	
	private int getSize() {
		return size;
	}
	private void setSize(int size) {
		this.size = size;
	}
	
	public String toString() {
		String x = Math.round(getLocation().getX() * 1000.0)/1000.0 + "";
		String y = Math.round(getLocation().getY() * 1000.0)/1000.0 + "";
		while(x.substring(0,x.indexOf('.')).length() < 4)
			x = "0" + x;
		while(x.substring(x.indexOf('.') + 1).length() < 3)
			x = x +"0";
		while(y.substring(0,y.indexOf('.')).length() < 4)
			y = "0" + y;
		while(y.substring(y.indexOf('.') + 1).length() < 3)
			y = y +"0";
		String name = this.getClass().toString(); 
		name = name.substring(name.lastIndexOf('.')+1);
		while(name.length() < 10)
			name = name + " ";
		return name + ":\tloc=\t"+ x +"\t" + y + " \tcolor=\t" + getColor() + " \tsize=\t" + getSize();
	}
	
}
