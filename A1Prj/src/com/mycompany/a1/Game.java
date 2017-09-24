package com.mycompany.a1;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.lang.String;

public class Game extends Form {
	private GameWorld gw;
	private boolean confirm = false;
	
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
		
	}
	/* Accepts keyboard commands from the player & invokes GameWorld methods*/
	public void play() {
		Label myLabel = new Label("Enter a Command");
		this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		myTextField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				switch (sCommand.charAt(0)) {
					//List commands here
				case 'r':
					//Move the spaceship to the right;
					//gw.rightMovement();
					gw.moveSpaceShipRight();
					System.out.println("The spaceship moves right.");
					break;
				case 'l':
					//Move the spaceship to the left;
					//gw.leftMovement();
					gw.moveSpaceShipLeft();
					System.out.println("The spaceship moves left.");
					break;
				case 'u':
					//Move the spaceship up;
					//gw.upMovement();
					gw.moveSpaceShipUp();
					System.out.println("The spaceship moves up.");
					break;
				case 'd':
					//Move the spaceship down;
					//gw.downMovement();
					gw.moveSpaceShipDown();
					System.out.println("The spaceship moves down.");
					break;
				case 'c':
					//Decrease the size of the spaceship door.
					gw.compress();
					System.out.println("COMPRESS");
					break;
				case 'e':
					//Increase the size of the spaceship door.
					gw.expand();
					System.out.println("EXPAND");
					break;
				case 't':
					/*Tell the Gw that the game clock has ticked.
					 * All moving objects are told to update their positions
					 * according to their current direction and speed.*/
					gw.tick();
					break;
				case 's':
					/*Open the door & update the score according to the types
					 * & conditions of opponents that are let in to the spaceship
					 * as described above in rules of play.
					 * This causes all of the opponents whose centers are within 
					 * the boundaries of the bounding square of the door to be removed
					 * from the game world.*/
					System.out.println("The spaceship door has opened.");
					break;
				case 'w':
					/*PRETEND that a collision occurred between two aliens. 
					 * This type of collision means that a new alien is generated.
					 * ELSE, if there is less than two aliens, print an error message instead.*/
					gw.bred();
					System.out.println("Two aliens bred.");
					break;
				case 'f':
					/*PRETEND that a collision occurred between an alien and an astronaut.
					 * This type of collision means that a fight occurred between the two.
					 * Chooses random astronaut & decrements its health value, updates speed, change color.
					 * ELSE, if there are no aliens, print an error message instead.*/
					gw.fight();
					System.out.println("The astronaut fought the alien & the alien won.");
					break;
				case 'p':
					/*Print the points of game state values:
					 * current score
					 * number of astronauts rescued
					 * number of aliens sneaked in to the spaceship
					 * number of astronauts left in the world
					 * Output should be appropriately labeled in easily readable format*/
					gw.stats();
					break;
				case 'm':
					/*Print a 'map' showing the current world state.*/
					gw.map();
					break;
				case 'x':
					/*Exit, by calling the method 'System.exit(0)' to terminate the program.
					 * Your program should confirm the user's intent to quit before actually exiting.*/
					if(confirm)
						System.exit(0);
					else{
						System.out.println("You attempted to exit the game without confirming. \nIf you wish to quit, please press 'y' then press 'x'.");
					}
					break;
				case 'y':
					/*User has confirmed the exit the exit by saying yes.*/
					System.out.println("You are confirming you wish to exit the game.");
					confirm = true;
					break;
				case 'n':
					/*User has not confirmed the exit by saying no.*/
					System.out.println("You are confirming you do not wish to exit the game.");
					confirm = false;
					break;
				default: 
					System.out.println("Error: Not a valid command");
				}
			}	//actionPerformed
		}//newActionListner()
		); //addActionListener
	}//play
	
	private void init() {
		
	}
	
	private void stop() {
		
	}
	
	private void destroy() {
		
	}

}
