package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
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
import com.mycompany.a3.BottomButton;

import com.codename1.ui.util.UITimer;

//import java.io.IOException;
import java.lang.String;

public class Game extends Form implements Runnable {
	private Game game;
	private GameWorld gw = new GameWorld();
	private MapView mv;
	private ScoreView sv;
	private static UITimer timer;
	private int time;
	private boolean toggle = false;
	private boolean isPaused = false;
	private PauseCommand pauseCommand = new PauseCommand("Playing", gw, this);
	private AboutCommand aboutCommand = new AboutCommand();
	private HelpCommand helpCommand = new HelpCommand();
	private HealCommand healCommand = new HealCommand(gw, null);
	private TeleportToAlienCommand teleToAlienCommand = new TeleportToAlienCommand(gw);
	private TeleportToAstronautCommand teleToAstroCommand = new TeleportToAstronautCommand(gw);
	private LeftCommand leftCommand = new LeftCommand(gw);
	private RightCommand rightCommand = new RightCommand(gw);
	private UpCommand upCommand = new UpCommand(gw);
	private DownCommand downCommand = new DownCommand(gw);
	private CompressCommand compressCommand = new CompressCommand(gw);
	private ExpandCommand expandCommand = new ExpandCommand(gw);
	private MapCommand mapCommand = new MapCommand(gw);
	private BredCommand bredCommand = new BredCommand(gw);
	private OpenDoorCommand openDoorCommand = new OpenDoorCommand(gw);
	private StatsCommand statsCommand = new StatsCommand(gw);
	private FightCommand myFightCommand = new FightCommand(gw);
	private QuitCommand myQuitCommand = new QuitCommand();
	private SoundCommand soundCheckCommand = new SoundCommand("Sound", gw, game);
	


	public Game() {
		gw.init();
		mv = new MapView(gw, this);
		sv = new ScoreView(gw);
		setTimer(new UITimer(this));
		time = 100;
		getTimer().schedule(time, true, this);

		gw.addObserver(mv);
		gw.addObserver(sv);
		/* Button Creation & Command setup */
		Button teleToAlienButton = new CButton("TeleToAlien");
		Button teleToAstroButton = new CButton("TeleToAstro");
		Button leftButton = new CButton("Left");
		Button rightButton = new CButton("Right");
		Button upButton = new CButton("upButton");
		Button downButton = new CButton("downButton");
		Button mapButton = new CButton("Map");
		Button expandButton = new CButton("Expand");
		Button compressButton = new CButton("Compress");
		Button statsButton = new CButton("Stats");
		Button openDoorButton = new CButton("OpenDoor");
		Button bredButton = new CButton("Bred");
		// Button fightButton = new CButton("Fight");
		// Button tickButton = new CButton("Tick");
		

		/* Creating the commands */
		BottomButton pauseButton = new BottomButton("Playing", pauseCommand);
		/* Set the commands for the buttons */
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
		// fightButton.setCommand(myFightCommand);
		openDoorButton.setCommand(openDoorCommand);
		statsButton.setCommand(statsCommand);
		//pauseButton.setCommand(pauseCommand);
		//pauseButton = new TypePauseButton (pausedMessage,pauseCommand);

		/* Adding key listeners to call commands */
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
		addKeyListener('s', openDoorCommand);
		addKeyListener('a', teleToAlienCommand);
		addKeyListener('o', teleToAstroCommand);
		addKeyListener('p', pauseCommand);
		addKeyListener('h', healCommand);
		/* Button Creation & Command setup */

		/* Title Bar */
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		this.setLayout(new BorderLayout());
		myToolbar.setTitle("Space Fights");
		myToolbar.setTitleCentered(true);

		/* Score view */
		this.add(BorderLayout.NORTH, sv);
		CheckBox soundCheckBox = new CheckBox("Sound");
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.rgb(150, 150, 150)); // Mild
																				// Gray
		soundCheckBox.setText("Toggle Sound");
		soundCheckBox.setSelected(true);
		soundCheckBox.setCommand(soundCheckCommand);
		soundCheckCommand.putClientProperty("sideComponent", soundCheckBox);
		this.add(BorderLayout.SOUTH, soundCheckBox);

		/* Map View */
		this.add(BorderLayout.CENTER, mv);

		/* West bar setup */
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setPadding(Component.TOP, 50);
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLUE));
		add(BorderLayout.WEST, leftContainer);
		leftContainer.add(expandButton);
		leftContainer.add(upButton);
		leftContainer.add(leftButton);
		leftContainer.add(compressButton);
		leftContainer.add(teleToAlienButton);
		leftContainer.add(teleToAstroButton);
		/* West bar setup */

		/* East bar setup */
		Container rightContainer = new Container(
		new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setPadding(Component.TOP, 50);
		rightContainer.getAllStyles().setBorder(
		Border.createLineBorder(1, ColorUtil.BLUE));
		add(BorderLayout.EAST, rightContainer);
		rightContainer.add(downButton);
		rightContainer.add(rightButton);
		rightContainer.add(statsButton);
		rightContainer.add(mapButton);
		rightContainer.add(openDoorButton);
		/* East bar setup */

		/* South bar setup */
		Container bottomContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		bottomContainer.getAllStyles().setPadding(Component.TOP, 50);
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLUE));
		add(BorderLayout.SOUTH, bottomContainer);
		bottomContainer.add(pauseButton);
		bottomContainer.add(soundCheckBox);
		// bottomContainer.add(bredButton);
		// bottomContainer.add(fightButton);
		// bottomContainer.add(tickButton);
		/* South bar setup */

		/* Tool bar Setup */
		myToolbar.addCommandToSideMenu(aboutCommand);
		myToolbar.addCommandToSideMenu(helpCommand);
		myToolbar.addCommandToSideMenu(statsCommand);
		myToolbar.addCommandToSideMenu(myQuitCommand);
		/* Tool bar Setup */

		this.show();
	}

	public void pause() {
		toggle = !toggle;
		if (toggle) {
			timer.cancel();
			System.out.println("timer is canceled");
			healCommand.setEnabled(true);
			isPaused = true;
			teleToAlienCommand.setEnabled(false);
			teleToAstroCommand.setEnabled(false);
			leftCommand.setEnabled(false);
			rightCommand.setEnabled(false);
			upCommand.setEnabled(false);
			downCommand.setEnabled(false);
			mapCommand.setEnabled(false);
			openDoorCommand.setEnabled(false);
			compressCommand.setEnabled(false);
			expandCommand.setEnabled(false);
			statsCommand.setEnabled(false);
			
		} else {
			timer.schedule(time, true, this);
			System.out.println("timer is resumed");
			healCommand.setEnabled(false);
			isPaused = false;
			teleToAlienCommand.setEnabled(true);
			teleToAstroCommand.setEnabled(true);
			leftCommand.setEnabled(true);
			rightCommand.setEnabled(true);
			upCommand.setEnabled(true);
			downCommand.setEnabled(true);
			mapCommand.setEnabled(true);
			openDoorCommand.setEnabled(true);
			compressCommand.setEnabled(true);
			expandCommand.setEnabled(true);
			statsCommand.setEnabled(true);
		}
	}
	
	public void gameOver(){
		timer.cancel();
		
	}

	public void run() {
		// TODO Auto-generated method stub
		gw.tick(time);
		repaint();
	}

	public UITimer getTimer() {
		return timer;
	}

	public void setTimer(UITimer timer) {
		this.timer = timer;
	}

	
	private void init() {
		
	}
	
	private void stop() {
		
	}
	
	private void destroy() {
		
	}

	public boolean getIsPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

}
