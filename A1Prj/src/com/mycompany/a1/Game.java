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

public class Game extends Form {
	private GameWorld gw;
	
	/*Instantiates a GameWorld.
	 * Calls GameWorld's init() to set the initial state of the game.
	 * Calls play()*/
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
		
	}
	/* Accepts keyboard commands from the player & invokes GameWorld methods*/
	private void play() {
		
	}
	
	private void init() {
		
	}
	
	private void stop() {
		
	}
	
	private void destroy() {
		
	}

}
