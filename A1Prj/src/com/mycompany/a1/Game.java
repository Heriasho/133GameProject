package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.Dialog;
//import com.codename1.ui.Display;
//import com.codename1.ui.plaf.UIManager;
//import com.codename1.ui.util.Resources;
//import com.codename1.io.Log;
//import com.codename1.ui.Toolbar;




import com.codename1.ui.layouts.BorderLayout;


import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;


import com.codename1.ui.plaf.Border;



//import java.io.IOException;
import java.lang.String;

public class Game extends Form {
	private GameWorld gw;
	private boolean confirm = false;
	
	public Game() {
		gw = new GameWorld();
		gw.init();
		//play();
		this.setLayout(new BorderLayout());
		Container topContainer = new Container(new GridLayout(1,2));
		topContainer.add(new Label("Read this"));
		topContainer.add(new Button("Press this"));
		topContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GREEN));
		add(BorderLayout.NORTH,topContainer);
		
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setPadding(Component.TOP, 50);
		leftContainer.add(new Label("Text (1)"));
		leftContainer.add(new Button("Click this"));
		leftContainer.add(new Button("Something"));
		leftContainer.add(new Button("Another thing"));
		leftContainer.add(new Button("Hopefully implemented"));
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		add(BorderLayout.WEST, leftContainer);
		
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setPadding(Component.TOP, 50);
		rightContainer.add(new Label("Text (2)"));
		rightContainer.add(new Button("Click this"));
		rightContainer.add(new Button("Something"));
		rightContainer.add(new Button("Another thing"));
		rightContainer.add(new Button("Hopefully implemented"));
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLUE));
		add(BorderLayout.WEST, rightContainer);
		
		this.add(BorderLayout.NORTH, topContainer);
		this.add(BorderLayout.CENTER, new Label("Center"));
		this.add(BorderLayout.WEST, leftContainer);
		this.add(BorderLayout.EAST, rightContainer);
		this.add(BorderLayout.SOUTH, new Label("South"));
		
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		
		Label titleLabel = new Label("TITLE");
		Button buttonOne = new Button("Button one");
		Button buttonTwo = new Button("Button two");
		Button buttonThree = new Button("Button three");
		Button buttonFour = new Button("Button four");
		
		DeleteCommand myDeleteCommand = new DeleteCommand();
		MapCommand myMapCommand = new MapCommand();
		StatsCommand myStatsCommand = new StatsCommand();
		CutCommand myCutCommand = new CutCommand();
		
		buttonOne.setCommand(myCutCommand);
		buttonTwo.setCommand(myDeleteCommand);
		buttonThree.setCommand(myMapCommand);
		buttonFour.setCommand(myStatsCommand);
		
		Command sideMenuItem1 = new Command("Side Menu Item 1");
		myToolbar.setTitleComponent(titleLabel);
		myToolbar.addCommandToSideMenu(sideMenuItem1);
		myToolbar.addCommandToSideMenu(myMapCommand);
		myToolbar.addCommandToSideMenu(myStatsCommand);
		
//		myToolbar.addCommandToRightBar(myDeleteCommand);
//		myToolbar.addCommandToLeftBar(myMapCommand);
//		myToolbar.addCommandToLeftBar(myStatsCommand);
		
		addKeyListener('c',myCutCommand);
		addKeyListener('d', myDeleteCommand);
		addKeyListener('m', myMapCommand);
		addKeyListener('s', myStatsCommand);
		
		show();
		
		
	}
	/* Accepts keyboard commands from the player & invokes GameWorld methods*/
	@SuppressWarnings("rawtypes")
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
				case 'a':
					//Teleports the spaceship to a random alien
					gw.teleportToAlien();
					break;
				case 'o':
					//Teleports the spaceship to a random astronaut
					gw.teleportToAstronaut();
					break;
				case 'r':
					//Move the spaceship to the right;
					gw.moveSpaceShipRight();
					break;
				case 'l':
					//Move the spaceship to the left;
					gw.moveSpaceShipLeft();
					break;
				case 'u':
					//Move the spaceship up;
					gw.moveSpaceShipUp();
					break;
				case 'd':
					//Move the spaceship down;
					gw.moveSpaceShipDown();
					break;
				case 'c':
					//Decrease the size of the spaceship door.
					gw.compress();
					break;
				case 'e':
					//Increase the size of the spaceship door.
					gw.expand();
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
					gw.openDoor();
					break;
				case 'w':
					/*PRETEND that a collision occurred between two aliens. 
					 * This type of collision means that a new alien is generated.
					 * ELSE, if there is less than two aliens, print an error message instead.*/
					gw.bred();
					break;
				case 'f':
					/*PRETEND that a collision occurred between an alien and an astronaut.
					 * This type of collision means that a fight occurred between the two.
					 * Chooses random astronaut & decrements its health value, updates speed, change color.
					 * ELSE, if there are no aliens, print an error message instead.*/
					gw.fight();
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
			}	
		}
		); 
	}
	
	
	public class CutCommand extends Command {
		public CutCommand() {
			super("Cut");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			System.out.println("Cut command is invoked...");
		}
	}
	public class DeleteCommand extends Command{
		public DeleteCommand() {
			super("Delete");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			System.out.println("Delete command is invoked...");
		}
	}
	public class MapCommand extends Command{
		public MapCommand() {
			super("Map");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.map();
		}
	}
	public class StatsCommand extends Command{
		public StatsCommand() {
			super("Stats");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.stats();
		}
	}
	
	private void init() {
		
	}
	
	private void stop() {
		
	}
	
	private void destroy() {
		
	}

}
