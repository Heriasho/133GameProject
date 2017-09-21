package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;

public class Astronaut extends Opponents {
	private String name = "Astronaut";
	private int size;
	private int color;
	private Point2D location;
	private int health;
	
	public Astronaut(String name, int size, int color, Point2D location) {
		super(name, size, color, location);
	}

	private void setcolor() {
		//Astronauts change color as their health dips.
	}

	private int getHealth() {
		return health;
	}

	private void setHealth(int health) {
		this.health = health;
	}
}
