package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class YesCommand extends Command {
	private GameWorld gw;
	public YesCommand() {
		super("Yes");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		//sets quitCommand's confirm to true
	}
}