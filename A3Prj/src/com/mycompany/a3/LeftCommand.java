package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

//Move the spaceship left;
public class LeftCommand extends Command{
	private GameWorld gw;
	public LeftCommand() {
		super("Left");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		gw.moveSpaceShipLeft();
	}
}
