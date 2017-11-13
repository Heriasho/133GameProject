package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

//Move the spaceship right;
public class RightCommand extends Command{
	private GameWorld gw;
	public RightCommand() {
		super("Right");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		gw.moveSpaceShipRight();
	}
}