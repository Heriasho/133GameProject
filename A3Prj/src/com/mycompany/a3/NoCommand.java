package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class NoCommand extends Command {
	private GameWorld gw;
	public NoCommand() {
		super("No");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		//sets quitCommand's confirm to false
	}
}