package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;

public abstract class Rescuers extends GameObject implements Iguided {
	
	
	/* (non-Javadoc)
	 * @see com.mycompany.a1.gameobject.rescuer.IGuided#jumpToLocation(com.codename1.ui.geom.Point2D)
	 * Jumps Rescuer to specified location.
	 * @param locatoin 
	 */
	public void jumpToLocation(Point2D location) {
		setLocation(location);
	}
	
	/* (non-Javadoc)
	 * @see com.mycompany.a1.gameobject.rescuer.IGuided#moveDown()
	 * Moves Rescuer down.
	 */
	public void moveDown() {
		setLocation(new Point2D(getLocation().getX(), getLocation().getY() + 10));
	}
	
	/* (non-Javadoc)
	 * @see com.mycompany.a1.gameobject.rescuer.IGuided#moveLeft()
	 * Moves Rescuer left.
	 */
	public void moveLeft() {
		setLocation(new Point2D(getLocation().getX() - 10, getLocation().getY()));
	}
	
	/* (non-Javadoc)
	 * @see com.mycompany.a1.gameobject.rescuer.IGuided#moveRight()
	 * Moves Rescuer right.
	 */
	public void moveRight() {
		setLocation(new Point2D(getLocation().getX() + 10, getLocation().getY()));
	}
	
	/* (non-Javadoc)
	 * @see com.mycompany.a1.gameobject.rescuer.IGuided#moveUp()
	 * Move Rescuer up.
	 */
	public void moveUp() {
		setLocation(new Point2D(getLocation().getX(), getLocation().getY() - 10));
	}
	
	/* (non-Javadoc)
	 * @see com.mycompany.a1.gameobject.GameObject#toString()
	 * Returns super toString() appended with this classes private variables.
	 * 
	 * Appends this class private variables to the string from the parent.
	 * @return String string super + private variables
	 */
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
