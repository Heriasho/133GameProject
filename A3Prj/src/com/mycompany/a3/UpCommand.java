package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

//Move the spaceship up;
public class UpCommand extends Command{
	private GameWorld gw;
	public UpCommand(GameWorld gw) {
		super("Up");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		gw.moveSpaceShipUp();
	}
}