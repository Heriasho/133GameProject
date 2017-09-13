package com.mycompany.a1;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;

public class GameWorld {
	
	private GameObject gameObject;
	private Spaceship spaceship;
	
	public GameWorld() {
		
	}
	
	/*Set the initial state of the game*/
	public void init() {
//      Form hi = new Form("Hi Game World");
//      hi.addComponent(new Label("Hi World"));
//      hi.show();
	}
	
	private void stop() {
		
	}
	
	private void destroy() {
		
	}
	void upMovement() {
		//Move spaceship up
		spaceship.moveUp();
	}
	void downMovement() {
		//Move spaceship down
		spaceship.moveDown();
	}
	void leftMovement() {
		spaceship.moveLeft();
	}
	void rightMovement() {
		spaceship.moveRight();
	}

}
