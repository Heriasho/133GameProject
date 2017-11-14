package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class BredCommand extends Command {
	private GameWorld gw;
	public BredCommand(GameWorld gw) {
		super("Bred");
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		gw.bred();
	}
}
