package com.mycompany.a3;

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
	private MapView mv;
	private ScoreView sv;
	private boolean confirm = false;
	
	public Game() {
		gw = new GameWorld();
		gw.init();	
		mv = new MapView(gw);
		sv = new ScoreView(gw);
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		this.setLayout(new BorderLayout());
		this.setTitle("Title");
		this.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.GREEN));
		
		this.add(BorderLayout.NORTH, sv);
	
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setPadding(Component.TOP, 50);
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLUE));
		add(BorderLayout.WEST, leftContainer);

		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setPadding(Component.TOP, 50);
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLUE));
		add(BorderLayout.EAST, rightContainer);
		
		Container bottomContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		bottomContainer.getAllStyles().setPadding(Component.TOP, 50);
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLUE));
		add(BorderLayout.SOUTH, bottomContainer);
		
		this.add(BorderLayout.CENTER, mv);
		
		/*Button Creation & Command setup*/
		Button teleToAlienButton = new Button("TeleToAlien");
		Button teleToAstroButton = new Button("TeleToAstro");
		Button leftButton = new Button("Left");
		Button rightButton = new Button("Right");
		Button upButton = new Button("upButton");
		Button downButton = new Button("downButton");
		
		Button mapButton = new Button("Map");
		Button expandButton = new Button("Expand");
		Button compressButton = new Button("Compress");
		Button statsButton = new Button("Stats");
		Button openDoorButton = new Button("OpenDoor");
		
		Button bredButton = new Button("Bred");
		Button fightButton = new Button("Fight");
		Button tickButton = new Button("Tick");
	
		teleToAlienButton.getAllStyles().setBorder(Border.createLineBorder(4));
		teleToAstroButton.getAllStyles().setBorder(Border.createLineBorder(4));
		leftButton.getAllStyles().setBorder(Border.createLineBorder(4));
		rightButton.getAllStyles().setBorder(Border.createLineBorder(4));
		upButton.getAllStyles().setBorder(Border.createLineBorder(4));
		downButton.getAllStyles().setBorder(Border.createLineBorder(4));
		mapButton.getAllStyles().setBorder(Border.createLineBorder(4));
		openDoorButton.getAllStyles().setBorder(Border.createLineBorder(4));
		expandButton.getAllStyles().setBorder(Border.createLineBorder(4));
		compressButton.getAllStyles().setBorder(Border.createLineBorder(4));
		statsButton.getAllStyles().setBorder(Border.createLineBorder(4));
		bredButton.getAllStyles().setBorder(Border.createLineBorder(4));
		fightButton.getAllStyles().setBorder(Border.createLineBorder(4));
		tickButton.getAllStyles().setBorder(Border.createLineBorder(4));
		
		/*Creating the commands*/
		TeleportToAstronautCommand myTeleToAstroCommand = new TeleportToAstronautCommand();
		TeleportToAlienCommand myTeleToAlienCommand = new TeleportToAlienCommand();
		LeftCommand myLeftCommand = new LeftCommand();
		RightCommand myRightCommand = new RightCommand();
		UpCommand myUpCommand = new UpCommand();
		DownCommand myDownCommand = new DownCommand();
		
		CompressCommand myCompressCommand = new CompressCommand();
		ExpandCommand myExpandCommand = new ExpandCommand();
		MapCommand myMapCommand = new MapCommand();
		OpenDoorCommand myOpenDoorCommand = new OpenDoorCommand();
		
		BredCommand myBredCommand = new BredCommand();
		FightCommand myFightCommand = new FightCommand();
		TickCommand myTickCommand = new TickCommand();
		
		HelpCommand myHelpCommand = new HelpCommand();
		AboutCommand myAboutCommand = new AboutCommand();
		StatsCommand myStatsCommand = new StatsCommand();
		QuitCommand myQuitCommand = new QuitCommand();
		
		
		/*Set the commands for the buttons*/
		teleToAlienButton.setCommand(myTeleToAlienCommand);
		teleToAstroButton.setCommand(myTeleToAstroCommand);
		leftButton.setCommand(myLeftCommand);
		rightButton.setCommand(myRightCommand);
		upButton.setCommand(myUpCommand);
		downButton.setCommand(myDownCommand);
		
		compressButton.setCommand(myCompressCommand);
		expandButton.setCommand(myExpandCommand);
		mapButton.setCommand(myMapCommand);
		bredButton.setCommand(myBredCommand);
		fightButton.setCommand(myFightCommand);
		tickButton.setCommand(myTickCommand);
		statsButton.setCommand(myStatsCommand);
		openDoorButton.setCommand(myOpenDoorCommand);

		
		/*Adding key listeners to call commands*/
		addKeyListener('c', myCompressCommand);
		addKeyListener('m', myMapCommand);
		addKeyListener('s', myStatsCommand);
		addKeyListener('e', myExpandCommand);
		addKeyListener('u', myUpCommand);
		addKeyListener('d', myDownCommand);
		addKeyListener('l', myLeftCommand);
		addKeyListener('r', myRightCommand);
		addKeyListener('q', myQuitCommand);
		addKeyListener('f', myFightCommand);
		addKeyListener('b', myBredCommand);
		addKeyListener('t', myTickCommand);
		addKeyListener('s', myOpenDoorCommand);
		addKeyListener('a', myTeleToAlienCommand);
		addKeyListener('o', myTeleToAstroCommand);
		/*Button Creation & Command setup*/
		
		/*West bar setup*/
		leftContainer.add(expandButton);
		leftContainer.add(upButton);
		leftContainer.add(leftButton);
		leftContainer.add(compressButton);
		leftContainer.add(teleToAlienButton);
		leftContainer.add(teleToAstroButton);
		/*West bar setup*/
		
		/*East bar setup*/
		rightContainer.add(downButton);
		rightContainer.add(rightButton);
		rightContainer.add(statsButton);
		rightContainer.add(mapButton);
		rightContainer.add(openDoorButton);
		/*East bar setup*/
		
		/*South bar setup*/
		bottomContainer.add(bredButton);
		bottomContainer.add(fightButton);
		bottomContainer.add(tickButton);
		/*South bar setup*/
		
		/*Tool bar Setup*/
		myToolbar.addCommandToSideMenu(myAboutCommand);
		myToolbar.addCommandToSideMenu(myHelpCommand);
		myToolbar.addCommandToSideMenu(myStatsCommand);
		myToolbar.addCommandToSideMenu(myQuitCommand);
		/*Tool bar Setup*/
				
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
					
					gw.teleportToAlien();
					break;
				case 'o':
					
					gw.teleportToAstronaut();
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

	public class AboutCommand extends Command{
		public AboutCommand() {
			super("About");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			System.out.println("Provides usefull info");
		}
	}
	public class HelpCommand extends Command{
		public HelpCommand() {
			super("Help");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			System.out.println("Provides helpful tips");
		}
	}
	//Teleports the spaceship to a random alien
	public class TeleportToAlienCommand extends Command{
		public TeleportToAlienCommand() {
			super("TeleToAlien");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.teleportToAlien();
		}
	}
	//Teleports the spaceship to a random astronaut
	public class TeleportToAstronautCommand extends Command{
		public TeleportToAstronautCommand() {
			super("TeleToAstro");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.teleportToAstronaut();
		}
	}
	/*Open the door & update the score according to the types
	 * & conditions of opponents that are let in to the spaceship
	 * as described above in rules of play.
	 * This causes all of the opponents whose centers are within 
	 * the boundaries of the bounding square of the door to be removed
	 * from the game world.*/
	public class OpenDoorCommand extends Command{
		public OpenDoorCommand() {
			super("Open Door");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.openDoor();
		}
	}
	//Move the spaceship left;
	public class LeftCommand extends Command{
		public LeftCommand() {
			super("Left");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.moveSpaceShipLeft();
		}
	}
	//Move the spaceship right;
	public class RightCommand extends Command{
		public RightCommand() {
			super("Right");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.moveSpaceShipRight();
		}
	}
	//Move the spaceship up;
	public class UpCommand extends Command{
		public UpCommand() {
			super("Up");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.moveSpaceShipUp();
		}
	}
	//Move the spaceship down;
	public class DownCommand extends Command{
		public DownCommand() {
			super("Down");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.moveSpaceShipDown();
		}
	}
	public class FightCommand extends Command {
		public FightCommand() {
			super("Fight");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.fight();
		}
	}
	public class BredCommand extends Command {
		public BredCommand() {
			super("Bred");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.bred();
		}
	}
	//Decrease the size of the spaceship door.
	public class CompressCommand extends Command{
		public CompressCommand() {
			super("Compress");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.compress();
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
	//Increase the size of the spaceship door.
	public class ExpandCommand extends Command{
		public ExpandCommand() {
			super("Expand");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.expand();
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
	/*Tell the Gw that the game clock has ticked.
	 * All moving objects are told to update their positions
	 * according to their current direction and speed.*/
	public class TickCommand extends Command{
		public TickCommand() {
			super("Tick");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			gw.tick();
		}
	}
	public class QuitCommand extends Command{
		public QuitCommand() {
			super("Quit");
		}
		@Override
		public void actionPerformed(ActionEvent e){
			if(confirm)
				System.exit(0);
			else{
				System.out.println("You attempted to exit the game without confirming. \nIf you wish to quit, please press 'y' then press 'x'.");
			}
		}
	}
	
	private void init() {
		
	}
	
	private void stop() {
		
	}
	
	private void destroy() {
		
	}

}
