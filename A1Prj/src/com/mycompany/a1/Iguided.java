package com.mycompany.a1;

import com.codename1.ui.geom.Point2D;

/*Interface that provides guided features to those that 'implements' this.*/
public interface Iguided {
	void moveLeft();
	void moveRight();
	void moveUp();
	void moveDown();
	void jumpToLocation(Point2D location);
}
