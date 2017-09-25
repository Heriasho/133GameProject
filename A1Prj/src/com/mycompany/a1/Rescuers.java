package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;

public abstract class Rescuers extends GameObject implements Iguided {
	
	public void jumpToLocation(Point2D location) {
		setLocation(location);
	}
	
	public void moveDown() {
		setLocation(new Point2D(getLocation().getX(), getLocation().getY() + 10));
		System.out.println("The spaceship moves down by 10");
	}
	
	public void moveLeft() {
		setLocation(new Point2D(getLocation().getX() - 10, getLocation().getY()));
		System.out.println("The spaceship moves left by 10");
	}
	
	public void moveRight() {
		setLocation(new Point2D(getLocation().getX() + 10, getLocation().getY()));
		System.out.println("The spaceship moves right by 10");
	}
	
	public void moveUp() {
		setLocation(new Point2D(getLocation().getX(), getLocation().getY() - 10));
		System.out.println("The spaceship moves up by 10.");
	}
	/*The Rescuers toString gets the x,y location of the rescuers, their color, and their size.*/
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
