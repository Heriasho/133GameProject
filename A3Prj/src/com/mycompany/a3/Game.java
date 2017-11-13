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
	
	private AboutCommand aboutCommand;
	private HelpCommand helpCommand;
	private TeleportToAlienCommand teleToAlienCommand;
	private TeleportToAstronautCommand teleToAstroCommand;
	private LeftCommand leftCommand;
	private RightCommand rightCommand;
	private UpCommand upCommand;
	private DownCommand downCommand;
	private OpenDoorCommand openDoorCommand;
	private FightCommand fightCommand;
	private BredCommand bredCommand;
	private CompressCommand compressCommand;
	private ExpandCommand expandCommand;
	private DeleteCommand deleteCommand;
	private StatsCommand statsCommand;
	private MapCommand mapCommand;
	private TickCommand tickCommand;
	
	public Game() {
		gw = new GameWorld();
		gw.init();	
		mv = new MapView(gw);
		sv = new ScoreView(gw);
		aboutCommand = new AboutCommand();
		helpCommand = new HelpCommand();
		teleToAlienCommand = new TeleportToAlienCommand();
		teleToAstroCommand = new TeleportToAstronautCommand();
		leftCommand = new LeftCommand();
		rightCommand = new RightCommand();
		upCommand = new UpCommand();
		downCommand = new DownCommand();
		openDoorCommand = new OpenDoorCommand();
		fightCommand = new FightCommand();
		bredCommand = new BredCommand();
		compressCommand = new CompressCommand();
		expandCommand = new ExpandCommand();
		deleteCommand = new DeleteCommand();
		statsCommand = new StatsCommand();
		mapCommand = new MapCommand();
		tickCommand = new TickCommand();
		
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
//		LeftCommand myLeftCommand = new LeftCommand();
//		RightCommand myRightCommand = new RightCommand();
//		UpCommand myUpCommand = new UpCommand();
//		DownCommand myDownCommand = new DownCommand();
		//CompressCommand myCompressCommand = new CompressCommand();
		//ExpandCommand myExpandCommand = new ExpandCommand();
		//MapCommand myMapCommand = new MapCommand();
		//OpenDoorCommand myOpenDoorCommand = new OpenDoorCommand();
		//BredCommand myBredCommand = new BredCommand();
		FightCommand myFightCommand = new FightCommand();
		//TickCommand myTickCommand = new TickCommand();
		//StatsCommand myStatsCommand = new StatsCommand();
		QuitCommand myQuitCommand = new QuitCommand();
		
		
		/*Set the commands for the buttons*/
		teleToAlienButton.setCommand(teleToAlienCommand);
		teleToAstroButton.setCommand(teleToAstroCommand);
		leftButton.setCommand(leftCommand);
		rightButton.setCommand(rightCommand);
		upButton.setCommand(upCommand);
		downButton.setCommand(downCommand);
		
		compressButton.setCommand(compressCommand);
		expandButton.setCommand(expandCommand);
		mapButton.setCommand(mapCommand);
		bredButton.setCommand(bredCommand);
		fightButton.setCommand(myFightCommand);
		tickButton.setCommand(tickCommand);
		statsButton.setCommand(statsCommand);
		openDoorButton.setCommand(openDoorCommand);

		
		/*Adding key listeners to call commands*/
		addKeyListener('c', compressCommand);
		addKeyListener('m', mapCommand);
		addKeyListener('s', statsCommand);
		addKeyListener('e', expandCommand);
		addKeyListener('u', upCommand);
		addKeyListener('d', downCommand);
		addKeyListener('l', leftCommand);
		addKeyListener('r', rightCommand);
		addKeyListener('q', myQuitCommand);
		addKeyListener('f', myFightCommand);
		addKeyListener('b', bredCommand);
		addKeyListener('t', tickCommand);
		addKeyListener('s', openDoorCommand);
		addKeyListener('a', teleToAlienCommand);
		addKeyListener('o', teleToAstroCommand);
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
		myToolbar.addCommandToSideMenu(aboutCommand);
		myToolbar.addCommandToSideMenu(helpCommand);
		myToolbar.addCommandToSideMenu(statsCommand);
		myToolbar.addCommandToSideMenu(myQuitCommand);
		/*Tool bar Setup*/
				
		show();
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
