package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

//Increase the size of the spaceship door.
public class ExpandCommand extends Command{
	private GameWorld gw;
	public ExpandCommand() {
		super("Expand");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		gw.expand();
	}
}