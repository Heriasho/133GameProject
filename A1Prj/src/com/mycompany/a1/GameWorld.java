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
import java.util.Hashtable;

public class GameWorld {
	
	private int roamingAliens;
	private int roamingAstronauts;
	private int rescuedAstronauts;
	private int rescuedAliens;
	private int score;
	private GameCollection theGameCollection;

	public GameWorld() {
		theGameCollection = new GameCollection();
//		theGameCollection.add(new GameObject("Alien"));
//		Aliens[] aliens = new Aliens[2];
//		Astronauts[] astronauts = new Astronauts[2];
//		
//		roamingAliens = aliens.length;
//		roamingAstronauts = astronauts.length;
	}
	
	/*Set the initial state of the game*/
	public void init() {

	}
	
	public void bred() {
//		if(alien instanceof alien) {
//			new Alien;
//		}
	}
	
	public void fight() {
//		if(alien instanceof astronaut){
//			this.astronaut.health --;
//		}
	}
	
	public void tick() {
		
	}
	
	public void stats() {
		
	}
	
	public void map() {
		
	}
	
	public void score() {
		
	}
	
	public void expand() {
		
	}
	
	public void compress() {
		
	}
	
	public void spaceShipMove() {
		
	}
	
	public void teleportToAlien() {
		
	}
	
	public void teleportToAstronaut() {
		
	}
	
	public void exit() {
		
	}
	

}
