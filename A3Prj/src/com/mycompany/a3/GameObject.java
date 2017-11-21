package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;


public abstract class GameObject implements IDrawable, ICollider {
	
	private String name;
	private int screenHeight;
	private int screenWidth;
	private int color;
	private Point2D location;
	private int size;
	private boolean debug = false;
	
	public String getName(){
		return name;
	}
	public void setName(String objectName){
		this.name = objectName;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(int screenHeight){
		this.screenHeight = screenHeight;
	}
	public int getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(int screenWidth){
		this.screenWidth = screenWidth;
	}
	
	/*Instead of handling x & y coordinates seperately, I choose to use 
	 * codenameone's Point2D to handle them both simultaneously*/
	public Point2D getLocation(){
		return location;
	}
	public void setLocation(Point2D location){
		if(location.getX() > screenWidth){
			location.setX(screenWidth);
			if(debug)System.err.println("Set x to screenWeight " + screenWidth);
		}
		if(location.getY() > screenHeight){
			location.setY(screenHeight);
			if(debug)System.err.println("Set x to screenHeight " + screenHeight);
		}
		if(location.getX() < 0){
			location.setX(0);
			if(debug)System.err.println("Set x to 0");
		}
		if(location.getY() < 0){
			location.setY(0);
			if(debug)System.err.println("Set y to 0");
		}
		this.location = location;
	}
	
	public int getColor(){
		return color;
	}
	public void setColor(int color){
		this.color = color;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	/*Each gameobject has a toString that will display their location (x,y), their color, and their size.*/
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

	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
	}
	
}
